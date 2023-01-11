import ie.sean.entities.Config;
import ie.sean.entities.Director;
import ie.sean.repo.directorRepo;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class) // Adds SpringExtension i.e. Spring stuff to Junit5
@ContextConfiguration(classes = {Config.class}) // To specify the context to be loaded
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestDirectorRepoFunctionality {

    @Autowired
    directorRepo DirectorRepo;

    @Test
    @Order(1)
    public void testAddDirector(){

        Assertions.assertAll(
                () -> Assertions.assertEquals(1, DirectorRepo.addDirector(new Director(7,"David","Fincher",true))),
                () -> Assertions.assertThrows(DuplicateKeyException.class, () -> DirectorRepo.addDirector(new Director(1,"David","Fincher",true)), "Message shows if DuplicateKeyException not thrown")
        );


    }

    @Test
    @Order(2)
    public void testFindDirectorsAverageTakings(){
        Assertions.assertAll(
                () -> Assertions.assertEquals(336000000, DirectorRepo.findDirectorsAverageTakings(3))
        );
    }

    @Test
    @Order(3)
    public void testInactiveDirectors(){
        Assertions.assertAll(
                () -> Assertions.assertEquals(2, DirectorRepo.inactiveDirectors())
        );
    }

    @Test
    @Order(4)
    public void testChangeDirectorStatus(){
        Assertions.assertAll(
                () -> Assertions.assertEquals(1, DirectorRepo.changeStatus(1, false))
        );
    }


    @Test
    @Order(5)
    public void testDeleteDirector(){
        Assertions.assertAll(
                () -> Assertions.assertEquals(1, DirectorRepo.deleteDirector(1)),
                () -> Assertions.assertEquals(0, DirectorRepo.deleteDirector(22))
        );
    }


}
