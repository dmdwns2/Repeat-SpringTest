package dao;

import domain.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;

@Configuration
public class DaoFactory {

    //H2
    @Bean
    UserDao h2UserDao() {
        return new UserDao(h2DataSource());
    }

    @Bean
    DataSource h2DataSource() {
        return new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.H2)
                .setName("likelion-db;MODE=MySQL")
                .addScript("classpath:jdbc/schema.sql")
                .build();
    }
/*    @Bean
    public UserDao user() {
        ConnectionMaker connectionMaker = new H2ConnectionMaker();
        UserDao userDao = new UserDao(connectionMaker);
        return userDao;
    }*/
/*

    @Bean
    public UserDao userDao() {
        return new UserDao(connectionMaker());
    }
*/
/*

    @Bean
    public ConnectionMaker connectionMaker() {
        return new H2ConnectionMaker();
    }

*/


}
