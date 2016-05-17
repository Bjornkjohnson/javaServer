import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class BuildImageResponse {
    private Request request;
    private String directoryPath;
    Response response = new Response();

    public BuildImageResponse(Request request, String directoryPath) {
        this.request = request;
        this.directoryPath = directoryPath;
    }

    public void readFromFile() {
        File image = new File(directoryPath + request.getURL());
        try {
            byte[] fileContent = Files.readAllBytes(image.toPath());
            response.setBody(fileContent);
        } catch (IOException e) {
            response.setStatus("404 NOT FOUND");
        }
    }

    public Response getResponse() {
        readFromFile();
        return response;
    }
}
