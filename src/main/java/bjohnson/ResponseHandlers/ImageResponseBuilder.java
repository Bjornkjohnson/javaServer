package bjohnson.ResponseHandlers;

import bjohnson.Request;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class ImageResponseBuilder implements ResponseBuilderInterface {
    private final String directoryPath;
    private Response response;

    public ImageResponseBuilder(String directoryPath) {
        this.directoryPath = directoryPath;
    }

    private void readFromFile(Request request) {
        String fullPath = directoryPath + request.getURL();
        File image = new File(fullPath);
        try {
            byte[] fileContent = Files.readAllBytes(image.toPath());
            response.setBody(fileContent);
        } catch (IOException e) {
           response = new FourOhFourResponseBuilder().getResponse(request);
        }
    }

    public Response getResponse(Request request) {
        response = new Response();
        readFromFile(request);
        return response;
    }
}
