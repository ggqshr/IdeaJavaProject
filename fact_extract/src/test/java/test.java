import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class test {
    @Test
    public void test() {
        File file = new File("D:\\Project\\intellij project\\fact_extract\\src\\main\\resources\\111.txt");
        BufferedReader reader = null;
        try {
            System.out.println("以行为单位读取文件内容，一次读一整行：");
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
            StringBuilder sb = new StringBuilder();
            int line = 1;
            // 一次读入一行，直到读入null为文件结束
            while ((tempString = reader.readLine()) != null) {
                // 显示行号
//                System.out.println("line " + line + ": " + tempString);
                sb.append(tempString);
                line++;
            }
            String[] strings = sb.toString().split("([。！!.？?])");
            for (String string : strings) {
                System.out.println(string);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
    }
}
