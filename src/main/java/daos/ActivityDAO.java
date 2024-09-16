package daos;

import dtos.ActivityDTO;
import dtos.WeatherInfoDTO;
import entities.Activity;
import entities.CityInfo;
import entities.CurrentData;
import entities.WeatherInfo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

public class ActivityDAO {

    private static ActivityDAO instance;
    private static EntityManagerFactory emf;

    private ActivityDAO(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public static ActivityDAO getInstance(EntityManagerFactory emf) {
        if (instance == null) {
            instance = new ActivityDAO(emf);
        }
        return instance;
    }

    public ActivityDTO createActivity(ActivityDTO activityDTO) {
        Activity activity = new Activity(activityDTO);
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
                CityInfo cityInfo = em.find(CityInfo.class, activityDTO.getCityInfo().getId());
                if (cityInfo != null) {
                    activity.setCityInfo(cityInfo);
                } else {
                    em.persist(activity.getCityInfo());
                }
                em.persist(activity);
            em.getTransaction().commit();
        }
        return new ActivityDTO(activity);
    }

    public ActivityDTO updateActivity(ActivityDTO activityDTO) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();

            // Find existing Activity by ID, throw an exception if not found
            Activity activity = em.find(Activity.class, activityDTO.getId());
            if (activity == null) {
                throw new IllegalArgumentException("Activity with ID " + activityDTO.getId() + " not found.");
            }

            // Update activity with new DTO values
            activity.setExerciseDate(activityDTO.getExerciseDate());
            activity.setExerciseType(activityDTO.getExerciseType());
            activity.setTimeOfDay(activityDTO.getTimeOfDay());
            activity.setDuration(activityDTO.getDuration());
            activity.setDistance(activityDTO.getDistance());
            activity.setComment(activityDTO.getComment());

            // Handle CityInfo - check if it exists and update it if necessary
            CityInfo cityInfo = em.find(CityInfo.class, activityDTO.getCityInfo().getId());
            if (cityInfo != null) {
                activity.setCityInfo(new CityInfo(activityDTO.getCityInfo()));
            } else {
                throw new IllegalArgumentException("CityInfo with ID " + activityDTO.getCityInfo().getId() + " not found.");
            }

            // Handle WeatherInfo - check if it exists and update it if necessary
            WeatherInfo weatherInfo = em.find(WeatherInfo.class, activityDTO.getWeatherInfo().getId());
            if (weatherInfo != null) {
                activity.setWeatherInfo(new WeatherInfo(activityDTO.getWeatherInfo()));
            } else {
                throw new IllegalArgumentException("WeatherInfo with ID " + activityDTO.getWeatherInfo().getId() + " not found.");
            }

            // Merge the updated activity
            em.merge(activity);

            em.getTransaction().commit();

            // Return the updated ActivityDTO based on the updated entity
            return new ActivityDTO(activity);

        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw e; // Optionally handle the exception better
        } finally {
            em.close();
        }
    }


}
