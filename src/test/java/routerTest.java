import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class routerTest {

    private Router router;
    private static final byte[] fourOhFour = "HTTP/1.1 404 Not Found\r\n\r\n".getBytes();
    private static final byte[] twoHundredOk = "HTTP/1.1 200 OK\r\n\r\n".getBytes();

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
        assertArrayEquals(twoHundredOk, router.getResponse("GET /").buildStatusAndHeaderBytes());
    }

    @Test
    public void testPutRouteReturns200ok() {
        assertArrayEquals(twoHundredOk, router.getResponse("PUT /").buildStatusAndHeaderBytes());
    }

    @Test
    public void testRouteThatDoesNotExistsReturnsFourOhFour() {
        assertArrayEquals(fourOhFour, router.getResponse("GET /notARoute").buildStatusAndHeaderBytes());
    }

    @Test
    public void testRouteResponseWithHeaders() {
        Response testResponse = new Response();
        testResponse.addHeader("Allow", "GET");
        router.addRoute("/route", testResponse);
        Response routerResponse = router.getResponse("/route");
        assertArrayEquals(testResponse.buildStatusAndHeaderBytes(), routerResponse.buildStatusAndHeaderBytes());
    }
}