package bjohnson.ResponseHandlers;

import bjohnson.Request;

public class HeadResponseBuilder implements ResponseBuilderInterface{
    @Override
    public Response getResponse(Request request) {
        return new Response();
    }
}
