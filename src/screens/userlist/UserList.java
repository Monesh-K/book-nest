package screens.userlist;

import datalayer.LibraryRepository;
import dto.User;

public class UserList {
    private UserListViewModel userListViewModel; // ViewModel to handle user-related logic

    // Constructor to initialize UserList and its ViewModel
    public UserList() {
        userListViewModel = new UserListViewModel(this);
    }

    // Method to add users to the system
    public void addUsers(String name, long mobile) {
        // Check if a user already exists with the same username or mobile number
        if (userListViewModel.checkUserExists(name, mobile)) {
            System.out.println("User already exists with either same username or mobile number.");
        }
        else {
            System.out.println("User created.");
        }
    }

    // Method to remove a user by their username
    public String removeUsers(String name) {
        LibraryRepository libraryRepository = LibraryRepository.getInstance(); // Get instance of the library repository

        // Check if the user list is empty
        if (libraryRepository.isEmpty()) {
            return "Users list is empty"; // Return message if no users exist
        }

        // Validate the user and retrieve their ID
        int id = userListViewModel.searchAndValidate(name);
        if (id != -1) {
            // Remove the user if found
            userListViewModel.remove(id);
            return "User has been removed successfully"; // Confirmation message
        }
        return "Invalid username"; // Message if the username is not found
    }

    // Method to check if a user exists in the system
    public boolean isUserExists(String userName) {
        // Search for the user and return true if found, otherwise false
        return userListViewModel.searchAndValidate(userName) != -1;
    }
}
