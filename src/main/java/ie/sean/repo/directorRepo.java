package ie.sean.repo;

import ie.sean.entities.Director;
import ie.sean.entities.Movie;

import java.util.List;

public interface directorRepo {

    int count();
    List<Director> getAll();
    boolean exists(int id);
    boolean existsByName(String firstName, String lastName);
    int addDirector(Director newDirector);
    int deleteDirector(int directorId);
    List<Movie> findDirectorsMovies(int directorId);
    int changeStatus(int directorId, boolean status);
    int findDirectorsAverageTakings(int directorId);
    int inactiveDirectors();

}
