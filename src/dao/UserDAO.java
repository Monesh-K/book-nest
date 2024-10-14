package dao;

import dto.User;

import java.sql.*;

public class UserDAO implements UserDAOInterface {

    // Retrieves and displays all users from the 'users' table.
    // It first checks if the table is empty using the isTableEmpty() method.
    public void getUserList(Connection connection) {
        if (!isTableEmpty(connection)) {
            String query = "SELECT * FROM users";
            try {
                Statement st = connection.createStatement();
                ResultSet rs = st.executeQuery(query);
                while (rs.next()) {
                    // Print user details
                    System.out.println("\n\nName: " + rs.getString("user_name") +
                            "\nMobile Number: " + rs.getString("mobile_number"));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // Checks if the 'users' table is empty by counting the number of records.
    // Returns true if the table is empty, otherwise returns false.
    public boolean isTableEmpty(Connection connection) {
        String query = "SELECT COUNT(*) FROM users";
        int count = 0;
        try {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(query);
            if (rs.next()) {
                count = rs.getInt(1); // Get the count of users
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count == 0; // Return true if count is 0
    }

    // Adds a new user to the 'users' table with the provided User object.
    // It uses a PreparedStatement to insert the user data into the database.
    public void addNewUser(User u1, Connection connection) {
        String query = "INSERT INTO users(user_name, mobile_number) VALUES(?,?)";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, u1.getUserName()); // Set the username
            ps.setString(2, u1.getMobileNumber() + ""); // Set the mobile number
            ps.executeUpdate(); // Execute the query
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Searches for a user in the 'users' table by username and mobile number.
    // Returns the user's ID if found, otherwise returns -1.
    public int searchUser(String uName, long mobile, Connection connection) {
        String query = "SELECT id FROM users WHERE user_name=? AND mobile_number=?";
        int id = -1;
        try {
            PreparedStatement pt = connection.prepareStatement(query);
            pt.setString(1, uName); // Set the username
            pt.setString(2, mobile + ""); // Set the mobile number
            ResultSet rs = pt.executeQuery();
            if (rs.next()) {
                id = rs.getInt("id"); // Get the user ID
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id; // Return the user ID or -1 if not found
    }

    // Retrieves the user ID from the 'users' table by username.
    // Returns the user ID if found, otherwise returns -1.
    public int getId(String uName, Connection connection) {
        String query = "SELECT id FROM users WHERE user_name=?";
        int id = -1;
        try {
            PreparedStatement pt = connection.prepareStatement(query);
            pt.setString(1, uName); // Set the username
            ResultSet rs = pt.executeQuery();
            if (rs.next()) {
                id = rs.getInt("id"); // Get the user ID
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id; // Return the user ID or -1 if not found
    }

    // Retrieves the user ID from the 'users' table by mobile number.
    // Returns the user ID if found, otherwise returns -1.
    public int getId(long mobile, Connection connection) {
        String query = "SELECT id FROM users WHERE mobile_number=?";
        int id = -1;
        try {
            PreparedStatement pt = connection.prepareStatement(query);
            pt.setString(1, mobile + ""); // Set the mobile number
            ResultSet rs = pt.executeQuery();
            if (rs.next()) {
                id = rs.getInt("id"); // Get the user ID
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id; // Return the user ID or -1 if not found
    }

    // Removes a user from the 'users' table based on their user ID.
    // It uses a PreparedStatement to delete the user record.
    public void removeUser(int userId, Connection connection) {
        String query = "DELETE FROM users WHERE id=?";
        try {
            PreparedStatement pt = connection.prepareStatement(query);
            pt.setInt(1, userId); // Set the user ID
            pt.executeUpdate(); // Execute the query to delete the user
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
