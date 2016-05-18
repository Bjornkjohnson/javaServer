package bjohnson.ResponseHandlers;

import bjohnson.Request;

public class GenericResponseBuilder implements ResponseBuilderInterface {
    public Response getResponse(Request request) {
        return new Response();
    }
}
