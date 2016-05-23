package bjohnson.ResponseHandlers;

import bjohnson.FileIO;
import bjohnson.Request;

import java.io.IOException;

public class FileReadResponseBuilder implements ResponseBuilderInterface {
    private final String directoryPath;
    private Response response;
    private Request request;

    public FileReadResponseBuilder(String directoryPath) {
        this.directoryPath = directoryPath;
    }

    private void buildFullResponse() {
        String fullPath = directoryPath + request.getURL();
        try {
            byte[] fileContent = FileIO.readFromFile(fullPath);
            response.setBody(fileContent);
        } catch (IOException e) {
            response = new FourOhFourResponseBuilder().getResponse(request);
        }
    }

    private void buildPartialResponse() {
        String fullPath = directoryPath + request.getURL();
        try {
            response.setStatus("206 PARTIAL CONTENT");
            response.setBody(FileIO.readPartialContents(fullPath, request.getHeaders().get("Range")));
        } catch (Exception e){
            response = new FourOhFourResponseBuilder().getResponse(request);
        }
    }

    private Boolean isPartialFileRequest() {
        return request.getHeaders().containsKey("Range");
    }

    public Response getResponse(Request request) {
        response = new Response();
        this.request = request;
        if (isPartialFileRequest()){
            buildPartialResponse();
        } else {
            buildFullResponse();
        }
        return response;
    }
}
