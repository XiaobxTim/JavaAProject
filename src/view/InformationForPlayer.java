package view;

import controller.GameController;
import util.ColorMap;

import javax.swing.*;
import java.awt.*;

public class InformationForPlayer extends JFrame{
    private JLabel Account;
    private JLabel Password;
    private JButton Login;
    private JButton Register;
    public InformationForPlayer(int width,int height){
        this.setTitle("Information");
        this.setLayout(null);
        this.setSize(width, height);
        ColorMap.InitialColorMap();
        this.Account=createLabel("Account",new Font("serif", Font.ITALIC,20),new Point(50,50),110,50);
        this.Password=createLabel("Password",new Font("serif", Font.ITALIC,20),new Point(50,100),110,50);
        Account.setHorizontalAlignment(0);
        Password.setHorizontalAlignment(0);
        this.Login=createButton("Login",new Point(200,300),100,50);
        this.Register=createButton("Register",new Point(400,300),100,50);
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
