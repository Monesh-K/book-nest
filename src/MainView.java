import datalayer.LibraryRepository;
import screens.login.LoginScreen;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MainView {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in); // Create a Scanner for user input
        LoginScreen loginScreen; // Declare a LoginScreen object

        // Main loop for the application
        while (true) {
            System.out.println("\n---------------------");
            System.out.println("Enter your choice\n1. Login\n2. Show Books List\n3. Search Book\n4. Exit");
            System.out.println("---------------------");

            // Get the instance of LibraryRepository
            LibraryRepository libraryRepository = LibraryRepository.getInstance();
            int choice;

            // Input loop to ensure valid choice
            while (true) {
                try {
                    choice = scan.nextInt(); // Read user choice
                    break; // Exit the loop if valid input is received
                } catch (InputMismatchException e) {
                    scan.nextLine(); // Clear the invalid input
                    System.out.println("Enter a valid number"); // Prompt for valid input
                }
            }
            scan.nextLine(); // Consume newline

            // Exit the application if user chooses option 4
            if (choice == 4) break;

            // Switch case to handle different choices
            switch (choice) {
                case 1 -> { // Login option
                    loginScreen = new LoginScreen(); // Create a new instance of LoginScreen
                    loginScreen.onCreate(); // Call the login method
                }
                case 2 -> { // Show book list option
                    libraryRepository.showList(); // Display the list of books
                }
                case 3 -> { // Search for a book option
                    if (libraryRepository.isEmpty()) { // Check if the library is empty
                        System.out.println("Sorry library doesn't have any books right now."); // Notify user
                    } else {
                        System.out.println("\nEnter Book name: "); // Prompt for book name
                        String bookName = scan.nextLine(); // Read the book name
                        libraryRepository.searchBook(bookName); // Search for the book
                    }
                }
                default -> System.out.println("Invalid choice!"); // Handle invalid choices
            }
        }

        scan.close(); // Close the scanner before exiting
    }
}
