package view;

import util.ColorMap;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.text.StyledEditorKit;
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
    private JLabel selectMode;
    public SelectModel(int width, int height){
        this.setTitle("Select Model");
        this.setLayout(null);
        this.setSize(width, height);
        ColorMap.InitialColorMap();
        JList<String> jl=new JList<>(new MyListMode());
        JScrollPane js=new JScrollPane(jl);
        js.setBounds(10,100,340,240);
        this.add(js);
        jl.setFixedCellHeight(50);
        jl.setFont(jl.getFont().deriveFont(22.0f));
        JFrame jFrame=this;

        this.selectMode = createLabel("Please Select Mode", new Font("serif", Font.ITALIC, 45), new Point(150, 10), 500, 50);
        jl.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount()==2){
                    JList list=(JList) e.getSource();
                    int index=list.getSelectedIndex();
                    Object obj=list.getModel().getElementAt(index);
                    String str=obj.toString();
                    if (str.equals("Classic Mode")){
                        setVisible(false);
                        GameFrame gameFrame=new GameFrame(400,500,jFrame);
                        gameFrame.setVisible(true);
                    }
                    if (str.equals("Custom Mode")){
                        setVisible(false);
                        int size = Integer.parseInt(JOptionPane.showInputDialog(this, "Input Size:"));
                        if (size<=10){
                            CustomFrame customFrame=new CustomFrame(400,500,size,jFrame);
                            customFrame.setVisible(true);
                        }else {
                            JOptionPane.showMessageDialog(null,"Please input again");
                        }
                    }
                    if (str.equals("TimeLimit Mode")){
                        setVisible(false);
                        TimeLimitFrame timeLimitFrame=new TimeLimitFrame(400,500,jFrame);
                        timeLimitFrame.setVisible(true);
                    }
                    if (str.equals("Adventure Mode")){

                    }
                }
            }
        });

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
}
class MyListMode extends AbstractListModel<String> {
    private String[] contents = {"Classic Mode", "Adventure Mode", "AI Mode", "Custom Mode", "TimeLimit Mode", "Entertaining Mode"};
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