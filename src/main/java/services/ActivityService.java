package services;

import daos.ActivityDAO;
import dtos.ActivityDTO;
import dtos.CityInfoDTO;
import dtos.WeatherInfoDTO;
import enums.ActivityType;
import jakarta.persistence.EntityManagerFactory;
import org.hibernate.jpa.AvailableHints;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;

public class ActivityService {

    public static ActivityDTO createActivity(EntityManagerFactory emf, String cityName,
                                 double distance, double duration, LocalTime timeOfDay,
                                  LocalDate exerciseDate, String comment,
                                 ActivityType activityType) throws IOException, InterruptedException {

        ActivityDAO activityDAO = ActivityDAO.getInstance(emf);
        WeatherInfoDTO weatherInfo = WeatherService.fetchWeatherDataByLocationName(cityName);
        CityInfoDTO cityInfo = CityService.getCityInfo(cityName);

        // Normally you would get this data from a form on a website or similar
        ActivityDTO activityDTO = ActivityDTO
                .builder()
                .exerciseType(activityType)
                .cityInfo(cityInfo)
                .distance(distance)
                .exerciseDate(exerciseDate)
                .duration(duration)
                .timeOfDay(timeOfDay)
                .comment(comment)
                .cityInfo(cityInfo)
                .weatherInfo(weatherInfo)
                .build();

        // Persist data to database
        return activityDTO = activityDAO.createActivity(activityDTO);
    }

    public static ActivityDTO updateActivity(EntityManagerFactory emf, ActivityDTO activityDTO) {
        ActivityDAO activityDAO = ActivityDAO.getInstance(emf);

        // Potentially we could do some validation here before updating the activity
        return activityDAO.updateActivity(activityDTO);
    }
}
