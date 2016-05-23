package bjohnson.ResponseHandlers;

import java.util.HashMap;

public class Response {
    private final String CRLF = "\r\n";
    private final String PROTOCOL = "HTTP/1.1 ";
    private String status = "200 OK";
    private byte[] body;
    private final HashMap<String, String> headers = new HashMap<>();

    public byte[] buildResponse() {
        String statusAndHeaders = PROTOCOL + status + CRLF +
                buildHeader() + CRLF;
        if (body != null) {
            return combineByteArrays(statusAndHeaders.getBytes(), body);
        }
        return statusAndHeaders.getBytes();

    }

    public void setBody(byte[] body) {
        setContentLength(body);
        this.body = body;
    }

    private void setContentLength(byte[] body) {
        String contentLength = String.valueOf(body.length);
        headers.put("Content-Length", contentLength);
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

    private byte[] combineByteArrays(byte[] one, byte[] two){
        byte[] combined = new byte[one.length + two.length];

        System.arraycopy(one,0,combined,0         ,one.length);
        System.arraycopy(two,0,combined,one.length,two.length);
        return combined;
    }

    public byte[] getBody() {
        return body;
    }

    public String getStatus() {
        return status;
    }

    public String getHeader(String location) {
        return headers.getOrDefault(location, "Header Not Found");
    }
}
