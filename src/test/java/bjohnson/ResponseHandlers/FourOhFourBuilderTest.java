package bjohnson.ResponseHandlers;

import bjohnson.Request;
import org.junit.Assert;
import org.junit.Test;

public class FourOhFourBuilderTest {

    @Test
    public void testReturnsFourOhFour(){
        Assert.assertEquals("404 NOT FOUND", new FourOhFourResponseBuilder().getResponse(new Request()).getStatus());
    }
}