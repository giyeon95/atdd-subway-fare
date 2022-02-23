package nextstep.subway.unit;

import lombok.val;
import nextstep.subway.domain.Fare;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static org.assertj.core.api.Assertions.assertThat;

public class FareTest {

    @Test
    void 최소범위테스트() {
        // when
        val fare1 = Fare.calculateAmount(9);
        val fare2 = Fare.calculateAmount(10);

        assertThat(fare1).isEqualTo(1250);
        assertThat(fare2).isEqualTo(1250);
    }

    @Test
    void 중간범위테스트() {
        // when
        val fare1 = Fare.calculateAmount(11);
        val fare2 = Fare.calculateAmount(16);
        val fare3 = Fare.calculateAmount(50);

        assertThat(fare1).isEqualTo(1350);
        assertThat(fare2).isEqualTo(1450);
        assertThat(fare3).isEqualTo(2050);
    }

    @Test
    void 최대범위테스트() {
        // when
        val fare1 = Fare.calculateAmount(51);
        val fare2 = Fare.calculateAmount(57);
        val fare3 = Fare.calculateAmount(60);

        assertThat(fare1).isEqualTo(2150);
        assertThat(fare2).isEqualTo(2150);
        assertThat(fare3).isEqualTo(2250);
    }
}