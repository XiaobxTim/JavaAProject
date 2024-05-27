package view;


import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

//public class SaveToFile {


    //    public static void writeFile(String filePath, String content) {
//        String filePath = "src/" + account + "_content.txt";
//        try (PrintWriter pw = new PrintWriter(new FileWriter(filePath))) {//文件路径
//            pw.println(content);
//            System.out.println("Content has been written to " + "src/" + account + "_content.txt);//文件路径
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//}
    public class SaveToFile {

        // Method to write content to a file
        public static void writeContentToFile(String filePath, String content) {
            try (PrintWriter pw = new PrintWriter(new FileWriter(filePath))) {
                pw.println(content);
                System.out.println("Content has been written to " + filePath);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        //就举个例子输入二维数组
        public static void writeArrayToFile(String filePath, int[][] array) {
            try (PrintWriter pw = new PrintWriter(new FileWriter(filePath))) {
                for (int[] row : array) {
                    for (int element : row) {
                        pw.print(element + " ");
                    }
                    pw.println();
                }
                System.out.println("Array has been written to " + filePath);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }





