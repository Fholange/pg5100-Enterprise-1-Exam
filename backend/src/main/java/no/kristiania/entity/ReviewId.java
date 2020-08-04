package no.kristiania.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class ReviewId implements Serializable {

    @Column(name = "user_id")
    private String userId;

    @Column(name = "movie_id")
    private Long movieId;

    public ReviewId(){}

    public ReviewId(String userId, Long movieId) {
        this.userId = userId;
        this.movieId = movieId;
    }

    public String getUserId() {
        return userId;
    }

    public Long getMovieId() {
        return movieId;
    }
}
