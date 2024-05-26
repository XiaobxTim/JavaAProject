package view;
import AI.AI;
import AI.GameState;
import controller.CustomController;
import controller.CustomControllerForVisitor;
import controller.GameController;
import model.GridNumber;
import util.ColorMap;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;

public class CustomFrameForVisitor extends JFrame{
    private CustomControllerForVisitor controller;
    private JButton restartBtn;

    private JLabel stepLabel;
    private JLabel scoreLabel;
    private JLabel jl1;
    private JLabel jl2;
    private CustomPanelForVisitor gamePanel;
    private JMenuBar menuBar;
    private Image image;
    private GridNumber model;
    private int count;

    public CustomFrameForVisitor(int width, int height,int size) {
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
        this.setTitle("Custom");
        this.setLayout(null);
        this.setSize(width, height);
        ColorMap.InitialColorMap();
        gamePanel = new CustomPanelForVisitor((int) (this.getHeight() * 0.65),size);
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
            ClickSound.playSound(getClass(),  "ClickButton.wav");
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
        back.addActionListener(e -> {
            ClickSound.playSound(getClass(),  "ClickButton.wav");
            for (int i=0;i<4;i++){
                for (int j=0;j<4;j++){
                    model.setNumber(i,j,model.getNum(i,j));
                    gamePanel.updateGridsNumber();
                }
            }
            model.setScore(model.getS());
            gamePanel.updateScoreAndStep();
        });
        restart.addActionListener(e -> {
            ClickSound.playSound(getClass(),  "ClickButton.wav");
            setVisible(true);
            RestartCustomForVisitor restartFrameForVisitor=new RestartCustomForVisitor(700,500, controller, gamePanel, this);
            restartFrameForVisitor.setVisible(true);
        });
        menuItem.addActionListener(e -> {
            ClickSound.playSound(getClass(),  "ClickButton.wav");
            setVisible(true);
            CustomDirectionForVisitor customDirection=new CustomDirectionForVisitor(300,250,gamePanel);
            customDirection.setVisible(true);
        });
        setting.addActionListener(e ->{
            ClickSound.playSound(getClass(),  "ClickButton.wav");
            int aim= Integer.parseInt(JOptionPane.showInputDialog("Please input the aim of the game"));
            model.setAim(aim);
        });
        stop.addActionListener(e -> {
            ClickSound.playSound(getClass(),  "ClickButton.wav");
            gamePanel.setEnabled(false);
        });
        begin.addActionListener(e -> {
            ClickSound.playSound(getClass(),  "ClickButton.wav");
            gamePanel.setEnabled(true);
        });

        this.controller = new CustomControllerForVisitor(gamePanel, gamePanel.getModel());
        this.jl1=createLabel("2048",new Font("serif",Font.ITALIC|Font.BOLD,42),new Point(20,20),100,50);
        this.jl2=createLabel("Join the numbers and get to the 2048 tile!",new Font("serif",Font.PLAIN,16),new Point(30,65),270,50);
        this.stepLabel = createLabel("Start", new Font("serif", Font.ITALIC|Font.BOLD, 20), new Point(300, 20), 150, 50);
        this.scoreLabel = createLabel("Score", new Font("serif", Font.ITALIC|Font.BOLD, 20), new Point(130, 20), 150, 50);
        gamePanel.setStepLabel(stepLabel);
        gamePanel.setScoreLabel(scoreLabel);
        jl1.setForeground(Color.BLACK);
        jl2.setForeground(Color.BLACK);
        scoreLabel.setForeground(Color.BLACK);
        stepLabel.setForeground(Color.BLACK);

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
