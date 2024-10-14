package screens.admin;

import datalayer.LibraryRepository;
import screens.book.BookBorrowList;
import screens.book.BookList;
import screens.userlist.UserList;

import java.util.InputMismatchException;
import java.util.Scanner;

public class AdminControls {
    // Method to display admin controls and handle user input
    public void displayControls() {
        Scanner scan = new Scanner(System.in);
        LibraryRepository libraryRepository = LibraryRepository.getInstance(); // Get the singleton instance of LibraryRepository

        while (true) {
            System.out.println("----------------------------------------------------------------");
            // Display the menu options for admin controls
            System.out.println(
                    """
                          \n  1. Add User        2. Remove User     3. Show User List
                            4. Add Book        5. Remove Book     6. Show Book List
                            7. Borrow Book     8. Return Book     9. Show Borrower List
                           10. Logout""");
            System.out.println("----------------------------------------------------------------");
            int choice;
            while (true) {
                // Get the user's choice with error handling for invalid input
                try {
                    choice = scan.nextInt();
                    break;
                } catch (InputMismatchException e) {
                    System.out.println("Please enter a valid number!"); // Prompt for valid input
                }
            }
            scan.nextLine(); // Clear the buffer

            // Exit the loop if the user chooses to logout
            if (choice == 10) break;

            // Handle the user's choice with a switch statement
            switch (choice) {
                case 1 -> {
                    // Add a new user
                    System.out.println("\nEnter new user's name: ");
                    String name = scan.nextLine();
                    System.out.println("Enter mobile number: ");
                    long mobile;
                    while (true) {
                        // Get the mobile number with error handling for invalid input
                        try {
                            mobile = scan.nextLong();
                            break;
                        } catch (InputMismatchException e) {
                            scan.nextLine();
                            System.out.println("Please enter a valid number!"); // Prompt for valid input
                        }
                    }
                    scan.nextLine(); // Clear the buffer
                    UserList userList = new UserList(); // Create a new UserList instance
                    userList.addUsers(name, mobile); // Add the user
                }
                case 2 -> {
                    // Remove a user
                    if (libraryRepository.isEmpty()) {
                        System.out.println("Users list is empty."); // Inform if there are no users
                    } else {
                        System.out.println("\nEnter username to remove: ");
                        String name = scan.nextLine();
                        UserList userList = new UserList(); // Create a new UserList instance
                        System.out.println(userList.removeUsers(name)); // Remove the user and display the result
                    }
                }
                case 3 -> {
                    // Show the user list
                    libraryRepository.displayUserList(); // Display the list of users
                }
                case 4 -> {
                    // Add a new book
                    BookList bookList = new BookList(); // Create a new BookList instance
                    bookList.addBook(); // Add the book
                }
                case 5 -> {
                    // Remove a book
                    if (libraryRepository.isBookEmpty()) {
                        System.out.println("Book list is empty."); // Inform if there are no books
                    } else {
                        BookList bookList = new BookList(); // Create a new BookList instance
                        bookList.removeBook(); // Remove the book
                    }
                }
                case 6 -> {
                    // Show the book list
                    libraryRepository.showList(); // Display the list of books
                }
                case 7 -> {
                    // Borrow a book
                    if (libraryRepository.isEmpty()) {
                        System.out.println("\nNo books to show."); // Inform if there are no books available
                    } else {
                        System.out.println("\nEnter Book name: ");
                        String bookName = scan.nextLine();
                        // Check if the book is available
                        if (!libraryRepository.isBookAvailable(bookName)) {
                            System.out.println("Sorry! " + bookName + " is currently unavailable."); // Inform if the book is not available
                        } else {
                            System.out.println("\nEnter username of borrower: ");
                            String userName = scan.next();
                            UserList userList = new UserList(); // Create a new UserList instance
                            // Check if the user exists
                            if (!userList.isUserExists(userName)) {
                                System.out.println("User doesn't exist.\nEnter mobile number to add user: ");
                                long mobile;
                                while (true) {
                                    // Get the mobile number for new user with error handling
                                    try {
                                        mobile = scan.nextLong();
                                        break;
                                    } catch (InputMismatchException e) {
                                        scan.nextLine();
                                        System.out.println("Please enter a valid number!"); // Prompt for valid input
                                    }
                                }
                                scan.nextLine(); // Clear the buffer
                                userList.addUsers(userName, mobile); // Add the new user
                            }
                            BookBorrowList bookBorrowList = new BookBorrowList(); // Create a new BookBorrowList instance
                            bookBorrowList.addBorrower(userName, bookName); // Add the borrower
                        }
                    }
                }
                case 8 -> {
                    // Return a borrowed book
                    BookBorrowList bookBorrowList = new BookBorrowList(); // Create a new BookBorrowList instance
                    if (libraryRepository.isBorrowerEmpty()) {
                        System.out.println("No one has borrowed any books."); // Inform if no books are borrowed
                    } else {
                        System.out.println("\nEnter username of borrower: ");
                        String userName = scan.nextLine();
                        // Check if the user exists
                        if (!bookBorrowList.isUserExists(userName)) {
                            System.out.println("User doesn't exist."); // Inform if user does not exist
                        } else {
                            System.out.println("\nEnter Book name to return: ");
                            String bookName = scan.nextLine();
                            // Check if the user has borrowed the book
                            if (bookBorrowList.isBookBorrowed(userName, bookName)) {
                                System.out.println("Thanks for returning the book."); // Confirm return of the book
                            } else {
                                System.out.println("Sorry " + userName + " didn't borrow '" + bookName + "' book."); // Inform if the book was not borrowed by the user
                            }
                        }
                    }
                }
                case 9 -> {
                    // Show the list of borrowers
                    libraryRepository.getBorrowerList(); // Display the list of borrowers
                }
                default -> System.out.println("Invalid choice"); // Inform if the input is not valid
            }
        }
    }
}
