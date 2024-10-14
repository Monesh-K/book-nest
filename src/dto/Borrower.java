package dto;

public class Borrower {
    private String borrowerName; // Name of the borrower
    private String bookName;      // Name of the borrowed book

    // Constructor to initialize the Borrower object with its attributes
    public Borrower(String borrowerName, String bookName) {
        this.borrowerName = borrowerName;
        this.bookName = bookName;
    }

    // Getter method for borrower name
    public String getBorrowerName() {
        return borrowerName;
    }

    // Getter method for book name
    public String getBookName() {
        return bookName;
    }
}
