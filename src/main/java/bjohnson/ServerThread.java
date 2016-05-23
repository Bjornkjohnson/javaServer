package bjohnson;

import bjohnson.ResponseHandlers.Response;
import bjohnson.ResponseHandlers.ResponseBuilderInterface;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

public class ServerThread implements Runnable {
    private Socket clientSocket;
    private Router router;
    private RequestLogger logger;

    public ServerThread(Socket clientSocket, Router router ,RequestLogger logger) {
        this.clientSocket = clientSocket;
        this.router = router;
        this.logger = logger;
    }

    @Override
    public void run()  {
        try {
            OutputStream out = clientSocket.getOutputStream();
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            Request request = new RequestBuilder(in, new ParameterParser()).buildRequest();
            logger.logRequest(request.getStatusLine());
            ResponseBuilderInterface responseBuilder = router.getResponse(request.getRoute());
            Response response = responseBuilder.getResponse(request);
            out.write(response.buildResponse());
            out.flush();
            out.close();
        } catch (Exception e) {
            System.err.println(e);
        }

    }
}
