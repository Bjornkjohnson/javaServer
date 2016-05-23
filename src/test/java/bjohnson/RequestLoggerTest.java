package bjohnson;

import org.junit.Before;
import org.junit.Test;

import java.io.File;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.*;

public class RequestLoggerTest {
    private String filePath;

    @Before
    public void setUp(){
        filePath = System.getProperty("user.dir") + "/src/test/java/bjohnson/testResource";

    }
    @Test
    public void testLogsRequestToFile() throws Exception {
        RequestLogger logger = new RequestLogger(filePath + "/Log");
        logger.logRequest("hi");
        logger.logRequest("there");
        logger.logRequest("guy");
        String contents = new String(FileIO.readFromFile(filePath + "/Log"));
        assertThat(contents, containsString("hi"));
        assertThat(contents, containsString("there"));
        assertThat(contents, containsString("guy"));
        File log = new File(filePath + "/Log");
        log.delete();
    }



}