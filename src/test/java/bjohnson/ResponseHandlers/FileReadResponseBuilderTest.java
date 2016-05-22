package bjohnson.ResponseHandlers;

import bjohnson.Request;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class FileReadResponseBuilderTest {
    private Request request;
    private String filePath;

    @Before
    public void setUp() throws Exception {
        request = new Request();
        request.setMethod("GET");
        filePath = System.getProperty("user.dir") + "/src/test/java/bjohnson/testResource";

    }

    @Test
    public void testResponseReadsFromFile(){
        request.setURL("/readerFile.txt");
        FileReadResponseBuilder fileReadResponseBuilder = new FileReadResponseBuilder(filePath);
        Response response = fileReadResponseBuilder.getResponse(request);
        assertArrayEquals("Test Text!".getBytes(), response.getBody());
    }

    @Test
    public void testBodyIsEmptyIfFileDoesNotExist(){
        request.setURL("/nonExistentFile.txt");
        FileReadResponseBuilder fileReadResponseBuilder = new FileReadResponseBuilder(filePath);
        Response response = fileReadResponseBuilder.getResponse(request);
        assertArrayEquals(null, response.getBody());
    }

    @Test
    public void testReturnsPartialFileContentWithStartAndEnd(){
        request.setURL("/partial_content.txt");
        request.addHeader("Range", "bytes=0-4");
        FileReadResponseBuilder fileReadResponseBuilder = new FileReadResponseBuilder(filePath);
        Response response = fileReadResponseBuilder.getResponse(request);
        assertArrayEquals("This ".getBytes(), response.getBody());
    }

    @Test
    public void testReturnsPartialFileContentWithoutStart(){
        request.setURL("/partial_content.txt");
        request.addHeader("Range", "bytes=-6");
        FileReadResponseBuilder fileReadResponseBuilder = new FileReadResponseBuilder(filePath);
        Response response = fileReadResponseBuilder.getResponse(request);
        assertArrayEquals(" 206.\n".getBytes(), response.getBody());
    }

    @Test
    public void testReturnsPartialFileContentWithoutEnd(){
        request.setURL("/partial_content.txt");
        request.addHeader("Range", "bytes=4-");
        FileReadResponseBuilder fileReadResponseBuilder = new FileReadResponseBuilder(filePath);
        Response response = fileReadResponseBuilder.getResponse(request);
        assertArrayEquals(" is a file that contains text to read part of in order to fulfill a 206.\n".getBytes(), response.getBody());
    }

    @Test
    public void testReturnsTwoOhSix(){
        request.setURL("/partial_content.txt");
        request.addHeader("Range", "bytes=0-4");
        FileReadResponseBuilder fileReadResponseBuilder = new FileReadResponseBuilder(filePath);
        Response response = fileReadResponseBuilder.getResponse(request);
        assertEquals("206 PARTIAL CONTENT", response.getStatus());
    }
}