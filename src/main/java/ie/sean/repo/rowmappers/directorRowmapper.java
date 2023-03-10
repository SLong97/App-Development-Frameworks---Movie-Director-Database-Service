package ie.sean.repo.rowmappers;

import ie.sean.entities.Director;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class directorRowmapper implements RowMapper<Director>{

    @Override
    public Director mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Director(rs.getInt(1),rs.getString(2),rs.getString(3), rs.getBoolean(4));
    }

}
