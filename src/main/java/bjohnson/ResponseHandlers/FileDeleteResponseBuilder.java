package bjohnson.ResponseHandlers;

import bjohnson.Request;

import java.io.File;

public class FileDeleteResponseBuilder implements ResponseBuilderInterface{
    private final String filePath;
    private Response response;


    public FileDeleteResponseBuilder(String filePath) {

        this.filePath = filePath;

    }

    @Override
    public Response getResponse(Request request) {
        deleteFile(request);
        return response;
    }

    private void deleteFile(Request request) {
        response = new Response();
        String fullPath = filePath + request.getURL();
        File file = new File(fullPath);
        try {
            file.delete();
        } catch (Exception e) {
            response.setStatus("404 NOT FOUND");
        }

    }
}
