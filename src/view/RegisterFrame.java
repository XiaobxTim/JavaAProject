package view;

import util.ColorMap;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class RegisterFrame extends JFrame {
    private JLabel Account;
    private JLabel Password;
    private JLabel jl;
    private JButton OK;
    private JButton Cancel;
    private Image image;
    private ImageIcon icon;
    public RegisterFrame(int width,int height) {
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

        this.setTitle("Register");
        this.setLayout(null);
        this.setSize(width, height);
        ColorMap.InitialColorMap();
        this.Account=createLabel("Account:",new Font("serif",Font.BOLD,20),new Point(30,220),100,50);
        this.Password=createLabel("Password:",new Font("serif",Font.BOLD,20),new Point(30,280),100,50);
        this.add(Account);
        this.add(Password);

        icon=null;
        try{
            icon = new ImageIcon(ImageIO.read(new File("src/屏幕截图 2024-05-13 182603.png")));
        }catch (IOException e){
            e.getStackTrace();
        }
        JLabel jLabel=createLabel1(icon,new Point(270,10),150,160);
        this.add(jLabel);


        JTextField AccountField=new JTextField();
        this.add(AccountField);
        AccountField.setBounds(120,235,550,30);
        JLabel jl1=new JLabel("Please input your account");
        jl1.setForeground(Color.DARK_GRAY);
        jl1.setVisible(true);
        this.add(jl1);
        jl1.setBounds(120,265,550,20);

        JPasswordField PasswordField=new JPasswordField();
        this.add(PasswordField);
        PasswordField.setBounds(120,295,550,30);
        JLabel jl2=new JLabel("Please input your password");
        jl2.setForeground(Color.DARK_GRAY);
        jl2.setVisible(true);
        this.add(jl2);
        jl2.setBounds(120,325,550,30);

        this.jl=createLabel("Register",new Font("serif", Font.ITALIC|Font.BOLD,40),new Point(275,170),200,50);
        this.OK=createButton("OK",new Point(150,370),110,50);
        this.Cancel=createButton("Cancel",new Point(430,370),110,50);
        this.add(OK);
        this.add(Cancel);
        Font font=new Font("serif",Font.BOLD,20);
        OK.setFont(font);
        OK.setForeground(Color.BLACK);
        OK.setOpaque(false);
        OK.setContentAreaFilled(false);
        Cancel.setFont(font);
        Cancel.setForeground(Color.BLACK);
        Cancel.setOpaque(false);
        Cancel.setContentAreaFilled(false);

        Cancel.addActionListener(e -> {
            Cancel.setFocusable(true);
            setVisible(false);
            LoginFrame loginFrame=new LoginFrame(700,500);
            loginFrame.setVisible(true);
            Cancel.setFocusable(false);
        });
        OK.addActionListener(e -> {
            // AccountField.getText()
//            PasswordField.getText()
            String account;
            String password;
//            Scanner input = new Scanner(System.in);
            account = AccountField.getText();
            password = PasswordField.getText();


            if (account.isEmpty()) {
                return;
            }

            File file = new File("src/" + account + "_password.txt");
            try {
                if (file.createNewFile()) {
                    FileWriter fileWriter = new FileWriter(file);
                    fileWriter.write(password);
                    fileWriter.close();
                    JOptionPane.showMessageDialog(this, "OK!");
                } else {
                    System.out.println("create failed");
                    JOptionPane.showMessageDialog(this, "Fail!");
                }
            } catch (Exception exception){
                exception.printStackTrace();
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
    private JLabel createLabel1(ImageIcon icon, Point location, int width, int height) {
        JLabel label = new JLabel(icon);
        label.setLocation(location);
        label.setSize(width, height);
        this.add(label);
        return label;
    }
}
