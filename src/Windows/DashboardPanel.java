package Windows;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DashboardPanel extends JPanel {

    public DashboardPanel(String username) {
        initComponents(username);
    }

    private void initComponents(String username) {
        // Create and customize your dashboard GUI components here

        // Example: Create a label to welcome the user
        JLabel welcomeLabel = new JLabel("Welcome, " + username + "!");
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 20));

        // Example: Create buttons for dashboard actions
        JButton viewApartmentsButton = new JButton("View Apartments");
        JButton manageTenantsButton = new JButton("Manage Tenants");
        JButton viewReportsButton = new JButton("View Reports");

        // Add action listeners to the buttons (implement functionality accordingly)
        viewApartmentsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Implement view apartments functionality
                JOptionPane.showMessageDialog(DashboardPanel.this, "View Apartments Button Clicked");
            }
        });

        manageTenantsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Implement manage tenants functionality
                JOptionPane.showMessageDialog(DashboardPanel.this, "Manage Tenants Button Clicked");
            }
        });

        viewReportsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Implement view reports functionality
                JOptionPane.showMessageDialog(DashboardPanel.this, "View Reports Button Clicked");
            }
        });

        // Layout manager (e.g., GridLayout) and add components to the panel
        setLayout(new GridLayout(4, 1));
        add(welcomeLabel);
        add(viewApartmentsButton);
        add(manageTenantsButton);
        add(viewReportsButton);
    }

    public static void main(String[] args) {
        // Example: Create and display the dashboard panel
        javax.swing.SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Dashboard Example");
            frame.setSize(400, 300);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            DashboardPanel dashboardPanel = new DashboardPanel("Sample User");
            frame.add(dashboardPanel);

            frame.setVisible(true);
        });
    }
}
