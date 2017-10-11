package com.lidl.socket;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * reader 字节流
 */
public class TalkClient {
    public static void main(String args[]) {
        try {
            Socket socket = new Socket("127.0.0.1", 4700);

            BufferedReader sin = new BufferedReader(new InputStreamReader(System.in));
            String readline = sin.readLine(); //从系统标准输入读入一字符串

            // 把信息发送给server
            // 创建输出，并打开socket输出流
            PrintWriter os = new PrintWriter(socket.getOutputStream());

            // 获得server返回信息
            BufferedReader is = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//            System.out.println("Server -> " + is.readLine());

            while (!readline.equals("bye")) {
                // 信息发送给server
                os.println(readline);
                // server
                os.flush();
                System.out.println("Server -> " + is.readLine());
                readline = sin.readLine();
            }
            os.close();
            is.close();
            socket.close();
        } catch (Exception e) {
            System.out.println("Error" + e);
        }
    }
}
