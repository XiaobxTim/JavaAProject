package view;

import util.ColorMap;

import javax.swing.*;
import java.awt.*;

public class SelectModel extends JFrame {
    private JButton ClassicMode;
    private JButton AdventureMode;
    private JButton AIMode;
    private JButton CustomMode;
    private JButton TimeLimitMode;
    private JButton EntertainingMode;
    public SelectModel(int width, int height){
        this.setTitle("Select Model");
        this.setLayout(null);
        this.setSize(width, height);
        ColorMap.InitialColorMap();
        this.ClassicMode= createButton("Classic Mode",new Point(100,100),500,50);
        this.AdventureMode= createButton("Adventure Mode",new Point(100,160),500,50);
        this.AIMode= createButton("AI Mode",new Point(100,220),500,50);
        this.CustomMode= createButton("Custom Mode",new Point(100,280),500,50);
        this.TimeLimitMode= createButton("TimeLimit Mode",new Point(100,340),500,50);
        this.EntertainingMode= createButton("Entertaining Mode",new Point(100,400),500,50);

        this.ClassicMode.addActionListener(e ->{
            setVisible(false);
            GameFrame gameFrame=new GameFrame(700,500);
            gameFrame.setVisible(true);
        });
        this.AdventureMode.addActionListener(e -> {
            setVisible(false);
        });
        this.CustomMode.addActionListener(e -> {
            setVisible(false);
        });
        this.AIMode.addActionListener(e -> {
            setVisible(false);
        });
        this.TimeLimitMode.addActionListener(e -> {
            setVisible(false);
        });
        this.EntertainingMode.addActionListener(e -> {
            setVisible(false);
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
