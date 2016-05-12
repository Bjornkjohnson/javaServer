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
                Request request = new RequestBuilder(in).buildRequest();
                System.out.println(request.getMethod());
                System.out.println(request.getURL());
                Response response = new Response();
                if (request.getURL().equals("/file1")){
                    response.setBody("file1 contents");
                }
                out.print(response.getResponse());
                out.close();
            }
        } catch (Exception e){
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
