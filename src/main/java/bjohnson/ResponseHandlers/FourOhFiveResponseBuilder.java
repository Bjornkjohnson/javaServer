package bjohnson.ResponseHandlers;

import bjohnson.Request;

public class FourOhFiveResponseBuilder implements ResponseBuilderInterface {
    @Override
    public Response getResponse(Request request) {
        Response response = new Response();
        response.setStatus("405 METHOD NOT ALLOWED");
        return response;
    }
}
