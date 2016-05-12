import org.junit.Before;
import org.junit.Test;

import javax.xml.bind.annotation.XmlAttribute;

import static org.junit.Assert.*;

public class routerTest {

    private Router router;

    @Before
    public void setUp() throws Exception {
        router = new Router();
    }

    @Test
    public void testAddingRoute() {
        router.addRoute("GET /", new Response());
        assertTrue(router.routeExists("GET /"));
    }

    @Test
    public void testRouteThatExistsReturns200ok() {
        assertEquals("HTTP/1.1 200 OK\r\n\r\n", router.getResponse().getResponse());
    }

    @Test
    public void testRouteThatDoesNotExistsReturnsFourOhFour() {
        assertEquals("HTTP/1.1 404 Not Found\r\n\r\n", router.getResponse().getResponse());
    }
}