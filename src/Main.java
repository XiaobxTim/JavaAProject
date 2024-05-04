import view.LoginFrame;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            LoginFrame loginframe = new LoginFrame(700, 500);
            loginframe.setVisible(true);
        });
    }
}
