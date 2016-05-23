package bjohnson.ResponseHandlers;

import bjohnson.Request;
import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class HTMLDirectoryResponseBuilderTest {
    private String filePath;
    private Request request;

    @Before
    public void setUp(){
        filePath = System.getProperty("user.dir") + "/src/test/java/bjohnson/testResource";
        request = new Request();
        request.setURL("/");
        request.setMethod("GET");
    }
    @Test
    public void testReturns200Ok() throws Exception {
        Response response = new HTMLDirectoryResponseBuilder(filePath).getResponse(request);
        assertEquals("200 OK", response.getStatus());
    }

    @Test
    public void testBodyContainsTestHtml() throws Exception {
        HTMLDirectoryResponseBuilder htmlDirectoryResponseBuilder = new HTMLDirectoryResponseBuilder(filePath);
        Response response = htmlDirectoryResponseBuilder.getResponse(request);
        assertThat(new String(response.getBody()), CoreMatchers.containsString("<li><a href=\"/image.png\">image.png</a></li>\n"));
        assertThat(new String(response.getBody()), CoreMatchers.containsString("<li><a href=\"/partial_content.txt\">partial_content.txt</a></li>\n"));
        assertThat(new String(response.getBody()), CoreMatchers.containsString("<li><a href=\"/patch-content.txt\">patch-content.txt</a></li>\n"));
        assertThat(new String(response.getBody()), CoreMatchers.containsString("<li><a href=\"/readerFile.txt\">readerFile.txt</a></li>\n"));
    }
}