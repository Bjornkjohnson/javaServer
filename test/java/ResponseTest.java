import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ResponseTest {

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testNewResponseReturnsProtocolAndStatus() throws Exception {
        Response testResponse = new Response();
        assertEquals("HTTP/1.1 200 OK\r\n\r\n", testResponse.getResponse());
    }


}