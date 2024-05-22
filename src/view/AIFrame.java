package view;

import AI.AI;
import AI.GameState;
import controller.GameController;
import model.GridNumber;
import util.ColorMap;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;

public class AIFrame extends JFrame{
    private GameController controller;

    private JLabel stepLabel;
    private JLabel scoreLabel;
    private JLabel maxscoreLabel;
    private JLabel jl1;
    private JLabel jl2;
    private GamePanel gamePanel;
    private JMenuBar menuBar;
    private Image image;
    public JFrame jf;
    private GridNumber model;
    public AIFrame(int width, int height,JFrame jFrame) {
        jf=jFrame;
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
        this.setTitle("AI");
        this.setLayout(null);
        this.setSize(width, height);
        ColorMap.InitialColorMap();
        gamePanel = new GamePanel((int) (this.getHeight() * 0.65));
        gamePanel.setLocation(this.getHeight() / 15, this.getWidth() /4);
        this.add(gamePanel);
        this.model=gamePanel.getModel();
        menuBar=new JMenuBar();
        setJMenuBar(menuBar);
        JMenu menu=new JMenu("菜单");
        menuBar.add(menu);
        JMenuItem menuItem=new JMenuItem("调出方向小键盘");
        JMenuItem restart=new JMenuItem("restart");
        JMenuItem load=new JMenuItem("load");
        JMenuItem save=new JMenuItem("save");
        JMenuItem stop=new JMenuItem("stop");
        JMenuItem begin=new JMenuItem("begin");
        JMenuItem setting=new JMenuItem("setting");
        menu.add(menuItem);
        menu.add(restart);
        menu.add(load);
        menu.add(save);
        menu.add(stop);
        menu.add(begin);
        menuItem.setAccelerator(KeyStroke.getKeyStroke((char) KeyEvent.VK_D,KeyEvent.CTRL_DOWN_MASK));
        restart.setAccelerator(KeyStroke.getKeyStroke((char)KeyEvent.VK_R,KeyEvent.CTRL_DOWN_MASK));
        load.setAccelerator(KeyStroke.getKeyStroke((char)KeyEvent.VK_L,KeyEvent.CTRL_DOWN_MASK));
        save.setAccelerator(KeyStroke.getKeyStroke((char)KeyEvent.VK_S,KeyEvent.CTRL_DOWN_MASK));
        stop.setAccelerator(KeyStroke.getKeyStroke((char)KeyEvent.VK_S,KeyEvent.ALT_DOWN_MASK));
        begin.setAccelerator(KeyStroke.getKeyStroke((char)KeyEvent.VK_B,KeyEvent.ALT_DOWN_MASK));
        setting.setAccelerator(KeyStroke.getKeyStroke((char)KeyEvent.VK_S,KeyEvent.SHIFT_DOWN_MASK));
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
        setting.addActionListener(e ->{
            int aim= Integer.parseInt(JOptionPane.showInputDialog("Please input the aim of the game"));
            model.setAim(aim);
        });
        load.addActionListener(e -> {
            String string = JOptionPane.showInputDialog(this, "Input path:");
            System.out.println(string);
            gamePanel.requestFocusInWindow();
        });
        save.addActionListener(e -> {

        });
        Timer timer= new Timer(1000/60, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int[][] model2=new int[4][4];
                for (int i=0;i<model2.length;i++){
                    for (int j=0;j<model2[0].length;j++){
                        model2[i][j]=model.getNumber(i,j);
                    }
                }
                GameState model1=new GameState(model2);
                AI ai=new AI(model1);
                int direction=ai.getBestMove();
                switch (direction) {
                    case 0 -> gamePanel.doMoveUp();
                    case 1 -> gamePanel.doMoveRight();
                    case 2 -> gamePanel.doMoveDown();
                    default -> gamePanel.doMoveLeft();
                }
            }
        });
        timer.start();
        stop.addActionListener(e -> {
            timer.stop();
            gamePanel.setEnabled(false);
        });
        begin.addActionListener(e -> {
            timer.start();
            gamePanel.setEnabled(true);
            int[][] model2=new int[4][4];
            for (int i=0;i<model2.length;i++){
                for (int j=0;j<model2[0].length;j++){
                    model2[i][j]=model.getNumber(i,j);
                }
            }
            GameState model1=new GameState(model2);
            AI ai=new AI(model1);
            int direction=ai.getBestMove();
            switch (direction) {
                case 0 -> gamePanel.doMoveUp();
                case 1 -> gamePanel.doMoveRight();
                case 2 -> gamePanel.doMoveDown();
                default -> gamePanel.doMoveLeft();
            }
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
