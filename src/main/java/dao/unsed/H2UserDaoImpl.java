/*
package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Map;

public class H2UserDaoImpl extends UserDaoAbstract{

    @Override
    public Connection makeConnection() throws SQLException {
        Map<String, String> env = System.getenv();
        //DB 접속 (ex. sql workbench실행)
        Connection c = DriverManager.getConnection(env.get("DB_HOST"),
                env.get("DB_USER"), env.get("DB_PASSWORD"));

        return c;
    }

}
*/
