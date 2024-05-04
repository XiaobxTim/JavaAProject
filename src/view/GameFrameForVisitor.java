package view;
import controller.GameController;
import util.ColorMap;

import javax.swing.*;
import java.awt.*;
public class GameFrameForVisitor extends JFrame{
    private GameController controller;
    private JButton restartBtn;

    private JLabel stepLabel;
    private GamePanel gamePanel;
    private JButton Right;
    private JButton Left;
    private JButton Up;
    private JButton Down;

    public GameFrameForVisitor(int width, int height) {
        this.setTitle("2048");
        this.setLayout(null);
        this.setSize(width, height);
        ColorMap.InitialColorMap();
        gamePanel = new GamePanel((int) (this.getHeight() * 0.8));
        gamePanel.setLocation(this.getHeight() / 15, this.getWidth() / 15);
        this.add(gamePanel);

        this.controller = new GameController(gamePanel, gamePanel.getModel());
        this.restartBtn = createButton("Restart", new Point(500, 150), 110, 50);
        this.stepLabel = createLabel("Start", new Font("serif", Font.ITALIC, 22), new Point(480, 50), 180, 50);
        gamePanel.setStepLabel(stepLabel);

        this.restartBtn.addActionListener(e -> {
            setVisible(false);
            controller.restartGameForVisitor();
            gamePanel.requestFocusInWindow();//enable key listener
        });
        //todo: add other button here
        this.Right=createButton("→",new Point(605,350),50,50);
        this.Left=createButton("←",new Point(505,350),50,50);
        this.Up=createButton("↑",new Point(555,300),50,50);
        this.Down=createButton("↓",new Point(555,350),50,50);

        this.Right.addActionListener(e -> {
            gamePanel.doMoveRight();
        });
        this.Left.addActionListener(e -> {
            gamePanel.doMoveLeft();
        });
        this.Up.addActionListener(e -> {
            gamePanel.doMoveUp();
        });
        this.Down.addActionListener(e -> {
            gamePanel.doMoveDown();
        });

        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
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
