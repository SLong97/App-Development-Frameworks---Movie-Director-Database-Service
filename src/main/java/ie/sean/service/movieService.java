package ie.sean.service;

import ie.sean.entities.Movie;
import ie.sean.entities.Result;

import java.util.List;
import java.util.Optional;

public interface movieService {

    List<Movie> findAll();
    boolean newMovie(Movie newMovie);
    boolean delete(int movieId);
    Optional<Result> findMovieAndDirector(int movieId);
    boolean updateTakings(int movieId, int takings);
    Optional<Result> findHighestTaking();

}
