package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectDB {
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/football field management";
    static final String UNAME = "root";
    static final String PASS = "12345";

    public static Connection getConnection(){
        Connection connection = null;
        try {
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, UNAME, PASS);
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Connect failed");
            e.printStackTrace();
        }
        return connection;
    }
}
