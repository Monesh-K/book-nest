package screens.book;

import dto.Book;

import java.util.Scanner;

public class BookList {
    // Reference to the ViewModel managing book-related operations
    private static BookListViewModel bookViewModel;
    // Scanner instance for user input
    static Scanner scan = new Scanner(System.in);

    // Constructor initializes the ViewModel with the current BookList instance
    public BookList() {
        bookViewModel = new BookListViewModel(this);
    }

    // Method to add a new book to the library
    public void addBook() {
        System.out.println("Enter the name of Book: ");
        String bookName = scan.nextLine(); // Prompt user for the book name
        System.out.println("Enter the author name: ");
        String author = scan.nextLine(); // Prompt user for the author name
        System.out.println("Enter the isbn number: ");
        String isbn = scan.nextLine(); // Prompt user for the ISBN number
        System.out.println("Availability (true/false): ");
        String availability = scan.nextLine(); // Prompt user for availability status

        // Convert availability input to a standardized format
        if (availability.equalsIgnoreCase("true")) {
            availability = "available"; // Set to "available" if user input is "true"
        } else {
            availability = "not available"; // Set to "not available" otherwise
        }

        // Check if the book already exists based on ISBN
        if (bookViewModel.getBookId(isbn) == -1) {
            bookViewModel.createBook(bookName, isbn, author, availability); // Create a new book record
            System.out.println("Book added successfully!"); // Confirmation message
        } else {
            System.out.println("Book already exists!"); // Error message if book exists
        }
    }

    // Method to remove an existing book from the library
    public void removeBook() {
        System.out.println("Enter isbn number of book to remove: ");
        String isbn = scan.nextLine(); // Prompt user for the ISBN number of the book to remove
        int bookId = bookViewModel.getBookId(isbn); // Get the book ID using the provided ISBN

        // Check if the book exists before attempting to remove it
        if (bookId == -1) {
            System.out.println("Book doesn't exist."); // Error message if book is not found
        } else {
            bookViewModel.deleteBook(bookId); // Delete the book using its ID
            System.out.println("Book removed successfully."); // Confirmation message
        }
    }
}
