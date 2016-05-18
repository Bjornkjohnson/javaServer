package bjohnson.ResponseHandlers;

import bjohnson.Request;
import org.junit.Test;

import static org.junit.Assert.*;

public class RedirectResponseHandlerTest {
    @Test
    public void testRedirectHasTheeOhTwoStatus() throws Exception {
        Request request = new Request();
        request.setMethod("GET");
        request.setURL("/redirect");
        RedirectResponseHandler redirectResponseHandler = new RedirectResponseHandler("/redirect/address");
        Response response = redirectResponseHandler.getResponse(request);
        assertEquals("302 FOUND", response.getStatus());
    }

    @Test
    public void testRedirectHasForwardingAddress() throws Exception {
        Request request = new Request();
        request.setMethod("GET");
        request.setURL("/redirect");
        RedirectResponseHandler redirectResponseHandler = new RedirectResponseHandler("/redirect/address");
        Response response = redirectResponseHandler.getResponse(request);
        assertEquals("/redirect/address", response.getHeader("Location"));
    }
}