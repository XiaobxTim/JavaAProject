package view;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

    public class LoadFile {

        public static void main(String[] args) {
            String inputFilePath = "path/to/your/input_file.txt";  // 输入文件路径
            String outputFilePath = "path/to/your/output_file.txt"; // 输出文件路径

            // 捕获System.out的输出内容
            ByteArrayOutputStream baos = new ByteArrayOutputStream();//对象baos用来存储‘system.out'的内容
            PrintStream ps = new PrintStream(baos);//创建一个 PrintStream 对象 ps，并将其包装在 baos 中
            PrintStream old = System.out;//保存原始的 System.out 到 PrintStream 对象 old 中
            System.setOut(ps);//将 System.out 重定向到 ps，这样所有通过 System.out 打印的信息都会写入到 baos 中

            // 模拟输出内容
            System.out.println("This is the content that was printed in Java.");
            System.out.println("This is another line of content.");

            // 将输出内容重置回控制台
            System.out.flush();//调用 System.out.flush() 确保所有缓冲的输出内容被写入
            System.setOut(old);//将 System.out 重置回原始的输出流 old

            // 获取捕获的内容
            String content = baos.toString();//将 baos 中的内容转换为字符串，存储在 content 变量中

            //修改其捕获内容
            String modifiedContent = modifyContent(content);

            // 将修改后的内容写回文件
            SaveToFile.writeContentToFile(outputFilePath, modifiedContent);
        }

        // 修改内容的方法
        public static String modifyContent(String content) {
            // 这里简单地将内容转换为大写，需要进行修改
            return content.toUpperCase();
            //我觉得就是直接把数组重写进来就行
        }
    }





