import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private int port;
    private ServerSocket listener;
    private Socket socket;

    public Server(int port){
        this.port = port;
        try {
            listener = new ServerSocket(port);
        } catch (IOException e) {
        }
    }

    public boolean accept(){
        try {
            socket = listener.accept();
            return true;
        } catch (IOException e){
            return false;
        }
    }
}
