package bjohnson;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;

public class RequestTest {
    private String rawRequest;
    private Request testRequest;

    @Before
    public void setUp() throws Exception {
        testRequest = new Request();

        rawRequest = "GET / HTTP/1.1";
    }

    @After
    public void tearDown() throws Exception {


    }

    @Test
    public void testRequestHasGet() throws Exception {
        testRequest.setMethod("GET");
        assertEquals("GET", testRequest.getMethod());
    }

    @Test
    public void testRequestHasURL() throws Exception {
        testRequest.setURL("/");
        assertEquals("/", testRequest.getURL());
    }

    @Test
    public void testRequestHasProtocol() throws Exception {
        testRequest.setProtocol("HTTP/1.1");
        assertEquals("HTTP/1.1", testRequest.getProtocol());
    }

    @Test
    public void testGetRoute() throws Exception {
        testRequest.setMethod("GET");
        testRequest.setURL("/");
        assertEquals("GET /", testRequest.getRoute());
    }

    @Test
    public void testSetBody() throws Exception {
        testRequest.setBody("I'm a Body!");
        assertEquals("I'm a Body!", testRequest.getBody());
    }

    @Test
    public void testSetHeaders() throws Exception {
        HashMap<String, String> headers = new HashMap<>();
        headers.put("HEADER1", "Header Details");
        testRequest.setHeaders(headers);
        assertEquals("Header Details", testRequest.getHeaders().get("HEADER1"));
    }
}
