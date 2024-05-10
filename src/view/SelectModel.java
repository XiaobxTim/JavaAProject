package view;

import util.ColorMap;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SelectModel extends JFrame {
    public JButton ClassicMode;
    public JButton AdventureMode;
    public JButton AIMode;
    public JButton CustomMode;
    public JButton TimeLimitMode;
    public JButton EntertainingMode;
    public SelectModel(int width, int height){
        this.setTitle("Select Model");
        this.setLayout(null);
        this.setSize(width, height);
        ColorMap.InitialColorMap();
        JList<String> jl=new JList<>(new MyListMode());
        JScrollPane js=new JScrollPane(jl);
        js.setBounds(10,10,340,240);
        this.add(js);
        /*this.ClassicMode= createButton("Classic Mode",new Point(100,50),500,50);
        this.AdventureMode= createButton("Adventure Mode",new Point(100,110),500,50);
        this.AIMode= createButton("AI Mode",new Point(100,170),500,50);
        this.CustomMode= createButton("Custom Mode",new Point(100,230),500,50);
        this.TimeLimitMode= createButton("TimeLimit Mode",new Point(100,290),500,50);
        this.EntertainingMode= createButton("Entertaining Mode",new Point(100,350),500,50);

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
            int size = Integer.parseInt(JOptionPane.showInputDialog(this, "Input Size:"));
            if (size<=10){
                CustomFrame customFrame=new CustomFrame(700,500,size);
                customFrame.setVisible(true);
            }else {
                JOptionPane.showMessageDialog(null,"Please input again");
            }
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
    }*/
        jl.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount()==2){
                    JList list=(JList) e.getSource();
                    int index=list.getSelectedIndex();
                    Object obj=list.getModel().getElementAt(index);
                    String str=obj.toString();
                    if (str.equals("ClassicMode")){
                        setVisible(false);
                        GameFrame gameFrame=new GameFrame(700,500);
                        gameFrame.setVisible(true);
                    }
                    if (str.equals("CustomMode")){
                        setVisible(false);
                        int size = Integer.parseInt(JOptionPane.showInputDialog(this, "Input Size:"));
                        if (size<=10){
                            CustomFrame customFrame=new CustomFrame(700,500,size);
                            customFrame.setVisible(true);
                        }else {
                            JOptionPane.showMessageDialog(null,"Please input again");
                        }
                    }
                }
            }
        });
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    }
}
class MyListMode extends AbstractListModel<String> {
    private String[] contents = {"ClassicMode", "AdventureMode", "AIMode", "CustomMode", "TimeLimitMode", "EntertainingMode"};
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