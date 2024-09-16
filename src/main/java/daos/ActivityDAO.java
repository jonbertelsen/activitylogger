package daos;

import dtos.ActivityDTO;
import entities.Activity;
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
            em.persist(activity);
            em.getTransaction().commit();
        }
        return new ActivityDTO(activity);
    }


}
