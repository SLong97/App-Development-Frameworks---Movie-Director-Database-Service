# App-Development-Frameworks---Movie-Director-Database-Service

### Data stored about director

    First name
    Last name
    Still active (Boolean)

### Data stored about movie

    Title
    Year released
    Takings

### Using this system you should be able to

  1. list all directors
  2. list all movies (director not needed)
  3. add a director
  4. add a movie assigning it to a specific director
  5. delete a movie given its ID 
  6. delete director given their ID 
  7. find a movie by its ID showing all information and its director
  8. find all movies by a director given the director's ID
  9. change a director’s active status given their ID
  10. modify a movie's takings given its ID
  11. determine the average income for all movies by a particular director
  12. determine the number of inactive directors
  13. determine the name of the movie with the highest takings along with the name of its director (you might use a record class here)


## Write unit tests for

- messages in different languages
    
- all outcomes for the following methods in the repository layer (you may use Assertions.assertAll() or write separate tests for each outcome)
  - delete a director
  - add a director
  - change the status of the director
        
- the average income
    
- number of inactive directors
    
- highest takings along with the director
    
- all outcomes for the following methods in the service layer (you may use Assertions.assertAll() or write separate tests for each outcome)
  - add a movie
  - find all movies by a director given their ID
  - modify a movie's takings given its ID


In the service layer ensure that you verify a record exists before you try to edit or retrieve it. Furthermore in cases where the result of the query in the repository layer might throw an exception (e.g. when finding a record), return Optional<> .
