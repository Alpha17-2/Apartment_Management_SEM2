package Windows;
// AdminLoginWindow.java
import javax.swing.*;
import java.awt.*;
// AdminLoginWindow.java
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminLoginWindow extends JFrame {

    private JTextField emailField;
    private JPasswordField passwordField;

    public AdminLoginWindow() {
        setTitle("Admin Login");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);  // Close only this window, not the entire application
        setSize(300, 200);
        setLocationRelativeTo(null);

        JLabel emailLabel = new JLabel("Email:");
        emailField = new JTextField();

        JLabel passwordLabel = new JLabel("Password:");
        passwordField = new JPasswordField();

        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loginAdmin();
            }
        });

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2, 5, 5));
        panel.add(emailLabel);
        panel.add(emailField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(new JLabel());  // Empty label for spacing
        panel.add(loginButton);

        add(panel, BorderLayout.CENTER);
    }

    private void loginAdmin() {
        // Placeholder logic for admin login validation
        String enteredEmail = emailField.getText();
        char[] enteredPassword = passwordField.getPassword();
        String enteredPasswordString = new String(enteredPassword);

        // For simplicity, let's check if email is "admin@example.com" and password is "admin123"
        if ("admin@admin.com".equals(enteredEmail) && "root123".equals(enteredPasswordString)) {
            JOptionPane.showMessageDialog(this, "Admin Login Successful");
            // Open the Admin Dashboard (you need to implement this part)
            openAdminDashboard();
        } else {
            JOptionPane.showMessageDialog(this, "Invalid Email or Password");
        }

        // Clear the password field after validation
        passwordField.setText("");
    }

    private void openAdminDashboard() {
        // Placeholder logic for opening the Admin Dashboard
        AdminDashboard adminDashboard = new AdminDashboard();
        adminDashboard.setVisible(true);
        // You need to implement the AdminDashboard class
    }
}


