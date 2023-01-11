package ie.sean;

import ie.sean.entities.Config;
import ie.sean.entities.Director;
import ie.sean.entities.Movie;
import ie.sean.service.directorService;
import ie.sean.service.directorServiceImp;
import ie.sean.service.movieService;
import ie.sean.service.movieServiceImp;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import java.util.Locale;

public class Main {

    public static void main(String[] args) {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
        directorService DirectorService = context.getBean(directorServiceImp.class);
        movieService MovieService = context.getBean(movieServiceImp.class);

        //Printing Greetings in Italian, French and English
        System.out.println("Greetings");
        System.out.println("------------------------------------------------------------");
        System.out.println(context.getMessage("greeting", null, Locale.ITALIAN));
        System.out.println(context.getMessage("greeting", null, Locale.FRENCH));
        System.out.println(context.getMessage("greeting", null, Locale.getDefault()));
        System.out.println("");
        System.out.println("");

        //Listing all Directors in the "director" table
        System.out.println("All Directors");
        System.out.println("------------------------------------------------------------");
        DirectorService.findAll().forEach(System.out::println);
        System.out.println("");
        System.out.println("");

        //Listing all Movies in the "movie" table
        System.out.println("All Movies");
        System.out.println("------------------------------------------------------------");
        MovieService.findAll().forEach(System.out::println);
        System.out.println("");
        System.out.println("");

        //Adding a new Director to the "director" table
        System.out.println("Add new Director");
        System.out.println("------------------------------------------------------------");
        Director David = new Director(7,"David","Fincher",true);
        System.out.println(DirectorService.newDirector(David) + " --> " + David.getFirstName() +" "+ David.getLastName());
        System.out.println("");
        DirectorService.findAll().forEach(System.out::println);
        System.out.println("");
        System.out.println("");

        //Adding a director where the ID is already in use
            //System.out.println("Add director with id already existing: " + DirectorService.newDirector(new Director(6,"David","Fincher",true)));

        //Adding a director where the name is already in use (first name + second name = name)
            //System.out.println("Add director where name already used: " + DirectorService.newDirector(new Director(6,"Bob","Fincher",true)));

        //Adding a new Movie to the "movie" table
        System.out.println("Add new Movie");
        System.out.println("------------------------------------------------------------");
        Movie CinderellaMan = new Movie(9, "Cinderella Man", 2005, 108500000, 3);
        System.out.println(MovieService.newMovie(CinderellaMan) + " --> " + CinderellaMan.getTitle());
        System.out.println("");
        MovieService.findAll().forEach(System.out::println);
        System.out.println("");
        System.out.println("");

        //Adding a movie where ID is already in use
            //System.out.println("Add movie with id already existing: " + MovieService.newMovie(new Movie(9, "Cinderella Man", 2022, 108500000, 3)));

        //Adding a movie where name and year is already in use (movies can be remade years later so that's why year is checked as well as name, same name different year != same movie)
            //System.out.println("Add movie where name & year already used: " + MovieService.newMovie(new Movie(10, "Cinderella Man", 2005, 108500000, 3)));

        //Getting a Movie's information along with its Directors name using movie ID
        System.out.println("Get Movie along with its Directors name");
        System.out.println("------------------------------------------------------------");
        MovieService.findMovieAndDirector(2).ifPresentOrElse(System.out::println, () -> System.out.println("Not a recognised id"));
        System.out.println("");
        System.out.println("");

        //Getting a Movie's information along with its Directors name using movie ID that doesn't exist
            //MovieService.findMovieAndDirector(22).ifPresentOrElse(System.out::println, () -> System.out.println("Not a recognised id"));

        //Find all Movies belonging to a Director using director ID
        System.out.println("Get all Movies by a Director");
        System.out.println("------------------------------------------------------------");
        int directorID = 2;
        System.out.println("All Movies by Director with ID: " + directorID);
        System.out.println("");
        DirectorService.getDirectorsMovies(directorID).ifPresentOrElse(value -> value.forEach(System.out::println), () -> System.out.println("There is no Director / No Movies aligned with that Director"));
        System.out.println("");
        System.out.println("");

        //Find all Movies belonging to a Director giving a director with no movies in "movie" table
            //DirectorService.getDirectorsMovies(2).ifPresentOrElse(value -> value.forEach(System.out::println), () -> System.out.println("There is no Director / No Movies aligned with that Director"));

        //Find all Movies belonging to a Director giving ID that doesn't exist
            //DirectorService.getDirectorsMovies(10).ifPresentOrElse(value -> value.forEach(System.out::println), () -> System.out.println("There is no Director / No Movies aligned with that Director"));

        //Get Number of Inactive Directors in "director" table
        System.out.println("Number of Inactive Directors");
        System.out.println("------------------------------------------------------------");
        System.out.println(DirectorService.inactiveCount());
        System.out.println("");
        System.out.println("");

        //Get average takings of all of a Directors Movies using Directors ID
        System.out.println("Average Takings for all of Directors Movies");
        System.out.println("------------------------------------------------------------");
        directorID = 3;
        System.out.println("Average Takings for movies by Director with ID: " + directorID);
        System.out.println("");
        System.out.println(DirectorService.averageTakings(directorID));
        System.out.println("");
        System.out.println("");

        //Get average takings of all of a Directors Movies given ID that doesn't exist
        //System.out.println(DirectorService.averageTakings(11));

        //Update Directors "active" field passing in Directors ID and Boolean
        System.out.println("Update a Directors \"active\" field");
        System.out.println("------------------------------------------------------------");
        directorID = 3;
        System.out.println(DirectorService.updateActive(directorID, false) + " --> Updated \"active\" field for Director with ID: " + directorID);
        System.out.println("");
        DirectorService.findAll().forEach(System.out::println);
        System.out.println("");
        System.out.println("");

        //Update Director Active field given ID that doesn't exist
            //System.out.println("Update director active status no such id: " + DirectorService.updateActive(7, false));

        //Update a Movie's "takings" field using Movie ID and passing in new takings
        System.out.println("Update a Movies \"takings\" field");
        System.out.println("------------------------------------------------------------");
        int movieID = 3;
        System.out.println(MovieService.updateTakings(movieID, 50000000) + " --> Updated \"takings\" field for Movie with ID: " + movieID);
        System.out.println("");
        MovieService.findAll().forEach(System.out::println);
        System.out.println("");
        System.out.println("");

        //Update a Movie's "takings" field given ID that doesn't exist
            //System.out.println("Update movie takings no such id: " + MovieService.updateTakings(11, 666000000));

        //Return Movie And its Director where movie has the highest taking
        System.out.println("Get Movie with highest Takings and it's Director");
        System.out.println("------------------------------------------------------------");
        MovieService.findHighestTaking().ifPresentOrElse(System.out::println, () -> System.out.println("Not Found"));
        System.out.println("");
        System.out.println("");

        //Deleting a Movie from the "movie" table using Movie ID
        System.out.println("Delete a Movie");
        System.out.println("------------------------------------------------------------");
        movieID = 1;
        System.out.println(MovieService.delete(movieID) + " --> Deleted Movie with ID: " + movieID);
        System.out.println("");
        MovieService.findAll().forEach(System.out::println);
        System.out.println("");
        System.out.println("");

        //Deleting a Movie that doesn't exist
        //System.out.println("Delete Movie that doesn't exist: " + MovieService.delete(11));

        //Delete a Director from the "director" table using Director ID
        System.out.println("Delete a Director");
        System.out.println("------------------------------------------------------------");
        directorID = 1;
        System.out.println(DirectorService.delete(directorID) + " --> Deleted Director with ID: " + directorID);
        System.out.println("");
        DirectorService.findAll().forEach(System.out::println);
        System.out.println("");
        System.out.println("");

        //Delete a Director that doesn't exist
        //System.out.println("Delete Director that doesn't exist: " + DirectorService.delete(11));

        //Printing Farewells in Italian, French and English
        System.out.println("");
        System.out.println("Farewells");
        System.out.println("------------------------------------------------------------");
        System.out.println(context.getMessage("farewell", null, Locale.ITALIAN));
        System.out.println(context.getMessage("farewell", null, Locale.FRENCH));
        System.out.println(context.getMessage("farewell", null, Locale.getDefault()));


    }

}
