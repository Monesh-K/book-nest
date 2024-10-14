package screens.book;

import datalayer.LibraryRepository;
import dto.Borrower;

public class BookBorrowListViewModel {
    // Reference to the BookBorrowList instance
    private BookBorrowList viewModel;

    // Constructor to initialize the ViewModel with the BookBorrowList instance
    public BookBorrowListViewModel(BookBorrowList bookBorrowList) {
        viewModel = bookBorrowList;
    }

    // Method to add a borrower to the library repository
    public boolean isBorrowerAdded(Borrower borrower) {
        LibraryRepository libraryRepository = LibraryRepository.getInstance(); // Get the singleton instance of LibraryRepository
        return libraryRepository.addBorrower(borrower); // Delegate the addition of the borrower to the repository
    }

    // Method to check if a borrower exists based on the username
    public boolean checkBorrowerName(String userName) {
        LibraryRepository libraryRepository = LibraryRepository.getInstance(); // Get the singleton instance of LibraryRepository
        int userId = libraryRepository.getNameId(userName); // Retrieve the user ID associated with the username
        return userId != -1; // Return true if the user exists (user ID is valid)
    }

    // Method to check if a specific book has been borrowed by a specific user
    public boolean checkIsBorrowed(String borrowerName, String borrowedBook) {
        LibraryRepository libraryRepository = LibraryRepository.getInstance(); // Get the singleton instance of LibraryRepository
        int userId = libraryRepository.getNameId(borrowerName); // Get the user ID for the borrower
        int bookId = libraryRepository.getBookIdWithName(borrowedBook); // Get the book ID for the borrowed book
        System.out.println("USER ID: "+userId+" BOOK ID: " + bookId);
        return libraryRepository.checkStatus(userId, bookId); // Check the borrowing status of the book for the user
    }

    // Method to update the borrower list by removing a borrowed book for a user
    public void updateBorrowerList(String borrowerName, String borrowedBook) {
        LibraryRepository libraryRepository = LibraryRepository.getInstance(); // Get the singleton instance of LibraryRepository
        libraryRepository.removeBorrower(borrowerName, borrowedBook); // Remove the borrowed book from the borrower's list in the repository
    }
}
