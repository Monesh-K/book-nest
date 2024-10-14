package dao;

import java.sql.Connection;

public interface BorrowerDAOInterface {

    // Adds a new borrower entry for the given user and book in the database
    boolean add(int userId, int bookId, Connection connection);

    // Displays the list of all borrowers from the database
    void displayBorrowerList(Connection connection);

    // Checks if the borrower table is empty
    boolean isTableEmpty(Connection connection);

    // Removes a borrower entry from the database for the given borrower and book
    void remove(int borrowerId, int bookId, Connection connection);

    // Checks if a valid borrower exists for the given user and book
    boolean isValidBorrower(int userId, int bookId, Connection connection);
}
