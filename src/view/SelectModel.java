package view;

import util.ColorMap;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.text.DefaultCaret;
import javax.swing.text.StyledEditorKit;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class SelectModel extends JFrame {
    public JButton ClassicMode;
    public JButton AdventureMode;
    public JButton AIMode;
    public JButton CustomMode;
    public JButton TimeLimitMode;
    public JButton EntertainingMode;
    private JButton OK;
    private JLabel selectMode;
    private Image image;
    private ImageIcon icon1;
    private ImageIcon icon2;
    private String selectValue;
    private JPanel jPanel;
    private File ClassicFile;
    private File CustomFile;
    private File TimeLimitFile;
    private File EntertainingFile;
    private File AIFile;
    public SelectModel(int width, int height,String account){
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
        JList<String> jl=new JList<>(new MyListMode());
        JScrollPane js=new JScrollPane(jl);
        js.setBounds(10,100,340,240);
        this.add(js);
        jl.setFixedCellHeight(50);
        jl.setFont(jl.getFont().deriveFont(22.0f));
        jl.setCellRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(
                    JList<?> list,
                    Object value,
                    int index,
                    boolean isSelected,
                    boolean cellHasFocus) {

                // 调用父类的实现以获取基本的渲染组件
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

                // 设置文本居中（水平和垂直）
                setHorizontalAlignment(JLabel.CENTER);
                setVerticalAlignment(JLabel.CENTER);

                // 如果你想在文本周围添加一些空白，可以添加EmptyBorder
                setBorder(new EmptyBorder(5, 10, 5, 10)); // 上、左、下、右的空白

                return this;
            }
        });
        JFrame jFrame=this;

        this.selectMode = createLabel("Please Select Mode", new Font("serif", Font.ITALIC, 45), new Point(150, 10), 500, 50);
        /*jl.addMouseListener(new MouseAdapter() {
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
        });*/
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
            ClickSound.playSound(getClass(),  "ClickButton.wav");
            if (selectValue.equals("Classic Mode")){
                ClassicFile = new File("src/" + account + "_ClassicMode.txt");
               // ClassicFile = new File("src/" + account + "_ClassicMode.txt");
                if (!ClassicFile.exists()){
                    try{
                        System.out.println("create file");
                        FileWriter fileWriter = new FileWriter(ClassicFile,true);
                        fileWriter.close();
                    }catch (Exception exception){
                        exception.printStackTrace();
                    }
                }
                setVisible(false);
                GameFrame gameFrame=new GameFrame(400,500,jFrame,account);
                gameFrame.setVisible(true);
            }
            if (selectValue.equals("Custom Mode")){
                setVisible(false);
                int size = Integer.parseInt(JOptionPane.showInputDialog(this, "Input Size:"));
                if (size<=10){
                    CustomFile = new File("src/" + account +size+ "_CustomMode.txt");
                    if (!CustomFile.exists()){
                        try{
                            System.out.println("create file");
                            FileWriter fileWriter = new FileWriter(CustomFile,true);
                            fileWriter.close();
                        }catch (Exception exception){
                            exception.printStackTrace();
                        }
                    }
                    CustomFrame customFrame=new CustomFrame(400,500,size,jFrame,account);
                    customFrame.setVisible(true);
                }else {
                    JOptionPane.showMessageDialog(null,"Please input again");
                }
            }
            if (selectValue.equals("TimeLimit Mode")){
                TimeLimitFile = new File("src/" + account + "_TimeLimitMode.txt");
                if (!TimeLimitFile.exists()){
                    try{
                        System.out.println("create file");
                        FileWriter fileWriter = new FileWriter(TimeLimitFile,true);
                        fileWriter.close();
                    }catch (Exception exception){
                        exception.printStackTrace();
                    }
                }
                setVisible(false);
                TimeLimitFrame timeLimitFrame=new TimeLimitFrame(400,500,jFrame,account);
                timeLimitFrame.setVisible(true);
            }
            if (selectValue.equals("Entertaining Mode")){
                EntertainingFile = new File("src/" + account + "_EntertainingMode.txt");
                if (!EntertainingFile.exists()){
                    try{
                        System.out.println("create file");
                        FileWriter fileWriter = new FileWriter(EntertainingFile,true);
                        fileWriter.close();
                    }catch (Exception exception){
                        exception.printStackTrace();
                    }
                }
                setVisible(false);
                EntertainingFrame entertainingFrame=new EntertainingFrame(400,500,jFrame,account);
                entertainingFrame.setVisible(true);
            }
            if (selectValue.equals("AI Mode")){

                AIFile = new File("src/" + account + "_AIMode.txt");
                if (!AIFile.exists()){
                    try{
                        System.out.println("create file");
                        FileWriter fileWriter = new FileWriter(AIFile,true);
                        fileWriter.close();
                    }catch (Exception exception){
                        exception.printStackTrace();
                    }
                }
                setVisible(false);
                AIFrame aiFrame=new AIFrame(400,500,jFrame,account);
                aiFrame.setVisible(true);
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
        JFrame gameFrame1=this;
        gameFrame1.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                centerWindow(gameFrame1);
            }
        });
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
        if (selectValue.equals("TimeLimit Mode")){
            jPanel.removeAll();
            JLabel jLabel=new JLabel("TimeLimit Mode");
            jPanel.add(jLabel,BorderLayout.NORTH);
            icon2=null;
            try{
                icon2 = new ImageIcon(ImageIO.read((new File("src/TimeLimit.png"))));
            }catch (IOException e){
                e.getStackTrace();
            }
            JLabel jLabel1=new JLabel(icon2);
            jPanel.add(jLabel1,BorderLayout.EAST);
            JTextArea textArea=new JTextArea("TimeLimit mode involves merging numbers by sliding tiles to achieve a higher score before the countdown ends, with 2048 being the default target.");
            textArea.setEditable(false);
            textArea.setLineWrap(true);
            textArea.setWrapStyleWord(true);
            DefaultCaret caret = (DefaultCaret)textArea.getCaret();
            caret.setVisible(false);
            jPanel.add(textArea);
            jPanel.revalidate();
            jPanel.repaint();
        }
        if (selectValue.equals("Entertaining Mode")){
            jPanel.removeAll();
            JLabel jLabel=new JLabel("Entertaining Mode");
            jPanel.add(jLabel,BorderLayout.NORTH);
            icon2=null;
            try{
                icon2 = new ImageIcon(ImageIO.read((new File("src/Entertaining.png"))));
            }catch (IOException e){
                e.getStackTrace();
            }
            JLabel jLabel1=new JLabel(icon2);
            jPanel.add(jLabel1,BorderLayout.EAST);
            JTextArea textArea=new JTextArea("In the entertaining mode, you can earn a certain amount of coins when you reach a certain goal value, during which time some obstacles may appear. You can use the coins to buy some props.");
            textArea.setEditable(false);
            textArea.setLineWrap(true);
            textArea.setWrapStyleWord(true);
            DefaultCaret caret = (DefaultCaret)textArea.getCaret();
            caret.setVisible(false);
            jPanel.add(textArea);
            jPanel.revalidate();
            jPanel.repaint();
        }
        if (selectValue.equals("AI Mode")){
            jPanel.removeAll();
            JLabel jLabel=new JLabel("AI Mode");
            jPanel.add(jLabel,BorderLayout.NORTH);
            icon1=null;
            try{
                icon1 = new ImageIcon(ImageIO.read((new File("src/classic.png"))));
            }catch (IOException e){
                e.getStackTrace();
            }
            JLabel jLabel1=new JLabel(icon1);
            jPanel.add(jLabel1,BorderLayout.EAST);
            JTextArea textArea=new JTextArea("An AI model is designed based on the minimax algorithm with alpha-beta pruning to achieve higher scores using AI.");
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
    private static void centerWindow(Window window) {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension windowSize = window.getSize();

        // 计算新位置，使得窗体在屏幕中央
        int x = (screenSize.width - windowSize.width) / 2;
        int y = (screenSize.height - windowSize.height) / 2;

        // 设置窗体的新位置
        window.setLocation(x, y);
    }
}
class MyListMode extends AbstractListModel<String> {
    private String[] contents = {"Classic Mode", "AI Mode", "Custom Mode", "TimeLimit Mode", "Entertaining Mode"};
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