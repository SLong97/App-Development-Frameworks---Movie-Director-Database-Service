package ie.sean.repo.rowmappers;

import ie.sean.entities.Result;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class resultRowmapper implements RowMapper<Result> {

    @Override
    public Result mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Result(rs.getInt("movie.movie_id"),rs.getString("movie.title"),rs.getInt("movie.yearReleased"),rs.getInt("movie.takings"),rs.getString("director.first_name"),rs.getString("director.last_name"));
    }


}