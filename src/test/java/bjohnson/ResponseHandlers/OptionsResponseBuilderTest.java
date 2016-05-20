package bjohnson.ResponseHandlers;

import bjohnson.Request;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class OptionsResponseBuilderTest {
    private Request request;
    private OptionsResponseBuilder optionsResponseBuilder;


    @Before
    public void setUp() throws Exception {
        request = new Request();
        request.setMethod("OPTIONS");
        request.setURL("/method_options");
        optionsResponseBuilder = new OptionsResponseBuilder();
    }

    @Test
    public void testContainsAllAllowHeader() throws Exception {
        optionsResponseBuilder.allowAll();
        Response response = optionsResponseBuilder.getResponse(request);
        String correctResponse = "HTTP/1.1 200 OK\r\nAllow: HEAD,POST,GET,OPTIONS,PUT\r\n\r\n";
        Assert.assertArrayEquals(correctResponse.getBytes(), response.buildResponse());
    }

    @Test
    public void testContainsSetSpecificHeaders() throws Exception {
        optionsResponseBuilder.setHeaders("HEAD,POST,PUT");
        Response response = optionsResponseBuilder.getResponse(request);
        String correctResponse = "HTTP/1.1 200 OK\r\nAllow: HEAD,POST,PUT\r\n\r\n";
        Assert.assertArrayEquals(correctResponse.getBytes(), response.buildResponse());
    }
}