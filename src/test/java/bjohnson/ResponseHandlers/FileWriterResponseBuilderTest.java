package bjohnson.ResponseHandlers;

import bjohnson.Request;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.*;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class FileWriterResponseBuilderTest {
    private Request request;
    private String filePath;
    private String url;
    File file;


    @Before
    public void setUp() throws Exception {
        request = new Request();
        request.setMethod("GET");
        filePath = System.getProperty("user.dir") + "/src/test/java/bjohnson/testResource";
        url = "/form";
        file = new File(filePath + url);

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
    public void testWriterCreatesFileIfNonExists()throws Exception{
        request.setMethod("POST");
        request.setURL("/form");
        FileWriterResponseBuilder fileWriterResponseBuilder = new FileWriterResponseBuilder(filePath);
        fileWriterResponseBuilder.getResponse(request);
        assertTrue(file.exists());
    }

//    @Test
//    public void testWriterWritesToFile()throws Exception{
//        request.setMethod("POST");
//        request.setURL("/form");
//        FileWriterResponseBuilder fileWriterResponseBuilder = new FileWriterResponseBuilder(filePath);
//        fileWriterResponseBuilder.getResponse(request);
//        String output =
//        assertTrue(file.exists());
//    }

    @After
    public void tearDown(){
        if (file.exists()){
            file.delete();
        }
    }
}