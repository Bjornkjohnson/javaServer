package bjohnson.ResponseHandlers;

import bjohnson.Request;
import org.junit.Test;

import static org.junit.Assert.*;

public class EchoParamsResponseBuilderTest {
    @Test
    public void TestResponseBodyContainsParams() throws Exception {
        Request request = new Request();
        request.setParams(new String[]{"Hi There", "Goodbye"});
        Response response = new EchoParamsResponseBuilder().getResponse(request);
        assertArrayEquals("Hi There\r\nGoodbye".getBytes(), response.getBody());
    }
}