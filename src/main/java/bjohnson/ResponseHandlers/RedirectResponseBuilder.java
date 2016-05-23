package bjohnson.ResponseHandlers;

import bjohnson.Request;

public class RedirectResponseBuilder implements ResponseBuilderInterface{
    private final String redirectAddress;

    public RedirectResponseBuilder(String redirectAddress) {
        this.redirectAddress = redirectAddress;
    }

    @Override
    public Response getResponse(Request request) {
        Response response = new Response();
        response.setStatus("302 FOUND");
        response.addHeader("Location", redirectAddress);
        return response;
    }
}
