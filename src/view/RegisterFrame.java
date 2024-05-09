package view;

import util.ColorMap;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.Scanner;


public class RegisterFrame extends JFrame {
    private JLabel Account;
    private JLabel Password;
    private JButton OK;
    private JButton Cancel;
    public RegisterFrame(int width,int height) {
        SpringLayout springLayout = new SpringLayout();
        Container container = getContentPane();
        this.setTitle("Register Interface");
        this.setLayout(springLayout);
        this.setSize(width, height);
        ColorMap.InitialColorMap();
        this.Account = new JLabel("Account:");
        this.Password = new JLabel("Password:");
        this.add(Account);
        this.add(Password);
        springLayout.putConstraint(SpringLayout.NORTH, Account, 100, SpringLayout.NORTH, container);
        springLayout.putConstraint(SpringLayout.WEST, Account, 20, SpringLayout.WEST, container);
        JTextField AccountField = new JTextField();
        this.add(AccountField);
        springLayout.putConstraint(SpringLayout.NORTH, AccountField, 100, SpringLayout.NORTH, container);
        springLayout.putConstraint(SpringLayout.WEST, AccountField, 20, SpringLayout.EAST, Account);
        springLayout.putConstraint(SpringLayout.EAST, AccountField, -20, SpringLayout.EAST, container);
        springLayout.putConstraint(SpringLayout.NORTH, Password, 20, SpringLayout.SOUTH, Account);
        springLayout.putConstraint(SpringLayout.WEST, Password, 20, SpringLayout.WEST, container);
        JTextField PasswordField = new JTextField();
        this.add(PasswordField);
        springLayout.putConstraint(SpringLayout.NORTH, PasswordField, 20, SpringLayout.SOUTH, AccountField);
        springLayout.putConstraint(SpringLayout.WEST, PasswordField, 20, SpringLayout.EAST, Password);
        springLayout.putConstraint(SpringLayout.EAST, PasswordField, -20, SpringLayout.EAST, container);

        this.OK = new JButton("OK");
        this.Cancel = new JButton("Cancel");
        this.add(OK);
        this.add(Cancel);
        springLayout.putConstraint(SpringLayout.SOUTH, OK, -125, SpringLayout.SOUTH, container);
        springLayout.putConstraint(SpringLayout.SOUTH, Cancel, -125, SpringLayout.SOUTH, container);
        springLayout.putConstraint(SpringLayout.EAST, Cancel, -200, SpringLayout.EAST, container);
        springLayout.putConstraint(SpringLayout.SOUTH, PasswordField, -150, SpringLayout.NORTH, Cancel);
        springLayout.putConstraint(SpringLayout.EAST, OK, -100, SpringLayout.WEST, Cancel);

        Cancel.addActionListener(e -> {
            Cancel.setFocusable(true);
            setVisible(false);
            LoginFrame loginFrame=new LoginFrame(700,500);
            loginFrame.setVisible(true);
            Cancel.setFocusable(false);
        });
        OK.addActionListener(e -> {

        });
        //!

        OK.addActionListener(e -> {
            // AccountField.getText()
//            PasswordField.getText()
            String account;
            String password;
//            Scanner input = new Scanner(System.in);
            account = AccountField.getText();
            password = PasswordField.getText();


            if (account.isEmpty()) {
                return;
            }

            File file = new File("src/" + account + "_password.txt");
            try {
                if (file.createNewFile()) {
                    FileWriter fileWriter = new FileWriter(file);
                    fileWriter.write(password);
                    fileWriter.close();
                    JOptionPane.showMessageDialog(this, "OK!");
                } else {
                    System.out.println("create failed");
                    JOptionPane.showMessageDialog(this, "Fail!");
                }
            } catch (Exception exception){
                exception.printStackTrace();
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
    }
}
