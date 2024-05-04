package view;

import util.ColorMap;

import javax.swing.*;
import java.awt.*;

public class SelectModelForVistor extends JFrame {
    private JButton ClassicModel;
    public SelectModelForVistor(int width, int height){
        this.setTitle("Select Model");
        this.setLayout(null);
        this.setSize(width, height);
        ColorMap.InitialColorMap();
        this.ClassicModel= createButton("Classic Model",new Point(100,200),500,50);
        this.ClassicModel.addActionListener(e ->{
            setVisible(false);
            GameFrameForVisitor gameFrameForVisitor=new GameFrameForVisitor(700,500);
            gameFrameForVisitor.setVisible(true);
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
