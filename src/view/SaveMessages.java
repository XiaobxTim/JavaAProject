//package view;
//
//import model.GridNumber;
//
//import javax.swing.*;
//import java.io.File;
//import java.io.FileWriter;
//import java.util.Scanner;
//
//public class SaveMessages {
//    private JButton Save;
//
//    public SaveMessages(String account, String mode, GridNumber gridNumber) {
//        this.Save = new JButton("Save");
//        Save.addActionListener(e -> {
//            int n = gridNumber.getX_COUNT();
//            int m = gridNumber.getY_COUNT();
//            int[][] numbers = new int [n][m];
//
//            for (int i = 0; i < n; i++) {
//                for (int j = 0; j < m; j++) {
//                    numbers[i][j] = gridNumber.getNumber(i, j);
//                }
//            }
//
//            File file = new File("src/" + account  + "storedMessages.txt");
//            try {
//                if (file.createNewFile()) {
//                    FileWriter fileWriter = new FileWriter(file);
//                    fileWriter.close();
//                    JOptionPane.showMessageDialog(this, "OK!");
//
//                } else {
////                    System.out.println("create failed");
//                    JOptionPane.showMessageDialog(this, "Fail!");
//                }
//            } catch (Exception exception) {
//                exception.printStackTrace();
//            }
//
//        });
//    }
//}