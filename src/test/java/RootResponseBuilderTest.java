import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class RootResponseBuilderTest {
    private BufferedReader mockIn;


    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void testRootResponseContains200OK() throws Exception {

        mockIn = mock(BufferedReader.class);
        when(mockIn.readLine()).thenReturn("GET / HTTP/1.1");
        Request request = new RequestBuilder(mockIn).buildRequest();
        Response response = RootResponseBuilder.buildResponse(request);
        String responseString = response.buildResponseString();
        assertTrue(responseString.contains("200 OK"));
    }
}