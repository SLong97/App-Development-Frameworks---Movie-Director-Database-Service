package ie.sean.repo.rowmappers;

import ie.sean.entities.Movie;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class movieRowmapper implements RowMapper<Movie> {

    @Override
    public Movie mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Movie(rs.getInt(1),rs.getString(2),rs.getInt(3), rs.getInt(4), rs.getInt(5));
    }

}
