package bjohnson;

import java.io.BufferedReader;
import java.util.HashMap;

public class RequestBuilder {
    private BufferedReader in;
    private Request request;
    private HashMap<String, String> headers;


    public RequestBuilder(BufferedReader in) throws Exception{
        this.in = in;
        request = new Request();
        headers = new HashMap<>();
    }

    public Request buildRequest() throws Exception {
        buildRequestLine();
        buildHeaders();
        buildBody();
        return request;
    }

    private void buildBody() throws Exception{
        request.setBody(in.readLine());
    }

    private void buildHeaders() throws Exception{
        String header;
        while ((header = in.readLine()).length() != 0){
            addHeader(header);
        }
        request.setHeaders(headers);
    }

    private void addHeader(String header) {
        String[] parsedHeader = header.split(": ");
        headers.put(parsedHeader[0], parsedHeader[1]);
    }

    private void buildRequestLine() throws Exception{
        String rawRequest = in.readLine();
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
