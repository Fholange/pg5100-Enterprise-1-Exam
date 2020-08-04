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
import java.util.List;

@Service
@Transactional
public class ReviewService {

    @Autowired
    private EntityManager em;

    public List<Review> getAllReviewsByMovie(long movieId){
        TypedQuery<Review> query = em.createQuery("select r from Review r where r.reviewId.movieId=?1", Review.class);
        query.setParameter(1, movieId);

        return query.getResultList();

    }

    public List<Review> getAllReviewsByUser(String userId){
        TypedQuery<Review> query = em.createQuery("select r from Review r where r.reviewId.userId=?1", Review.class);
        query.setParameter(1, userId);

        return query.getResultList();

    }

    public void createReview(long movieId, String userId, String reviewText, int rating){
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

        if(review == null){
            review = new Review();
        }

        review.setReviewText(reviewText);
        review.setReviewId(reviewId);
        review.setRating(rating);
        //remember to test this
        em.persist(review);

    }

}
