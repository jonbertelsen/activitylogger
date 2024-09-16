package dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.*;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class WeatherInfoDTO {
    @JsonProperty("LocationName")
    private String locationName;
    @JsonProperty("CurrentData")
    private CurrentDataDTO currentData;
}


