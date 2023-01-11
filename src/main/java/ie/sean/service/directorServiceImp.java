package ie.sean.service;

import ie.sean.entities.Director;
import ie.sean.entities.Movie;
import ie.sean.repo.directorRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class directorServiceImp implements directorService{

    @Autowired
    directorRepo DirectorRepo;

    @Override
    public int count(){
        return DirectorRepo.count();
    }

    @Override
    public List<Director> findAll() {
        return DirectorRepo.getAll();
    }

    @Override
    public boolean newDirector(Director newDirector){
        if (DirectorRepo.existsByName(newDirector.getFirstName(), newDirector.getLastName())){
            log.error("Could not add Director because a director called " + newDirector.getFirstName() +" "+ newDirector.getLastName() + " already exists");
            return false;
        }

        if (DirectorRepo.exists(newDirector.getDirectorId())){
            log.error("Could not add Director because a director with id " + newDirector.getDirectorId() + " already exists");
            return false;
        }

        return DirectorRepo.addDirector(newDirector) == 1;
    }

    @Override
    public boolean delete(int directorId){
        if(DirectorRepo.exists(directorId)){
            return DirectorRepo.deleteDirector(directorId) == 1;
        }
        log.warn("Could not delete Director with id " + directorId + " because it does not exist.");
        return false;
    }

    @Override
    public Optional<List<Movie>> getDirectorsMovies(int directorId){
        if(DirectorRepo.exists(directorId)){
            List<Movie> movies = DirectorRepo.findDirectorsMovies(directorId);
            return movies.isEmpty()? Optional.empty(): Optional.of(movies);
        }
        return Optional.empty();
    }

    @Override
    public boolean updateActive(int directorId, boolean status){
        if (! DirectorRepo.exists(directorId)){
            log.error("Could not edit Director active status because a director with id " + directorId + " does not exist");
            return false;
        }

        return DirectorRepo.changeStatus(directorId, status) == 1;
    }

    @Override
    public int averageTakings(int directorId){
        if (! DirectorRepo.exists(directorId)){
            log.error("Could not find Directors average takings because a director with id " + directorId + " does not exist");
            return 0;
        }

        return DirectorRepo.findDirectorsAverageTakings(directorId);

    }

    @Override
    public int inactiveCount(){
        if (DirectorRepo.count() == 0){
            log.error("Could not find number of inactive Directors as no directors exist");
            return 0;
        }

        return DirectorRepo.inactiveDirectors();

    }

}
