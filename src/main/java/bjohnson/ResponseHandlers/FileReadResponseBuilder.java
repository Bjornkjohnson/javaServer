package bjohnson.ResponseHandlers;

import bjohnson.Request;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class FileReadResponseBuilder implements ResponseBuilderInterface {
    private String directoryPath;
    Response response = new Response();

    public FileReadResponseBuilder(String directoryPath) {
        this.directoryPath = directoryPath;
    }

    public void readFromFile(Request request) {
        String fullPath = directoryPath + request.getURL();
        File image = new File(fullPath);
        try {
            byte[] fileContent = Files.readAllBytes(image.toPath());
            response.setBody(fileContent);
        } catch (IOException e) {

        }
    }

    public Response getResponse(Request request) {
        readFromFile(request);
        return response;
    }
}
