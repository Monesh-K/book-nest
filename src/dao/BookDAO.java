package dao;

import dto.Book;
import java.sql.*;

public class BookDAO implements BookDAOInterface {

    // Method to check if the books table is empty
    public boolean isTableEmpty(Connection connection) {
        String query = "select count(*) from books";
        int count = 0;
        try {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(query);
            if (rs.next()) {
                count = rs.getInt(1); // Get the count of rows
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count == 0; // Return true if no rows are found
    }

    // Method to display the list of all books
    public void getList(Connection connection) {
        String query = "select * from books";
        try {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                // Print book details
                System.out.println("\nBook Name: " + rs.getString(2)
                        + "\nISBN: " + rs.getString(3)
                        + "\nAuthor: " + rs.getString(4)
                        + "\nAvailability: " + rs.getString(5));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to get the book ID using the ISBN
    public int getIdIsbn(String isbn, Connection connection) {
        String query = "select * from books where isbn=?";
        int id = -1;
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, isbn);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                id = rs.getInt(1); // Get the book ID
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id; // Return the book ID
    }

    // Method to remove a book by its ID
    public void remove(int bookId, Connection connection) {
        String query = "delete from books where id=?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, bookId);
            ps.executeUpdate(); // Delete the book
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to get the book ID using the book name
    public int getIdName(String bookName, Connection connection) {
        String query = "select * from books where book_name=?";
        int id = -1;
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, bookName);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                id = rs.getInt(1); // Get the book ID
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id; // Return the book ID
    }

    // Method to display details of a specific book by its ID
    public void displayBookDetails(int bookId, Connection connection) {
        String query = "select * from books where id=?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, bookId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                // Print book details
                System.out.println("\nBook Name: " + rs.getString(2));
                System.out.println("ISBN: " + rs.getString(3));
                System.out.println("Author: " + rs.getString(4));
                System.out.println("Availability: " + rs.getString(5));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to add a new book to the database
    public void add(Book b1, Connection connection) {
        String query = "insert into books(book_name, isbn, author, availability) values(?,?,?,?)";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, b1.getBookName());
            ps.setString(2, b1.getIsbn());
            ps.setString(3, b1.getAuthor());
            ps.setString(4, b1.getAvailability());
            ps.executeUpdate(); // Insert the book into the database
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to get the availability status of a book by its ID
    public String getAvailability(int bookId, Connection connection) {
        String query = "select * from books where id=?";
        String availability = "";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, bookId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                availability = rs.getString(5); // Get the availability status
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return availability; // Return the availability status
    }

    // Method to update the availability status of a book
    public void setAvailablity(int bookId, String availability, Connection connection) {
        String query = "update books set availability=? where id=?";
        availability = availability.equals("available") ? "unavailable" : "available"; // Toggle availability
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, availability);
            ps.setInt(2, bookId);
            ps.executeUpdate(); // Update the availability status
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
