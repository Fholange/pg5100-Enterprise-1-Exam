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
    public void testCreateReview(){
        String movieTitle = "Movie Title";
        String username = "foo";
        String reviewText = "a great Movie with some flaws ";

        long movieId = movieService.createMovie(movieTitle, "firstname lastname", "Interesting Movie about...");
        userService.createUser(username, "123");

        movieService.createReview(movieId, username, reviewText, 4);

        assertEquals(1, reviewService.getAllReviewsSortedByAvgRating(movieId).size());
        assertEquals(reviewText, reviewService.getAllReviewsSortedByAvgRating(movieId).get(0).getReviewText());


    }


    @Test
    public void testComputeAvg(){
        String movieTitle = "Movie Title";
        String userA = "fooA";
        String userB = "fooB";
        String userC = "fooC";
        String reviewText = "a great Movie with some flaws ";

        long movieId = movieService.createMovie(movieTitle, "firstname lastname", "Interesting Movie about...");
        userService.createUser(userA, "123");
        userService.createUser(userB, "123");
        userService.createUser(userC, "123");


        movieService.createReview(movieId, userA, reviewText, 4);
        movieService.createReview(movieId, userB, reviewText, 3);
        movieService.createReview(movieId, userC, reviewText, 2);

        //(4+3+2)/3 = 3    expected: 3

        assertEquals((Double) 3.0 , movieService.computeAverageRating(movieId));

    }


}
