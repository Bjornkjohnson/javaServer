public class FourOhFourBuilder implements ResponseBuilderInterface {
    public Response getResponse(Request request) {
        Response response = new Response();
        response.setStatus("404 NOT FOUND");
        return response;
    }
}
