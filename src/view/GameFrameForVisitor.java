package view;
import controller.GameController;
import model.GridNumber;
import util.ColorMap;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class GameFrameForVisitor extends JFrame{
    private GridNumber model;
    private final int TotalTime=3600;
    private int countdownSeconds=TotalTime;
    private GameController controller;
    private JLabel scoreLabel;
    private JLabel stepLabel;
    private JLabel jl1;
    private JLabel jl2;
    private GamePanel gamePanel;
    private JMenuBar menuBar;

    public GameFrameForVisitor(int width, int height) {
        this.setTitle("Classic");
        this.setLayout(null);
        this.setSize(width, height);
        ColorMap.InitialColorMap();
        gamePanel = new GamePanel((int) (this.getHeight() * 0.65));
        gamePanel.setLocation(this.getHeight() / 15, this.getWidth() /4);
        this.add(gamePanel);

        menuBar=new JMenuBar();
        setJMenuBar(menuBar);
        JMenu menu=new JMenu("菜单");
        menuBar.add(menu);
        JMenuItem menuItem=new JMenuItem("调出方向小键盘");
        JMenuItem restart=new JMenuItem("restart");
        menu.add(menuItem);
        menu.add(restart);
        menuItem.setAccelerator(KeyStroke.getKeyStroke((char) KeyEvent.VK_D,KeyEvent.CTRL_DOWN_MASK));
        restart.setAccelerator(KeyStroke.getKeyStroke((char)KeyEvent.VK_R,KeyEvent.CTRL_DOWN_MASK));
        restart.addActionListener(e -> {
            setVisible(true);
            RestartFrameForVisitor restartFrameForVisitor=new RestartFrameForVisitor(700,500, controller, gamePanel, this);
            restartFrameForVisitor.setVisible(true);
        });
        menuItem.addActionListener(e -> {
            setVisible(true);
            DirectionFrame directionFrame=new DirectionFrame(300,300,gamePanel);
            directionFrame.setVisible(true);
        });

        this.controller = new GameController(gamePanel, gamePanel.getModel());
        this.jl1=createLabel("2048",new Font("serif",Font.ITALIC|Font.BOLD,42),new Point(20,20),100,50);
        this.jl2=createLabel("Join the numbers and get to the 2048 tile!",new Font("serif",Font.PLAIN,16),new Point(30,65),270,50);
        this.stepLabel = createLabel("Start", new Font("serif", Font.ITALIC|Font.BOLD, 20), new Point(300, 20), 150, 50);
        this.scoreLabel = createLabel("Score", new Font("serif", Font.ITALIC|Font.BOLD, 20), new Point(130, 20), 150, 50);
        gamePanel.setStepLabel(stepLabel);
        gamePanel.setScoreLabel(scoreLabel);


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
