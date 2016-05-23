package bjohnson.ResponseHandlers;

import bjohnson.FileIO;
import bjohnson.Request;

import javax.xml.bind.DatatypeConverter;
import java.io.IOException;
import java.security.MessageDigest;
import java.util.Arrays;

public class FileWriterResponseBuilder implements ResponseBuilderInterface {
    private Response response;
    private final String filePath;
    private String fullPath;
    private Request request;

    public FileWriterResponseBuilder(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public Response getResponse(Request request) {
        this.response = new Response();
        fullPath = filePath + request.getURL();
        this.request = request;
        
        if (isPatch(request.getMethod())){
            getPatchResponse();
        } else {
            writeToFile();
        }
        return response;
    }

    private void writeToFile() {
        try {
            FileIO.writeToFile(fullPath, request.getBody());
        } catch (IOException e) {
            response = new FourOhFourResponseBuilder().getResponse(request);
        }
    }

    private void getPatchResponse() {
        if (RequestIsPatchable()){
            response.setStatus("204 No Content");
            writeToFile();
        }
    }

    private boolean RequestIsPatchable() {

        try {
            MessageDigest mDigest = MessageDigest.getInstance("SHA1");
            byte[] fileContentsHash = mDigest.digest(getFileContents());
            byte[] requestHash = DatatypeConverter.parseHexBinary(request.getHeaders().get("If-Match"));
            if (Arrays.equals(requestHash, fileContentsHash)){
                return true;
            }
        }catch (Exception e){
            System.out.println(e);
        }
        response.setStatus("400 Bad Request");
        return false;
    }

    private byte[] getFileContents() throws IOException {
        return FileIO.readFromFile(filePath + request.getURL());
    }

    private boolean isPatch(String method) {
        return method.equals("PATCH");
    }
}
