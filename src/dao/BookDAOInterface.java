package dao;

import dto.Book;
import java.sql.Connection;

public interface BookDAOInterface {

    // Check if the books table is empty
    boolean isTableEmpty(Connection connection);

    // Get and display the list of all books
    void getList(Connection connection);

    // Get the ID of a book using its ISBN
    int getIdIsbn(String isbn, Connection connection);

    // Remove a book by its ID
    void remove(int bookId, Connection connection);

    // Get the ID of a book using its name
    int getIdName(String bookName, Connection connection);

    // Display the details of a specific book using its ID
    void displayBookDetails(int bookId, Connection connection);

    // Add a new book to the books table
    void add(Book b1, Connection connection);

    // Get the availability status of a book by its ID
    String getAvailability(int bookId, Connection connection);

    // Set or toggle the availability status of a book
    void setAvailablity(int bookId, String availability, Connection connection);
}
