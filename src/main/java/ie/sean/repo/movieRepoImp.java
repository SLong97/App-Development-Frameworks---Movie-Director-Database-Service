package ie.sean.repo;

import ie.sean.entities.Result;
import ie.sean.repo.rowmappers.resultRowmapper;
import ie.sean.entities.Movie;
import ie.sean.repo.rowmappers.movieRowmapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class movieRepoImp implements movieRepo{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;


    @Override
    public List<Movie> getAll() {
        String sql = "select * from movie";
        return jdbcTemplate.query(sql, new movieRowmapper());
    }

    @Override
    public int addMovie(Movie newMovie){
        String sql = "insert into movie values (:id, :title, :yearReleased, :takings, :director)";
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
                .addValue("id", newMovie.getMovieId())
                .addValue("title", newMovie.getTitle())
                .addValue("yearReleased", newMovie.getYearReleased())
                .addValue("takings", newMovie.getTakings())
                .addValue("director", newMovie.getDirectorId());

        return namedParameterJdbcTemplate.update(sql, sqlParameterSource);

    }

    @Override
    public boolean exists(int id){
        String sql = "select count(*) from movie where movie_id = :movieId";
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource().addValue("movieId", id);
        Integer number = namedParameterJdbcTemplate.queryForObject(sql, sqlParameterSource, Integer.class);
        return number != null && number == 1;

    }

    @Override
    public boolean existsByNameYear(String title, int yearReleased){
        String sql = "select count(*) from movie where title = :title and yearReleased = :yearReleased";
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
                .addValue("title", title)
                .addValue("yearReleased", yearReleased);
        Integer number = namedParameterJdbcTemplate.queryForObject(sql, sqlParameterSource, Integer.class);
        return number != null && number == 1;
    }

    @Override
    public int deleteMovie(int movieId){
        String sql = "delete movie where movie_id = :movieId";
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource().addValue("movieId", movieId);
        return namedParameterJdbcTemplate.update(sql, sqlParameterSource);
    }

    @Override
    public Result findMovieWithDirector(int movieId) {
        String sql = "select m.movie_id, m.title, m.yearReleased, m.takings, d.first_name, d.last_name  from movie m inner join director d on d.director_id = m.director_id where m.movie_id = :movieId";
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource().addValue("movieId", movieId);
        return namedParameterJdbcTemplate.queryForObject(sql, sqlParameterSource, new resultRowmapper());
    }

    @Override
    public int changeTakings(int movieId, int takings){
        String sql = "update movie set takings = :newTakings where movie_id = :movieId";
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
                .addValue("movieId", movieId)
                .addValue("newTakings", takings);
        return namedParameterJdbcTemplate.update(sql, sqlParameterSource);
    }

    @Override
    public Result findHighestTaking() {
        String sql = "select m.movie_id, m.title, m.yearReleased, m.takings, d.first_name, d.last_name from movie m inner join director d on d.director_id = m.director_id where m.takings = (select max(takings) from movie where movie_id = movie_id)";
        return jdbcTemplate.queryForObject(sql, new resultRowmapper());
    }

}
