package bjohnson.ResponseHandlers;

import bjohnson.Request;
import org.junit.Assert;
import org.junit.Test;

public class TwoHundredOKResponseBuilderTest {

    @Test
    public void testReturns200OK(){
        Assert.assertEquals("200 OK", new TwoHundredOKResponseBuilder().getResponse(new Request()).getStatus());
    }
}