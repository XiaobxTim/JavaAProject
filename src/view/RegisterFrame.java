package view;

import util.ColorMap;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class RegisterFrame extends JFrame {
    private JLabel Account;
    private JLabel Password;
    private JLabel Confirm;
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
        this.Account=createLabel("Account:",new Font("serif",Font.BOLD,20),new Point(30,230),100,30);
        this.Password=createLabel("Password:",new Font("serif",Font.BOLD,20),new Point(30,275),100,30);
        this.Confirm=createLabel("Confirm:",new Font("serif",Font.BOLD,20),new Point(30,320),100,30);
        this.add(Account);
        this.add(Password);
        this.add(Confirm);

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

        JPasswordField PasswordField1=new JPasswordField();
        this.add(PasswordField1);
        PasswordField1.setBounds(120,325,450,20);
        JLabel jl21=new JLabel("Please input your password again");
        jl21.setForeground(Color.DARK_GRAY);
        jl21.setVisible(true);
        this.add(jl21);
        jl21.setBounds(120,345,550,15);

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
        System.out.println("here");
        OK.addActionListener(e -> {
            // AccountField.getText()
//            PasswordField.getText()
            String account;
            String password;
            String confirm;
//            Scanner input = new Scanner(System.in);
            account = AccountField.getText();
            password = PasswordField.getText();
            confirm = PasswordField1.getText();


            if (account.isEmpty()) {
                return;
            }

            File file = new File("src/" + account + "_password.txt");
            try {
                System.out.println("pass = " + password);
                System.out.println("conf = " + confirm);
                if (file.createNewFile() && password.equals(confirm)) {
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
        JFrame gameFrame=this;
        gameFrame.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                centerWindow(gameFrame);
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
    private JLabel createLabel1(ImageIcon icon, Point location, int width, int height) {
        JLabel label = new JLabel(icon);
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
