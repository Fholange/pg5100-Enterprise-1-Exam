package no.kristiania.entity;

import org.hibernate.validator.constraints.Range;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.persistence.*;


@Entity
public class Review implements Serializable {

    @EmbeddedId
    private ReviewId reviewId;

    @Range(min = 1, max = 5)
    private int rating;

    @Column(columnDefinition = "text")
    private String reviewText;

    private Date dateCreated;

    @PrePersist
    protected void onCreate() {
        dateCreated = new Date();
    }

    public ReviewId getReviewId() {
        return reviewId;
    }

    public void setReviewId(ReviewId reviewId) {
        this.reviewId = reviewId;
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

    public Date getDateCreated() {
        return dateCreated;
    }
    public String getSimpleDateFormat(){
        SimpleDateFormat sd = new SimpleDateFormat("MM/dd/yyyy hh:mm");
        String date = sd.format(getDateCreated());
        return date;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }
}
