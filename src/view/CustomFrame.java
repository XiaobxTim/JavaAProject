package view;
import controller.CustomController;
import controller.GameController;
import util.ColorMap;

import javax.swing.*;
import java.awt.*;
public class CustomFrame extends JFrame{
    private CustomController controller;
    private JButton restartBtn;
    private JButton loadBtn;
    private JButton saveBtn;
    private JLabel stepLabel;
    private JLabel scoreLabel;
    private JLabel maxscoreLabel;
    private CustomPanel gamePanel;
    private JButton Right;
    private JButton Left;
    private JButton Up;
    private JButton Down;

    public CustomFrame(int width, int height,int size,JFrame jFrame) {
        JFrame gameFrame=this;
        this.setTitle("2048");
        this.setLayout(null);
        this.setSize(width, height);
        ColorMap.InitialColorMap();
        gamePanel = new CustomPanel((int) (this.getHeight() * 0.8),size);
        gamePanel.setLocation(this.getHeight() / 15, this.getWidth() / 15);
        this.add(gamePanel);

        this.controller = new CustomController(gamePanel, gamePanel.getModel());
        this.restartBtn = createButton("Restart", new Point(500, 170), 110, 30);
        this.loadBtn = createButton("Load", new Point(500, 210), 110, 30);
        this.saveBtn = createButton("Save", new Point(500, 250), 110, 30);
        this.stepLabel = createLabel("Start", new Font("serif", Font.ITALIC, 22), new Point(480, 60), 180, 50);
        this.scoreLabel = createLabel("Score", new Font("serif", Font.ITALIC, 22), new Point(480, 120), 180, 50);
        this.maxscoreLabel = createLabel("Maxscore", new Font("serif", Font.ITALIC, 22), new Point(480, 0), 180, 50);
        gamePanel.setStepLabel(stepLabel);
        gamePanel.setScoreLabel(scoreLabel);
        gamePanel.setMaxscoreLabel(maxscoreLabel);

        this.restartBtn.addActionListener(e -> {
            restartBtn.setFocusable(true);
            setVisible(true);
            RestartCustom restartFrame=new RestartCustom(700,500, controller, gamePanel, gameFrame,jFrame);
            restartFrame.setVisible(true);
            restartBtn.setFocusable(false);
        });
        this.loadBtn.addActionListener(e -> {
            String string = JOptionPane.showInputDialog(this, "Input path:");
            System.out.println(string);
            gamePanel.requestFocusInWindow();//enable key listener
        });
        this.saveBtn.addActionListener(e -> {

        });
        //todo: add other button here
        this.Right=createButton("→",new Point(605,350),50,50);
        this.Left=createButton("←",new Point(505,350),50,50);
        this.Up=createButton("↑",new Point(555,300),50,50);
        this.Down=createButton("↓",new Point(555,350),50,50);

        this.Right.addActionListener(e -> {
            Right.setFocusable(true);
            gamePanel.doMoveRight();
            Right.setFocusable(false);
        });
        this.Left.addActionListener(e -> {
            Left.setFocusable(true);
            gamePanel.doMoveLeft();
            Left.setFocusable(false);
        });
        this.Up.addActionListener(e -> {
            Up.setFocusable(true);
            gamePanel.doMoveUp();
            Up.setFocusable(false);
        });
        this.Down.addActionListener(e -> {
            Down.setFocusable(true);
            gamePanel.doMoveDown();
            Down.setFocusable(false);
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
