package ie.sean.repo;

import ie.sean.entities.Movie;
import ie.sean.entities.Result;

import java.util.List;

public interface movieRepo {

    List<Movie> getAll();
    boolean exists(int id);
    boolean existsByNameYear(String title, int yearReleased);
    int addMovie(Movie newMovie);
    int deleteMovie(int movieId);
    Result findMovieWithDirector(int movieId);
    int changeTakings(int movieId, int takings);
    Result findHighestTaking();

}
