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
    @OneToOne(mappedBy = "weatherInfo")
    private Activity activity;

    public WeatherInfo(WeatherInfoDTO weatherInfo) {
        this.id = weatherInfo.getId();
        this.locationName = weatherInfo.getLocationName();
        this.currentData = new CurrentData(weatherInfo.getCurrentData());
    }

}
