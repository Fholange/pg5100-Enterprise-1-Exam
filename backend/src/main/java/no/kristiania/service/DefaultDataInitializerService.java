package no.kristiania.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.function.Supplier;

@Service
public class DefaultDataInitializerService {

    @Autowired
    private MovieService movieService;

    @PostConstruct
    public void initialize(){
        attempt(() -> movieService.createMovie("The Godfather", "Francis Ford Coppola", "The aging patriarch of an organized crime"));

    }


    //this i copied from the class main repo
    private  <T> T attempt(Supplier<T> lambda){
        try{
            return lambda.get();
        }catch (Exception e){
            return null;
        }
    }
}
