package bjohnson;

import java.io.BufferedReader;
import java.util.HashMap;

public class RequestBuilder {
    private BufferedReader in;
    private Request request;
    private HashMap<String, String> headers;
    private ParameterParser paramParser;


    public RequestBuilder(BufferedReader in, ParameterParser paramParser) throws Exception{
        this.in = in;
        this.paramParser = paramParser;
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
        Integer length = Integer.parseInt(headers.getOrDefault("Content-Length", "0"));

        if (length > 0) {
            StringBuilder body = new StringBuilder();
            for (int i = 0; i < length; i++) {
                body.append((char) in.read());
            }
            request.setBody(body.toString());
        }
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
        String methodAndParams = rawRequest.split(" ")[1];
        request.setURL(methodAndParams.split("\\?")[0]);
        if (methodAndParams.contains("?")){
            parseParams(methodAndParams);
        }
    }

    private void parseParams(String rawParams) {
        request.setParams(paramParser.parseParameters(rawParams));
    }

    private void extractProtocol(String rawRequest) {
        request.setProtocol(rawRequest.split(" ")[2]);
    }
}
