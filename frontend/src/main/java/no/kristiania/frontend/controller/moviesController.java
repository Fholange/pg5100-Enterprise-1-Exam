package no.kristiania.frontend.controller;

import no.kristiania.entity.Movie;
import no.kristiania.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.util.List;
import java.util.Map;

@Named
@ApplicationScoped
public class moviesController {

    @Autowired
    MovieService movieService;

    private Movie selectedMovie;

    public List<Movie> getAllMoviesSortedByRating(){
        return movieService.getMoviesSortedByRating();
    }

    public String selectMovie(Movie movie){

        selectedMovie = movie;
        return "/moviedetails.jsf?faces-redirect=true";

    }


    public Movie getSelectedMovie() {
        return selectedMovie;
    }
}
