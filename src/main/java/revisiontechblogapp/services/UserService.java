package revisiontechblogapp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import revisiontechblogapp.models.User;
import revisiontechblogapp.repository.UserRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    public User loginUser(User user){
        return userRepository.loginUser(user);
    }

    public User registerUser(User user) {
         return userRepository.registerUser(user);
    }
}
