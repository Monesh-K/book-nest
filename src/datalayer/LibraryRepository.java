package datalayer;

import dao.BookDAO;
import dao.BorrowerDAO;
import dao.UserDAO;
import db.DBConnection;
import dto.Book;
import dto.Borrower;
import dto.User;

import java.sql.Connection;
import java.sql.SQLException;

public class LibraryRepository {
    private User loggedInUser;  // Stores the currently logged-in user
    private Connection connection; // Database connection
    private BookDAO bookDAO; // Data access object for books
    private BorrowerDAO borrowerDAO; // Data access object for borrowers
    private UserDAO userDAO; // Data access object for users
    private static LibraryRepository repository; // Singleton instance of LibraryRepository

    // Private constructor to initialize database connection and DAOs
    private LibraryRepository() {
        try {
            connection = DBConnection.getConnection();
            bookDAO = new BookDAO();
            borrowerDAO = new BorrowerDAO();
            userDAO = new UserDAO();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Singleton pattern to get the instance of LibraryRepository
    public static LibraryRepository getInstance() {
        if (repository == null) {
            repository = new LibraryRepository();
        }
        return repository;
    }

    // User Authentication
    public User validate(String userName, String password) {
        if (userName.equals("admin") && password.equals("password")) {
            User user = new User(userName);
            loggedInUser = user;
            return user;
        }
        return null;
    }

    // User Management
    public void getUsersList() {
        userDAO.getUserList(connection);
    }

    public void addUsers(User u1) {
        userDAO.addNewUser(u1, connection);
    }

    public boolean isUserExist(String uName, long mobile) {
        return userDAO.searchUser(uName, mobile, connection) != -1;
    }

    public int getNameId(String uName) {
        return userDAO.getId(uName, connection);
    }

    public int getUserMobile(long mobile) {
        return userDAO.getId(mobile, connection);
    }

    public boolean isEmpty() {
        return userDAO.isTableEmpty(connection);
    }

    public void deleteUser(int userId) {
        userDAO.removeUser(userId, connection);
    }

    public void displayUserList() {
        if (isEmpty()) {
            System.out.println("\nNo user exists.");
        } else {
            getUsersList();
        }
    }

    // Book Management
    public boolean isBookEmpty() {
        return bookDAO.isTableEmpty(connection);
    }

    public void showList() {
        if (isBookEmpty()) {
            System.out.println("No books to show.");
        } else {
            getBookList();
        }
    }

    public void getBookList() {
        bookDAO.getList(connection);
    }

    public int getBookIdWithIsbn(String isbn) {
        return bookDAO.getIdIsbn(isbn, connection);
    }

    public int getBookIdWithName(String name) {
        return bookDAO.getIdName(name, connection);
    }

    public void removeBook(int bookId) {
        bookDAO.remove(bookId, connection);
    }

    public void searchBook(String bookName) {
        if (isBookEmpty()) {
            System.out.println("Sorry library doesn't have any books right now.");
        } else {
            int bookId = getBookIdWithName(bookName);
            if (bookId != -1) {
                bookDAO.displayBookDetails(bookId, connection);
            } else {
                System.out.println("Sorry the '" + bookName + "' book is currently unavailable.");
            }
        }
    }

    public void addBook(Book b1) {
        bookDAO.add(b1, connection);
    }

    public boolean isBookAvailable(String bookName) {
        int bookId = getBookIdWithName(bookName);
        String availability = getBookAvailability(bookId);
        return availability.equalsIgnoreCase("available");
    }

    private String getBookAvailability(int bookId) {
        return bookDAO.getAvailability(bookId, connection);
    }

    // Borrower Management
    public boolean addBorrower(Borrower borrower) {
        int userId = getNameId(borrower.getBorrowerName());
        int bookId = getBookIdWithName(borrower.getBookName());
        return borrowerDAO.add(userId, bookId, connection);
    }

    public void getBorrowerList() {
        if (isBorrowerEmpty()) {
            System.out.println("No one borrowed any books");
        } else {
            borrowerDAO.displayBorrowerList(connection);
        }
    }

    public boolean isBorrowerEmpty() {
        return borrowerDAO.isTableEmpty(connection);
    }

    public void changeAvailability(String bookName) {
        int bookId = getBookIdWithName(bookName);
        String availability = getBookAvailability(bookId);
        bookDAO.setAvailablity(bookId, availability, connection);
    }

    public void removeBorrower(String borrowerName, String borrowedBook) {
        int userId = getNameId(borrowerName);
        int bookId = getBookIdWithName(borrowedBook);
        borrowerDAO.remove(userId, bookId, connection);
        String availability = getBookAvailability(bookId);
        bookDAO.setAvailablity(bookId, availability, connection);
    }

    public boolean checkStatus(int userId, int bookId) {
        return borrowerDAO.isValidBorrower(userId, bookId, connection);
    }
}
