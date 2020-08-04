package no.kristiania.frontend.controller;

import no.kristiania.entity.Movie;
import no.kristiania.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import java.util.List;

@Named
@RequestScoped
public class moviesController {

    @Autowired
    MovieService movieService;

    public List<Movie> getAllMovies(){
        return movieService.getAllMovies();
    }

}
