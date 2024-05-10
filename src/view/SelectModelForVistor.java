package view;

import util.ColorMap;

import javax.swing.*;
import java.awt.*;

public class SelectModelForVistor extends JFrame {
    private JButton ClassicMode;
    private JButton CustomMode;
    public SelectModelForVistor(int width, int height) {
        this.setTitle("Select Model");
        this.setLayout(null);
        this.setSize(width, height);
        ColorMap.InitialColorMap();
        JList<String> jl=new JList<>(new MyListModel());
        JScrollPane js=new JScrollPane(jl);
        js.setBounds(10,10,340,240);
        this.add(js);
        /*this.ClassicMode= createButton("Classic Mode",new Point(100,170),500,50);
        this.CustomMode= createButton("Custom Mode",new Point(100,230),500,50);

        this.ClassicMode.addActionListener(e ->{
            setVisible(false);
            GameFrameForVisitor gameFrameForVisitor=new GameFrameForVisitor(700,500);
            gameFrameForVisitor.setVisible(true);
        });
        this.CustomMode.addActionListener(e -> {
            setVisible(false);
            int size = Integer.parseInt(JOptionPane.showInputDialog(this, "Input Size:"));
            if (size<=10){
                CustomFrameForVisitor customFrameForVisitor=new CustomFrameForVisitor(700,500,size);
                customFrameForVisitor.setVisible(true);
            }else {
                JOptionPane.showMessageDialog(null,"Please input again");
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
    }*/
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    }

}
class MyListModel extends AbstractListModel<String> {
    private String[] contents = {"ClassicMode", "CustomMode"};
    public String  getElementAt(int index) {
        if (index<contents.length){
            return contents[index];
        }else {
            return null;
        }
    }
    public int getSize() {
        return contents.length;
    }
}
