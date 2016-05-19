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
        String rawRequest = in.readLine();
        String header;
        while ((header = in.readLine()).length() != 0){
            addHeader(header);
        }
        request.setHeaders(headers);
        buildRequestLine(rawRequest);
        return request;
    }

    private void addHeader(String header) {
        String[] parsedHeader = header.split(": ");
        headers.put(parsedHeader[0], parsedHeader[1]);
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
