import ie.sean.entities.Config;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import java.util.Locale;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {Config.class})


public class TestMessagesLanguages {

    @Autowired
    ApplicationContext context;

    @Test
    public void italianGreetingTest() {
        String actual = context.getMessage("greeting", null, "Default", Locale.ITALIAN);
        assertEquals("Ciao, benvenuto nel database di film e registi", actual);
    }

    @Test
    public void frenchGreetingTest() {
        String actual = context.getMessage("greeting", null, "Default", Locale.FRENCH);
        assertEquals("Bonjour, bienvenue dans la base de donnees de films et de realisateurs", actual);
    }

    @Test
    public void defaultGreetingTest() {
        String actual = context.getMessage("greeting", null, "Default", Locale.getDefault());
        assertEquals("Hello, welcome to the Movie and Director Database", actual);
    }


    @Test
    public void italianFarewellTest() {
        String actual = context.getMessage("farewell", null, "Default", Locale.ITALIAN);
        assertEquals("Arrivederci, grazie per aver visitato il database di film e registi", actual);
    }

    @Test
    public void frenchFarewellTest() {
        String actual = context.getMessage("farewell", null, "Default", Locale.FRENCH);
        assertEquals("Au revoir, merci d'avoir visite la base de donnees de films et de realisateurs", actual);
    }

    @Test
    public void defaultFarewellTest() {
        String actual = context.getMessage("farewell", null, "Default", Locale.getDefault());
        assertEquals("Goodbye, thank you for visiting the Movie and Director Database", actual);
    }

}