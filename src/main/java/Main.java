
import daos.ActivityDAO;
import dtos.ActivityDTO;
import enums.ActivityType;
import jakarta.persistence.EntityManagerFactory;
import config.HibernateConfig;
import services.ActivityService;
import services.JsonService;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {

        EntityManagerFactory emf = HibernateConfig.getEntityManagerFactory("activitylogger");

        // Fetch data from external APIs
        ActivityDTO activityDTO = ActivityService.createActivity(emf, "Roskilde", 5.5, 35.0, LocalTime.of(12, 30),
                LocalDate.now(), "Nice walk in the park", ActivityType.WALKING);

        System.out.println("Activity persisted to database:");
        System.out.println(JsonService.convertObjectToJson(activityDTO));

        // Update activity
        String json = "{\"id\":1,\"exerciseDate\":\"2024-09-20\",\"exerciseType\":\"RUNNING\",\"timeOfDay\":\"12:30\",\"duration\":46.0,\"distance\":7.5,\"comment\":\"Lovely downhill stroll in the rain\",\"cityInfo\":{\"id\":\"12337669-dbab-6b98-e053-d480220a5a3f\",\"primærtnavn\":\"Roskilde By\",\"href\":\"https://en.wikipedia.org/wiki/Roskilde\",\"visueltcenter\":[57.6415,11.0805]},\"weatherInfo\":{\"id\":1,\"LocationName\":\"Roskilde\",\"CurrentData\":{\"temperature\":5.0,\"skyText\":\"Rainy as hell\",\"humidity\":0,\"windText\":\" m/s\"}}}\n";
        activityDTO = JsonService.convertJsonToObject(json, ActivityDTO.class);
        activityDTO = ActivityService.updateActivity(emf, activityDTO);

        System.out.println("Activity updated in database:");
        System.out.println(JsonService.convertObjectToJson(activityDTO));

        ActivityDTO activityDTO2 = ActivityService.createActivity(emf, "Skagen", 3.5, 25.0, LocalTime.of(14, 30),
                LocalDate.now(), "Downhill bonanza", ActivityType.SNOWBOARDING);

        System.out.println("Activity persisted to database:");
        System.out.println(JsonService.convertObjectToJson(activityDTO2));

        System.out.println("All activities in database:");
        List<ActivityDTO> activities = ActivityDAO.getAllActivities();
        activities.forEach(activity -> System.out.println(JsonService.convertObjectToJson(activity)));
    }

}