package dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import entities.WeatherInfo;
import lombok.*;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WeatherInfoDTO {
    private Long id;
    @JsonProperty("LocationName")
    private String locationName;
    @JsonProperty("CurrentData")
    private CurrentDataDTO currentData;

    public WeatherInfoDTO(WeatherInfo weatherInfo) {
        this.id = weatherInfo.getId();
        this.locationName = weatherInfo.getLocationName();
        this.currentData = new CurrentDataDTO(weatherInfo.getCurrentData());
    }
}


