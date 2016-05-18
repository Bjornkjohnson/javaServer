package bjohnson.ResponseHandlerTests;

import bjohnson.Request;
import bjohnson.ResponseHandlers.GenericResponseBuilder;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class GenericResponseBuilderTest {

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void testRuturns200OK(){
        Assert.assertEquals("200 OK", new GenericResponseBuilder().getResponse(new Request()).getStatus());
    }
}