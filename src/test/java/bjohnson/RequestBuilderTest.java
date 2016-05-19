package bjohnson;

import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

import static org.junit.Assert.assertEquals;

public class RequestBuilderTest {
    private BufferedReader bufferedReader;
    @Before
    public void setUp() throws Exception {
        String rawRequest = "GET / HTTP/1.1\r\nHost: www.w3.org\r\nHeader2: another\r\n\r\n";
        InputStream stream = new ByteArrayInputStream(rawRequest.getBytes());
        bufferedReader = new BufferedReader(new InputStreamReader(stream));
    }

    @Test
    public void buildGetRequest() throws Exception {
        RequestBuilder builder = new RequestBuilder(bufferedReader);
        Request request = builder.buildRequest();
        assertEquals("GET", request.getMethod());
        assertEquals("/", request.getURL());
        assertEquals("HTTP/1.1", request.getProtocol());
        assertEquals("www.w3.org", request.getHeaders().get("Host"));
        assertEquals("another", request.getHeaders().get("Header2"));

    }

}
