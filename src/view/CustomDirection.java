package view;

import util.ColorMap;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class CustomDirection extends JFrame {
    private CustomPanel gamePanel;
    private JButton Right;
    private JButton Left;
    private JButton Up;
    private JButton Down;
    private ImageIcon icon1;
    private ImageIcon icon2;
    private ImageIcon icon3;
    private ImageIcon icon4;
    public CustomDirection(int width,int height,CustomPanel gamePanel){
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
            gamePanel.doMoveRight();
            Right.setFocusable(false);
        });
        this.Left.addActionListener(e -> {
            Left.setFocusable(true);
            gamePanel.doMoveLeft();
            Left.setFocusable(false);
        });
        this.Up.addActionListener(e -> {
            Up.setFocusable(true);
            gamePanel.doMoveUp();
            Up.setFocusable(false);
        });
        this.Down.addActionListener(e -> {
            Down.setFocusable(true);
            gamePanel.doMoveDown();
            Down.setFocusable(false);
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
