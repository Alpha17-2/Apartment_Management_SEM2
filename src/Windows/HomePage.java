package Windows;
// HomePage.java
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomePage extends JFrame {

    public HomePage() {
        setTitle("Welcome to Apartment Management System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);

        JLabel welcomeLabel = new JLabel("Welcome to Apartment Management System");
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 16));
        welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JButton userButton = new JButton("User");
        JButton managerButton = new JButton("Manager");
        JButton adminButton = new JButton("Admin");

        userButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openLoginPage("User");
            }
        });

        managerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openLoginPage("Manager");
            }
        });

        adminButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openLoginPage("Admin");
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(3, 1, 0, 10));
        buttonPanel.add(userButton);
        buttonPanel.add(managerButton);
        buttonPanel.add(adminButton);

        setLayout(new BorderLayout());
        add(welcomeLabel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);
    }

    private void openLoginPage(String userType) {
        // Open the respective login page based on the user type
        if ("User".equals(userType)) {
            UserLoginWindow userLoginWindow = new UserLoginWindow();
            userLoginWindow.setVisible(true);
        } else if ("Manager".equals(userType)) {
            ManagerLoginWindow managerLoginWindow = new ManagerLoginWindow();
            managerLoginWindow.setVisible(true);
        } else if ("Admin".equals(userType)) {
            AdminLoginWindow adminLoginWindow = new AdminLoginWindow();
            adminLoginWindow.setVisible(true);
        }
        // You can customize this logic based on your application structure
    }
}
