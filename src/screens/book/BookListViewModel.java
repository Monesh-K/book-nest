package screens.book;

import datalayer.LibraryRepository;
import dto.Book;

import java.util.List;

public class BookListViewModel {
    // Reference to the BookList instance for managing book-related actions
    private BookList bookListViewModel;

    // Constructor initializes the ViewModel with the current BookList instance
    public BookListViewModel(BookList bookList) {
        bookListViewModel = bookList;
    }

    // Method to retrieve the book ID using the ISBN
    public int getBookId(String isbn) {
        LibraryRepository libraryRepository = LibraryRepository.getInstance(); // Get the singleton instance of LibraryRepository
        return libraryRepository.getBookIdWithIsbn(isbn); // Fetch the book ID associated with the provided ISBN
    }

    // Method to create a new book and add it to the repository
    public void createBook(String bookName, String isbn, String author, String availability) {
        Book b1 = new Book(bookName, isbn, author, availability); // Create a new Book object
        LibraryRepository libraryRepository = LibraryRepository.getInstance(); // Get the singleton instance of LibraryRepository
        libraryRepository.addBook(b1); // Add the new book to the library repository
    }

    // Method to delete a book from the repository using its ID
    public void deleteBook(int bookId) {
        LibraryRepository libraryRepository = LibraryRepository.getInstance(); // Get the singleton instance of LibraryRepository
        libraryRepository.removeBook(bookId); // Remove the book from the library repository using its ID
    }
}
