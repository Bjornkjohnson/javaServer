package bjohnson;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class RequestTest {
    String rawRequest;

    @Before
    public void setUp() throws Exception {
        rawRequest = "GET / HTTP/1.1";
    }

    @After
    public void tearDown() throws Exception {


    }

    @Test
    public void testRequestHasGet() throws Exception {
        Request testRequest = new Request();
        testRequest.setMethod("GET");
        Assert.assertEquals("GET", testRequest.getMethod());
    }

    @Test
    public void testRequestHasURL() throws Exception {
        Request testRequest = new Request();
        testRequest.setURL("/");
        Assert.assertEquals("/", testRequest.getURL());
    }

    @Test
    public void testRequestHasProtocol() throws Exception {
        Request testRequest = new Request();
        testRequest.setProtocol("HTTP/1.1");
        Assert.assertEquals("HTTP/1.1", testRequest.getProtocol());
    }

    @Test
    public void testGetRoute() throws Exception {
        Request testRequest = new Request();
        testRequest.setMethod("GET");
        testRequest.setURL("/");
        Assert.assertEquals("GET /", testRequest.getRoute());
    }
}
