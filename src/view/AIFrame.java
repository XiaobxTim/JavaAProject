package view;

import AI.AI;
import AI.GameState;
import controller.AIController;
import controller.GameController;
import model.GridNumber;
import util.ColorMap;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Stream;

public class AIFrame extends JFrame{
    private AIController controller;

    private JLabel stepLabel;
    private JLabel scoreLabel;
    private JLabel maxscoreLabel;
    private JLabel jl1;
    private JLabel jl2;
    private AIPanel gamePanel;
    private JMenuBar menuBar;
    private Image image;
    public JFrame jf;
    private GridNumber model;
    private Timer timer;
    public AIFrame(int width, int height,JFrame jFrame,String account) {
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
        gamePanel = new AIPanel((int) (this.getHeight() * 0.65),account);
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
        JMenuItem Rank=new JMenuItem("Rank");
        menu.add(menuItem);
        menu.add(restart);
        menu.add(load);
        menu.add(save);
        menu.add(stop);
        menu.add(begin);
        menu.add(setting);
        menu.add(Rank);
        JMenuItem Dispose=new JMenuItem("Dispose");
        menu.add(Dispose);
        menuItem.setAccelerator(KeyStroke.getKeyStroke((char) KeyEvent.VK_D,KeyEvent.CTRL_DOWN_MASK));
        restart.setAccelerator(KeyStroke.getKeyStroke((char)KeyEvent.VK_R,KeyEvent.CTRL_DOWN_MASK));
        load.setAccelerator(KeyStroke.getKeyStroke((char)KeyEvent.VK_L,KeyEvent.CTRL_DOWN_MASK));
        save.setAccelerator(KeyStroke.getKeyStroke((char)KeyEvent.VK_S,KeyEvent.CTRL_DOWN_MASK));
        stop.setAccelerator(KeyStroke.getKeyStroke((char)KeyEvent.VK_S,KeyEvent.ALT_DOWN_MASK));
        begin.setAccelerator(KeyStroke.getKeyStroke((char)KeyEvent.VK_B,KeyEvent.ALT_DOWN_MASK));
        setting.setAccelerator(KeyStroke.getKeyStroke((char)KeyEvent.VK_S,KeyEvent.SHIFT_DOWN_MASK));
        Rank.setAccelerator(KeyStroke.getKeyStroke((char)KeyEvent.VK_R,KeyEvent.SHIFT_DOWN_MASK));
        timer= new Timer(1000/60, e -> {
            int number=model.FindMaxNumber();
            if (number < model.getAim()){
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
                    case 0 -> {
                        try {
                            gamePanel.doMoveUp();
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                    case 1 -> {
                        try {
                            gamePanel.doMoveRight();
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                    case 2 -> {
                        try {
                            gamePanel.doMoveDown();
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                    default -> {
                        try {
                            gamePanel.doMoveLeft();
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                }
            }else {
                timer.stop();
            }
        });
        timer.start();

        stop.addActionListener(e -> {
            ClickSound.playSound(getClass(),  "ClickButton.wav");
            timer.stop();
            gamePanel.setEnabled(false);
        });
        begin.addActionListener(e -> {
            ClickSound.playSound(getClass(),  "ClickButton.wav");
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
                case 0 -> {
                    try {
                        gamePanel.doMoveUp();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }
                case 1 -> {
                    try {
                        gamePanel.doMoveRight();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }
                case 2 -> {
                    try {
                        gamePanel.doMoveDown();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }
                default -> {
                    try {
                        gamePanel.doMoveLeft();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
        });
        Rank.addActionListener(e -> {
            ClickSound.playSound(getClass(),  "ClickButton.wav");
            this.setVisible(true);
            String filePath = "src/" + account + "_AIMode.txt";
            JList<String> list = new JList<>();
            ArrayList<String> lines = readFirstTenLines(filePath);
            DefaultListModel<String> model = new DefaultListModel<>();
            for (String line : lines) {
                model.addElement(line);
            }
            list.setModel(model);
            list.setFixedCellHeight(50);
            list.setFont(list.getFont().deriveFont(22.0f));
            list.setCellRenderer(new DefaultListCellRenderer() {
                @Override
                public Component getListCellRendererComponent(
                        JList<?> list,
                        Object value,
                        int index,
                        boolean isSelected,
                        boolean cellHasFocus) {

                    // 调用父类的实现以获取基本的渲染组件
                    super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

                    // 设置文本居中（水平和垂直）
                    setHorizontalAlignment(JLabel.CENTER);
                    setVerticalAlignment(JLabel.CENTER);

                    // 如果你想在文本周围添加一些空白，可以添加EmptyBorder
                    setBorder(new EmptyBorder(5, 10, 5, 10)); // 上、左、下、右的空白

                    return this;
                }
            });
            Rank rank=new Rank(400,500,list);
            rank.setVisible(true);
        });
        restart.addActionListener(e -> {
            ClickSound.playSound(getClass(),  "ClickButton.wav");
            timer.stop();
            setVisible(true);
            RestartAI restartAI=new RestartAI(700,500, controller, gamePanel, gameFrame,jFrame);
            restartAI.setVisible(true);
        });
        setting.addActionListener(e ->{
            ClickSound.playSound(getClass(),  "ClickButton.wav");
            timer.stop();
            int aim= Integer.parseInt(JOptionPane.showInputDialog("Please input the aim of the game"));
            model.setAim(aim);
        });
        load.addActionListener(e -> {
            timer.stop();
            ClickSound.playSound(getClass(),  "ClickButton.wav");
            String string = JOptionPane.showInputDialog(this, "Input path:");
            String filePath="src/"+account+"_content of AIMode.txt";
            File file=new File(filePath);
            if (file.exists()){
                try (Stream<String> lines = Files.lines(Paths.get(filePath))) {
                    String firstLine = lines.findFirst().orElse("0");
                    if (string.equals(firstLine)){
                        String line;
                        try (BufferedReader fileReader = new BufferedReader(new FileReader(filePath))) {
                            if ((line=fileReader.readLine())!=null){
                            }
                            for (int i=0;i<model.getX_COUNT();i++){
                                for (int j=0;j<model.getY_COUNT();j++){
                                    String num1=fileReader.readLine();
                                    if (num1!=null){
                                        model.setNumber(i,j,Integer.parseInt(num1));
                                    }else {
                                        JOptionPane.showMessageDialog(null,"Damage of file");
                                        restart.doClick();
                                    }
                                }
                            }
                            gamePanel.updateGridsNumber();
                            String num2=fileReader.readLine();
                            if (num2!=null){
                                gamePanel.setScore(Integer.parseInt(num2));
                                gamePanel.updateScore(gamePanel.getScore());
                            }else {
                                JOptionPane.showMessageDialog(null,"Damage of file");
                                restart.doClick();
                            }
                            String num3=fileReader.readLine();
                            if (num3!=null){
                                gamePanel.setStep(Integer.parseInt(num3));
                                gamePanel.updateStep(gamePanel.getStep());
                            }else {
                                JOptionPane.showMessageDialog(null,"Damage of file");
                                restart.doClick();
                            }
                            model.setScore(gamePanel.getScore());
                        } catch (IOException ex) {
                            ex.printStackTrace();
                            JOptionPane.showMessageDialog(null,"Wrong Path!");
                        }
                    }else {
                        JOptionPane.showMessageDialog(null,"Path is not right!");
                    }
                } catch (IOException er) {
                    er.printStackTrace();
                }
            }else {
                JOptionPane.showMessageDialog(null,"Have not saved before!");
            }
            gamePanel.requestFocusInWindow();
        });
        save.addActionListener(e -> {
            timer.stop();
            ClickSound.playSound(getClass(),  "ClickButton.wav");
            String string = JOptionPane.showInputDialog(this, "Input path:");
            String filePath="src/"+account+"_content of AIMode.txt";
            File file=new File(filePath);
            if (file.exists()){
                try (Stream<String> lines = Files.lines(Paths.get(filePath))) {
                    String firstLine = lines.findFirst().orElse("0");
                    if (string.equals(firstLine)){
                        try (BufferedWriter fileWriter = new BufferedWriter(new FileWriter(filePath))) {
                            fileWriter.write(string);
                            fileWriter.write(System.lineSeparator());
                            for (int i=0;i<model.getX_COUNT();i++){
                                for (int j=0;j<model.getY_COUNT();j++){
                                    fileWriter.write(Integer.toString(model.getNumber(i,j)));
                                    fileWriter.write(System.lineSeparator());
                                }
                            }
                            fileWriter.write(Integer.toString(model.getScore()));
                            fileWriter.write(System.lineSeparator());
                            fileWriter.write(Integer.toString(gamePanel.getStep()));
                            fileWriter.write(System.lineSeparator());
                        } catch (IOException ex) {
                            ex.printStackTrace();
                            System.err.println("Error occurred while writing to the file.");
                        }
                    }else {
                        JOptionPane.showMessageDialog(null,"Path is not right!");
                    }
                } catch (IOException er) {
                    er.printStackTrace();
                }
            }else {
                try (BufferedWriter fileWriter = new BufferedWriter(new FileWriter(filePath))) {
                    fileWriter.write(string);
                    fileWriter.write(System.lineSeparator());
                    for (int i=0;i<model.getX_COUNT();i++){
                        for (int j=0;j<model.getY_COUNT();j++){
                            fileWriter.write(Integer.toString(model.getNumber(i,j)));
                            fileWriter.write(System.lineSeparator());
                        }
                    }
                    fileWriter.write(Integer.toString(model.getScore()));
                    fileWriter.write(System.lineSeparator());
                    fileWriter.write(Integer.toString(gamePanel.getStep()));
                    fileWriter.write(System.lineSeparator());
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
        Dispose.setAccelerator(KeyStroke.getKeyStroke((char)KeyEvent.VK_H,KeyEvent.ALT_DOWN_MASK));
        Dispose.addActionListener(e -> {
            timer.stop();
            ClickSound.playSound(getClass(),  "ClickButton.wav");
            String filePath="src/"+account+"_content of ClassicMode.txt";
            File file=new File(filePath);
            if (file.exists()){
                file.delete();
            }else {
                JOptionPane.showMessageDialog(null,"Haven't saved yet");
            }
        });

        this.controller = new AIController(gamePanel, gamePanel.getModel());
        this.jl1=createLabel("2048",new Font("serif",Font.ITALIC|Font.BOLD,35),new Point(20,20),70,50);
        this.jl2=createLabel("Join the numbers and get to the 2048 tile!",new Font("serif",Font.PLAIN,16),new Point(30,65),270,50);
        this.scoreLabel = createLabel("Score", new Font("serif", Font.ITALIC|Font.BOLD, 15), new Point(100, 15), 150, 25);
        this.stepLabel = createLabel("Start", new Font("serif", Font.ITALIC|Font.BOLD, 15), new Point(280, 20), 100, 50);
        this.maxscoreLabel = createLabel("Maxscore", new Font("serif", Font.ITALIC|Font.BOLD, 15), new Point(100, 45), 150, 25);
        gamePanel.setStepLabel(stepLabel);
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
    private static ArrayList<String> readFirstTenLines(String filePath) {
        ArrayList<String> lines = new ArrayList<>();
        try (BufferedReader reader = Files.newBufferedReader(Paths.get(filePath))) {
            String line;
            int count = 0;
            while ((line = reader.readLine()) != null && count < 10) {
                lines.add(line);
                count++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }
}
