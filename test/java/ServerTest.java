import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.net.Socket;

import static org.junit.Assert.assertTrue;

public class ServerTest {
    private Server testServer;
    private final int TESTPORT = 5001;

    @Before
    public void setUp() throws Exception {
        testServer = new Server(TESTPORT);
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testAcceptReturnsTrueWhenConnectionExists() throws Exception {
        Socket testSocket = new Socket("localhost", TESTPORT);
        assertTrue(testServer.accept());
        testSocket.close();
    }

    @Test
    public void testAcceptReturnsTrueWhenConnectionExists() throws Exception {
        Socket testSocket = new Socket("localhost", TESTPORT);
        assertTrue(testServer.accept());
        testSocket.close();
    }



}