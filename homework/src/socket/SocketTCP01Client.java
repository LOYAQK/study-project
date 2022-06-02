package socket;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

/**
 * 客户端
 */
public class SocketTCP01Client {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket(InetAddress.getLocalHost(), 9999);
        OutputStream outputStream = socket.getOutputStream();
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream);
        outputStreamWriter.write("hello,server");
        socket.shutdownOutput();
        InputStream inputStream = socket.getInputStream();
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        char[] buf = new char[1024];
        int readLine = 0;
        while ((readLine = inputStreamReader.read(buf)) != -1) {
            System.out.println(new String(buf,0,readLine));
        }
        inputStreamReader.close();
        outputStreamWriter.close();
        outputStream.close();
        inputStream.close();
        socket.close();
    }
}
