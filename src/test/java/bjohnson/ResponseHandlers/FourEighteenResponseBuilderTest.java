package bjohnson.ResponseHandlers;

import bjohnson.Request;
import org.junit.Test;

import static org.junit.Assert.*;

public class FourEighteenResponseBuilderTest {
    @Test
    public void testResponseReturnsFourEighteen() throws Exception {
        FourEighteenResponseBuilder fourEighteenResponseBuilder = new FourEighteenResponseBuilder();
        Response response = fourEighteenResponseBuilder.getResponse(new Request());
        assertEquals("418", response.getStatus());
    }
}