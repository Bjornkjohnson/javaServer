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
}