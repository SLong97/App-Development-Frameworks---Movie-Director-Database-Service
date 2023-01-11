import ie.sean.entities.Config;
import ie.sean.entities.Movie;
import ie.sean.entities.Result;
import ie.sean.repo.movieRepo;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class) // Adds SpringExtension i.e. Spring stuff to Junit5
@ContextConfiguration(classes = {Config.class}) // To specify the context to be loaded
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestMovieRepoFunctionality {

    @Autowired
    movieRepo MovieRepo;

    @Test
    @Order(1)
    public void testFindHighestTakings(){

        Assertions.assertAll(
                () -> Assertions.assertInstanceOf(Result.class, MovieRepo.findHighestTaking()),
                () -> Assertions.assertEquals("Apollo 13", MovieRepo.findHighestTaking().title())
        );

    }

}
