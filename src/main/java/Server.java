import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private final Router router;
    private ServerSocket listener;
    private Socket clientSocket;
    private PrintWriter out;
    BufferedReader in;

    public Server(Router router){
        this.router = router;
    }

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
                Response response = router.getResponse(request.getRoute());
                if (request.getURL().equals("/file1")){
                    response.setBody("file1 contents");
                }
                out.print(response.buildResponse());
                System.out.println(response.buildResponse());


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
