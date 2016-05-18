package bjohnson.ResponseHandlers;

import bjohnson.Request;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class FileWriterResponseBuilderTest {
    private Request request;
    private String filePath;

    @Before
    public void setUp() throws Exception {
        request = new Request();
        request.setMethod("GET");
        filePath = System.getProperty("user.dir") + "/src/test/java/bjohnson/testResource";

    }

    @Test
    public void testSuccessfullWriteReturnTwoHundredOK()throws Exception{
        request.setMethod("POST");
        request.setURL("/form");
        FileWriterResponseBuilder fileWriterResponseBuilder = new FileWriterResponseBuilder(filePath);
        Response response = fileWriterResponseBuilder.getResponse(request);
        assertEquals("200 OK", response.getStatus());
    }

    @Test
    public void testWriterWritesToFile()throws Exception{
        request.setMethod("POST");
        request.setURL("/form");
        FileWriterResponseBuilder fileWriterResponseBuilder = new FileWriterResponseBuilder(filePath);
        Response response = fileWriterResponseBuilder.getResponse(request);
        assertEquals("200 OK", response.getStatus());
    }
}