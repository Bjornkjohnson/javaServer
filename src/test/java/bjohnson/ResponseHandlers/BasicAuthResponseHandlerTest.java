package bjohnson.ResponseHandlers;

import bjohnson.FileIO;
import bjohnson.Request;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class BasicAuthResponseHandlerTest {
    private String filePath;
    Request request;
    BasicAuthResponseHandler basicAuth;

    @Before
    public void setUp() throws Exception {
        filePath = System.getProperty("user.dir") + "/src/test/java/bjohnson/testResource";
        FileIO.writeToFile(filePath + "/logs", "GET / HTTP/1.1");
        basicAuth = new BasicAuthResponseHandler(filePath);
        request = new Request();
        request.setURL("/logs");
        request.setMethod("GET");
        request.setProtocol("HTTP/1.1");

    }

    @Test
    public void testWrongCredentialsReturnsFourOhOne() throws Exception {
        request.addHeader("Authorization", "Basic QWxhZGRpbjpvcGVuIHNlc2FtZQ");


        Response response = basicAuth.getResponse(request);
        assertEquals("401 Unauthorized", response.getStatus());
        assertEquals("Basic realm=\"WallyWorld\"", response.getHeader("WWW-Authenticate"));

    }

    @Test
    public void testNoCredentialsReturnsFourOhOne() throws Exception {
        Response response = basicAuth.getResponse(request);
        assertEquals("401 Unauthorized", response.getStatus());
        assertEquals("Basic realm=\"WallyWorld\"", response.getHeader("WWW-Authenticate"));
    }

    @Test
    public void testRequestWithCredentialsReturnsTwoHundred() throws Exception {
        request.addHeader("Authorization", "Basic YWRtaW46aHVudGVyMg==");
        Response response = basicAuth.getResponse(request);
        assertEquals("200 OK", response.getStatus());
    }

    @Test
    public void testRequestWithCredentialsBodyContainsLogContents() throws Exception {
        request.addHeader("Authorization", "Basic YWRtaW46aHVudGVyMg==");
        Response response = basicAuth.getResponse(request);
        assertArrayEquals("GET / HTTP/1.1".getBytes(), response.getBody());

    }

    @After
    public void tearDown() throws Exception {
        File log = new File(filePath + "/logs");
        log.delete();
    }
}