import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class wirteToFile {
    public static void writeTo(HashMap<String, Object> content) {
        String mac = (String) content.get("mac");
        @SuppressWarnings("unchecked")
        ArrayList<Integer> rssis = (ArrayList<Integer>) content.get("rssi");
        StringBuilder sb = new StringBuilder();
        sb.append(mac + "  ");
        for (Integer integer : rssis) {
            sb.append(integer + "  ");
        }
        sb.append("\n");
        fileWirte fWirte = new fileWirte(sb.toString());
        fWirte.start();
    }

    static class fileWirte extends Thread {
        public String wirteContent;

        public fileWirte(String s) {
            wirteContent = s;
        }

        @Override
        public void run() {
            // TODO Auto-generated method stub
            super.run();
            File file = new File("D:" + File.separator
                    + "java ee" + File.separator
                    + "Testzhen"
                    + File.separator + "result.txt");
            if (!file.exists()) {
                try {
                    file.createNewFile();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            try {
                FileWriter writer = new FileWriter(file, true);
                writer.write(wirteContent);
                writer.flush();
                writer.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}
