package view;

import java.io.BufferedReader;//读取字符、数组和行
import java.io.FileReader;
import java.io.IOException;

public class FileContentReader {

    public static String readFile(String filePath) {
        StringBuilder content = new StringBuilder();//用于存储读取的文件内容，比 String 更高效，适合用于拼接字符串。

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                content.append(line).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return content.toString();
    }
}


