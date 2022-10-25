package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Map;

public interface ConnectionMaker {
    public Connection makeConnection() throws SQLException,ClassNotFoundException;/**/

}