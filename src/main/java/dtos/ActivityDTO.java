package dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import entities.Activity;
import enums.ActivityType;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ActivityDTO {
    private Long id;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate exerciseDate;
    private ActivityType exerciseType;
    @JsonFormat(pattern = "HH:mm")
    private LocalTime timeOfDay;
    private double duration;  // In hours, for example
    private double distance;  // In kilometers or miles
    private String comment;
    private CityInfoDTO cityInfo;
    private WeatherInfoDTO weatherInfo;

    public ActivityDTO(Activity activity) {
        this.id = activity.getId();
        this.exerciseDate = activity.getExerciseDate();
        this.exerciseType = activity.getExerciseType();
        this.timeOfDay = activity.getTimeOfDay();
        this.duration = activity.getDuration();
        this.distance = activity.getDistance();
        this.comment = activity.getComment();
        this.cityInfo = new CityInfoDTO(activity.getCityInfo());
        this.weatherInfo = new WeatherInfoDTO(activity.getWeatherInfo());
    }
}
