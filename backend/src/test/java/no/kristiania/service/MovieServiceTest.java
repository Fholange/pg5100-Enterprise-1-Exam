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

    @Test
    public void testCreateMovie(){

        String title = "Movie Title";
        long movieId = movieService.createMovie(title, "firstname lastname", "Interesting Movie about...");

        assertEquals(1, movieService.getAllMovies().size());
        assertEquals(title, movieService.getMovie(movieId).getTitle());

    }


}
