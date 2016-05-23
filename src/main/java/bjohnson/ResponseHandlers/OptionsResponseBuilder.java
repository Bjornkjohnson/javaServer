package bjohnson.ResponseHandlers;

import bjohnson.Request;

public class OptionsResponseBuilder implements ResponseBuilderInterface {

    private final Response response;

    public OptionsResponseBuilder() {
        response = new Response();
    }

    public void allowAll() {
        response.addHeader("Allow","HEAD,POST,GET,OPTIONS,PUT");
    }

    public Response getResponse(Request request){
        return response;
    }

    public void setHeaders(String headers) {
        response.addHeader("Allow",headers);

    }
}
