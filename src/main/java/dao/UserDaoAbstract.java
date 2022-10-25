package dao;

import java.sql.Connection;
import java.sql.SQLException;

public abstract class UserDaoAbstract {
    public abstract Connection makeConnection() throws SQLException;
}
