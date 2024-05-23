package view;
import AI.AI;
import AI.GameState;
import controller.GameControllerForVisitor;
import model.GridNumber;
import util.ColorMap;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;

public class GameFrameForVisitor extends JFrame{
    private GridNumber model;
    private final int TotalTime=3600;
    private int countdownSeconds=TotalTime;
    private GameControllerForVisitor controller;
    private JLabel scoreLabel;
    private JLabel stepLabel;
    private JLabel jl1;
    private JLabel jl2;
    private GamePanelForVisitor gamePanel;
    private JMenuBar menuBar;
    private Image image;
    private int count;

    public GameFrameForVisitor(int width, int height) {
        JFrame gameFrame=this;
        count=0;
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
        this.setTitle("Classic");
        this.setLayout(null);
        this.setSize(width, height);
        ColorMap.InitialColorMap();
        gamePanel = new GamePanelForVisitor((int) (this.getHeight() * 0.65));
        gamePanel.setLocation(this.getHeight() / 15, this.getWidth() /4);
        this.add(gamePanel);
        this.model=gamePanel.getModel();
        menuBar=new JMenuBar();
        setJMenuBar(menuBar);
        JMenu menu=new JMenu("菜单");
        menuBar.add(menu);
        JMenuItem menuItem=new JMenuItem("调出方向小键盘");
        JMenuItem restart=new JMenuItem("restart");
        JMenuItem stop=new JMenuItem("stop");
        JMenuItem begin=new JMenuItem("begin");
        JMenuItem setting=new JMenuItem("setting");
        JMenuItem back=new JMenuItem("back");
        JMenuItem Hint=new JMenuItem("Hint");
        menu.add(menuItem);
        menu.add(restart);
        menu.add(stop);
        menu.add(begin);
        menu.add(setting);
        menu.add(back);
        menu.add(Hint);
        menuItem.setAccelerator(KeyStroke.getKeyStroke((char) KeyEvent.VK_D,KeyEvent.CTRL_DOWN_MASK));
        restart.setAccelerator(KeyStroke.getKeyStroke((char)KeyEvent.VK_R,KeyEvent.CTRL_DOWN_MASK));
        stop.setAccelerator(KeyStroke.getKeyStroke((char)KeyEvent.VK_S,KeyEvent.ALT_DOWN_MASK));
        begin.setAccelerator(KeyStroke.getKeyStroke((char)KeyEvent.VK_B,KeyEvent.ALT_DOWN_MASK));
        setting.setAccelerator(KeyStroke.getKeyStroke((char)KeyEvent.VK_S,KeyEvent.SHIFT_DOWN_MASK));
        back.setAccelerator(KeyStroke.getKeyStroke((char)KeyEvent.VK_Z,KeyEvent.CTRL_DOWN_MASK));
        Hint.setAccelerator(KeyStroke.getKeyStroke((char)KeyEvent.VK_H,KeyEvent.CTRL_DOWN_MASK));
        Hint.addActionListener(e -> {
            gamePanel.setEnabled(true);
            count++;
            if (count<=3){
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
                    case 0 -> JOptionPane.showMessageDialog(null,"Up");
                    case 1 -> JOptionPane.showMessageDialog(null,"Right");
                    case 2 -> JOptionPane.showMessageDialog(null,"Down");
                    default -> JOptionPane.showMessageDialog(null,"Left");
                }
            }else {
                JOptionPane.showMessageDialog(null,"No chances");
            }
        });
        restart.addActionListener(e -> {
            setVisible(true);
            RestartFrameForVisitor restartFrameForVisitor=new RestartFrameForVisitor(700,500, controller, gamePanel, this);
            restartFrameForVisitor.setVisible(true);
        });
        menuItem.addActionListener(e -> {
            setVisible(true);
            DirectionFrameForVisitor directionFrame=new DirectionFrameForVisitor(300,250,gamePanel);
            directionFrame.setVisible(true);
        });
        stop.addActionListener(e -> {
            gamePanel.setEnabled(false);
        });
        begin.addActionListener(e -> {
            gamePanel.setEnabled(true);
        });
        setting.addActionListener(e ->{
            int aim= Integer.parseInt(JOptionPane.showInputDialog("Please input the aim of the game"));
            model.setAim(aim);
        });
        back.addActionListener(e -> {
            for (int i=0;i<4;i++){
                for (int j=0;j<4;j++){
                    model.setNumber(i,j,model.getNum(i,j));
                    gamePanel.updateGridsNumber();
                }
            }
            model.setScore(model.getS());
            gamePanel.updateScoreAndStep();
        });

        this.controller = new GameControllerForVisitor(gamePanel, gamePanel.getModel());
        this.jl1=createLabel("2048",new Font("serif",Font.ITALIC|Font.BOLD,42),new Point(20,20),100,50);
        this.jl2=createLabel("Join the numbers and get to the 2048 tile!",new Font("serif",Font.PLAIN,16),new Point(30,65),270,50);
        this.stepLabel = createLabel("Start", new Font("serif", Font.ITALIC|Font.BOLD, 20), new Point(300, 20), 150, 50);
        this.scoreLabel = createLabel("Score", new Font("serif", Font.ITALIC|Font.BOLD, 20), new Point(130, 20), 150, 50);
        jl1.setForeground(Color.BLACK);
        jl2.setForeground(Color.BLACK);
        scoreLabel.setForeground(Color.BLACK);
        stepLabel.setForeground(Color.BLACK);
        gamePanel.setStepLabel(stepLabel);
        gamePanel.setScoreLabel(scoreLabel);

        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        gameFrame.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                centerWindow(gameFrame);
            }
        });
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
    private static void centerWindow(Window window) {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension windowSize = window.getSize();

        // 计算新位置，使得窗体在屏幕中央
        int x = (screenSize.width - windowSize.width) / 2;
        int y = (screenSize.height - windowSize.height) / 2;

        // 设置窗体的新位置
        window.setLocation(x, y);
    }

}
