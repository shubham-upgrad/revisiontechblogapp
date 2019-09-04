package revisiontechblogapp.repository;

import org.springframework.stereotype.Repository;
import revisiontechblogapp.models.Post;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.persistence.TypedQuery;
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

}
