package dao;

import domain.User;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;

public class DaoFactory {
    public UserDao user() {
        ConnectionMaker connectionMaker = new H2ConnectionMaker();
        UserDao userDao = new UserDao(connectionMaker);
        return userDao;
    }

/* H2
    UserDao H2UserDao(){
        return new UserDao(h2DataSource());
    }
    @Bean
    DataSource h2DataSource() {
        return new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.H2)
                .setName("likelion-db;MODE=MySQL")
                .addScript("classpath:jdbc/schema.sql")
                .build();
    }
*/

}
