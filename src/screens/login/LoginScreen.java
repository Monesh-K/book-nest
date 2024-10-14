package screens.login;

import dto.User;
import screens.admin.AdminControls;

import java.util.Scanner;

public class LoginScreen {
    // Instance of the LoginViewModel to handle login logic
    private LoginViewModel viewModel;

    // Constructor initializes the LoginViewModel with the current LoginScreen instance
    public LoginScreen() {
        viewModel = new LoginViewModel(this);
    }

    // Method to create the login screen and handle user input
    public void onCreate() {
        Scanner scan = new Scanner(System.in); // Create a scanner for user input
        System.out.println("\nPlease login to proceed...");
        System.out.println("Enter username: ");
        String userName = scan.next(); // Read the username input
        System.out.println("Enter password: ");
        String password = scan.next(); // Read the password input
        viewModel.validateUser(userName, password); // Validate the user credentials
    }

    // Method to display error messages to the user
    public void displayError(String msg) {
        System.out.println(msg); // Print the error message
    }

    // Method to navigate to the admin controls upon successful login
    public void navigateAdminControls(User loggedInUser) {
        System.out.println("Welcome, " + loggedInUser.getUserName()); // Greet the logged-in user
        AdminControls adminControlsViewModel = new AdminControls(); // Create an instance of AdminControls
        adminControlsViewModel.displayControls(); // Display the admin controls
    }
}
