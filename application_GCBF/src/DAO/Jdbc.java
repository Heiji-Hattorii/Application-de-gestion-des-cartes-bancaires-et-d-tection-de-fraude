package DAO; 
import java.sql.Connection; 
import java.sql.DriverManager; 
import java.sql.SQLException; 
public class Jdbc { 
    private static final String URL = "jdbc:mysql://localhost:3307/banque_db";
    private static final String USER = "root";
    private static final String PASSWORD = "heiji123";

    private Jdbc() {} 

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            System.err.println("error jdbc");
            throw new SQLException(e);
        }
    }
}