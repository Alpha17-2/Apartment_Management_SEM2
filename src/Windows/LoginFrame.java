package Windows;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Auth.AuthService;

public class LoginFrame extends JFrame {

    private JTextField usernameField;
    private JPasswordField passwordField;

    public LoginFrame() {
        setTitle("Login - Apartment Management System");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        initComponents();
    }

    private void initComponents() {
        // Create and customize your login GUI components here

        // Example: Create text fields for username and password
        usernameField = new JTextField(20);
        passwordField = new JPasswordField(20);

        // Example: Create labels for username and password
        JLabel usernameLabel = new JLabel("Username:");
        JLabel passwordLabel = new JLabel("Password:");

        // Example: Create a button to perform the login action
        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Perform authentication
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());

                AuthService.UserRole userRole = AuthService.authenticate(username, password);

                // Based on the user role, open the appropriate main window or show an error message
                handleLoginResult(userRole);
            }
        });

        // Example: Create a panel to hold the login components
        JPanel loginPanel = new JPanel(new GridLayout(3, 2));
        loginPanel.add(usernameLabel);
        loginPanel.add(usernameField);
        loginPanel.add(passwordLabel);
        loginPanel.add(passwordField);
        loginPanel.add(new JLabel()); // Empty label for spacing
        loginPanel.add(loginButton);

        // Layout manager (e.g., BorderLayout) and add components to the content pane
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(loginPanel, BorderLayout.CENTER);
    }

    private void handleLoginResult(AuthService.UserRole userRole) {
        // Handle the result of the login attempt
        switch (userRole) {
            case ADMIN:
            case MANAGER:
            case USER:
                // Open the main window based on the user role
                openMainWindow(userRole);
                break;
            default:
                // Show an error message for unsuccessful login
                JOptionPane.showMessageDialog(this, "Invalid username or password", "Login Error", JOptionPane.ERROR_MESSAGE);
                break;
        }
    }

    private void openMainWindow(AuthService.UserRole userRole) {
        // Example: Open the main window based on the user role
        MainWindow mainWindow = new MainWindow(usernameField.getText());
        mainWindow.setVisible(true);

        // Close the login window
        dispose();
    }

    public static void main(String[] args) {
        // Example: Create and display the login window
        javax.swing.SwingUtilities.invokeLater(() -> {
            LoginFrame loginFrame = new LoginFrame();
            loginFrame.setVisible(true);
        });
    }
}

