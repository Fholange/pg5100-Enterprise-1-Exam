package no.kristiania.service;

import no.kristiania.entity.Movie;
import no.kristiania.entity.Review;
import no.kristiania.entity.ReviewId;
import no.kristiania.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Service
@Transactional
public class MovieService {

    @Autowired
    private EntityManager em;

    public long createMovie(String title, String director, String plot){
        Movie movie = new Movie();
        movie.setTitle(title);
        movie.setDirector(director);
        movie.setPlot(plot);

        em.persist(movie);

        return movie.getId();

    }

    public List<Movie> getAllMovies(){
        TypedQuery<Movie> query = em.createQuery("select m from Movie m", Movie.class);
            return query.getResultList();
    }

    public Movie getMovie(long id){
        Movie movie = em.find(Movie.class, id);

        return movie;
    }

    public void deleteMovie(long id){
        Movie movie = em.find(Movie.class, id);
        if(movie == null){
            return;
        }
        em.remove(movie);

    }

    public Double computeAverageRating(long movieId){
        TypedQuery<Double> queryAvg = em.createQuery(
                "select avg(r.rating) from Review r where r.reviewId.movieId=?1", Double.class);
        queryAvg.setParameter(1, movieId);

        //round average ratings down to one decimal e.g  3.7
        Double result =  queryAvg.getSingleResult();
        BigDecimal round = new BigDecimal(result).setScale(1, RoundingMode.HALF_UP);
        result = round.doubleValue();

        return result;
    }

    public ReviewId createReview(long movieId, String userId, String reviewText, int rating){
        Movie movie = em.find(Movie.class, movieId);
        if(movie == null){
            throw new IllegalArgumentException("Movie id " + movieId + "does not exist!");
        }

        User user = em.find(User.class, userId);

        if(user == null){
            throw new IllegalArgumentException("User id " + userId + "does not exist!");
        }

        ReviewId reviewId = new ReviewId(userId, movieId);

        Review review = em.find(Review.class, reviewId);

        boolean persisted = !(review==null);
        if(!persisted){
            review = new Review();
        }

        review.setReviewText(reviewText);
        review.setReviewId(reviewId);
        review.setRating(rating);

        if(!persisted){
            em.persist(review);
        }

        //update movieAverage
        Double averageRating = computeAverageRating(movieId);
        movie.setAvgRating(averageRating);

        return reviewId;
    }

    public List<Movie> getMoviesSortedByRating(){
        TypedQuery<Movie> query = em.createQuery("select m from Movie m order by m.avgRating desc", Movie.class);

            return query.getResultList();
    }




}
