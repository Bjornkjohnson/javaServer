package bjohnson.ResponseHandlers;

import bjohnson.Request;

import java.io.File;

import org.apache.commons.io.filefilter.HiddenFileFilter;


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
        String[] files = directory.list(HiddenFileFilter.VISIBLE);
        body.append("<Body>\n<ol>\n");
        for (String file : files){
            body.append("<li><a href=\"/"+ file + "\">" + file + "</a></li>\n");
        }
        body.append("</ol>\n</Body>\n");
        response.setBody(body.toString().getBytes());
    }
}