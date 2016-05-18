import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class ImageResponseBuilder implements ResponseBuilderInterface {
    private String directoryPath;
    Response response = new Response();

    public ImageResponseBuilder(String directoryPath) {
        this.directoryPath = directoryPath;
    }

    public void readFromFile(Request request) {
        String fullPath = directoryPath + request.getURL();
        System.out.println(fullPath);

        File image = new File(fullPath);
        try {
            byte[] fileContent = Files.readAllBytes(image.toPath());
            response.setBody(fileContent);
        } catch (IOException e) {

            response.setStatus("404 NOT FOUND");
        }
    }

    public Response getResponse(Request request) {
        readFromFile(request);
        return response;
    }
}
