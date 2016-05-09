import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private ServerSocket listener;
    private Socket clientSocket;
    private OutputStream out;
    BufferedReader in;

    public Server(int port){
        try {
            listener = new ServerSocket(port);
        } catch (IOException e) {
            System.out.println("Exception caught when trying to listen on port "
                    + port + " or listening for a connection");
            System.out.println(e.getMessage());
        }
    }

    public boolean isRunning(){
        return !listener.isClosed();
    }

    public void start() {
        try {
            while (isRunning()) {
                clientSocket = listener.accept();
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                String response = "HTTP/1.1 200 OK\r\n";
                out.print(response);
                System.out.println(in.readLine());
                out.close();
            }
        } catch (IOException e){
            throw new RuntimeException(e);
        }

    }

    public void stop() {
        try {listener.close();}
        catch (IOException e) { throw new RuntimeException(e);}
    }

}
