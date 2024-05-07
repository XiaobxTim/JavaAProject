package view;

import controller.GameController;
import util.ColorMap;

import javax.swing.*;
import java.awt.*;

public class RestartFrameForVisitor extends JFrame{
    private JButton CancelBtn;
    private JButton ConfirmBtn;
    public RestartFrameForVisitor(int width, int height, GameController controller, GamePanel gamePanel, JFrame jFrame){
        this.setTitle("2048");
        this.setLayout(null);
        this.setSize(width, height);
        ColorMap.InitialColorMap();
        this.ConfirmBtn = createButton("Confirm", new Point(180,240),110 ,50);
        this.CancelBtn = createButton("Cancel", new Point(400,240),110 ,50);

        ConfirmBtn.addActionListener(e -> {
            ConfirmBtn.setFocusable(true);
            this.dispose();
            jFrame.setVisible(false);
            controller.restartGameForVisitor();
            gamePanel.requestFocusInWindow();
            ConfirmBtn.setFocusable(false);
        });
        CancelBtn.addActionListener(e -> {
            CancelBtn.setFocusable(true);
            this.dispose();
            CancelBtn.setFocusable(false);
        });

        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    }
    private JButton createButton(String name, Point location, int width, int height) {
        JButton button = new JButton(name);
        button.setLocation(location);
        button.setSize(width, height);
        this.add(button);
        return button;
    }

    private JLabel createLabel(String name, Font font, Point location, int width, int height) {
        JLabel label = new JLabel(name);
        label.setFont(font);
        label.setLocation(location);
        label.setSize(width, height);
        this.add(label);
        return label;
    }
}
