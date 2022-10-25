package dao;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;

@Configuration
public class UserDaoFactory {


    @Bean
    public UserDaoInterface userDaoInterface() {
        return new UserDaoInterface(() -> {
            return null;
        });
    }


    public class Main {
        public static void main(String[] args) {
            AnnotationConfigApplicationContext ctx =
                    new AnnotationConfigApplicationContext(UserDaoFactory2.class);
            UserDao05Interface userDao = ctx.getBean("userDaoInterface", UserDaoInterface.class);

        }


    }

    @Bean
    DataSource h2DataSource() {
        return new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.H2)
                .setName("likelion-db;MODE=MySQL")
                .addScript("classpath:jdbc/schema.sql")
                .build();
    }
}