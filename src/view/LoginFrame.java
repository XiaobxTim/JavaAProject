package view;

import controller.GameController;
import util.ColorMap;

import javax.swing.*;
import java.awt.*;

public class LoginFrame extends JFrame{
    private GameController controller;
    private JButton VisitorLogin;
    private JLabel NameLabel;
    public LoginFrame(int width,int height){
        this.setTitle("Login Interface");
        this.setLayout(null);
        this.setSize(width, height);
        ColorMap.InitialColorMap();
        this.NameLabel=createLabel("2048",new Font("serif", Font.ITALIC,45),new Point(295,100),110,50);
        this.VisitorLogin=createButton("VisitorLogin",new Point(295,200),110,50);
        this.VisitorLogin.addActionListener(e -> {
            setVisible(false);
            SelectModelForVistor selectModelForVistor=new SelectModelForVistor(700,500);
            selectModelForVistor.setVisible(true);
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
