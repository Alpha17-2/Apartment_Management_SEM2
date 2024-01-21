package Windows;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class MainWindow extends JFrame {

    public MainWindow(String username) {
        setTitle("Apartment Management System - Welcome, " + username);
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        initComponents();
    }

    private void initComponents() {
        // Create and customize your GUI components here

        // Example: Create a simple button to log out
        JButton logoutButton = new JButton("Logout");
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Perform logout actions
                // For simplicity, just close the main window in this example
                dispose();
                // You might want to show the login window again
                new LoginFrame().setVisible(true);
            }
        });

        // Example: Create a label to display information
        JLabel welcomeLabel = new JLabel("Welcome to the Apartment Management System!");

        // Example: Create a panel for navigation buttons
        JPanel navigationPanel = new JPanel(new FlowLayout());

        // Example: Create buttons for different functionalities
        JButton rentButton = new JButton("Rent Apartment");
        JButton sellButton = new JButton("Sell Apartment");
        JButton listForSaleButton = new JButton("List for Sale");
        JButton viewReportsButton = new JButton("View Reports");

        // Add action listeners to the buttons (implement functionality accordingly)
        rentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Implement rent functionality
            }
        });

        sellButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Implement sell functionality
            }
        });

        listForSaleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Implement list for sale functionality
            }
        });

        viewReportsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Implement view reports functionality
            }
        });

        // Add buttons to the navigation panel
        navigationPanel.add(rentButton);
        navigationPanel.add(sellButton);
        navigationPanel.add(listForSaleButton);
        navigationPanel.add(viewReportsButton);

        // Layout manager (e.g., BorderLayout) and add components to the content pane
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(welcomeLabel, BorderLayout.NORTH);
        getContentPane().add(navigationPanel, BorderLayout.CENTER);
        getContentPane().add(logoutButton, BorderLayout.SOUTH);
    }

    public static void main(String[] args) {
        // Example: Create and display the main window
        javax.swing.SwingUtilities.invokeLater(() -> {
            MainWindow mainWindow = new MainWindow("Sample User");
            mainWindow.setVisible(true);
        });
    }
}
