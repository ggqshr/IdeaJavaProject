package SocketTest;

import java.io.*;
import java.net.Socket;
import java.util.logging.SocketHandler;

/**
 * Created by ggq on 2018/3/15.
 */
public class ServiceClient {

    public static void main(String args[]) throws Exception {
        Socket sock = new Socket("localhost", 8899);
        InputStream in = sock.getInputStream();
        OutputStream out = sock.getOutputStream();
        PrintWriter pw = new PrintWriter(out);
        pw.println("hello");
        pw.flush();
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        String line = br.readLine();
        System.out.println(line);
        in.close();
        out.close();
    }
}
