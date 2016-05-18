package bjohnson.ResponseHandlerTests;

import bjohnson.Request;
import bjohnson.ResponseHandlers.FourOhFourResponseBuilder;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class FourOhFourBuilderTest {

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void testReturnsFourOhFour(){
        Assert.assertEquals("404 NOT FOUND", new FourOhFourResponseBuilder().getResponse(new Request()).getStatus());
    }
}