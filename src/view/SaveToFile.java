package view;


import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class SaveToFile {


    public static void writeFile(String filePath, String content) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(filePath))) {//文件路径
            pw.println(content);
            System.out.println("Content has been written to " + filePath);//文件路径
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}




