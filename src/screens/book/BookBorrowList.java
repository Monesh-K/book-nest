package screens.book;

import datalayer.LibraryRepository;
import dto.Borrower;

public class BookBorrowList {
    // ViewModel for managing the borrower's list
    private static BookBorrowListViewModel bookBorrowListViewModel;

    // Constructor initializes the BookBorrowListViewModel
    public BookBorrowList() {
        bookBorrowListViewModel = new BookBorrowListViewModel(this);
    }

    // Method to add a borrower for a specific book
    public void addBorrower(String uName, String book) {
        LibraryRepository libraryRepository = LibraryRepository.getInstance(); // Get the singleton instance of LibraryRepository
        Borrower borrower = new Borrower(uName, book); // Create a new Borrower object

        // Check if the borrower was added successfully
        if (bookBorrowListViewModel.isBorrowerAdded(borrower)) {
            System.out.println("Borrower added."); // Confirmation message
            libraryRepository.changeAvailability(borrower.getBookName()); // Update the book's availability status in the repository
        } else {
            System.out.println("Something went wrong."); // Error message if the borrower couldn't be added
        }
    }

    // Method to check if a user exists in the borrower list
    public boolean isUserExists(String userName) {
        return bookBorrowListViewModel.checkBorrowerName(userName); // Delegate the check to the ViewModel
    }

    // Method to check if a book has been borrowed by a specific user
    public boolean isBookBorrowed(String borrowerName, String borrowedBook) {
        // Check if the user has borrowed the specified book
        if (!bookBorrowListViewModel.checkIsBorrowed(borrowerName, borrowedBook)) {
            bookBorrowListViewModel.updateBorrowerList(borrowerName, borrowedBook); // Update the borrower's list if the book is borrowed
            return true; // Return true if the book was successfully borrowed
        }
        return false; // Return false if the book was not borrowed by the user
    }
}
