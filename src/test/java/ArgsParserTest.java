import com.sun.org.apache.xpath.internal.Arg;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Array;
import java.util.Arrays;

import static org.junit.Assert.*;

public class ArgsParserTest {

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testPortExtractionWhenPortFirst() throws Exception {
        String[] rawArgs = {"-p", "5020", "-d", "MY_DIR"};
        Arrays.asList(rawArgs);
        ArgsParser testParser = new ArgsParser(rawArgs);
        assertEquals(5020, testParser.getPort());
    }

    @Test
    public void testPortExtractionWhenPortSecond() throws Exception {
        String[] rawArgs = { "-d", "MY_DIR", "-p", "5020"};
        Arrays.asList(rawArgs);
        ArgsParser testParser = new ArgsParser(rawArgs);
        assertEquals(5020, testParser.getPort());
    }

    @Test
    public void testNoPortFlagReturnsDefault() throws Exception {
        String[] rawArgs = { "-d", "MY_DIR"};
        ArgsParser testParser = new ArgsParser(rawArgs);
        assertEquals(5000, testParser.getPort());
    }

    @Test(expected=NumberFormatException.class)
    public void testPortFlagFollowedByNonNumberThrowsExpception() throws Exception {
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
        assertEquals("MY_DIR", testParser.getDirectory());
    }

    @Test
    public void testNoDirectoryFlagReturnsDefault() throws Exception {
        String[] rawArgs = { "-p", "5020"};
        ArgsParser testParser = new ArgsParser(rawArgs);
        assertEquals("PUBLIC_DIR", testParser.getDirectory());
    }

    @Test
    public void tesLongerDirectoryPath() throws Exception {
        String[] rawArgs = { "-d", "/User/somebody/cob_spec/public/"};
        ArgsParser testParser = new ArgsParser(rawArgs);
        assertEquals("/User/somebody/cob_spec/public/", testParser.getDirectory());
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
        assertEquals("PUBLIC_DIR", testParser.getDirectory());
        assertEquals(5000, testParser.getPort());

    }
}