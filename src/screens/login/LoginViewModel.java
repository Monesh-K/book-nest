package screens.login;

import datalayer.LibraryRepository;
import dto.User;

public class LoginViewModel {
    private LoginScreen view; // Reference to the associated LoginScreen

    // Constructor to initialize the LoginViewModel with the LoginScreen instance
    public LoginViewModel(LoginScreen screen) {
        view = screen;
    }

    // Method to validate user credentials
    public void validateUser(String userName, String password) {
        // Check the credentials against the repository and retrieve the logged-in user
        User loggedInUser = LibraryRepository.getInstance().validate(userName, password);

        // If the user is found, navigate to admin controls
        if (loggedInUser != null) {
            view.navigateAdminControls(loggedInUser);
        } else {
            // If the credentials are invalid, display an error message and prompt for input again
            view.displayError("Invalid Login!");
            view.onCreate(); // Re-invoke the login process
        }
    }
}
