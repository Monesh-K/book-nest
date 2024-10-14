package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnection {
    private static Connection conn; // Database connection
    private static final String URL = "jdbc:mysql://localhost:3306/"; // Database URL
    private static final String USER = "root"; // Database user
    private static final String PASSWORD = "231104"; // Database password

    // Method to get the database connection
    public static Connection getConnection() throws SQLException {
        // Check if the connection has not been established yet
        if (conn == null) {
            String createDB = "CREATE DATABASE IF NOT EXISTS book_nest"; // SQL command to create database
            String useDB = "USE book_nest"; // SQL command to select the database
            try {
                // Establish connection to the MySQL server
                conn = DriverManager.getConnection(URL, USER, PASSWORD);
                Statement stmt = conn.createStatement(); // Create a statement to execute SQL commands
                stmt.execute(createDB); // Execute the database creation command
                stmt.execute(useDB); // Select the newly created database
                CreateTables.createNewTable(); // Create tables in the selected database
            } catch (SQLException e) {
                e.printStackTrace(); // Print any SQL exceptions that occur
            }
        }
        return conn; // Return the established connection
    }
}
