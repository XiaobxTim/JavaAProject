package view;
import AI.AI;
import AI.GameState;
import controller.TimeLimitController;
import model.GridNumber;
import util.ColorMap;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.stream.Collectors;

public class TimeLimitFrame extends JFrame{
    private GridNumber model;
    private final int TotalTime=1800;
    private int countdownSeconds=TotalTime;
    private TimeLimitController controller;
    private JLabel stepLabel;
    private JLabel scoreLabel;
    private JLabel maxscoreLabel;
    private JLabel time;
    private JLabel jl1;
    private JLabel jl2;
    private TimeLimitPanel gamePanel;
    private JMenuBar menuBar;
    private Image image;
    private int count;
    public TimeLimitFrame(int width, int height,JFrame jFrame,String account) {
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
        this.setTitle("TimeLimit");
        this.setLayout(null);
        this.setSize(width, height);
        ColorMap.InitialColorMap();
        gamePanel = new TimeLimitPanel((int) (this.getHeight() * 0.65),account);
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
        JMenuItem back=new JMenuItem("back");
        JMenuItem Hint=new JMenuItem("Hint");
        menu.add(menuItem);
        menu.add(restart);
        menu.add(load);
        menu.add(save);
        menu.add(stop);
        menu.add(begin);
        menu.add(back);
        menu.add(Hint);
        menuItem.setAccelerator(KeyStroke.getKeyStroke((char) KeyEvent.VK_D,KeyEvent.CTRL_DOWN_MASK));
        restart.setAccelerator(KeyStroke.getKeyStroke((char)KeyEvent.VK_R,KeyEvent.CTRL_DOWN_MASK));
        load.setAccelerator(KeyStroke.getKeyStroke((char)KeyEvent.VK_L,KeyEvent.CTRL_DOWN_MASK));
        save.setAccelerator(KeyStroke.getKeyStroke((char)KeyEvent.VK_S,KeyEvent.CTRL_DOWN_MASK));
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
        restart.addActionListener(e -> {
            setVisible(true);
            RestartTimeLimit restartTimeLimit=new RestartTimeLimit(700,500, controller, gamePanel, gameFrame,jFrame);
            restartTimeLimit.setVisible(true);
        });
        menuItem.addActionListener(e -> {
            setVisible(true);
            TimeLimitDirection timeLimitDirection=new TimeLimitDirection(300,250,gamePanel);
            timeLimitDirection.setVisible(true);
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

        this.controller = new TimeLimitController(gamePanel, gamePanel.getModel());
        this.jl1=createLabel("2048",new Font("serif",Font.ITALIC|Font.BOLD,35),new Point(20,20),70,50);
        this.jl2=createLabel("Join the numbers and get to the 2048 tile!",new Font("serif",Font.PLAIN,15),new Point(30,65),270,50);
        this.time = createLabel("time", new Font("serif", Font.ITALIC|Font.BOLD, 15), new Point(190, 20), 80, 50);
        this.scoreLabel = createLabel("Score", new Font("serif", Font.ITALIC|Font.BOLD, 15), new Point(100, 20), 80, 50);
        this.maxscoreLabel = createLabel("Maxscore", new Font("serif", Font.ITALIC|Font.BOLD, 15), new Point(280, 20), 90, 50);
        gamePanel.setStepLabel(stepLabel);
        gamePanel.setScoreLabel(scoreLabel);
        gamePanel.setMaxscoreLabel(maxscoreLabel);

        javax.swing.Timer timer = new javax.swing.Timer(1000, e -> {
            if (countdownSeconds > 0) {
                countdownSeconds--;
                time.setText("Time: " + countdownSeconds + "s");
            } else {
                ((Timer) e.getSource()).stop();
                time.setText("Time's up!");
                gamePanel.setEnabled(false);
                File TimeLimitFile = new File("src/" + account + "_TimeLimitMode.txt");
                if (TimeLimitFile.exists()){
                    try (FileWriter fileWriter = new FileWriter(TimeLimitFile,true)) {
                        fileWriter.write(Integer.toString(model.getScore()));
                        fileWriter.write(System.lineSeparator());
                    } catch (IOException exception) {
                        exception.printStackTrace();
                    }
                    String filePath = "src/" + account + "_TimeLimitMode.txt";
                    java.util.List<Integer> numbers = null;
                    try {
                        numbers = Files.lines(Paths.get(filePath)).map(line -> line.trim()).filter(line -> !line.isEmpty()).map(Integer::parseInt).collect(Collectors.toList());
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    Collections.sort(numbers,Collections.reverseOrder());
                    try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(filePath))) {
                        for (Integer num : numbers) {
                            writer.write(num.toString());
                            writer.newLine();
                        }
                    } catch (IOException er) {
                        er.printStackTrace();
                    }
                }
                gameFrame.setVisible(false);
                FailureFrame failureFrame=new FailureFrame(400,500,model.getScore(),account);
                failureFrame.setVisible(true);
            }
            if (gamePanel.getModel().gameEnd()){
                ((Timer)e.getSource()).stop();
            }
        });

        // 启动Timer
        timer.start();
        stop.addActionListener(e -> {
            gamePanel.setEnabled(false);
            timer.stop();
        });
        begin.addActionListener(e -> {
            gamePanel.setEnabled(true);
            timer.start();
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
