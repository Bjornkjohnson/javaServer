public class Response {
    private final String PROTOCOL = "HTTP/1.1 ";
    private String status = "200 OK\r\n\r\n";

    public String getResponse() {
        return PROTOCOL + status;
    }
}
