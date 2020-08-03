package no.kristiania.service;

import no.kristiania.entity.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
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


}
