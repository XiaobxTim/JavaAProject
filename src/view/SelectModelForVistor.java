package view;

import util.ColorMap;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.text.DefaultCaret;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

public class SelectModelForVistor extends JFrame {
    private JButton ClassicMode;
    private JButton CustomMode;
    private JButton OK;
    private JLabel selectMode;
    private Image image;
    private JPanel jPanel;
    private ImageIcon icon1;
    private ImageIcon icon2;
    private String selectValue;
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
        JFrame jFrame=this;
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
        jl.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        this.selectMode = createLabel("Please Select Mode", new Font("serif", Font.ITALIC, 45), new Point(150, 10), 500, 50);
        selectMode.setForeground(Color.BLACK);

        this.jPanel=new JPanel(new BorderLayout());
        jPanel.setBounds(350,100,310,240);
        this.add(jPanel);

        jl.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()){
                    selectValue= jl.getSelectedValue();
                    if (selectValue !=null){
                        updateContentPanel(selectValue);
                    }
                }
            }
        });

        Font font=new Font("serif",Font.BOLD,15);
        this.OK=createButton("OK",new Point(300,350),100,50);
        OK.setFont(font);
        OK.setForeground(Color.BLACK);
        OK.setOpaque(false);
        OK.setContentAreaFilled(false);
        OK.addActionListener(e -> {
            if (selectValue.equals("Classic Mode")){
                setVisible(false);
                GameFrameForVisitor gameFrameForVisitor=new GameFrameForVisitor(400,500);
                gameFrameForVisitor.setVisible(true);
            }
            if (selectValue.equals("Custom Mode")){
                setVisible(false);
                int size = Integer.parseInt(JOptionPane.showInputDialog(this, "Input Size:"));
                if (size<=10){
                    CustomFrameForVisitor customFrameForVisitor=new CustomFrameForVisitor(400,500,size);
                    customFrameForVisitor.setVisible(true);
                }else {
                    JOptionPane.showMessageDialog(null,"Please input again");
                }
            }
        });
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e){
                if (e.getKeyCode()==KeyEvent.VK_ENTER){
                    OK.doClick();
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
    private JButton createButton(String name, Point location, int width, int height) {
        JButton button = new JButton(name);
        button.setLocation(location);
        button.setSize(width, height);
        this.add(button);
        return button;
    }
    private void updateContentPanel(String selectValue){
        jPanel.removeAll();
        if (selectValue.equals("Classic Mode")){
            jPanel.removeAll();
            JLabel jLabel=new JLabel("Classic Mode");
            jPanel.add(jLabel,BorderLayout.NORTH);
            icon1=null;
            try{
                icon1 = new ImageIcon(ImageIO.read((new File("src/classic.png"))));
            }catch (IOException e){
                e.getStackTrace();
            }
            JLabel jLabel1=new JLabel(icon1);
            jPanel.add(jLabel1,BorderLayout.EAST);
            JTextArea textArea=new JTextArea("The classic mode involves merging numbers by moving tiles to achieve a higher score, with 2048 being the ultimate goal by default.");
            textArea.setEditable(false);
            textArea.setLineWrap(true);
            textArea.setWrapStyleWord(true);
            DefaultCaret caret = (DefaultCaret)textArea.getCaret();
            caret.setVisible(false);
            jPanel.add(textArea);
            jPanel.revalidate();
            jPanel.repaint();
        }
        if (selectValue.equals("Custom Mode")){
            jPanel.removeAll();
            JLabel jLabel=new JLabel("Custom Mode");
            jPanel.add(jLabel,BorderLayout.NORTH);
            icon2=null;
            try{
                icon2 = new ImageIcon(ImageIO.read((new File("src/custom.png"))));
            }catch (IOException e){
                e.getStackTrace();
            }
            JLabel jLabel1=new JLabel(icon2);
            jPanel.add(jLabel1,BorderLayout.EAST);
            JTextArea textArea=new JTextArea("In the custom mode, players can customize the number of levels and merge numbers by sliding tiles to reach higher scores, with 2048 being the default target.");
            textArea.setEditable(false);
            textArea.setLineWrap(true);
            textArea.setWrapStyleWord(true);
            DefaultCaret caret = (DefaultCaret)textArea.getCaret();
            caret.setVisible(false);
            jPanel.add(textArea);
            jPanel.revalidate();
            jPanel.repaint();
        }
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
