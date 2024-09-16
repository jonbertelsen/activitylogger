package entities;

import dtos.WeatherInfoDTO;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WeatherInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String locationName;
    @OneToOne(cascade = CascadeType.PERSIST)
    private CurrentData currentData;

    @ToString.Exclude
    @OneToOne(mappedBy = "weatherInfo")
    private Activity activity;

    public WeatherInfo(WeatherInfoDTO weatherInfoDTO) {
        this.id = weatherInfoDTO.getId();
        this.locationName = weatherInfoDTO.getLocationName();
        this.currentData = new CurrentData(weatherInfoDTO.getCurrentData());
    }

}
