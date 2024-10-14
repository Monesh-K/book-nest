package dao;

import dto.User;
import java.sql.Connection;

public interface UserDAOInterface {

    // Retrieves and displays the list of all users from the 'users' table.
    // The method prints user details like name and mobile number.
    void getUserList(Connection connection);

    // Checks if the 'users' table is empty by counting the number of rows in the table.
    // Returns true if the table is empty, otherwise returns false.
    boolean isTableEmpty(Connection connection);

    // Adds a new user to the 'users' table using the provided User object.
    // Takes a User object and inserts the user's name and mobile number into the table.
    void addNewUser(User u1, Connection connection);

    // Searches for a user in the 'users' table by username and mobile number.
    // Returns the user's ID if found, otherwise returns -1.
    int searchUser(String uName, long mobile, Connection connection);

    // Retrieves the user ID from the 'users' table using the username.
    // Returns the user ID if found, otherwise returns -1.
    int getId(String uName, Connection connection);

    // Retrieves the user ID from the 'users' table using the mobile number.
    // Returns the user ID if found, otherwise returns -1.
    int getId(long mobile, Connection connection);

    // Removes a user from the 'users' table based on their user ID.
    // Deletes the record of the user with the provided ID from the table.
    void removeUser(int userId, Connection connection);
}
