import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private ServerSocket listener;
    private Socket clientSocket;
    private PrintWriter out;
    BufferedReader in;

    public boolean isRunning(){
        return !listener.isClosed();
    }

    public void start(int port) {
        try {
            listener = new ServerSocket(port);
            while (isRunning()) {
                clientSocket = listener.accept();
                out = new PrintWriter(clientSocket.getOutputStream(), true);
                in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                String message;
                while ((message = in.readLine()) != null) {
                    System.out.println(message);
                    if (message.equals("")) {
                        break;
                    }
                }
                Response response = new Response();
                out.print(response.getResponse() + "hello\r\n");
                out.close();
            }
        } catch (IOException e){
            throw new RuntimeException(e);
        }

    }

    public void stop() {
        try {
            in.close();
            out.close();
            listener.close();
        }
        catch (IOException e) { throw new RuntimeException(e);}
    }

}
