import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ResponseTest {

    private Response testResponse;

    @Before
    public void setUp() throws Exception {
        testResponse = new Response();
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testNewResponseReturnsProtocolAndStatus() throws Exception {
        String rawString = "HTTP/1.1 200 OK\r\n\r\n";
        assertArrayEquals(rawString.getBytes(), testResponse.buildStatusAndHeaderBytes());
    }

    @Test
    public void testAddBody() throws Exception {
        byte[] bodyBytes = "Body".getBytes();
        testResponse.setBody(bodyBytes);
        assertArrayEquals(bodyBytes, testResponse.getBody());
    }

    @Test
    public void testAddFourOhFourStatus() throws Exception {
        testResponse.setStatus("404 NOT FOUND");
        String rawString = "HTTP/1.1 404 NOT FOUND\r\n\r\n";
        assertArrayEquals(rawString.getBytes(), testResponse.buildStatusAndHeaderBytes());
    }

    @Test
    public void testContainsOptionsHeader() throws Exception {
        testResponse.addHeader("Allow", "HEAD, POST, GET, OPTIONS, PUT");
        String rawString = "HTTP/1.1 200 OK\r\nAllow: HEAD, POST, GET, OPTIONS, PUT\r\n\r\n";
        assertArrayEquals(rawString.getBytes(), testResponse.buildStatusAndHeaderBytes());
    }


}