package bjohnson;

import org.junit.After;
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
        assertEquals("11", testRequest.getHeaders().get("Content-Length"));
    }

    @Test
    public void testSetHeaders() throws Exception {
        HashMap<String, String> headers = new HashMap<>();
        headers.put("HEADER1", "Header Details");
        testRequest.setHeaders(headers);
        assertEquals("Header Details", testRequest.getHeaders().get("HEADER1"));
    }

    @Test
    public void testAddHeader() throws Exception {
        testRequest.addHeader("Content-Length", "5");
        assertEquals("5", testRequest.getHeaders().get("Content-Length"));
    }

    @Test
    public void testGetStatusLine() throws Exception {
        testRequest.setMethod("GET");
        testRequest.setURL("/");
        testRequest.setProtocol("HTTP/1.1");
        assertEquals("GET / HTTP/1.1", testRequest.getStatusLine());
    }

    @Test
    public void testAddParams() throws Exception {
        String params[] = new String[]{"Hi", "There"};
        testRequest.setParams(params);
        assertEquals(2, testRequest.getParams().length);
        assertEquals("Hi", testRequest.getParams()[0]);
        assertEquals("There", testRequest.getParams()[1]);

    }
}
