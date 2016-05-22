package bjohnson.ResponseHandlers;

import bjohnson.Request;

public class TwoHundredOKResponseBuilder implements ResponseBuilderInterface {
    public Response getResponse(Request request) {
        return new Response();
    }
}
