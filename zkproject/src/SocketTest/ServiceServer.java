package SocketTest;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by ggq on 2018/3/15.
 */
public class ServiceServer {

    public static void main(String args[]) throws IOException {
        //创建一个ServerSocket，绑定到本机的8899端口上
        ServerSocket server = new ServerSocket();
        server.bind(new InetSocketAddress("localhost", 8899));
        //接受客户端的连接请求
        while (true) {
            Socket socket = server.accept();
            new Thread(new ServiceServerTask(socket)).start();
        }

    }
}
