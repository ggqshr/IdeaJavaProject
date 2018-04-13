package SocketTest;

import java.io.*;
import java.net.Socket;

/**
 * Created by ggq on 2018/3/15.
 */
public class ServiceServerTask implements Runnable {
    private Socket socket = null;

    public ServiceServerTask(Socket socket) {
        this.socket = socket;
    }

    //去跟客户端进行业务交互
    @Override
    public void run() {
        InputStream in = null;
        OutputStream out = null;
        try {
            //从socket中获得网络通讯流
            in = socket.getInputStream();
            out = socket.getOutputStream();

            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            //从网络通信输入流中读取一行  注意发送的数据要带回车符
            String param = br.readLine();
            GetDataServiceImpl getdata = new GetDataServiceImpl();
            String result = getdata.getData(param);

            //将结果写入到输出流中，
            PrintWriter pw = new PrintWriter(out);
            pw.println(result);
            pw.flush();


        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                in.close();
                out.close();
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
