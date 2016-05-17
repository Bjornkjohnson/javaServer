
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.nio.file.Files;

import static org.junit.Assert.*;

public class BuildImageResponseTest {
    private Request request;
    private String imagePath;

    @Before
    public void setUp() throws Exception {
        request = new Request();
        request.setMethod("GET");
        imagePath = System.getProperty("user.dir") + "/src/test/java/testResource";

    }

    @Test
    public void testResponseBodyContainsImageForGoodPath() throws Exception {
        request.setURL("/image.png");
        File testImage = new File(imagePath + "/image.png");
        byte[] fileContent = Files.readAllBytes(testImage.toPath());
        BuildImageResponse imageResponse = new BuildImageResponse(request, imagePath);
        Response response = imageResponse.getResponse();
        assertArrayEquals(fileContent, response.getBody());
    }

    @Test
    public void testMissingImageReturnsFourOhFour() throws Exception {
        request.setURL("/fake.png");
        BuildImageResponse imageResponse = new BuildImageResponse(request, imagePath);
        Response response = imageResponse.getResponse();
        assertEquals("404 NOT FOUND", response.getStatus());
    }
}