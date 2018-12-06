import com.sun.net.httpserver.HttpServer;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Serversocket {

    public static void main(String[] args){

        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(10000);
            System.out.printf("Listener success , and address %s \n", serverSocket.getLocalSocketAddress().toString());
            while(true){
                Socket socket = serverSocket.accept();
                System.out.printf("Receiver Request, from %s:%d \n", socket.getInetAddress().getHostAddress(), socket.getPort());
                handleClient(socket);
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.printf("Listener failed, and address %s \n", serverSocket.getLocalSocketAddress().toString());
            try {
                serverSocket.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }

    }

    public static void handleClient(Socket socket) throws IOException {
        DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
        DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());

        int size = dataInputStream.read();
        byte[] bs = new byte[size];
        dataInputStream.read(bs);

        String request = new String(bs);
        System.out.println("Request content is:" + request);

        String requestParams = request.substring(0, request.indexOf("\r\n")).split(" ")[1];
        String contentType;
        if(requestParams.indexOf("html") != -1 || requestParams.indexOf("htm") != -1){
            contentType = "text/html";
        }else if(requestParams.indexOf("jpg") != -1 || requestParams.indexOf("jpeg") != -1){
            contentType = "image/jpeg";
        }else if(requestParams.indexOf("gif") != -1){
            contentType = "image/gif";
        }else {
            contentType = "application/octet-stream";
        }

        String firstLine = "HTTP/1.1 200 OK\r\n";
        String responseHeader = "Content-type:" + contentType + "\r\n\r\n";
        dataOutputStream.write(firstLine.getBytes());
        dataOutputStream.write(responseHeader.getBytes());
        dataOutputStream.close();
        dataInputStream.close();
        socket.close();
    }
}
