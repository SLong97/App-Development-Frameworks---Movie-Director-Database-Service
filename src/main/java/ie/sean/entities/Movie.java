package ie.sean.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Movie {

    private int movieId;
    private String title;
    private int yearReleased;
    private int takings;
    private int directorId;

}
