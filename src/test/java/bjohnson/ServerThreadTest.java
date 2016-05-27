package bjohnson;

import bjohnson.ResponseHandlers.TwoHundredOKResponseBuilder;
import org.junit.Test;

import java.io.*;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class ServerThreadTest {

    @Test
    public void testRun() throws Exception {
        String rawRequest = "GET / HTTP/1.1\r\n";
        BufferedReader mockIn = mock(BufferedReader.class);
        OutputStream spyOut = new ByteArrayOutputStream();
        when(mockIn.readLine()).thenReturn(rawRequest,"Accept: *.*\r\n\r\n", "");
        Router router = new Router();
        router.addRoute("GET /", new TwoHundredOKResponseBuilder());
        RequestLogger mockLogger = mock(RequestLogger.class);
        ServerThread thread = new ServerThread(mockIn,spyOut, router, mockLogger);
        thread.run();

        assertEquals("HTTP/1.1 200 OK\r\n\r\n", spyOut.toString());

    }
}

