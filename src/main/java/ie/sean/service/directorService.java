package ie.sean.service;

import ie.sean.entities.Director;
import ie.sean.entities.Movie;

import java.util.List;
import java.util.Optional;

public interface directorService {

    int count();
    List<Director> findAll();
    boolean newDirector(Director newDirector);
    boolean delete(int directorId);
    Optional<List<Movie>> getDirectorsMovies(int directorId);
    boolean updateActive(int directorId, boolean status);
    int averageTakings(int directorId);
    int inactiveCount();


}
