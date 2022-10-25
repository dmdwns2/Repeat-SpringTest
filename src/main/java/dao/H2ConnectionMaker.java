package dao;

import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Map;

public class H2ConnectionMaker implements ConnectionMaker{

    @Override
    public Connection makeConnection() throws SQLException, ClassNotFoundException {
        Map<String, String> env = System.getenv();
        // DB접속 (ex sql workbeanch실행)
        Connection c = DriverManager.getConnection(env.get("DB_HOST"),
                env.get("DB_USER"), env.get("DB_PASSWORD"));
        return c;
    }
    @Bean
    DataSource h2DataSource() {
        return new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.H2)
                .setName("likelion-db;MODE=MySQL")
                .addScript("classpath:jdbc/schema.sql")
                .build();
    }

}
