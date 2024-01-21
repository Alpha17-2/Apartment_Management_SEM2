package Windows;
// UserLoginWindow.java
import Auth.AuthService;
import DB_Services.AccountDAO;
import Models.Account;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserLoginWindow extends JFrame {

    private JTextField emailField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton registerButton;

    private JTextField nameField;
    private JTextField registerEmailField;
    private JPasswordField registerPasswordField;

    public UserLoginWindow() {
        setTitle("User Login");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(300, 200);
        setLocationRelativeTo(null);

        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Login", createLoginPanel());
        tabbedPane.addTab("Register", createRegisterPanel());

        add(tabbedPane);
    }

    private JPanel createLoginPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2, 5, 5));

        JLabel emailLabel = new JLabel("Email:");
        emailField = new JTextField();

        JLabel passwordLabel = new JLabel("Password:");
        passwordField = new JPasswordField();

        loginButton = new JButton("Login");
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                login();
            }
        });

        panel.add(emailLabel);
        panel.add(emailField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(new JLabel());
        panel.add(loginButton);

        return panel;
    }

    private JPanel createRegisterPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 2, 5, 5));

        JLabel nameLabel = new JLabel("Name:");
        nameField = new JTextField();

        JLabel registerEmailLabel = new JLabel("Email:");
        registerEmailField = new JTextField();

        JLabel registerPasswordLabel = new JLabel("Password:");
        registerPasswordField = new JPasswordField();

        registerButton = new JButton("Register");
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                register();
            }
        });

        panel.add(nameLabel);
        panel.add(nameField);
        panel.add(registerEmailLabel);
        panel.add(registerEmailField);
        panel.add(registerPasswordLabel);
        panel.add(registerPasswordField);
        panel.add(new JLabel());
        panel.add(registerButton);

        return panel;
    }

    private void login() {
        String email = emailField.getText();
        char[] passwordChars = passwordField.getPassword();
        String password = new String(passwordChars);

        if (email.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in all login fields.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Use AccountDAO to check login credentials
        AccountDAO accountDAO = new AccountDAO();
        AuthService.UserRole userRole = AuthService.authenticate(email, password);

        if (userRole == AuthService.UserRole.USER) {
            // Navigate to the User Dashboard or perform any other action for normal users
            // Example: openUserDashboard();
            JOptionPane.showMessageDialog(this, "Login successful. Welcome, User!", "Success", JOptionPane.INFORMATION_MESSAGE);
        } else {
            // Invalid credentials or role mismatch
            JOptionPane.showMessageDialog(this, "Invalid credentials. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }


    private void register() {
        String name = nameField.getText();
        String email = registerEmailField.getText();
        char[] passwordChars = registerPasswordField.getPassword();
        String password = new String(passwordChars);

        if (name.isEmpty() || email.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in all registration fields.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Create an Account object
        Account newAccount = new Account();
        newAccount.setName(name);
        newAccount.setEmail(email);
        newAccount.setPassword(password);
        newAccount.setRole("User");  // Set the default role for new users

        // Use AccountDAO to create a new user
        AccountDAO accountDAO = new AccountDAO(/* initialize with your connection */);
        boolean registrationSuccess = accountDAO.createNewUser(newAccount);

        if (registrationSuccess) {
            JOptionPane.showMessageDialog(this, "Registration successful. You can now login.", "Success", JOptionPane.INFORMATION_MESSAGE);
            // Optionally clear registration fields after successful registration
            nameField.setText("");
            registerEmailField.setText("");
            registerPasswordField.setText("");
        } else {
            JOptionPane.showMessageDialog(this, "Registration failed. User with this email already exists.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    private void handleLoginResult(AuthService.UserRole userRole) {
        // Handle the login result based on the user role
        switch (userRole) {
            case ADMIN:
                // Navigate to the Admin Dashboard
                // Example: openAdminDashboard();
                break;
            case MANAGER:
                // Navigate to the Manager Dashboard
                // Example: openManagerDashboard();
                break;
            case USER:
                // Navigate to the User Dashboard
                // Example: openUserDashboard();
                break;
            default:
                JOptionPane.showMessageDialog(this, "Invalid user role.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                UserLoginWindow userLoginWindow = new UserLoginWindow();
                userLoginWindow.setVisible(true);
            }
        });
    }
}
