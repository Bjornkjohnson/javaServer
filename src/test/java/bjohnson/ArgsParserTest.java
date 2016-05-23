package bjohnson;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class ArgsParserTest {

    @Test
    public void testPortExtractionWhenPortFirst() throws Exception {
        String[] rawArgs = {"-p", "5020", "-d", "MY_DIR"};
        Arrays.asList(rawArgs);
        ArgsParser testParser = new ArgsParser(rawArgs);
        Assert.assertEquals(5020, testParser.getPort());
    }

    @Test
    public void testPortExtractionWhenPortSecond() throws Exception {
        String[] rawArgs = { "-d", "MY_DIR", "-p", "5020"};
        Arrays.asList(rawArgs);
        ArgsParser testParser = new ArgsParser(rawArgs);
        Assert.assertEquals(5020, testParser.getPort());
    }

    @Test
    public void testNoPortFlagReturnsDefault() throws Exception {
        String[] rawArgs = { "-d", "MY_DIR"};
        ArgsParser testParser = new ArgsParser(rawArgs);
        Assert.assertEquals(5000, testParser.getPort());
    }

    @Test(expected=NumberFormatException.class)
    public void testPortFlagFollowedByNonNumberThrowsException() throws Exception {
        String[] rawArgs = {"-p" , "-d", "MY_DIR"};
        ArgsParser testParser = new ArgsParser(rawArgs);
        testParser.getPort();
    }

    @Test(expected=ArrayIndexOutOfBoundsException.class)
    public void testPortFlagAtFinalPositionWithNoNumberThrowsException() throws Exception {
        String[] rawArgs = { "-d", "MY_DIR", "-p"};
        ArgsParser testParser = new ArgsParser(rawArgs);
        testParser.getPort();
    }

    @Test
    public void testDirectoryExtraction() throws Exception {
        String[] rawArgs = { "-d", "MY_DIR", "-p", "5020"};
        ArgsParser testParser = new ArgsParser(rawArgs);
        Assert.assertEquals("MY_DIR", testParser.getDirectory());
    }

    @Test
    public void testNoDirectoryFlagReturnsDefault() throws Exception {
        String[] rawArgs = { "-p", "5020"};
        ArgsParser testParser = new ArgsParser(rawArgs);
        Assert.assertEquals("/Users/bjornjohnson/dev/cob_spec/public", testParser.getDirectory());
    }

    @Test
    public void tesLongerDirectoryPath() throws Exception {
        String[] rawArgs = { "-d", "/User/somebody/cob_spec/public/"};
        ArgsParser testParser = new ArgsParser(rawArgs);
        Assert.assertEquals("/User/somebody/cob_spec/public/", testParser.getDirectory());
    }

    @Test(expected=ArrayIndexOutOfBoundsException.class)
    public void testDirectoryFlagAtFinalPositionWithNoNumberThrowsException() throws Exception {
        String[] rawArgs = { "-p", "5020", "-d"};
        ArgsParser testParser = new ArgsParser(rawArgs);
        testParser.getDirectory();
    }

    @Test
    public void testEmptyArgsReturnsDefault() throws Exception {
        String[] rawArgs = {};
        ArgsParser testParser = new ArgsParser(rawArgs);
        Assert.assertEquals("/Users/bjornjohnson/dev/cob_spec/public", testParser.getDirectory());
        Assert.assertEquals(5000, testParser.getPort());

    }
}