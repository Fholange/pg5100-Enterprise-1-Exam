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

    public List<Review> getAllReviewsSortedByAvgRating(long movieId){
        TypedQuery<Review> query = em.createQuery("select r from Review r where r.reviewId.movieId=?1 order by r.rating desc", Review.class);
        query.setParameter(1, movieId);

        return query.getResultList();

    }

    public List<Review> getAllReviewsSortedByDate(long movieId){
        TypedQuery<Review> query = em.createQuery("select r from Review r where r.reviewId.movieId=?1 order by r.dateCreated desc", Review.class);
        query.setParameter(1, movieId);

        return query.getResultList();

    }


}
