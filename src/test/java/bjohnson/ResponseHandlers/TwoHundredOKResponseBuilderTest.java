package bjohnson.ResponseHandlers;

import bjohnson.Request;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TwoHundredOKResponseBuilderTest {

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void testRuturns200OK(){
        Assert.assertEquals("200 OK", new TwoHundredOKResponseBuilder().getResponse(new Request()).getStatus());
    }
}