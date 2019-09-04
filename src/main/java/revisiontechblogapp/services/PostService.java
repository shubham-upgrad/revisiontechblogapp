package revisiontechblogapp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import revisiontechblogapp.models.Post;
import revisiontechblogapp.models.User;
import revisiontechblogapp.repository.PostRepository;
import revisiontechblogapp.repository.UserRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.Date;

@Service // The Businness Logic Containers
public class PostService {
    @Autowired
    PostRepository postRepository;
    public ArrayList<Post> getAllPosts() {
        return postRepository.getAllPosts();
    }


    public void createPost(Post post) {
        //3. Set date
        post.setDate(new Date());
        postRepository.createPost(post);
    }

    public ArrayList<Post> getUserPosts(User user) {
        return postRepository.getUserPosts(user);
    }
}
