package db;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateTables {
    private static CreateTables createTables; // Singleton instance
    private Connection conn; // Database connection

    // Private constructor to initialize the database connection and create tables
    private CreateTables() {
        try {
            // Get database connection
            conn = DBConnection.getConnection();

            // SQL statement to create books table
            String createBooksTable = "CREATE TABLE IF NOT EXISTS books ("
                    + "id INT PRIMARY KEY AUTO_INCREMENT, "
                    + "book_name VARCHAR(255), "
                    + "isbn VARCHAR(255) UNIQUE NOT NULL, "
                    + "author VARCHAR(255), "
                    + "availability ENUM('available', 'unavailable') DEFAULT 'available'"
                    + ")";

            // SQL statement to create users table
            String createUsersTable = "CREATE TABLE IF NOT EXISTS users ("
                    + "id INT PRIMARY KEY AUTO_INCREMENT, "
                    + "user_name VARCHAR(255), "
                    + "mobile_number VARCHAR(15) UNIQUE NOT NULL"
                    + ")";

            // SQL statement to create borrower table
            String createBorrowerTable = "CREATE TABLE IF NOT EXISTS borrower ("
                    + "id INT PRIMARY KEY AUTO_INCREMENT, "
                    + "borrower_id INT, "
                    + "book_id INT, "
                    + "FOREIGN KEY (borrower_id) REFERENCES users(id) "
                    + "ON DELETE CASCADE ON UPDATE CASCADE, "
                    + "FOREIGN KEY (book_id) REFERENCES books(id) "
                    + "ON DELETE CASCADE ON UPDATE CASCADE"
                    + ")";

            // Create a statement to execute the SQL commands
            Statement statement = conn.createStatement();
            // Execute the table creation statements
            statement.execute(createBooksTable);
            statement.execute(createUsersTable);
            statement.execute(createBorrowerTable);
        } catch (SQLException e) {
            e.printStackTrace(); // Print any SQL exceptions
        }
    }

    // Method to create new tables if they do not exist
    public static void createNewTable() {
        if (createTables == null) {
            createTables = new CreateTables(); // Instantiate if not already created
        }
    }
}
