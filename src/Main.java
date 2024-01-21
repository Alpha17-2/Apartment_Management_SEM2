import Windows.HomePage;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                HomePage homePage = new HomePage();
                homePage.setVisible(true);

                // Event listener to close the application when the homePage is closed
                homePage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            }
        });
    }
}
