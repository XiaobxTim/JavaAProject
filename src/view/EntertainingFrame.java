package view;

import AI.AI;
import AI.GameState;
import controller.EntertainingController;
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

public class EntertainingFrame extends JFrame {
    private EntertainingController controller;
    private GridNumber model;
    private JLabel CoinLabel;
    private JLabel scoreLabel;
    private JLabel maxscoreLabel;
    private JLabel jl1;
    private JLabel jl2;
    private EntertainingPanel gamePanel;
    private JMenuBar menuBar;
    private Image image;
    public JFrame jf;
    private int count;
    public EntertainingFrame(int width, int height,JFrame jFrame,String account) {
        jf=jFrame;
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
        JFrame gameFrame=this;
        this.setTitle("Classic");
        this.setLayout(null);
        this.setSize(width, height);
        ColorMap.InitialColorMap();
        gamePanel = new EntertainingPanel((int) (this.getHeight() * 0.65),account);
        gamePanel.setLocation(this.getHeight() / 15, this.getWidth() /4);
        this.add(gamePanel);
        this.model=gamePanel.getModel();
        menuBar=new JMenuBar();
        setJMenuBar(menuBar);
        JMenu menu=new JMenu("菜单");
        menuBar.add(menu);
        JMenuItem menuItem=new JMenuItem("调出方向小键盘");
        JMenu props=new JMenu("Props");
        JMenuItem restart=new JMenuItem("restart");
        JMenuItem load=new JMenuItem("load");
        JMenuItem save=new JMenuItem("save");
        JMenuItem change=new JMenuItem("change");
        JMenuItem crash=new JMenuItem("crash");
        JMenuItem stop=new JMenuItem("stop");
        JMenuItem begin=new JMenuItem("begin");
        JMenuItem setting=new JMenuItem("setting");
        JMenuItem Hint=new JMenuItem("Hint");
        menu.add(menuItem);
        menu.add(props);
        menu.add(restart);
        menu.add(load);
        menu.add(save);
        props.add(change);
        props.add(crash);
        menu.add(stop);
        menu.add(begin);
        menu.add(Hint);
        menuItem.setAccelerator(KeyStroke.getKeyStroke((char) KeyEvent.VK_D,KeyEvent.CTRL_DOWN_MASK));
        restart.setAccelerator(KeyStroke.getKeyStroke((char)KeyEvent.VK_R,KeyEvent.CTRL_DOWN_MASK));
        load.setAccelerator(KeyStroke.getKeyStroke((char)KeyEvent.VK_L,KeyEvent.CTRL_DOWN_MASK));
        save.setAccelerator(KeyStroke.getKeyStroke((char)KeyEvent.VK_S,KeyEvent.CTRL_DOWN_MASK));
        change.setAccelerator(KeyStroke.getKeyStroke((char)KeyEvent.VK_C,KeyEvent.CTRL_DOWN_MASK));
        crash.setAccelerator(KeyStroke.getKeyStroke((char)KeyEvent.VK_C,KeyEvent.ALT_DOWN_MASK));
        stop.setAccelerator(KeyStroke.getKeyStroke((char)KeyEvent.VK_S,KeyEvent.ALT_DOWN_MASK));
        begin.setAccelerator(KeyStroke.getKeyStroke((char)KeyEvent.VK_B,KeyEvent.ALT_DOWN_MASK));
        setting.setAccelerator(KeyStroke.getKeyStroke((char)KeyEvent.VK_S,KeyEvent.SHIFT_DOWN_MASK));
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
            RestartEntertaining restartEntertaining=new RestartEntertaining(700,500, controller, gamePanel, gameFrame,jFrame);
            restartEntertaining.setVisible(true);
        });
        menuItem.addActionListener(e -> {
            setVisible(true);
            EntertainingDirection entertainingDirection=new EntertainingDirection(300,250,gamePanel);
            entertainingDirection.setVisible(true);
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
        change.addActionListener(e -> {
            if (model.getCoin()>=30){
                int i= Integer.parseInt(JOptionPane.showInputDialog("input row"));
                int j= Integer.parseInt(JOptionPane.showInputDialog("input col"));
                if (gamePanel.getModel().getNumber(i,j)!=' '&& model.getCoin()>=30){
                    int str= Integer.parseInt(JOptionPane.showInputDialog("在2，4，8，16中选一个替换"));
                    gamePanel.getModel().setNumber(i,j,str);
                    gamePanel.updateGridsNumber();
                    int Coin=model.getCoin()-30;
                    model.setCoin(Coin);
                }
            }else {
                JOptionPane.showMessageDialog(null,"You don't have enough coins");
            }
        });
        crash.addActionListener(e -> {
            if (model.getCoin()>=30){
                int i= Integer.parseInt(JOptionPane.showInputDialog("input row"));
                int j= Integer.parseInt(JOptionPane.showInputDialog("input col"));
                if (gamePanel.getModel().getNumber(i,j)!=' '){
                    gamePanel.getModel().setNumber(i,j,0);
                    gamePanel.updateGridsNumber();
                    int Coin=model.getCoin()-30;
                    model.setCoin(Coin);
                }
            }else {
                JOptionPane.showMessageDialog(null,"You don't have enough coins");
            }
        });
        save.addActionListener(e -> {

        });
        stop.addActionListener(e -> {
            gamePanel.setEnabled(false);
        });
        begin.addActionListener(e -> {
            gamePanel.setEnabled(true);
        });

        this.controller = new EntertainingController(gamePanel, gamePanel.getModel());
        this.jl1=createLabel("2048",new Font("serif",Font.ITALIC|Font.BOLD,35),new Point(20,20),70,50);
        this.jl2=createLabel("Join the numbers and get to the 2048 tile!",new Font("serif",Font.PLAIN,16),new Point(30,65),270,50);
        this.CoinLabel = createLabel("Coin", new Font("serif", Font.ITALIC|Font.BOLD, 15), new Point(280, 20), 100, 50);
        this.scoreLabel = createLabel("Score", new Font("serif", Font.ITALIC|Font.BOLD, 15), new Point(150, 15), 100, 25);
        this.maxscoreLabel = createLabel("Maxscore", new Font("serif", Font.ITALIC|Font.BOLD, 15), new Point(150, 45), 100, 25);
        gamePanel.setCoinLabel(CoinLabel);
        gamePanel.setScoreLabel(scoreLabel);
        gamePanel.setMaxscoreLabel(maxscoreLabel);

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
