package Auth;


public class AuthService {
    private static final String ADMIN_USERNAME = "admin";
    private static final String ADMIN_PASSWORD = "admin";
    private static final String MANAGER_USERNAME = "manager";
    private static final String MANAGER_PASSWORD = "manager";

    // Enum to represent user roles
    public enum UserRole {
        ADMIN, MANAGER, USER
    }

    // Authenticate the user based on provided credentials
    public static UserRole authenticate(String username, String password) {
        if (ADMIN_USERNAME.equals(username) && ADMIN_PASSWORD.equals(password)) {
            return UserRole.ADMIN;
        } else if (MANAGER_USERNAME.equals(username) && MANAGER_PASSWORD.equals(password)) {
            return UserRole.MANAGER;
        } else {
            // Add more conditions for additional user roles or check against a database
            return UserRole.USER;
        }
    }
}

