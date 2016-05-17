import java.util.HashMap;

public class Response {
    private final String CRLF = "\r\n";
    private final String PROTOCOL = "HTTP/1.1 ";
    private String status = "200 OK";
    private String body = "";
    private HashMap<String, String> headers = new HashMap<>();

    public String buildResponseString() {
        return PROTOCOL + status + CRLF +
                buildHeader() + CRLF +
                body;
    }

    public void setBody(String body) {
        this.body = body + CRLF;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void addHeader(String type, String value) {
        headers.put(type, value);
    }

    private String buildHeader() {
        String headerString = "";
        for (String key : headers.keySet())
            headerString += key + ": " + headers.get(key) + CRLF;
        return headerString;
    }
}
