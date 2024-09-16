package dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import entities.CurrentData;
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

    @Data
    @ToString
    @AllArgsConstructor
    @NoArgsConstructor
    public class CurrentDataDTO {
        private double temperature;  // In Celsius or Fahrenheit
        private String skyText;      // Description of sky condition (e.g., clear, cloudy)
        private int humidity;        // Humidity percentage
        private String windText;     // Description of wind condition

        public CurrentDataDTO(CurrentData currentData) {
            this.temperature = currentData.getTemperature();
            this.skyText = currentData.getSkyText();
            this.humidity = currentData.getHumidity();
            this.windText = currentData.getWindText();
        }
    }
}


