import java.io.BufferedReader;

public class RequestBuilder {
    private BufferedReader in;

    public RequestBuilder(BufferedReader in) throws Exception{
        this.in = in;
    }

    public Request buildRequest() throws Exception {
        Request request = new Request();
        String rawRequest = in.readLine();
        request.setMethod(extractMethod(rawRequest));
        request.setURL(extractURL(rawRequest));
        return request;
    }



    private String extractMethod(String rawRequest) {
        return rawRequest.split(" ")[0];
    }

    private String extractURL(String rawRequest) {
        return rawRequest.split(" ")[1];
    }
}
