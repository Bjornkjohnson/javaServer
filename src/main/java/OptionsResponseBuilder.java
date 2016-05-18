public class OptionsResponseBuilder implements ResponseBuilderInterface {

    private Response response = new Response();

    public void allowAll() {
        response.addHeader("Allow","HEAD,POST,GET,OPTIONS,PUT");
    }

    public Response getResponse(Request request){
        return response;
    }

}
