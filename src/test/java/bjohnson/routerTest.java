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
        router.addRoute("GET /", new GenericResponseBuilder());
        router.addRoute("PUT /", new GenericResponseBuilder());
        router.addRoute("POST /", new GenericResponseBuilder());
    }

    @Test
    public void testAddingRoute() {
        router.addRoute("GET /", new GenericResponseBuilder());
        assertTrue(router.routeExists("GET /"));
    }

    @Test
    public void testRouteThatExistsReturns200ok() {
        assertThat(router.getResponse("GET /"), instanceOf(GenericResponseBuilder.class));
    }


    @Test
    public void testRouteThatDoesNotExistsReturnsFourOhFour() {
        assertThat(router.getResponse("GET /fakeRoute"), instanceOf(FourOhFourResponseBuilder.class));
    }


}