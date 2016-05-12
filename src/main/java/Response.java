public class Response {
    private final String PROTOCOL = "HTTP/1.1 ";
    private String status = "200 OK\r\n\r\n";
    private String body = "";

    public String getResponse() {
        return PROTOCOL +
                status +
                body;
    }

    public void setBody(String body) {
        this.body = body + "\r\n";
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
