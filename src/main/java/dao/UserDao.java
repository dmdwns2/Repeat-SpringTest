package dao;

import domain.User;
/*import org.springframework.boot.autoconfigure.batch.BatchProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.EmptyResultDataAccessException;*/
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
/*import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;*/

import javax.sql.DataSource;
import java.sql.*;
import java.util.List;
/*import java.util.Map;*/

public class UserDao {

    private JdbcTemplate jdbcTemplate;

    public UserDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public UserDao(){

    }
    RowMapper<User> rowMapper = new RowMapper<User>() {
        @Override
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            User user = new User(rs.getString("id"),
                    rs.getString("name"),
                    rs.getString("password"));

            return user;
        }
    };

    public void add(final User user) throws SQLException {
        this.jdbcTemplate.update("insert into user(id, name, password) values (?, ?, ?);",
                user.getId(), user.getName(), user.getPassword());
    }


    public User findById(String id)  {
        String sql = "select * from user where id = ?";
        return this.jdbcTemplate.queryForObject(sql, rowMapper, id);
    }

    public List<User> getAll() {
        String sql = "select * from user order by id";
        return this.jdbcTemplate.query(sql, rowMapper);
    }
    public void deleteALl() throws SQLException {
        this.jdbcTemplate.update("delete from user");
    }

    public int getCount() throws SQLException {
        return this.jdbcTemplate.queryForObject("select count (*) from user;", Integer.class);
    }


    public static void main(String[] args)  {
        UserDao userDao = new UserDao();
//        userDao.add();
        User user = userDao.findById("6");
        System.out.println(user.getName());
    }

}