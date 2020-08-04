package no.kristiania.service;

import no.kristiania.StubApplication;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static org.junit.jupiter.api.Assertions.assertEquals;
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = StubApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class MovieServiceTest extends ServiceTestBase{

    @Autowired
    private MovieService movieService;

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private UserService userService;

    @Test
    public void testCreateMovie(){

        String title = "Movie Title";
        long movieId = movieService.createMovie(title, "firstname lastname", "Interesting Movie about...");

        assertEquals(1, movieService.getAllMovies().size());
        assertEquals(title, movieService.getMovie(movieId).getTitle());

    }

    @Test
    public void testDeleteMovie(){
        String title = "Movie Title";
        long movieId = movieService.createMovie(title, "firstname lastname", "Interesting Movie about...");
        assertEquals(1, movieService.getAllMovies().size());
        movieService.deleteMovie(movieId);
        assertEquals(0, movieService.getAllMovies().size());
    }

    @Test
    public void testSortByRating(){
        String movieTitleA = "Movie Title A";
        String movieTitleB = "Movie Title B";
        String userA = "fooA";
        String reviewText = "a great Movie with some flaws ";

        userService.createUser(userA, "123");

        long movieIdA = movieService.createMovie(movieTitleA, "Name", "Movie about...");
        long movieIdB = movieService.createMovie(movieTitleB, "Name", "Movie about...");

        movieService.createReview(movieIdA, userA, reviewText, 1);
        movieService.createReview(movieIdB, userA, reviewText, 4);

        assertEquals(movieTitleB, movieService.getMoviesSortedByRating().get(0).getTitle());



    }




}
