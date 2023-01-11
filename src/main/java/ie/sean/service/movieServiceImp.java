package ie.sean.service;

import ie.sean.entities.Movie;
import ie.sean.entities.Result;
import ie.sean.repo.movieRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class movieServiceImp implements movieService{

    @Autowired
    movieRepo MovieRepo;

    @Override
    public List<Movie> findAll() {
        return MovieRepo.getAll();
    }

    @Override
    public boolean newMovie(Movie newMovie){
        if (MovieRepo.existsByNameYear(newMovie.getTitle(), newMovie.getYearReleased())){
            log.error("Could not add Movie because a movie called: " + newMovie.getTitle() +", Released in: "+ newMovie.getYearReleased() + ", already exists");
            return false;
        }

        if (MovieRepo.exists(newMovie.getMovieId())){
            log.error("Could not add Movie because a movie with id " + newMovie.getMovieId() + " already exists");
            return false;
        }

        return MovieRepo.addMovie(newMovie) == 1;
    }

    @Override
    public boolean delete(int movieId){
        if(MovieRepo.exists(movieId)){
            return MovieRepo.deleteMovie(movieId) == 1;
        }
        log.warn("Could not delete Movie with id " + movieId + " because it does not exist.");
        return false;
    }

    @Override
    public Optional<Result> findMovieAndDirector(int movieId){
        return MovieRepo.exists(movieId)?
                Optional.of(MovieRepo.findMovieWithDirector(movieId))
                : Optional.empty();
    }

    @Override
    public boolean updateTakings(int movieId, int takings){
        if (! MovieRepo.exists(movieId)){
            log.error("Could not edit Movie takings because a movie with id " + movieId + " does not exist");
            return false;
        }

        return MovieRepo.changeTakings(movieId, takings) == 1;
    }

    @Override
    public Optional<Result> findHighestTaking(){
        return Optional.of(MovieRepo.findHighestTaking());
    }

}
