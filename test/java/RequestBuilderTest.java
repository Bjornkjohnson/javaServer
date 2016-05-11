import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class RequestBuilderTest {
    private Request testRequest;
    private BufferedReader bufferedReader;
    @Before
    public void setUp() throws Exception {
        testRequest = new Request();
        bufferedReader = mock(BufferedReader.class);


    }

    @Test
    public void buildSimpleGetRequest() throws Exception {
        testRequest.setMethod("GET");
        testRequest.setURL("/");
        when(bufferedReader.readLine()).thenReturn("GET / HTTP/1.1");
        RequestBuilder builder = new RequestBuilder(bufferedReader);

        assertEquals(testRequest.getMethod(), builder.buildRequest().getMethod());
        assertEquals(testRequest.getURL(), builder.buildRequest().getURL());

    }
}