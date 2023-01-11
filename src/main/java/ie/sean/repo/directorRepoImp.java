package ie.sean.repo;

import ie.sean.entities.Director;
import ie.sean.entities.Movie;
import ie.sean.repo.rowmappers.directorRowmapper;
import ie.sean.repo.rowmappers.movieRowmapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class directorRepoImp implements directorRepo{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public int count() {
        String sql = "select count(*) from director";
        Integer number = jdbcTemplate.queryForObject(sql, Integer.class);
        return number != null? number : -1;
    }

    @Override
    public List<Director> getAll() {
        String sql = "select * from director";
        return jdbcTemplate.query(sql, new directorRowmapper());
    }

    @Override
    public int addDirector(Director newDirector){
        String sql = "insert into director values (:id, :firstName, :lastName, :active)";
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
                .addValue("id", newDirector.getDirectorId())
                .addValue("firstName", newDirector.getFirstName())
                .addValue("lastName", newDirector.getLastName())
                .addValue("active", newDirector.isActive());

        return namedParameterJdbcTemplate.update(sql, sqlParameterSource);

    }

    @Override
    public boolean exists(int id){
        String sql = "select count(*) from director where director_id = :directorId";
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource().addValue("directorId", id);
        Integer number = namedParameterJdbcTemplate.queryForObject(sql, sqlParameterSource, Integer.class);
        return number != null && number == 1;

    }

    @Override
    public boolean existsByName(String firstName, String lastName){
        String sql = "select count(*) from director where first_name = :firstName and last_name = :lastName";
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
                .addValue("firstName", firstName)
                .addValue("lastName", lastName);
        Integer number = namedParameterJdbcTemplate.queryForObject(sql, sqlParameterSource, Integer.class);
        return number != null && number == 1;
    }

    @Override
    public int deleteDirector(int directorId){
        String sql = "delete director where director_id = :directorId";
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource().addValue("directorId", directorId);
        return namedParameterJdbcTemplate.update(sql, sqlParameterSource);
    }

    @Override
    public List<Movie> findDirectorsMovies(int directorId){
        String sql = "select * from movie where director_id = :directorId";
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource().addValue("directorId", directorId);
        return namedParameterJdbcTemplate.query(sql, sqlParameterSource, new movieRowmapper());
    }

    @Override
    public int changeStatus(int directorId, boolean status){
        String sql = "update director set active = :newStatus where director_id = :directorId";
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
                .addValue("directorId", directorId)
                .addValue("newStatus", status);
        return namedParameterJdbcTemplate.update(sql, sqlParameterSource);
    }

    @Override
    public int findDirectorsAverageTakings(int directorId){
        String sql = "select avg(takings) from movie where director_id = " + directorId;
        Integer number = jdbcTemplate.queryForObject(sql, Integer.class);
        return number != null? number : -1;

    }

    @Override
    public int inactiveDirectors() {
        String sql = "select count(*) from director where active = false";
        Integer number = jdbcTemplate.queryForObject(sql, Integer.class);
        return number != null? number : -1;
    }


}
