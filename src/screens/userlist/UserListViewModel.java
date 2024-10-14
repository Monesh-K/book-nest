package screens.userlist;

import datalayer.LibraryRepository;
import dto.User;

public class UserListViewModel {
    private UserList viewModel; // Reference to the UserList view
    private LibraryRepository libraryRepository; // Reference to the library repository for data operations

    // Constructor to initialize UserListViewModel with the corresponding UserList
    public UserListViewModel(UserList userList) {
        viewModel = userList; // Assign the passed UserList object
        libraryRepository = LibraryRepository.getInstance(); // Get the instance of LibraryRepository
    }

    // Method to search for a user by their name and validate their existence
    public int searchAndValidate(String name) {
        return libraryRepository.getNameId(name); // Retrieve the user ID based on the name
    }

    // Method to check if a user exists based on their name and mobile number
    public boolean checkUserExists(String name, long mobile) {
        // If the user does not exist, create a new user
        if (!libraryRepository.isUserExist(name, mobile)) {
            User u1 = new User(name, mobile); // Create a new User object
            createUser(u1); // Call the method to add the new user
            return false; // Return false indicating the user was created
        }
        return true; // Return true if the user already exists
    }

    // Method to create a new user in the library repository
    public void createUser(User user) {
        libraryRepository.addUsers(user); // Add the user to the repository
    }

    // Method to remove a user by their user ID
    public void remove(int userId) {
        libraryRepository.deleteUser(userId); // Remove the user from the repository
    }
}
