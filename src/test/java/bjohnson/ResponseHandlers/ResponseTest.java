package bjohnson.ResponseHandlers;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ResponseTest {

    private Response testResponse;

    @Before
    public void setUp() throws Exception {
        testResponse = new Response();
    }

    @Test
    public void testNewResponseReturnsProtocolAndStatus() throws Exception {
        String rawString = "HTTP/1.1 200 OK\r\n\r\n";
        Assert.assertArrayEquals(rawString.getBytes(), testResponse.buildResponse());
    }

    @Test
    public void testAddBody() throws Exception {
        byte[] bodyBytes = "Body".getBytes();
        testResponse.setBody(bodyBytes);
        Assert.assertArrayEquals(bodyBytes, testResponse.getBody());
        assertEquals("4", testResponse.getHeader("Content-Length"));

    }

    @Test
    public void testAddFourOhFourStatus() throws Exception {
        testResponse.setStatus("404 NOT FOUND");
        String rawString = "HTTP/1.1 404 NOT FOUND\r\n\r\n";
        Assert.assertArrayEquals(rawString.getBytes(), testResponse.buildResponse());
    }

    @Test
    public void testContainsOptionsHeader() throws Exception {
        testResponse.addHeader("Allow", "HEAD, POST, GET, OPTIONS, PUT");
        String rawString = "HTTP/1.1 200 OK\r\nAllow: HEAD, POST, GET, OPTIONS, PUT\r\n\r\n";
        Assert.assertArrayEquals(rawString.getBytes(), testResponse.buildResponse());
    }

    @Test
    public void testGetStatus() throws Exception {
        assertEquals("200 OK", testResponse.getStatus());

    }

    @Test
    public void testGetHeaderReturnsHeaderAttribute() throws Exception {
        testResponse.addHeader("Allow", "HEAD, POST, GET, OPTIONS, PUT");
        assertEquals("HEAD, POST, GET, OPTIONS, PUT", testResponse.getHeader("Allow"));
    }

    @Test
    public void testGetHeaderReturnsNotFoundForMissingHeader() throws Exception {
        assertEquals("Header Not Found", testResponse.getHeader("Allow"));
    }
}