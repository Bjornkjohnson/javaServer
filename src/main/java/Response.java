import java.util.HashMap;

public class Response {
    private final String CRLF = "\r\n";
    private final String PROTOCOL = "HTTP/1.1 ";
    private String status = "200 OK";
    private byte[] body;
    private HashMap<String, String> headers = new HashMap<>();

    public byte[] buildStatusAndHeaderBytes() {
        String statusAndHeaders = PROTOCOL + status + CRLF +
                buildHeader() + CRLF;
        return statusAndHeaders.getBytes();
    }

    public void setBody(byte[] body) {
        this.body = body;
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


    public byte[] getBody() {
        return body;
    }
}
