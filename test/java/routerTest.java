import org.junit.Before;
import org.junit.Test;

import javax.xml.bind.annotation.XmlAttribute;

import static org.junit.Assert.*;

public class routerTest {

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void testAddingRoute() {
        Router router = new Router();
        router.addRoute("/");
        assertTrue(router.routeExists("/"));
    }
}