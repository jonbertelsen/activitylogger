package dtos;

import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.*;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class WeatherInfoDTO {
    @JsonSetter("LocationName")
    private String locationName;
    @JsonSetter("CurrentData")
    private CurrentData currentData;

    @Data
    @ToString
    @AllArgsConstructor
    @NoArgsConstructor
    private class CurrentData {
        private double temperature;  // In Celsius or Fahrenheit
        private String skyText;      // Description of sky condition (e.g., clear, cloudy)
        private int humidity;        // Humidity percentage
        private String windText;     // Description of wind condition
    }
}


