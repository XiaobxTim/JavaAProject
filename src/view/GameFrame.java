package view;

import AI.AI;
import AI.GameState;
import controller.GameController;
import model.GridNumber;
import util.ColorMap;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyEvent;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Stream;

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
    public JFrame jf;
    private GridNumber model;
    private int count;

    public GameFrame(int width, int height,JFrame jFrame,String account) {
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
        gamePanel = new GamePanel((int) (this.getHeight() * 0.65),account);
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
        JMenuItem Rank=new JMenuItem("Rank");
        menu.add(menuItem);
        menu.add(restart);
        menu.add(load);
        menu.add(save);
        menu.add(stop);
        menu.add(begin);
        menu.add(setting);
        menu.add(back);
        menu.add(Hint);
        menu.add(Rank);
        menuItem.setAccelerator(KeyStroke.getKeyStroke((char) KeyEvent.VK_D,KeyEvent.CTRL_DOWN_MASK));
        restart.setAccelerator(KeyStroke.getKeyStroke((char)KeyEvent.VK_R,KeyEvent.CTRL_DOWN_MASK));
        load.setAccelerator(KeyStroke.getKeyStroke((char)KeyEvent.VK_L,KeyEvent.CTRL_DOWN_MASK));
        save.setAccelerator(KeyStroke.getKeyStroke((char)KeyEvent.VK_S,KeyEvent.CTRL_DOWN_MASK));
        stop.setAccelerator(KeyStroke.getKeyStroke((char)KeyEvent.VK_S,KeyEvent.ALT_DOWN_MASK));
        begin.setAccelerator(KeyStroke.getKeyStroke((char)KeyEvent.VK_B,KeyEvent.ALT_DOWN_MASK));
        setting.setAccelerator(KeyStroke.getKeyStroke((char)KeyEvent.VK_S,KeyEvent.SHIFT_DOWN_MASK));
        back.setAccelerator(KeyStroke.getKeyStroke((char)KeyEvent.VK_Z,KeyEvent.CTRL_DOWN_MASK));
        Hint.setAccelerator(KeyStroke.getKeyStroke((char)KeyEvent.VK_H,KeyEvent.CTRL_DOWN_MASK));
        Rank.setAccelerator(KeyStroke.getKeyStroke((char)KeyEvent.VK_R,KeyEvent.SHIFT_DOWN_MASK));
        Rank.addActionListener(e -> {
            this.setVisible(true);
            String filePath = "src/" + account + "_ClassicMode.txt";
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
        restart.addActionListener(e -> {
            ClickSound.playSound(getClass(),  "ClickButton.wav");
            setVisible(true);
            RestartFrame restartFrame=new RestartFrame(700,500, controller, gamePanel, gameFrame,jFrame);
            restartFrame.setVisible(true);
        });
        menuItem.addActionListener(e -> {
            ClickSound.playSound(getClass(),  "ClickButton.wav");
            setVisible(true);
            DirectionFrame directionFrame=new DirectionFrame(300,250,gamePanel);
            directionFrame.setVisible(true);
        });
        setting.addActionListener(e ->{
            ClickSound.playSound(getClass(),  "ClickButton.wav");
            int aim= Integer.parseInt(JOptionPane.showInputDialog("Please input the aim of the game"));
            model.setAim(aim);
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
        load.addActionListener(e -> {
            ClickSound.playSound(getClass(),  "ClickButton.wav");
            String string = JOptionPane.showInputDialog(this, "Input path:");
            String filePath="src/"+account+"_content of ClassicMode.txt";
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
                                    model.setNumber(i,j,Integer.parseInt(fileReader.readLine()));
                                }
                            }
                            gamePanel.updateGridsNumber();
                            gamePanel.setScore(Integer.parseInt(fileReader.readLine()));
                            gamePanel.updateScore(gamePanel.getScore());
                            gamePanel.setStep(Integer.parseInt(fileReader.readLine()));
                            gamePanel.updateStep(gamePanel.getStep());
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
            ClickSound.playSound(getClass(),  "ClickButton.wav");
            String string = JOptionPane.showInputDialog(this, "Input path:");
            String filePath="src/"+account+"_content of ClassicMode.txt";
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
                    fileWriter.write(Integer.toString(model.getStep()));
                    fileWriter.write(System.lineSeparator());
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
        stop.addActionListener(e -> {
            ClickSound.playSound(getClass(),  "ClickButton.wav");
            gamePanel.setEnabled(false);
        });
        begin.addActionListener(e -> {
            ClickSound.playSound(getClass(),  "ClickButton.wav");
            gamePanel.setEnabled(true);
        });

        this.controller = new GameController(gamePanel, gamePanel.getModel());
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