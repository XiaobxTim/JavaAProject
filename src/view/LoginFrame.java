package view;

import controller.GameController;
import util.ColorMap;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class LoginFrame extends JFrame{
    private JLabel Account;
    private JLabel Password;
    private JLabel Captcha;
    private JLabel jl;
    private JButton Login;
    private JButton Register;
    private Image image;
    private ImageIcon icon;
    private String currentCaptcha;
    public LoginFrame(int width,int height){
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

        this.setTitle("Login");
        this.setLayout(null);
        this.setSize(width, height);
        ColorMap.InitialColorMap();
        this.Account=createLabel("Account:",new Font("serif",Font.BOLD,20),new Point(30,230),100,30);
        this.Password=createLabel("Password:",new Font("serif",Font.BOLD,20),new Point(30,275),100,30);
        this.Captcha=createLabel("Captcha:",new Font("serif",Font.BOLD,20),new Point(30,320),100,30);
        this.add(Account);
        this.add(Password);
        this.add(Captcha);

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
        AccountField.setBounds(120,235,450,20);
        JLabel jl1=new JLabel("Please input your account");
        jl1.setForeground(Color.DARK_GRAY);
        jl1.setVisible(true);
        this.add(jl1);
        jl1.setBounds(120,255,550,15);

        JPasswordField PasswordField=new JPasswordField();
        this.add(PasswordField);
        PasswordField.setBounds(120,280,450,20);
        JLabel jl2=new JLabel("Please input your password");
        jl2.setForeground(Color.DARK_GRAY);
        jl2.setVisible(true);
        this.add(jl2);
        jl2.setBounds(120,300,550,15);

        JButton jl3=new JButton("Forget Your Password?");
        jl3.setFont(new Font("serif",Font.BOLD,10));
        jl3.setBounds(400,290,200,30);
        this.add(jl3);
        jl3.setForeground(Color.BLACK);
        jl3.setOpaque(false);
        jl3.setContentAreaFilled(false);
        jl3.setBorderPainted(false);

        JTextField jx=new JTextField();
        this.add(jx);
        jx.setBounds(120,330,300,20);
        JLabel jl4=new JLabel("Loading...");
        this.add(jl4);
        jl4.setBounds(450,315,80,50);
        JButton jb=new JButton("Refresh");
        this.add(jb);
        jb.setBounds(500,320,80,40);
        jb.setForeground(Color.BLACK);
        jb.setOpaque(false);
        jb.setContentAreaFilled(false);
        jb.setBorderPainted(false);
        jb.addActionListener(e -> {
            generateCaptcha();
            jl4.setText(currentCaptcha);
        });
        generateCaptcha();
        jl4.setText(currentCaptcha);

        this.jl=createLabel("Login",new Font("serif", Font.ITALIC|Font.BOLD,40),new Point(295,170),130,50);
        this.Login=createButton("Login",new Point(150,370),90,50);
        this.Register=createButton("Register",new Point(430,370),90,50);
        this.add(Login);
        this.add(Register);
        Font font=new Font("serif",Font.BOLD,15);
        Login.setFont(font);
        Login.setForeground(Color.BLACK);
        Login.setOpaque(false);
        Login.setContentAreaFilled(false);
        Register.setFont(font);
        Register.setForeground(Color.BLACK);
        Register.setOpaque(false);
        Register.setContentAreaFilled(false);

        AccountField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                update();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                update();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {

            }
            public void update(){
                jl1.setVisible(AccountField.getText().trim().isEmpty());
            }
        });
        PasswordField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                update();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                update();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {

            }
            public void update(){
                jl2.setVisible(PasswordField.getText().trim().isEmpty());
            }
        });

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
                        if (jx.getText().equals(jl4.getText())){
                            JOptionPane.showMessageDialog(this, "login Successfully");
                            setVisible(false);
                            SelectModel selectModel=new SelectModel(700,500);
                            selectModel.setVisible(true);
                        }else{
                            JOptionPane.showMessageDialog(this,"Wrong Captcha");
                            generateCaptcha();
                            jl4.setText(currentCaptcha);
                        }
                    } else {
                        JOptionPane.showMessageDialog(this, "Fail to Login");
                    }
                } catch (FileNotFoundException err){
                    err.printStackTrace();
                }
            }
        });
        jl3.addActionListener(e -> {
            setVisible(true);
            String account=JOptionPane.showInputDialog("Please input your account");
            File file=new File("src/"+account+"_password.txt");
            if (file.exists()){
                try {
                    Scanner in= new Scanner(file);
                    String str=in.nextLine();
                    JOptionPane.showMessageDialog(this,str);
                }catch (FileNotFoundException err){
                    err.printStackTrace();
                }
            }else {
                JOptionPane.showMessageDialog(this,"Cannot Find the Account");
            }
        });
        PasswordField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e){
                if (e.getKeyCode()==KeyEvent.VK_ENTER){
                    Login.doClick();
                }
            }
        });
        AccountField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e){
                if (e.getKeyCode()==KeyEvent.VK_ENTER){
                    Login.doClick();
                }
            }
        });
        jx.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e){
                if (e.getKeyCode()==KeyEvent.VK_ENTER){
                    Login.doClick();
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

    private JLabel createLabel1(ImageIcon icon, Point location, int width, int height) {
        JLabel label = new JLabel(icon);
        label.setLocation(location);
        label.setSize(width, height);
        this.add(label);
        return label;
    }
    private void generateCaptcha(){
        String characters="ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder captchaBuilder=new StringBuilder();
        Random random=new Random();
        for (int i=0;i<4;i++){
            captchaBuilder.append(characters.charAt(random.nextInt(characters.length())));
        }
        currentCaptcha=captchaBuilder.toString();
    }
}
