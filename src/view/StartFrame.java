package view;

import controller.GameController;
import util.ColorMap;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class StartFrame extends JFrame {
    private GameController controller;
    private JButton VisitorLogin;
    private JButton PlayerLogin;
    private JLabel NameLabel;
    private Image image;
    public StartFrame(int width,int height){
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
        this.setTitle("Start Frame");
        this.setLayout(null);
        this.setSize(width, height);
        ColorMap.InitialColorMap();
        this.NameLabel=createLabel("2048",new Font("serif", Font.ITALIC|Font.BOLD,60),new Point(285,100),130,50);
        NameLabel.setForeground(Color.BLACK);
        Font font=new Font("serif",Font.BOLD,20);
        this.VisitorLogin=createButton("VisitorLogin",new Point(100,200),500,50);
        VisitorLogin.setFont(font);
        VisitorLogin.setForeground(Color.BLACK);
        VisitorLogin.setOpaque(false);
        VisitorLogin.setContentAreaFilled(false);
        this.VisitorLogin.addActionListener(e -> {
            setVisible(true);
            SelectModelForVistor selectModelForVistor=new SelectModelForVistor(700,500);
            selectModelForVistor.setVisible(true);
        });
        this.PlayerLogin=createButton("PlayerLogin",new Point(100,300),500,50);
        PlayerLogin.setFont(font);
        PlayerLogin.setForeground(Color.BLACK);
        PlayerLogin.setOpaque(false);
        PlayerLogin.setContentAreaFilled(false);
        this.PlayerLogin.addActionListener(e -> {
            setVisible(true);
            LoginFrame loginFrame=new LoginFrame(700,500);
            loginFrame.setVisible(true);
        });
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
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
