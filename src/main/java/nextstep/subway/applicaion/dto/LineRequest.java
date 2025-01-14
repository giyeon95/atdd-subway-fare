package nextstep.subway.applicaion.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
public class LineRequest {
    private String name;

    private String color;

    private Long upStationId;
    private Long downStationId;
    private int distance;
    private int duration;
    private int surcharge;


    public LineRequest(String name, String color, Long upStationId, Long downStationId, int distance, int duration, int surcharge) {
        this.name = name;
        this.color = color;
        this.upStationId = upStationId;
        this.downStationId = downStationId;
        this.distance = distance;
        this.duration = duration;
        this.surcharge = surcharge;
    }
}
