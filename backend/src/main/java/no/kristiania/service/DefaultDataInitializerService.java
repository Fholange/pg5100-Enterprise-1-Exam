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

        userService.createUser("foo", "123");

         attempt(() -> movieService.createMovie(
                 "The Godfather",
                 "Francis Ford Coppola",
                 "The aging patriarch of an organized crime"
         ));

         attempt(() -> movieService.createMovie("The Shawshank Redemption",
                 "Frank Darabont",
                 "Two imprisoned men bond over a number of years, finding solace and eventual redemption through acts of common decency."
         ));

        attempt(() -> movieService.createMovie("The Dark Knight",
                "Christopher Nolan",
                "When the menace known as the Joker wreaks havoc and chaos on the people of Gotham, " +
                        "Batman must accept one of the greatest psychological and physical tests of his ability to fight injustice."
        ));

        attempt(() -> movieService.createMovie("Schindler's List",
                "Steven Spielberg",
                "In German-occupied Poland during World War II, industrialist Oskar Schindler gradually becomes concerned for his Jewish workforce after witnessing their persecution by the Nazis."
        ));

        attempt(() -> movieService.createMovie("The Lord of the Rings: The Return of the King",
                "Peter Jackson",
                "Gandalf and Aragorn lead the World of Men against Sauron's army to draw his gaze from Frodo and Sam as they approach Mount Doom with the One Ring."
        ));

        attempt(() -> movieService.createMovie("Pulp Fiction",
                "Quentin Tarantino",
                "The lives of two mob hitmen, a boxer, a gangster and his wife, and a pair of diner bandits intertwine in four tales of violence and redemption."
        ));
        attempt(() -> movieService.createMovie("The Room",
                "Tommy Wiseau",
                "Johnny is a successful bank executive who lives quietly in a San Francisco townhouse with his future wife Lisa. One day she unscrupulously seduces his best friend Mark. Nothing will ever be the same again."
        ));



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
