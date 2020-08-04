//this code was copied from the main repo of this class
//https://github.com/arcuri82/testing_security_development_enterprise_systems/blob/master/intro/exercise-solutions/quiz-game/part-11/backend/src/main/java/org/tsdes/intro/exercises/quizgame/backend/service/UserService.java
package no.kristiania.service;
import no.kristiania.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import java.util.Collections;


@Service
@Transactional
public class UserService {

    @Autowired
    private EntityManager em;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public boolean createUser(String username, String password){
        String hashedPassword = passwordEncoder.encode(password);
        if(em.find(User.class, username) != null){
            return false;
        }

        User user = new User();
        user.setUsername(username);
        user.setPassword(hashedPassword);
        user.setRoles(Collections.singleton("USER"));
        user.setEnabled(true);

        em.persist(user);
        return true;
    }

    public User getUserByUsername(String userName){
        User user = em.find(User.class, userName);
        if(user == null){
            throw new IllegalArgumentException("No existing user: " + userName);
        }
        return user;
    }

}
