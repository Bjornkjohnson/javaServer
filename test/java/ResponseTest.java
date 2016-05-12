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
        assertEquals("HTTP/1.1 200 OK\r\n\r\n", testResponse.getResponse());
    }

    @Test
    public void testAddBody() throws Exception {
        testResponse.setBody("Body");
        assertEquals("HTTP/1.1 200 OK\r\n\r\nBody\r\n", testResponse.getResponse());
    }

    @Test
    public void testAddFourOhFourStatus() throws Exception {
        testResponse.setStatus("404 NOT FOUND\r\n\r\n");
        assertEquals("HTTP/1.1 404 NOT FOUND\r\n\r\n", testResponse.getResponse());
    }


}