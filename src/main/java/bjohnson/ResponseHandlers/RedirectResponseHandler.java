package bjohnson.ResponseHandlers;

import bjohnson.Request;

public class RedirectResponseHandler implements ResponseBuilderInterface{
    private Response response;
    private String redirectAddress;

    public RedirectResponseHandler(String redirectAddress) {
        this.redirectAddress = redirectAddress;
        response = new Response();
    }

    @Override
    public Response getResponse(Request request) {
        response.setStatus("302 FOUND");
        response.addHeader("Location", redirectAddress);
        return response;
    }
}
