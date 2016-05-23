package bjohnson.ResponseHandlers;

import bjohnson.Request;

import java.io.File;


public class HTMLDirectoryResponseBuilder implements ResponseBuilderInterface {
    private final String filePath;
    private Response response;

    public HTMLDirectoryResponseBuilder(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public Response getResponse(Request request) {

        response = new Response();
        buildDirectoryHtml(request);
        return response;
    }

    private void buildDirectoryHtml(Request request) {
        StringBuilder body = new StringBuilder();
        File directory = new File(filePath + request.getURL());
        String[] files = directory.list();
        body.append("<Body>\n<ol>\n");
        for (String file : files){
            body.append("<li><a href=\"/").append(file).append("\">").append(file).append("</a></li>\n");
        }
        body.append("</ol>\n</Body>\n");
        response.setBody(body.toString().getBytes());
    }
}
