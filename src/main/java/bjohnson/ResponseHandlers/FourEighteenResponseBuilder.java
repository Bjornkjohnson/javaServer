package bjohnson.ResponseHandlers;

import bjohnson.Request;

public class FourEighteenResponseBuilder implements ResponseBuilderInterface {
    @Override
    public Response getResponse(Request request) {

        Response response = new Response();
        response.setStatus("418");
        response.setBody("I'm a teapot".getBytes());
        return response;
    }
}
