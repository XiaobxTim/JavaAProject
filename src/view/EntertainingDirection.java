package view;

import util.ColorMap;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class EntertainingDirection extends JFrame {
    private GamePanel gamePanel;
    private JButton Right;
    private JButton Left;
    private JButton Up;
    private JButton Down;
    private ImageIcon icon1;
    private ImageIcon icon2;
    private ImageIcon icon3;
    private ImageIcon icon4;
    private Image image;
    public EntertainingDirection(int width,int height,EntertainingPanel gamePanel){
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
        JFrame jFrame=this;
        this.setTitle("Keyboard");
        this.setLayout(null);
        this.setSize(width, height);
        ColorMap.InitialColorMap();
        icon1=null;
        icon2=null;
        icon3=null;
        icon4=null;
        try{
            icon1 = new ImageIcon(ImageIO.read((new File("src/icons8-maple-leaf-48.png"))));
        }catch (IOException e){
            e.getStackTrace();
        }
        try{
            icon2 = new ImageIcon(ImageIO.read((new File("src/icons8-maple-leaf-48 - 副本.png"))));
        }catch (IOException e){
            e.getStackTrace();
        }
        try{
            icon3 = new ImageIcon(ImageIO.read((new File("src/icons8-maple-leaf-48 - 副本 (2).png"))));
        }catch (IOException e){
            e.getStackTrace();
        }
        try{
            icon4 = new ImageIcon(ImageIO.read((new File("src/icons8-maple-leaf-48 - 副本 (3).png"))));
        }catch (IOException e){
            e.getStackTrace();
        }

        this.Right=createButton(new Point(180,100),80,80,icon4);
        this.Left=createButton(new Point(20,100),80,80,icon2);
        this.Up=createButton(new Point(100,20),80,80,icon1);
        this.Down=createButton(new Point(100,100),80,80,icon3);

        Right.setOpaque(false);
        Right.setContentAreaFilled(false);
        Left.setOpaque(false);
        Left.setContentAreaFilled(false);
        Up.setOpaque(false);
        Up.setContentAreaFilled(false);
        Down.setOpaque(false);
        Down.setContentAreaFilled(false);

        this.Right.addActionListener(e -> {
            Right.setFocusable(true);
            try {
                gamePanel.doMoveRight();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            Right.setFocusable(false);
            jFrame.setFocusable(true);
        });
        this.Left.addActionListener(e -> {
            Left.setFocusable(true);
            try {
                gamePanel.doMoveLeft();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            Left.setFocusable(false);
            jFrame.setFocusable(true);
        });
        this.Up.addActionListener(e -> {
            Up.setFocusable(true);
            try {
                gamePanel.doMoveUp();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            Up.setFocusable(false);
            jFrame.setFocusable(true);
        });
        this.Down.addActionListener(e -> {
            Down.setFocusable(true);
            try {
                gamePanel.doMoveDown();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            Down.setFocusable(false);
            jFrame.setFocusable(true);
        });
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    }
    private JButton createButton(Point location, int width, int height,ImageIcon icon) {
        JButton button = new JButton(icon);
        button.setLocation(location);
        button.setSize(width, height);
        this.add(button);
        return button;
    }
}
