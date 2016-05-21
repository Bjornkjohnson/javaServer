package bjohnson.ResponseHandlers;

import bjohnson.Request;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;

public class FileReadResponseBuilder implements ResponseBuilderInterface {
    private String directoryPath;
    private Response response;
    private Request request;
    private int startRange = 0;
    private int endRange = 0;

    public FileReadResponseBuilder(String directoryPath) {
        this.directoryPath = directoryPath;
    }



    public void readFromFile() {
        String fullPath = directoryPath + request.getURL();
        File image = new File(fullPath);
        try {
            byte[] fileContent = Files.readAllBytes(image.toPath());
            if (isPartial()){
                endRange = fileContent.length;
                setContentRange();
                response.setStatus("206 PARTIAL CONTENT");
                response.setBody(Arrays.copyOfRange(fileContent, startRange, endRange));
            } else {
                response.setBody(fileContent);
            }
        } catch (IOException e) {
            response = new FourOhFourResponseBuilder().getResponse(request);
        }
    }

    private void setContentRange() {
        String rangeString = request.getHeaders().get("Range");
        String range[] = rangeString.split("=")[1].split("");


        if (range.length == 3) {
            startRange = Integer.parseInt(range[0]);
            endRange = Integer.parseInt(range[2]) + 1;
        } else if (range[0].equals("-")) {
            startRange = endRange - Integer.parseInt(range[1]);
        } else if (range[1].equals("-")) {
            startRange = Integer.parseInt(range[0]);

        }
    }

    private Boolean isPartial() {
        return request.getHeaders().containsKey("Range");
    }

    public Response getResponse(Request request) {
        response = new Response();
        this.request = request;
        readFromFile();
        return response;
    }
}
