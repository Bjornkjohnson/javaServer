package bjohnson;

import bjohnson.ResponseHandlers.Response;
import bjohnson.ResponseHandlers.ResponseBuilderInterface;

import java.io.BufferedReader;
import java.io.OutputStream;

public class ServerThread implements Runnable {
    private BufferedReader in;
    private Router router;
    private RequestLogger logger;
    private OutputStream out;

    public ServerThread(BufferedReader in, OutputStream out , Router router ,RequestLogger logger) {
        this.in = in;
        this.out = out;
        this.router = router;
        this.logger = logger;
    }

    private void writeToStream(Response response) throws Exception {
        out.write(response.buildResponse());
        out.flush();
        out.close();
    }

    @Override
    public void run()  {
        try {
            Request request = new RequestBuilder(in, new ParameterParser()).buildRequest();
            logger.logRequest(request.getStatusLine());
            ResponseBuilderInterface responseBuilder = router.getResponse(request.getRoute());
            writeToStream(responseBuilder.getResponse(request));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
