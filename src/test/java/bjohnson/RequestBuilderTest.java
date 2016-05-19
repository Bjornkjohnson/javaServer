package bjohnson;

import org.junit.Assert;
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

        Assert.assertEquals("GET", builder.buildRequest().getMethod());
        Assert.assertEquals("/", builder.buildRequest().getURL());
        Assert.assertEquals("HTTP/1.1", builder.buildRequest().getProtocol());
    }

    @Test
    public void buildPostRequest() throws Exception {
        when(bufferedReader.readLine()).thenReturn("POST / HTTP/1.1\r\n\r\nI'm a Body!");
        RequestBuilder builder = new RequestBuilder(bufferedReader);

        Assert.assertEquals("POST", builder.buildRequest().getMethod());
        Assert.assertEquals("/", builder.buildRequest().getURL());
        Assert.assertEquals("HTTP/1.1", builder.buildRequest().getProtocol());
        Assert.assertEquals("I'm a Body!", builder.buildRequest().getBody());

    }
}