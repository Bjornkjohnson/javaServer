import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class RequestBuilderTest {
    private BufferedReader bufferedReader;
    @Before
    public void setUp() throws Exception {
        bufferedReader = mock(BufferedReader.class);


    }

    @Test
    public void buildSimpleGetRequest() throws Exception {
        when(bufferedReader.readLine()).thenReturn("GET / HTTP/1.1");
        RequestBuilder builder = new RequestBuilder(bufferedReader);

        assertEquals("GET", builder.buildRequest().getMethod());
        assertEquals("/", builder.buildRequest().getURL());
        assertEquals("HTTP/1.1", builder.buildRequest().getProtocol());
    }
}