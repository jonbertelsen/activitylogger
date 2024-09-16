package entities;

import dtos.CurrentDataDTO;
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

    public CurrentData(CurrentDataDTO currentDataDTO) {
        this.temperature = currentDataDTO.getTemperature();
        this.skyText = currentDataDTO.getSkyText();
        this.humidity = currentDataDTO.getHumidity();
        this.windText = currentDataDTO.getWindText();
    }
}
