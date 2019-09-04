package revisiontechblogapp.repository;

import org.springframework.stereotype.Repository;
import revisiontechblogapp.models.User;

import javax.persistence.*;

@Repository
public class UserRepository {
    @PersistenceUnit(name="techblog")
    EntityManagerFactory emf;
    public User loginUser(User user){
        try {
            EntityManager em = emf.createEntityManager();
            TypedQuery<User> tq = em.createQuery("SELECT u from User u where" +
                    " u.username=:tobecheckedusername " +
                    "and u.password=:tobecheckedpassword", User.class);
            tq.setParameter("tobecheckedusername",user.getUsername());
            tq.setParameter("tobecheckedpassword",user.getPassword());
            User existing = tq.getSingleResult();
            return existing;
        }catch(NoResultException e){
            return null;
        }

    }

    public User registerUser(User user) {
        EntityManager em=emf.createEntityManager();
        EntityTransaction et=em.getTransaction();
        try{
            et.begin();
            em.persist(user); // user object is an entity instance(instance of User)
            // persist method is simply ,making a transient/new object persistent
            et.commit();
            return user;
        }catch(Exception e){
            e.printStackTrace();
            et.rollback();
            return null;

        }

    }
}
