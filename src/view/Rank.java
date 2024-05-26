package view;

import util.ColorMap;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Rank extends JFrame {
    private Image image;
    private JList<String> list;
    private JLabel label;

    public Rank(int width,int height,JList<String> list){
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
        this.setTitle("Rank");
        this.setLayout(null);
        this.setSize(width, height);
        ColorMap.InitialColorMap();
        JScrollPane js=new JScrollPane(list);
        js.setBounds(45,100,300,300);
        this.add(js);
        label=createLabel("Rank",new Font("serif",Font.BOLD|Font.ITALIC,45),new Point(140,50),200,50);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    }
    private JLabel createLabel(String name, Font font, Point location, int width, int height) {
        JLabel label = new JLabel(name);
        label.setFont(font);
        label.setLocation(location);
        label.setSize(width, height);
        this.add(label);
        return label;
    }
    private void setJList(JList<String> list){this.list=list;}
    private JList<String> getList(){return list;}
}
