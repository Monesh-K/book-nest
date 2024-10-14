package dao;

import java.sql.*;

public class BorrowerDAO implements BorrowerDAOInterface {

    // Adds a borrower with the user ID and book ID
    public boolean add(int userId, int bookId, Connection connection) {
        String sql = "INSERT INTO borrower(borrower_id, book_id) VALUES(?, ?)";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, userId);
            ps.setInt(2, bookId);
            ps.executeUpdate(); // Execute the insert operation
            return true; // Return true if insertion is successful
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false; // Return false if there is an error
    }

    // Displays the list of all borrowers and their borrowed books
    public void displayBorrowerList(Connection connection) {
        String query = "SELECT u.user_name AS borrower_name, b.book_name AS borrowed_book " +
                "FROM borrower br " +
                "JOIN users u ON br.borrower_id = u.id " +
                "JOIN books b ON br.book_id = b.id";
        try {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(query); // Execute the query
            // Iterate through the results and display borrower details
            while (rs.next()) {
                System.out.println("\n\nBorrower Name: " + rs.getString("borrower_name") +
                        "\nBook Name: " + rs.getString("borrowed_book"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Checks if the borrower table is empty
    public boolean isTableEmpty(Connection connection) {
        String query = "SELECT COUNT(*) FROM borrower";
        int count = 0;
        try {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(query); // Execute the query
            if (rs.next()) {
                count = rs.getInt(1); // Get the count of rows
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count == 0; // Return true if the table is empty, else false
    }

    // Removes a borrower entry using borrower ID and book ID
    public void remove(int borrowerId, int bookId, Connection connection) {
        String query = "DELETE FROM borrower WHERE borrower_id=? AND book_id=?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, borrowerId); // Set borrower ID
            ps.setInt(2, bookId); // Set book ID
            ps.executeUpdate(); // Execute the delete operation
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Checks if the borrower entry is valid (i.e., whether the user has borrowed the book)
    public boolean isValidBorrower(int userId, int bookId, Connection connection) {
        String query = "SELECT COUNT(*) FROM borrower WHERE borrower_id=? AND book_id=?";
        int count = 0;
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, userId); // Set borrower ID
            ps.setInt(2, bookId); // Set book ID
            ResultSet rs = ps.executeQuery(); // Execute the query
            if (rs.next()) {
                count = rs.getInt(1); // Get the count of matching rows
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count == 0; // Return true if no match found (i.e., valid to borrow)
    }
}
