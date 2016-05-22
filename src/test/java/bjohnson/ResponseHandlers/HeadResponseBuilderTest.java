package bjohnson.ResponseHandlers;

import bjohnson.Request;
import org.junit.Test;

import static org.junit.Assert.*;

public class HeadResponseBuilderTest {

    @Test
    public void TestReturnsTwoHundredOK(){
        Request request = new Request();
        request.setURL("/");
        request.setMethod("HEAD");
        HeadResponseBuilder headResponseBuilder = new HeadResponseBuilder();
        Response response = headResponseBuilder.getResponse(request);
        assertEquals("200 OK", response.getStatus());
    }

}