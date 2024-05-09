package view;

import controller.GameController;
import util.ColorMap;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class LoginFrame extends JFrame{
    private JLabel Account;
    private JLabel Password;
    private JButton Login;
    private JButton Register;
    public LoginFrame(int width,int height){
        SpringLayout springLayout=new SpringLayout();
        Container container=getContentPane();
        this.setTitle("Information");
        this.setLayout(springLayout);
        this.setSize(width, height);
        ColorMap.InitialColorMap();
        this.Account=new JLabel("Account:");
        this.Password=new JLabel("Password:");
        this.add(Account);
        this.add(Password);
        springLayout.putConstraint(SpringLayout.NORTH,Account,100,SpringLayout.NORTH,container);
        springLayout.putConstraint(SpringLayout.WEST,Account,20,SpringLayout.WEST,container);
        JTextField AccountField=new JTextField();
        this.add(AccountField);
        springLayout.putConstraint(SpringLayout.NORTH,AccountField,100,SpringLayout.NORTH,container);
        springLayout.putConstraint(SpringLayout.WEST,AccountField,20,SpringLayout.EAST,Account);
        springLayout.putConstraint(SpringLayout.EAST,AccountField,-20,SpringLayout.EAST,container);
        springLayout.putConstraint(SpringLayout.NORTH,Password,20,SpringLayout.SOUTH,Account);
        springLayout.putConstraint(SpringLayout.WEST,Password,20,SpringLayout.WEST,container);
        JPasswordField PasswordField=new JPasswordField();
        this.add(PasswordField);
        springLayout.putConstraint(SpringLayout.NORTH,PasswordField,20,SpringLayout.SOUTH,AccountField);
        springLayout.putConstraint(SpringLayout.WEST,PasswordField,20,SpringLayout.EAST,Password);
        springLayout.putConstraint(SpringLayout.EAST,PasswordField,-20,SpringLayout.EAST,container);

        this.Login=new JButton("Login");
        this.Register=new JButton("Register");
        this.add(Login);
        this.add(Register);
        springLayout.putConstraint(SpringLayout.SOUTH,Login,-125,SpringLayout.SOUTH,container);
        springLayout.putConstraint(SpringLayout.SOUTH,Register,-125,SpringLayout.SOUTH,container);
        springLayout.putConstraint(SpringLayout.EAST,Register,-200,SpringLayout.EAST,container);
        springLayout.putConstraint(SpringLayout.SOUTH,PasswordField,-150,SpringLayout.NORTH,Register);
        springLayout.putConstraint(SpringLayout.EAST,Login,-100,SpringLayout.WEST,Register);

        Register.addActionListener(e -> {
            Register.setFocusable(true);
            setVisible(false);
            RegisterFrame registerFrame=new RegisterFrame(700,500);
            registerFrame.setVisible(true);
            Register.setFocusable(false);
        });
        //监听login
        Login.addActionListener(e ->{
            String account;
            String password;

            account = AccountField.getText();
            password = PasswordField.getText();
            File file = new File("src/" + account + "_password.txt");
            if(file.exists()){
                try{
                    Scanner in = new Scanner(file);
//                    while(in.hasNext()){
//                        System.out.println(in.next());
//                    }
                    if (in.nextLine().equals(password)) {
                        JOptionPane.showMessageDialog(this, "login Successfully");
                        setVisible(false);
                        SelectModel selectModel=new SelectModel(700,500);
                        selectModel.setVisible(true);
                    } else {
                        JOptionPane.showMessageDialog(this, "Fail to Login");
                    }
                } catch (FileNotFoundException err){
                    err.printStackTrace();
                }
            }
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
