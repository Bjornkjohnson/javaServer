public class Request {
    String rawRequest;
    public Request(String rawRequest) {
        this.rawRequest = rawRequest;
    }

    public String getMethod() {
        return rawRequest.split(" ")[0];
    }

    public String getURL() {
        return rawRequest.split(" ")[1];
    }
}
