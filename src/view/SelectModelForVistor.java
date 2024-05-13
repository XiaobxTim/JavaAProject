package view;

import util.ColorMap;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

public class SelectModelForVistor extends JFrame {
    private JButton ClassicMode;
    private JButton CustomMode;
    private JLabel selectMode;
    private Image image;
    public SelectModelForVistor(int width, int height) {
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
        this.setTitle("Select Model");
        this.setLayout(null);
        this.setSize(width, height);
        ColorMap.InitialColorMap();
        JList<String> jl=new JList<>(new MyListModel());
        JScrollPane js=new JScrollPane(jl);
        js.setBounds(30,100,310,240);
        this.add(js);
        jl.setFixedCellHeight(50);
        jl.setFont(jl.getFont().deriveFont(22.0f));

        this.selectMode = createLabel("Please Select Mode", new Font("serif", Font.ITALIC, 45), new Point(150, 10), 500, 50);
        selectMode.setForeground(Color.BLACK);
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
                        GameFrameForVisitor gameFrameForVisitor=new GameFrameForVisitor(400,500);
                        gameFrameForVisitor.setVisible(true);
                    }
                    if (str.equals("Custom Mode")){
                        setVisible(false);
                        int size = Integer.parseInt(JOptionPane.showInputDialog(this, "Input Size:"));
                        if (size<=10){
                            CustomFrameForVisitor customFrameForVisitor=new CustomFrameForVisitor(400,500,size);
                            customFrameForVisitor.setVisible(true);
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
    private JLabel createLabel(String name, Font font, Point location, int width, int height) {
        JLabel label = new JLabel(name);
        label.setFont(font);
        label.setLocation(location);
        label.setSize(width, height);
        this.add(label);
        return label;
    }

}
class MyListModel extends AbstractListModel<String> {
    private String[] contents = {"Classic Mode", "Custom Mode"};
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
