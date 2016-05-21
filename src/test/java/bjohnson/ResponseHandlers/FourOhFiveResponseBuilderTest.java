package bjohnson.ResponseHandlers;

import bjohnson.Request;
import org.junit.Test;

import static org.junit.Assert.*;

public class FourOhFiveResponseBuilderTest {
    @Test
    public void testReturnsFourOhFive() throws Exception {
        FourOhFiveResponseBuilder fourOhFiveResponseBuilder = new FourOhFiveResponseBuilder();
        Response response = fourOhFiveResponseBuilder.getResponse(new Request());
        assertEquals("405 METHOD NOT ALLOWED", response.getStatus());
    }
}