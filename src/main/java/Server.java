import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private ServerSocket listener;
    private Socket socket;
    private OutputStream out;

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
            socket = listener.accept();
            out = socket.getOutputStream();
        } catch (IOException e){
            throw new RuntimeException(e);
        }

    }

    public void stop() {
        try {listener.close();}
        catch (IOException e) { throw new RuntimeException(e);}
    }

    public void getResponse() {

        try {
            String response = "HTTP/1.1 200 OK\r\n";
            out.write(response.getBytes());
        } catch (IOException e) { throw new RuntimeException(e);}
    }
}
