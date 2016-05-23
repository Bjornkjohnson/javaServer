package bjohnson.ResponseHandlers;

import bjohnson.Request;

public class EchoParamsResponseBuilder implements ResponseBuilderInterface {
    private Response response;
    @Override
    public Response getResponse(Request request) {
        response = new Response();
        buildResponseBody(request);
        return response;
    }

    private void buildResponseBody(Request request) {
        String[] params = request.getParams();
        String paramsString = String.join("\r\n", params);
        response.setBody(paramsString.getBytes());
    }
}
