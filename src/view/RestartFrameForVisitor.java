package view;

import controller.GameController;
import controller.GameControllerForVisitor;
import util.ColorMap;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;

public class RestartFrameForVisitor extends JFrame{
    private JButton CancelBtn;
    private JButton ConfirmBtn;
    private JLabel jl;
    private Image image;
    public RestartFrameForVisitor(int width, int height, GameControllerForVisitor controller, GamePanelForVisitor gamePanel, JFrame jFrame){
        setFocusable(true);
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
        this.setTitle("Restart");
        this.setLayout(null);
        this.setSize(width, height);
        ColorMap.InitialColorMap();
        this.jl=createLabel("Are you sure to restart the game?",new Font("serif",Font.BOLD|Font.ITALIC,35),new Point(100,150),500,50);
        this.ConfirmBtn = createButton("Confirm", new Point(180,240),110 ,50);
        this.CancelBtn = createButton("Cancel", new Point(400,240),110 ,50);
        Font font=new Font("serif",Font.BOLD,20);
        ConfirmBtn.setFont(font);
        ConfirmBtn.setForeground(Color.BLACK);
        ConfirmBtn.setOpaque(false);
        ConfirmBtn.setContentAreaFilled(false);
        CancelBtn.setFont(font);
        CancelBtn.setForeground(Color.BLACK);
        CancelBtn.setOpaque(false);
        CancelBtn.setContentAreaFilled(false);
        ConfirmBtn.addActionListener(e -> {
            ClickSound.playSound(getClass(),  "ClickButton.wav");
            ConfirmBtn.setFocusable(true);
            this.dispose();
            jFrame.setVisible(false);
            controller.restartGameForVisitor();
            gamePanel.requestFocusInWindow();
            ConfirmBtn.setFocusable(false);
        });

        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e){
                if (e.getKeyCode()==KeyEvent.VK_ENTER){
                    ConfirmBtn.doClick();
                }
            }
        });
        CancelBtn.addActionListener(e -> {
            ClickSound.playSound(getClass(),  "ClickButton.wav");
            CancelBtn.setFocusable(true);
            this.dispose();
            CancelBtn.setFocusable(false);
        });

        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        JFrame gameFrame1=this;
        gameFrame1.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                centerWindow(gameFrame1);
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
