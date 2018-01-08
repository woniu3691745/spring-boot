package com.lidl.socket;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class TalkServer {
    public static void main(String args[]) {
        try {
            ServerSocket server = null;
            Socket socket = null;
            try {
                server = new ServerSocket(4700);
                socket = server.accept();
            } catch (Exception e) {
                System.out.println("can not listen to:" + e);
            }

            // 获得client信息
            assert socket != null;
            BufferedReader is = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            System.out.println("Client -> " + is.readLine());

            // 创建输出，并打开socket输出流
            PrintWriter os = new PrintWriter(socket.getOutputStream());
            BufferedReader sin = new BufferedReader(new InputStreamReader(System.in));

            String line = sin.readLine();
            while (!line.equals("bye")) {
                os.println(line);
                //向客户端输出该字符串
                os.flush();
                //刷新输出流，使Client马上收到该字符串
                System.out.println("Client -> " + is.readLine());
                line = sin.readLine();
            }
            os.close();
            is.close();
            socket.close();
            server.close();
        } catch (Exception e) {
            System.out.println("Error:" + e);
        }
    }
}
