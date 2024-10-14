package dto;

public class User {
    private String userName;     // Username of the user
    private long mobileNumber;   // Mobile number of the user

    // Constructor to initialize the User object with both username and mobile number
    public User(String userName, long mobileNumber) {
        this.userName = userName;
        this.mobileNumber = mobileNumber;
    }

    // Overloaded constructor to initialize the User object with only the username
    public User(String userName) {
        this.userName = userName;
        this.mobileNumber = 0; // Default mobile number if not provided
    }

    // Getter method for username
    public String getUserName() {
        return userName;
    }

    // Getter method for mobile number
    public long getMobileNumber() {
        return mobileNumber;
    }
}
