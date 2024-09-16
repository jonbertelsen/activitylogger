import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import daos.ActivityDAO;
import dtos.ActivityDTO;
import dtos.CityInfoDTO;
import dtos.WeatherInfoDTO;
import enums.ActivityType;
import jakarta.persistence.EntityManagerFactory;
import config.HibernateConfig;
import services.CityService;
import services.WeatherService;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {

        EntityManagerFactory emf = HibernateConfig.getEntityManagerFactory("activitylogger");

        WeatherInfoDTO weatherInfo = WeatherService.fetchWeatherDataByLocationName("Roskilde");
        CityInfoDTO cityInfo = CityService.getCityInfo("Roskilde");

        ActivityDTO activityDTO = ActivityDTO
                .builder()
                .exerciseType(ActivityType.RUNNING)
                .cityInfo(cityInfo)
                .distance(6.5)
                .exerciseDate(LocalDate.now())
                .duration(30.0)
                .timeOfDay(LocalTime.of(15, 45))
                .comment("Lovely downhill stroll")
                .cityInfo(cityInfo)
                .weatherInfo(weatherInfo)
                .build();

        ActivityDAO activityDAO = ActivityDAO.getInstance(emf);
        activityDTO = activityDAO.createActivity(activityDTO);

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        // Serialize LocalDateTime to JSON
        String json = objectMapper.writeValueAsString(activityDTO);
        System.out.println(json);
    }
}