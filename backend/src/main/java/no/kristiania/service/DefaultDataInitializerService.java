package no.kristiania.service;

import no.kristiania.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.swing.text.html.parser.Entity;
import java.util.function.Supplier;

@Service
public class DefaultDataInitializerService {

    @Autowired
    private UserService userService;

    @Autowired
    private MovieService movieService;

    @Autowired
    private EntityManager em;

    @PostConstruct
    public void initialize(){

        userService.createUser("test", "123");

        Long movieAid = attempt(() -> movieService.createMovie("The Godfather", "Francis Ford Coppola", "The aging patriarch of an organized crime"));
        Long movieBid = attempt(() -> movieService.createMovie("The Shawshank Redemption", "Frank Darabont", "Two imprisoned men bond over a number of years, finding solace and eventual redemption through acts of common decency."));



    }


    //this i copied from the class main repo
    private  <T> T attempt(Supplier<T> lambda){
        try{
            return lambda.get();
        }catch (Exception e){
            return null;
        }
    }
}
