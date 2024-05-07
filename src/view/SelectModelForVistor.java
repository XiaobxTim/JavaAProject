package view;

import util.ColorMap;

import javax.swing.*;
import java.awt.*;

public class SelectModelForVistor extends JFrame {
    private JButton ClassicMode;
    private JButton AdventureMode;
    private JButton AIMode;
    private JButton CustomMode;
    private JButton TimeLimitMode;
    private JButton EntertainingMode;
    public SelectModelForVistor(int width, int height){
        this.setTitle("Select Model");
        this.setLayout(null);
        this.setSize(width, height);
        ColorMap.InitialColorMap();
        this.ClassicMode= createButton("Classic Mode",new Point(100,50),500,50);
        this.AdventureMode= createButton("Adventure Mode",new Point(100,110),500,50);
        this.AIMode= createButton("AI Mode",new Point(100,170),500,50);
        this.CustomMode= createButton("Custom Mode",new Point(100,230),500,50);
        this.TimeLimitMode= createButton("TimeLimit Mode",new Point(100,290),500,50);
        this.EntertainingMode= createButton("Entertaining Mode",new Point(100,350),500,50);

        this.ClassicMode.addActionListener(e ->{
            setVisible(false);
            GameFrameForVisitor gameFrameForVisitor=new GameFrameForVisitor(700,500);
            gameFrameForVisitor.setVisible(true);
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
