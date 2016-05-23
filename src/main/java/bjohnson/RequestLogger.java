package bjohnson;

import java.io.IOException;

public class RequestLogger {
    private String filePath;

    public RequestLogger(String filePath) {
        this.filePath = filePath;
    }

    public void logRequest(String request) throws IOException{
        FileIO.appendToFile(filePath, request);
    }
}
