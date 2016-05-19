package bjohnson;

import java.util.HashMap;

public class Request {
    private String method;
    private String URL;
    private String protocol;
    private String body;
    private HashMap<String, String> headers;

    public void setMethod(String method) {
        this.method = method;
    }

    public String getMethod() {
        return method;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    public String getURL() {
        return URL;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public String getProtocol() {
        return protocol;
    }

    public String getRoute() {
        return this.method + " " + this.URL;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getBody() {
        return body;
    }

    public void setHeaders(HashMap<String, String> headers) {
        this.headers = headers;
    }

    public HashMap<String, String> getHeaders() {
        return headers;
    }
}
