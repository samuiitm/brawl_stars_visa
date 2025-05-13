package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnexioBD {
    private static final String URL = "jdbc:mysql://localhost:3306/db_brawl_visa";
    private static final String USER = "visa";
    private static final String PASSWORD = "P@ssw0rd";

    public static Connection getConnexio() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
