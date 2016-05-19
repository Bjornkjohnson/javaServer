package bjohnson;

import java.io.BufferedReader;

public class RequestBuilder {
    private BufferedReader in;
    private Request request;


    public RequestBuilder(BufferedReader in) throws Exception{
        this.in = in;
        request = new Request();

    }

    public Request buildRequest() throws Exception {
        String rawRequest = in.readLine();
        buildRequestLine(rawRequest);
        return request;
    }

    private void buildRequestLine(String rawRequest){
        extractMethod(rawRequest);
        extractURL(rawRequest);
        extractProtocol(rawRequest);
    }

    private void extractMethod(String rawRequest) {
        request.setMethod(rawRequest.split(" ")[0]);
    }

    private void extractURL(String rawRequest) {
        request.setURL(rawRequest.split(" ")[1]);
    }

    private void extractProtocol(String rawRequest) {
        request.setProtocol(rawRequest.split(" ")[2]);
    }
}
