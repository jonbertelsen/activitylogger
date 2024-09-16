package daos;

import config.HibernateConfig;
import dtos.ActivityDTO;
import enums.ActivityType;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import services.JsonService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ActivityDAOTest {

    private static EntityManagerFactory emf = HibernateConfig.getEntityManagerFactoryForTest();
    private static final ActivityDAO activityDAO = ActivityDAO.getInstance(emf);
    private static ActivityDTO a1;
    private static ActivityDTO a2;

    @BeforeEach
    void setUp() {
        try (EntityManager em = emf.createEntityManager()){
            em.getTransaction().begin();
                em.createQuery("DELETE FROM Activity").executeUpdate();
                em.createNativeQuery("ALTER SEQUENCE activity_id_seq RESTART WITH 1").executeUpdate();
                em.createQuery("DELETE FROM WeatherInfo").executeUpdate();
                em.createNativeQuery("ALTER SEQUENCE weatherinfo_id_seq RESTART WITH 1").executeUpdate();
                em.createQuery("DELETE FROM CurrentData").executeUpdate();
                em.createNativeQuery("ALTER SEQUENCE currentdata_id_seq RESTART WITH 1").executeUpdate();
                em.createQuery("DELETE FROM CityInfo").executeUpdate();
            em.getTransaction().commit();

            String a1Json = "{\"id\":null,\"exerciseDate\":\"2024-09-16\",\"exerciseType\":\"WALKING\",\"timeOfDay\":\"12:30\",\"duration\":35.0,\"distance\":5.5,\"comment\":\"Nice walk in the park\",\"cityInfo\":{\"id\":\"12337669-dbab-6b98-e053-d480220a5a3f\",\"primærtnavn\":\"Roskilde\",\"href\":\"https://api.dataforsyningen.dk/steder/12337669-dbab-6b98-e053-d480220a5a3f\",\"visueltcenter\":[12.08713962,55.63659446]},\"weatherInfo\":{\"id\":null,\"LocationName\":\"Roskilde\",\"CurrentData\":{\"temperature\":0.0,\"skyText\":\"Ukendt vejr\",\"humidity\":0,\"windText\":\" m/s\"}}}";
            a1 = JsonService.convertJsonToObject(a1Json, ActivityDTO.class);
            a1 = activityDAO.createActivity(a1);
            String a2Json = "{\"id\":null,\"exerciseDate\":\"2024-09-16\",\"exerciseType\":\"SNOWBOARDING\",\"timeOfDay\":\"14:30\",\"duration\":25.0,\"distance\":3.5,\"comment\":\"Downhill bonanza\",\"cityInfo\":{\"id\":\"12337669-cdcd-6b98-e053-d480220a5a3f\",\"primærtnavn\":\"Skagen\",\"href\":\"https://api.dataforsyningen.dk/steder/12337669-cdcd-6b98-e053-d480220a5a3f\",\"visueltcenter\":[10.57757857,57.72384166]},\"weatherInfo\":{\"id\":null,\"LocationName\":\"Skagen\",\"CurrentData\":{\"temperature\":0.0,\"skyText\":\"Ukendt vejr\",\"humidity\":0,\"windText\":\" m/s\"}}}";
            a2 = JsonService.convertJsonToObject(a2Json, ActivityDTO.class);
            a2 = activityDAO.createActivity(a2);
        } catch (Exception e) {
           e.printStackTrace();
        }
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void createActivity() {
        String a3Json = "{\"id\":null,\"exerciseDate\":\"2024-09-16\",\"exerciseType\":\"RUNNING\",\"timeOfDay\":\"12:30\",\"duration\":46.0,\"distance\":7.5,\"comment\":\"Lovely downhill stroll in the rain\",\"cityInfo\":{\"id\":\"12337669-dbab-6b98-e053-d480220a5a3f\",\"primærtnavn\":\"Roskilde\",\"href\":\"https://api.dataforsyningen.dk/steder/12337669-dbab-6b98-e053-d480220a5a3f\",\"visueltcenter\":[12.08713962,55.63659446]},\"weatherInfo\":{\"id\":null,\"LocationName\":\"Roskilde\",\"CurrentData\":{\"temperature\":0.0,\"skyText\":\"Ukendt vejr\",\"humidity\":0,\"windText\":\" m/s\"}}}";
        ActivityDTO a3 = JsonService.convertJsonToObject(a3Json, ActivityDTO.class);
        a3 = activityDAO.createActivity(a3);
        assertNotNull(a3.getId());
        assertEquals("12337669-dbab-6b98-e053-d480220a5a3f", a3.getCityInfo().getId());
    }

    @Test
    void updateActivity() {
        a1.setExerciseType(ActivityType.RUNNING);
        a1.setDistance(7.5);
        a1.setComment("Lovely downhill stroll in the rain");
        a1 = activityDAO.updateActivity(a1);
        ActivityDTO updatedActivity = activityDAO.getActivityById(a1.getId());
        assertEquals(ActivityType.RUNNING, updatedActivity.getExerciseType());
        assertEquals(7.5, updatedActivity.getDistance());
        assertEquals("Lovely downhill stroll in the rain", updatedActivity.getComment());
    }

    @Test
    void getAllActivities() {
        List<ActivityDTO> activityDTOS = activityDAO.getAllActivities();
        assertEquals(2, activityDTOS.size());
    }
}