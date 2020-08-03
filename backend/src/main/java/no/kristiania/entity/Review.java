
package no.kristiania.entity;

import org.hibernate.validator.constraints.Range;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;


@Entity
public class Review implements Serializable {

    @Id
    @ManyToOne
    private Movie movie;

    @Id
    @ManyToOne
    private User user;

    @Range(min = 1, max = 5)
    private int rating;

    private String reviewText;

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getReviewText() {
        return reviewText;
    }

    public void setReviewText(String reviewText) {
        this.reviewText = reviewText;
    }
}
