package entities;

import dtos.ActivityDTO;
import enums.ActivityType;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Activity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private LocalDate exerciseDate;
    @Enumerated(EnumType.STRING)
    private ActivityType exerciseType;
    private LocalTime timeOfDay;
    private double duration;  // In hours, for example
    private double distance;  // In kilometers or miles
    private String comment;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "weather_info_id", unique = true)
    private WeatherInfo weatherInfo;
    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "city_info_id", unique = true)
    private CityInfo cityInfo;

    public Activity(ActivityDTO activityDTO) {
        this.id = activityDTO.getId();
        this.exerciseDate = activityDTO.getExerciseDate();
        this.exerciseType = activityDTO.getExerciseType();
        this.timeOfDay = activityDTO.getTimeOfDay();
        this.duration = activityDTO.getDuration();
        this.distance = activityDTO.getDistance();
        this.comment = activityDTO.getComment();
        this.weatherInfo = new WeatherInfo(activityDTO.getWeatherInfo());
        this.cityInfo =  new CityInfo(activityDTO.getCityInfo());
    }
}
