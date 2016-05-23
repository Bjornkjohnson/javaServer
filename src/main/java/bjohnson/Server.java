package bjohnson;

import bjohnson.ResponseHandlers.Response;
import bjohnson.ResponseHandlers.ResponseBuilderInterface;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

class Server {
    private final Router router;
    private final String publicDir;
    private RequestLogger logger;
    private ServerSocket listener;
    private Socket clientSocket;
    private OutputStream out;
    private BufferedReader in;

    public Server(Router router, String publicDir, RequestLogger logger){
        this.router = router;
        this.publicDir = publicDir;
        this.logger = logger;
        System.out.println(this.publicDir);
    }

    private boolean isRunning(){
        return !listener.isClosed();
    }

    public void start(int port) {
        try {
            listener = new ServerSocket(port);
            while (isRunning()) {
                clientSocket = listener.accept();
                out = clientSocket.getOutputStream();
                in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                Request request = new RequestBuilder(in, new ParameterParser()).buildRequest();
                logger.logRequest(request.getStatusLine());
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
