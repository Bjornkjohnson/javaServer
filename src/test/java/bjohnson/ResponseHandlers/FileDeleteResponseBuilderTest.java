package bjohnson.ResponseHandlers;

import bjohnson.Request;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.*;

public class FileDeleteResponseBuilderTest {
    private File file;
    private Request request;
    private FileDeleteResponseBuilder deleteResponseBuilder;


    @Before
    public void setUp() throws Exception{
        String filePath = System.getProperty("user.dir") + "/src/test/java/bjohnson/testResource";
        String fileName = "/testFile";
        file = new File(filePath + fileName);
        request = new Request();
        request.setURL(fileName);
        deleteResponseBuilder = new FileDeleteResponseBuilder(filePath);
    }

    @Test
    public void testDeleteFileReturnsTwoHundredOK() throws Exception{
        file.createNewFile();
        Response response = deleteResponseBuilder.getResponse(request);
        assertEquals("200 OK", response.getStatus());
    }

    @Test
    public void testDeleteFileDeletesFile() throws Exception{
        file.createNewFile();
        assertTrue(file.exists());
        deleteResponseBuilder.getResponse(request);
        assertFalse(file.exists());
    }

//    @Test
//    public void testDeleteReturnsFourOhFourIfFileNotFound() throws Exception{
//        deleteResponseBuilder.getResponse(request);
//        Response response = deleteResponseBuilder.getResponse(request);
//        assertEquals("404 NOT FOUND", response.getStatus());
//    }

}