package revisiontechblogapp.repository;

import org.springframework.stereotype.Repository;
import revisiontechblogapp.models.Post;
import revisiontechblogapp.models.User;

import javax.persistence.*;
import java.util.ArrayList;

@Repository
public class PostRepository { // The Database Communicators
    @PersistenceUnit(name="techblog")
    EntityManagerFactory entityManagerFactory;
    public ArrayList<Post> getAllPosts() {
        EntityManager em=entityManagerFactory.createEntityManager();
        TypedQuery<Post> tq = em.createQuery("SELECT p FROM Post p", Post.class);
        ArrayList<Post> al=(ArrayList<Post>) tq.getResultList();

        // Adding it to the model(the one that sends data to views)
        return al;
    }

    public void createPost(Post post) {
        EntityManager em=entityManagerFactory.createEntityManager();
        EntityTransaction et=em.getTransaction();
        try{
            et.begin();
            em.persist(post); // user object is an entity instance(instance of User)
            // persist method is simply ,making a transient/new object persistent
            et.commit();
        }catch(Exception e){
            e.printStackTrace();
            et.rollback();
        }
    }

    public ArrayList<Post> getUserPosts(User loggeduser) {
        EntityManager em=entityManagerFactory.createEntityManager();
        TypedQuery<Post> tq = em.createQuery("SELECT p FROM Post p where p.user=:u", Post.class);
        tq.setParameter("u",loggeduser);
        ArrayList<Post> al=(ArrayList<Post>) tq.getResultList();

        // Adding it to the model(the one that sends data to views)
        return al;
    }
}
