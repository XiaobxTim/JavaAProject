package view;

import controller.GameController;
import util.ColorMap;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
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
    private static final int WIDTH = 100;
    private static final int HEIGHT = 50;
    private JLabel captchaLabel;
    private String captchaText;
    private JButton Login;
    private JButton Register;
    private Image image;
    private ImageIcon icon;
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
        jx.setBounds(120,330,340,20);
        this.captchaLabel=new JLabel();
        this.add(captchaLabel);
        captchaLabel.setBounds(480,325,80,30);
        generateCaptcha();

        captchaLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 1) { // 检测单击事件
                    generateCaptcha(); // 生成新的验证码
                }
            }
        });

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
                        if (jx.getText().equalsIgnoreCase(captchaLabel.getText())){
                            JOptionPane.showMessageDialog(this, "login Successfully");
                            setVisible(false);
                            SelectModel selectModel=new SelectModel(700,500);
                            selectModel.setVisible(true);
                        }else{
                            JOptionPane.showMessageDialog(this,"Wrong Captcha");
                            generateCaptcha();
                            captchaLabel.setText(captchaText);
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
    private void generateCaptcha() {
        captchaText = generateRandomCaptchaText();
        BufferedImage captchaImage = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = captchaImage.createGraphics();

        // 设置背景颜色、字体等
        g2d.setColor(Color.WHITE);
        g2d.fillRect(0, 0, WIDTH, HEIGHT);
        g2d.setColor(Color.BLACK);
        g2d.setFont(new Font("Arial", Font.BOLD, 18));

        // 绘制验证码文本
        g2d.drawString(captchaText, (WIDTH - g2d.getFontMetrics().stringWidth(captchaText)) / 2, HEIGHT / 2 + g2d.getFontMetrics().getAscent() / 2);

        // 添加干扰：绘制随机线条
        Random random = new Random();
        for (int i = 0; i < 8; i++) {
            int x1 = random.nextInt(WIDTH);
            int y1 = random.nextInt(HEIGHT);
            int x2 = random.nextInt(WIDTH);
            int y2 = random.nextInt(HEIGHT);
            g2d.setColor(new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256))); // 随机颜色
            g2d.drawLine(x1, y1, x2, y2);
        }

        // 添加干扰：添加噪点
        for (int i = 0; i < 50; i++) {
            int x = random.nextInt(WIDTH);
            int y = random.nextInt(HEIGHT);
            g2d.setColor(Color.BLACK); // 噪点颜色
            g2d.fillRect(x, y, 1, 1); // 绘制1x1的矩形作为噪点
        }

        // 释放资源
        g2d.dispose();

        // 更新标签以显示新验证码
        captchaLabel.setIcon(new ImageIcon(captchaImage));
        captchaLabel.setText(captchaText);
    }

    private String generateRandomCaptchaText() {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder captcha = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 5; i++) {
            captcha.append(characters.charAt(random.nextInt(characters.length())));
        }
        return captcha.toString();
    }
}
