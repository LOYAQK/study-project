package socket;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 服务端
 */
public class SocketTCP01Server {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(9999);
        System.out.println("俺的端口号是9999...");
        Socket socket = serverSocket.accept();
        System.out.println("来呀...");
        InputStream inputStream = socket.getInputStream();
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        char[] buf = new char[1024];
        int readLine = 0;
        while ((readLine = inputStreamReader.read(buf)) != -1) {
            System.out.println(new String(buf,0,readLine));
        }
        OutputStream outputStream = socket.getOutputStream();
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream);
        outputStreamWriter.write("hello,client");
        socket.shutdownOutput();
        outputStreamWriter.close();
        inputStreamReader.close();
        serverSocket.close();
        outputStream.close();
        socket.close();
        inputStream.close();
    }
}
