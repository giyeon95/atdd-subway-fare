package nextstep.subway.path.documentation;

import com.google.common.collect.Lists;
import io.restassured.specification.RequestSpecification;
import nextstep.subway.Documentation;
import nextstep.subway.auth.dto.TokenResponse;
import nextstep.subway.line.domain.PathType;
import nextstep.subway.member.dto.MemberRequest;
import nextstep.subway.path.application.PathService;
import nextstep.subway.path.dto.PathResponse;
import nextstep.subway.station.dto.StationResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.restdocs.payload.ResponseFieldsSnippet;
import org.springframework.restdocs.request.RequestParametersSnippet;
import org.springframework.restdocs.restassured3.RestDocumentationFilter;

import java.time.LocalDateTime;

import static nextstep.subway.member.MemberSteps.유효한_토큰_생성됨;
import static nextstep.subway.path.steps.PathDocumentationSteps.*;
import static nextstep.subway.path.steps.PathSteps.두_역의_최단_거리_경로_조회를_요청;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

public class PathDocumentation extends Documentation {

    @MockBean
    PathService pathService;
    
    @BeforeEach
    void setUp(){
        // given
        PathResponse pathResponse = new PathResponse(
                Lists.newArrayList(
                        new StationResponse(1L, "강남역", LocalDateTime.now(), LocalDateTime.now())
                        , new StationResponse(2L, "역삼역", LocalDateTime.now(), LocalDateTime.now())
                ), 10, 10, 1000
        );

        when(pathService.findPath(any(), anyLong(), anyLong(), any(PathType.class))).thenReturn(pathResponse);
    }

    @Test
    @DisplayName("경로조회 문서화")
    void path() {

        RequestParametersSnippet requestParametersSnippet = 경로조회_요청_설정();
        ResponseFieldsSnippet responseFieldsSnippet = 경로조회_응답_설정();

        RestDocumentationFilter documentationFilter = 문서화_필터_설정("path", requestParametersSnippet, responseFieldsSnippet);
        RequestSpecification requestSpecification = 문서화_요청_설정(spec, documentationFilter);
        TokenResponse tokenResponse = 유효한_토큰_생성됨(new MemberRequest("email@email.com", "1234", 15));

        두_역의_최단_거리_경로_조회를_요청(requestSpecification, tokenResponse,1L, 2L);
    }


}
