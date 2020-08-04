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
public class ReviewServiceTest extends ServiceTestBase{

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private MovieService movieService;

    @Autowired
    private UserService userService;



    @Test
    public void testCreateReviewAndRetrieveByMovie(){
        String movieTitle = "Movie Title";
        String username = "foo";
        String reviewText = "a great Movie with some flaws ";

        long movieId = movieService.createMovie(movieTitle, "firstname lastname", "Interesting Movie about...");
        userService.createUser(username, "123");

        reviewService.createReview(movieId, username, reviewText, 4);

        assertEquals(1, reviewService.getAllReviewsByMovie(movieId).size());
        assertEquals(reviewText, reviewService.getAllReviewsByMovie(movieId).get(0).getReviewText());


    }

    @Test
    public void testCreateReviewAndRetrieveByUser(){
        String movieTitle = "Movie Title";
        String username = "foo";
        String reviewText = "a great Movie with some flaws ";

        long movieId = movieService.createMovie(movieTitle, "firstname lastname", "Interesting Movie about...");
        userService.createUser(username, "123");

        reviewService.createReview(movieId, username, reviewText, 4);

        assertEquals(1, reviewService.getAllReviewsByUser(username).size());
        assertEquals(reviewText, reviewService.getAllReviewsByUser(username).get(0).getReviewText());
    }



}
