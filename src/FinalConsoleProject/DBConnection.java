package FinalConsoleProject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

  
    // only one instance of this class
    private static DBConnection instance;
    // only one connection object
    private Connection connection;
    private static final String URL = "jdbc:mysql://localhost:3306/AttendanceDB";
    private static final String USER = "root";
    private static final String PASSWORD = "Rocky@765760";
    // private constructor - no one can create object from outside
    private DBConnection() {
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    // returns the same instance every time
    public static DBConnection getInstance() {
        if (instance == null) {        // create only once
            instance = new DBConnection();
        }
        return instance;
    }
    // returns the same connection every time
    public Connection getConnection() {
        return connection;
    }
}
