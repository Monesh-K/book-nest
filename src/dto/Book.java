package dto;

public class Book {
    private String bookName;       // Name of the book
    private String isbn;           // ISBN of the book
    private String author;         // Author of the book
    private String availability;    // Availability status of the book (e.g., available/unavailable)

    // Constructor to initialize the Book object with its attributes
    public Book(String bookName, String isbn, String author, String availability) {
        this.bookName = bookName;
        this.isbn = isbn;
        this.author = author;
        this.availability = availability;
    }

    // Getter method for book name
    public String getBookName() {
        return bookName;
    }

    // Getter method for ISBN
    public String getIsbn() {
        return isbn;
    }

    // Getter method for author name
    public String getAuthor() {
        return author;
    }

    // Getter method for availability status
    public String getAvailability() {
        return availability;
    }
}
