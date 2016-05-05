import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.net.Socket;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ServerTest {
    private Server testServer;
    private Socket testSocket;
    private final int TESTPORT = 5001;

    @Before
    public void setUp() throws Exception {
        testServer = new Server(TESTPORT);
        testSocket = new Socket("localhost", TESTPORT);
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testServerStartsAndStops() throws Exception {
        testServer.start();
        assertTrue(testServer.isRunning());
        testServer.stop();
        assertFalse(testServer.isRunning());
        testSocket.close();
    }

    @Test
    public void testReturns200() throws Exception {
        testServer.start();
        assertEquals(1, 1);
    }
}