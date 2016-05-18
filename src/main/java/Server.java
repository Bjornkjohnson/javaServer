import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private final Router router;
    private final String publicDir;
    private ServerSocket listener;
    private Socket clientSocket;
    private OutputStream out;
    BufferedReader in;

    public Server(Router router, String publicDir){
        this.router = router;
        this.publicDir = publicDir;
        System.out.println(this.publicDir);
    }

    public boolean isRunning(){
        return !listener.isClosed();
    }

    public void start(int port) {
        try {
            listener = new ServerSocket(port);
            while (isRunning()) {
                clientSocket = listener.accept();
                out = clientSocket.getOutputStream();
                in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                Request request = new RequestBuilder(in).buildRequest();
                ResponseBuilderInterface responseBuilder = router.getResponse(request.getRoute());
                Response response = responseBuilder.getResponse(request);
                out.write(response.buildResponse());
                out.flush();
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
