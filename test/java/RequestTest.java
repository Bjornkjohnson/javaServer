import org.junit.After;
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
        Request testRequest = new Request(rawRequest);
        assertEquals("GET", testRequest.getMethod());
    }

    @Test
    public void testRequestHasURL() throws Exception {
        Request testRequest = new Request(rawRequest);
        assertEquals("/", testRequest.getURL());
    }
}
