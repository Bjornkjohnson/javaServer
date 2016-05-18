import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class OptionsResponseBuilderTest {

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void testContainsAllAllowHeader() throws Exception {
        Request request = new Request();
        request.setMethod("OPTIONS");
        request.setURL("/method_options");
        OptionsResponseBuilder optionsResponseBuilder = new OptionsResponseBuilder();
        optionsResponseBuilder.allowAll();
        Response response = optionsResponseBuilder.getResponse(request);
        String correctResponse = "HTTP/1.1 200 OK\r\nAllow: HEAD,POST,GET,OPTIONS,PUT\r\n\r\n";
        assertArrayEquals(correctResponse.getBytes(), response.buildResponse());
    }
}