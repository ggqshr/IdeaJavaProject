import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by ggq on 2017/6/9.
 */
@WebServlet(name = "Test", urlPatterns = "/g")
public class Test extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        out.print("ok");
        wirteToFile wirteToFile = new wirteToFile();
        ArrayList<Integer> rssis = new ArrayList<>();
        HashMap<String, Object> map = new HashMap<>();
        String data = request.getParameter("data");
        String delimter = "\1";
        int startp = data.indexOf(delimter);
        if (startp != 1) {
            int endp = data.lastIndexOf(delimter);
            if ((endp - startp) - 1 > 12) {
                String datatrimed = data.substring(startp + 1, endp);
                String[] datasplits = datatrimed.split(delimter);
                for (int i = 0; i < datasplits.length; i++) {
                    if (datasplits[i].length() > 12) {
                        map.clear();
                        String mac = datasplits[i].substring(0, 12);
                        map.put("mac", mac);
//						System.out.println(mac);
                        byte[] datasplitbyte = datasplits[i].getBytes();
                        rssis.clear();
                        for (int j = 12; j < datasplitbyte.length; j++) {
                            int rssi = datasplitbyte[j];
                            rssis.add(rssi);
//							System.out.println(rssi);
                        }
                        map.put("rssi", rssis);
                        wirteToFile.writeTo(map);
                    }
                }
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.print("!!");
        PrintWriter out = response.getWriter();
        out.print("ok");
        out.flush();
        out.close();
    }
}
