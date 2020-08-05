package no.kristiania.frontend.controller;

import no.kristiania.entity.Movie;
import no.kristiania.entity.Review;
import no.kristiania.service.MovieService;
import no.kristiania.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named
@SessionScoped
public class moviesController implements Serializable {

    @Autowired
    MovieService movieService;

    @Autowired
    ReviewService reviewService;

   private boolean hasReviews = false;

   private List<Review> movieReviews = new ArrayList<>();

    private Movie selectedMovie;

    private String reviewText;

    private String rating;


    public List<Movie> getAllMoviesSortedByRating(){
        return movieService.getMoviesSortedByRating();
    }

    public String selectMovie(Movie movie){

        selectedMovie = movie;
        if(selectedMovie!= null){
            setMovieReviewsSortedByRating();
            hasReviews = !movieReviews.isEmpty();
        }


        return "/moviedetails.jsf?faces-redirect=true";

    }


    public Movie getSelectedMovie() {
        return selectedMovie;
    }

    public void setMovieReviewsSortedByRating(){

        if(selectedMovie == null){
            return;
        }
        movieReviews = reviewService.getAllReviewsSortedByAvgRating(selectedMovie.getId());
    }

    public void setMovieReviewsSortedByDate(){
        if(selectedMovie == null){
            return;
        }
        movieReviews = reviewService.getAllReviewsSortedByDate(selectedMovie.getId());
    }

    public String addReview(){
        if(rating == null){
            return "/moviedetails.jsf?faces-redirect=true&error=true";
        }else {
            int ratingValue = Integer.parseInt(rating);

            movieService.createReview(selectedMovie.getId(), getUserName(), reviewText, (ratingValue));
            setMovieReviewsSortedByRating();

            setReviewText(null);
            return "/moviedetails.jsf?faces-redirect=true";

        }

    }

    public String getUserName() {
        return ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
    }


    public boolean isHasReviews() {
        return hasReviews;
    }

    public String getReviewText() {
        return reviewText;
    }

    public void setReviewText(String reviewText) {
        this.reviewText = reviewText;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public List<Review> getMovieReviews() {
        return movieReviews;
    }
}
