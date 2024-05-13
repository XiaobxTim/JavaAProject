package view;

import controller.GameController;
import util.ColorMap;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;

public class GameFrame extends JFrame {

    private GameController controller;

    private JLabel stepLabel;
    private JLabel scoreLabel;
    private JLabel maxscoreLabel;
    private JLabel jl1;
    private JLabel jl2;
    private GamePanel gamePanel;
    private JMenuBar menuBar;
    private Image image;

    public GameFrame(int width, int height,JFrame jFrame) {
        try {
            image= ImageIO.read(new File("src/微信图片_20240513134449.jpg"));
        }catch (IOException e){
            e.getStackTrace();
        }
        setContentPane(new JPanel(){
            @Override
            protected void paintComponent(Graphics g){
                super.paintComponent(g);
                g.drawImage(image,0,0,getWidth(),getHeight(),this);
            }
        });
        JFrame gameFrame=this;
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
        JMenuItem load=new JMenuItem("load");
        JMenuItem save=new JMenuItem("save");
        menu.add(menuItem);
        menu.add(restart);
        menu.add(load);
        menu.add(save);
        menuItem.setAccelerator(KeyStroke.getKeyStroke((char) KeyEvent.VK_D,KeyEvent.CTRL_DOWN_MASK));
        restart.setAccelerator(KeyStroke.getKeyStroke((char)KeyEvent.VK_R,KeyEvent.CTRL_DOWN_MASK));
        load.setAccelerator(KeyStroke.getKeyStroke((char)KeyEvent.VK_L,KeyEvent.CTRL_DOWN_MASK));
        save.setAccelerator(KeyStroke.getKeyStroke((char)KeyEvent.VK_S,KeyEvent.CTRL_DOWN_MASK));
        restart.addActionListener(e -> {
            setVisible(true);
            RestartFrame restartFrame=new RestartFrame(700,500, controller, gamePanel, gameFrame,jFrame);
            restartFrame.setVisible(true);
        });
        menuItem.addActionListener(e -> {
            setVisible(true);
            DirectionFrame directionFrame=new DirectionFrame(300,250,gamePanel);
            directionFrame.setVisible(true);
        });
        load.addActionListener(e -> {
            String string = JOptionPane.showInputDialog(this, "Input path:");
            System.out.println(string);
            gamePanel.requestFocusInWindow();
        });
        save.addActionListener(e -> {

        });

        this.controller = new GameController(gamePanel, gamePanel.getModel());
        this.jl1=createLabel("2048",new Font("serif",Font.ITALIC|Font.BOLD,35),new Point(20,20),70,50);
        this.jl2=createLabel("Join the numbers and get to the 2048 tile!",new Font("serif",Font.PLAIN,16),new Point(30,65),270,50);
        this.stepLabel = createLabel("Start", new Font("serif", Font.ITALIC|Font.BOLD, 15), new Point(190, 20), 80, 50);
        this.scoreLabel = createLabel("Score", new Font("serif", Font.ITALIC|Font.BOLD, 15), new Point(100, 20), 80, 50);
        this.maxscoreLabel = createLabel("Maxscore", new Font("serif", Font.ITALIC|Font.BOLD, 15), new Point(280, 20), 90, 50);
        gamePanel.setStepLabel(stepLabel);
        gamePanel.setScoreLabel(scoreLabel);
        gamePanel.setMaxscoreLabel(maxscoreLabel);

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