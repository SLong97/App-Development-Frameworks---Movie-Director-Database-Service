package ie.sean.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Director {

    private int directorId;
    private String firstName;
    private String lastName;
    private boolean active;

}
