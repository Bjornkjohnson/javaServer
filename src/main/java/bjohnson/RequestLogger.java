package bjohnson;

import java.io.IOException;

public class RequestLogger {
    private String filePath;

    public RequestLogger(String filePath) {
        this.filePath = filePath + "/logs";
    }

    public void logRequest(String request) throws IOException{
        System.out.println(filePath);
        FileIO.appendToFile(filePath, request);
    }
}
