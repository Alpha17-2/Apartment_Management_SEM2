package Windows;

// ManagerLoginWindow.java
import javax.swing.*;
import java.awt.*;

public class ManagerLoginWindow extends JFrame {

    public ManagerLoginWindow() {
        setTitle("Manager Login");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);  // Close only this window, not the entire application
        setSize(300, 200);
        setLocationRelativeTo(null);

        JLabel label = new JLabel("Manager Login Page");
        label.setHorizontalAlignment(SwingConstants.CENTER);

        add(label, BorderLayout.CENTER);
    }
}

