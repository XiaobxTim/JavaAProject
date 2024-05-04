package view;

import util.ColorMap;

import javax.swing.*;
import java.awt.*;

public class SelectModel extends JFrame {
    private JButton ClassicModel;
    public SelectModel(int width, int height){
        this.setTitle("Select Model");
        this.setLayout(null);
        this.setSize(width, height);
        ColorMap.InitialColorMap();
        this.ClassicModel= createButton("Classic Model",new Point(295,200),110,50);
        this.ClassicModel.addActionListener(e ->{
            setVisible(false);
            GameFrame gameFrame=new GameFrame(700,500);
            gameFrame.setVisible(true);
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
}
