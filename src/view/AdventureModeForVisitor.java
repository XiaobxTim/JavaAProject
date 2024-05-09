package view;
import controller.AdventureController;
import controller.GameController;
import util.ColorMap;

import javax.swing.*;
import java.awt.*;
public class AdventureModeForVisitor extends JFrame{
    private AdventureController controller;
    private JButton restartBtn;

    private JLabel stepLabel;
    private JLabel scoreLabel;
    private AdventurePanel adventurePanel;
    private JButton Right;
    private JButton Left;
    private JButton Up;
    private JButton Down;

    public AdventureModeForVisitor(int width, int height) {
        this.setTitle("2048");
        this.setLayout(null);
        this.setSize(width, height);
        ColorMap.InitialColorMap();
        adventurePanel = new AdventurePanel((int) (this.getHeight() * 0.8));
        adventurePanel.setLocation(this.getHeight() / 15, this.getWidth() / 15);
        this.add(adventurePanel);

        this.controller = new AdventureController(adventurePanel, adventurePanel.getModel());
        this.restartBtn = createButton("Restart", new Point(500, 170), 110, 50);
        this.stepLabel = createLabel("Start", new Font("serif", Font.ITALIC, 22), new Point(480, 60), 180, 50);
        this.scoreLabel = createLabel("Score", new Font("serif", Font.ITALIC, 22), new Point(480, 120), 180, 50);
        adventurePanel.setStepLabel(stepLabel);
        adventurePanel.setScoreLabel(scoreLabel);

        this.restartBtn.addActionListener(e -> {
            restartBtn.setFocusable(true);
            setVisible(true);
            RestartAdventureForVisitor restartAdventureForVisitor=new RestartAdventureForVisitor(700,500, controller, adventurePanel, this);
            restartAdventureForVisitor.setVisible(true);
            restartBtn.setFocusable(false);
        });
        //todo: add other button here
        this.Right=createButton("→",new Point(605,350),50,50);
        this.Left=createButton("←",new Point(505,350),50,50);
        this.Up=createButton("↑",new Point(555,300),50,50);
        this.Down=createButton("↓",new Point(555,350),50,50);

        this.Right.addActionListener(e -> {
            Right.setFocusable(true);
            adventurePanel.doMoveRight();
            Right.setFocusable(false);
        });
        this.Left.addActionListener(e -> {
            Left.setFocusable(true);
            adventurePanel.doMoveLeft();
            Left.setFocusable(false);
        });
        this.Up.addActionListener(e -> {
            Up.setFocusable(true);
            adventurePanel.doMoveUp();
            Up.setFocusable(false);
        });
        this.Down.addActionListener(e -> {
            Down.setFocusable(true);
            adventurePanel.doMoveDown();
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