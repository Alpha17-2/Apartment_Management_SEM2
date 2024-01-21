package Windows;

// AdminDashboard.java
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminDashboard extends JFrame {

    public AdminDashboard() {
        setTitle("Admin Dashboard");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // Close the entire application on closing the dashboard
        setSize(800, 600);
        setLocationRelativeTo(null);

        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("User Management", createUserManagementPanel());
        tabbedPane.addTab("Apartment Management", createApartmentManagementPanel());
        tabbedPane.addTab("Lease Management", createLeaseManagementPanel());
        tabbedPane.addTab("Sales Management", createSalesManagementPanel());

        add(tabbedPane);
    }

    private JPanel createUserManagementPanel() {
        JPanel panel = new JPanel();
        // Add user management components and logic here
        return panel;
    }

    private JPanel createApartmentManagementPanel() {
        JPanel panel = new JPanel();
        // Add apartment management components and logic here
        return panel;
    }

    private JPanel createLeaseManagementPanel() {
        JPanel panel = new JPanel();
        // Add lease management components and logic here
        return panel;
    }

    private JPanel createSalesManagementPanel() {
        JPanel panel = new JPanel();
        // Add sales management components and logic here
        return panel;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                AdminDashboard adminDashboard = new AdminDashboard();
                adminDashboard.setVisible(true);
            }
        });
    }
}
