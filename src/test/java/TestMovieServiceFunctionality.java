import ie.sean.entities.Config;
import ie.sean.entities.Movie;
import ie.sean.entities.Result;
import ie.sean.service.movieService;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

@ExtendWith(SpringExtension.class) // Adds SpringExtension i.e. Spring stuff to Junit5
@ContextConfiguration(classes = {Config.class}) // To specify the context to be loaded
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestMovieServiceFunctionality {

    @Autowired
    movieService MovieService;

    @Test
    @Order(1)
    public void testAddAMovie(){

        Assertions.assertAll(
                () -> Assertions.assertTrue(MovieService.newMovie(new Movie(9, "12 Monkeys", 1995, 168800000, 6))),
                () -> Assertions.assertFalse(MovieService.newMovie(new Movie(9, "12 Monkeys", 1995, 168800000, 6))),
                () -> Assertions.assertFalse(MovieService.newMovie(new Movie(10, "12 Monkeys", 1995, 168800000, 6)))
        );

    }

    @Test
    @Order(2)
    public void testModifyTakings(){

        Assertions.assertAll(
                () -> Assertions.assertInstanceOf(Boolean.class, MovieService.updateTakings(1,100)),
                () -> Assertions.assertTrue(MovieService.updateTakings(2, 100)),
                () -> Assertions.assertFalse(MovieService.updateTakings(12, 100))
        );

    }







}
