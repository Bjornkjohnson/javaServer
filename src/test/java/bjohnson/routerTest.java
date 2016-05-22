package bjohnson;

import bjohnson.ResponseHandlers.*;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.*;

public class routerTest {

    private Router router;

    @Before
    public void setUp() throws Exception {
        router = new Router();
        router.addRoute("GET /", new TwoHundredOKResponseBuilder());
        router.addRoute("PUT /", new TwoHundredOKResponseBuilder());
        router.addRoute("POST /", new TwoHundredOKResponseBuilder());
    }

    @Test
    public void testAddingRoute() {
        router.addRoute("GET /", new TwoHundredOKResponseBuilder());
        assertTrue(router.routeExists("/"));
    }

    @Test
    public void testRouteThatExistsReturns200ok() {
        assertThat(router.getResponse("GET /"), instanceOf(TwoHundredOKResponseBuilder.class));
    }


    @Test
    public void testRouteThatDoesNotExistsReturnsFourOhFour() {
        assertThat(router.getResponse("GET /fakeRoute"), instanceOf(FourOhFourResponseBuilder.class));
    }

    @Test
    public void testRouteWithoutMethodReturnsFourOhFive() {
        assertThat(router.getResponse("HEAD /"), instanceOf(FourOhFiveResponseBuilder.class));
    }


}