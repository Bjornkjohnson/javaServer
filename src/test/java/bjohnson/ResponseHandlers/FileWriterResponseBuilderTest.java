package bjohnson.ResponseHandlers;

import bjohnson.Request;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.*;

import static org.junit.Assert.*;

public class FileWriterResponseBuilderTest {
    private Request request;
    private String filePath;
    private String url;
    File file;


    @Before
    public void setUp() throws Exception {
        request = new Request();
        request.setMethod("POST");
        request.setURL("/form");

        filePath = System.getProperty("user.dir") + "/src/test/java/bjohnson/testResource";
        url = "/form";
        file = new File(filePath + url);

    }

    @Test
    public void testSuccessfullWriteReturnTwoHundredOK()throws Exception{
        request.setBody("Test Stuff");
        FileWriterResponseBuilder fileWriterResponseBuilder = new FileWriterResponseBuilder(filePath);
        Response response = fileWriterResponseBuilder.getResponse(request);
        assertEquals("200 OK", response.getStatus());
    }

    @Test
    public void testWriterCreatesFileIfNonExists()throws Exception{
        request.setBody("Test Stuff");
        FileWriterResponseBuilder fileWriterResponseBuilder = new FileWriterResponseBuilder(filePath);
        fileWriterResponseBuilder.getResponse(request);
        assertTrue(file.exists());
    }

    @Test
    public void testWriterWritesToFile()throws Exception{
        request.setBody("Test Stuff");
        FileWriterResponseBuilder fileWriterResponseBuilder = new FileWriterResponseBuilder(filePath);
        fileWriterResponseBuilder.getResponse(request);
        assertEquals("Test Stuff", checkFileContents());
    }

    @Test
    public void testResponseHasTwoOhFourStatus() throws Exception {
        request.setURL("/patch-content.txt");
        request.setMethod("PATCH");
        request.addHeader("If-Match", "dc50a0d27dda2eee9f65644cd7e4c9cf11de8bec");
        request.setBody("default content");
        FileWriterResponseBuilder fileWriterResponseBuilder = new FileWriterResponseBuilder(filePath);
        Response response = fileWriterResponseBuilder.getResponse(request);
        assertEquals("204 No Content", response.getStatus());
    }

    @Test
    public void testResponseHasFourHundredIfNotPatchable() throws Exception {
        request.setURL("/patch-content.txt");
        request.setMethod("PATCH");
        request.addHeader("If-Match", "dc50a0d27dda2eee9f65644cd7e4c9cf11de8b");
        request.setBody("default content");
        FileWriterResponseBuilder fileWriterResponseBuilder = new FileWriterResponseBuilder(filePath);
        Response response = fileWriterResponseBuilder.getResponse(request);
        assertEquals("400 Bad Request", response.getStatus());
    }

    private String checkFileContents() throws IOException{
        file = new File(filePath + url);
        BufferedReader br = new BufferedReader(new FileReader(file));
        return br.readLine();
    }

    @After
    public void tearDown(){
        if (file.exists()){
            file.delete();
        }
    }
}