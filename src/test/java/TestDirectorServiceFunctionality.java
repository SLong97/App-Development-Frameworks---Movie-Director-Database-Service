import ie.sean.entities.Config;
import ie.sean.entities.Director;
import ie.sean.entities.Result;
import ie.sean.service.directorService;
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
public class TestDirectorServiceFunctionality {

    @Autowired
    directorService DirectorService;

    @Test
    @Order(1)
    public void testGetDirectorsMovies(){

        Assertions.assertAll(
                () -> Assertions.assertInstanceOf(Optional.class, DirectorService.getDirectorsMovies(3)),
                () -> Assertions.assertEquals(2, DirectorService.getDirectorsMovies(3).get().size()),
                () -> Assertions.assertEquals(Optional.empty(), DirectorService.getDirectorsMovies(1))
        );

    }







}
