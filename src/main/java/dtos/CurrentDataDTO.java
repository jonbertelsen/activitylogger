package dtos;

import entities.CurrentData;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

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