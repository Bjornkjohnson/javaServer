package bjohnson.ResponseHandlers;

import bjohnson.Request;

public class BasicAuthResponseHandler implements ResponseBuilderInterface {
    private String filePath;

    public BasicAuthResponseHandler(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public Response getResponse(Request request) {
        Response response = new Response();
        if (credentialAreValid(request)) {
            response = new FileReadResponseBuilder(filePath).getResponse(request);
            return response;
        }
        response.addHeader("WWW-Authenticate", "Basic realm=\"WallyWorld\"");
        response.setStatus("401 Unauthorized");
        return response;
    }

    private boolean credentialAreValid(Request request) {
        String credentials = "YWRtaW46aHVudGVyMg==";
        String rawRequestCredentials = request.getHeaders().getOrDefault("Authorization","None None");
        String parsedRequestCredentials = rawRequestCredentials.split(" ")[1];
        return credentials.equals(parsedRequestCredentials);
    }
}
