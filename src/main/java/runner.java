
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class runner {
    public static void main(String [] args) {
//        Server server = new Server(5000);
        try {
            ServerSocket serverSocket = new ServerSocket(5000);
            System.out.println("HTTPServer started...\nport: 5000\n");
            while (true) {
                Socket clientSocket = serverSocket.accept();
                OutputStream out = clientSocket.getOutputStream();
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                String response = "HTTP/1.1 200 OK\r\n";
                out.write(response.getBytes());
                System.out.println(in.readLine());
                out.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
