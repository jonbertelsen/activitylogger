package entities;

import dtos.WeatherInfoDTO;
import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CurrentData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private double temperature;
    private String skyText;
    private int humidity;
    private String windText;
    @OneToOne(mappedBy = "currentData")
    @ToString.Exclude
    private WeatherInfo weatherInfo;

    public CurrentData(WeatherInfoDTO.CurrentDataDTO currentData) {
        this.temperature = currentData.getTemperature();
        this.skyText = currentData.getSkyText();
        this.humidity = currentData.getHumidity();
        this.windText = currentData.getWindText();
    }
}
