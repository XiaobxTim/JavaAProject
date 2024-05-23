package view;

import util.ColorMap;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.File;
import java.io.IOException;

public class FailureForVisitor extends JFrame{
    private JLabel NameLabel;
    private JLabel jl;
    private JLabel jl1;
    private JButton Yes;
    private JButton No;
    private Image image;
    public FailureForVisitor(int width,int height,int score){
        JFrame gameFrame=this;
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
        this.setTitle("FailureFrame");
        this.setLayout(null);
        this.setSize(width, height);
        ColorMap.InitialColorMap();
        this.NameLabel=createLabel("GAMEOVER",new Font("serif", Font.ITALIC|Font.BOLD,35),new Point(90,80),200,50);
        NameLabel.setForeground(Color.BLACK);
        String str="Your final score is "+score;
        this.jl=createLabel(str,new Font("serif", Font.ITALIC|Font.BOLD,20),new Point(90,150),400,50);
        jl.setForeground(Color.BLACK);
        this.jl1=createLabel("PLAY AGAIN?",new Font("serif", Font.ITALIC|Font.BOLD,25),new Point(110,210),200,50);
        jl1.setForeground(Color.BLACK);
        Font font=new Font("serif",Font.BOLD,10);
        this.Yes=createButton("YES",new Point(70,280),100,50);
        Yes.setFont(font);
        Yes.setForeground(Color.BLACK);
        Yes.setOpaque(false);
        Yes.setContentAreaFilled(false);
        this.Yes.addActionListener(e -> {
            setVisible(false);
            SelectModelForVistor selectModelForVistor=new SelectModelForVistor(700,500);
            selectModelForVistor.setVisible(true);
        });
        this.No=createButton("No",new Point(220,280),100,50);
        No.setFont(font);
        No.setForeground(Color.BLACK);
        No.setOpaque(false);
        No.setContentAreaFilled(false);
        this.No.addActionListener(e -> {
            this.dispose();
        });
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
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
