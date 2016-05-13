import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class routerTest {

    private Router router;
    private static final String fourOhFour = "HTTP/1.1 404 Not Found\r\n\r\n";
    private static final String twoHundredOk = "HTTP/1.1 200 OK\r\n\r\n";

    @Before
    public void setUp() throws Exception {
        router = new Router();
        router.addRoute("GET /", new Response());
        router.addRoute("PUT /", new Response());
        router.addRoute("POST /", new Response());
    }

    @Test
    public void testAddingRoute() {
        router.addRoute("GET /", new Response());
        assertTrue(router.routeExists("GET /"));
    }

    @Test
    public void testRouteThatExistsReturns200ok() {
        assertEquals(twoHundredOk, router.getResponse("GET /").buildResponse());
    }

    @Test
    public void testPutRouteReturns200ok() {
        assertEquals(twoHundredOk, router.getResponse("PUT /").buildResponse());
    }

    @Test
    public void testRouteThatDoesNotExistsReturnsFourOhFour() {
        assertEquals(fourOhFour, router.getResponse("GET /notARoute").buildResponse());
    }

    @Test
    public void testRouteResponseWithHeaders() {
        Response testResponse = new Response();
        testResponse.addHeader("Allow", "GET");
        router.addRoute("/route", testResponse);
        Response routerResponse = router.getResponse("/route");
        assertEquals(testResponse.buildResponse(), routerResponse.buildResponse());
    }
}