package bjohnson.ResponseHandlers;

import bjohnson.Request;
import org.junit.Test;

import static org.junit.Assert.*;

public class PartialContentRangeParserTest {
    @Test
    public void testParserReturnsRangeGivenStartAndEnd() throws Exception {
        Request request = new Request();
        request.addHeader("Range", "bytes=3-5");
        PartialContentRangeParser parser = new PartialContentRangeParser(request, 11);
        assertEquals(3, parser.getStart());
        assertEquals(6, parser.getEnd());
    }

    @Test
    public void testParserReturnsRangeGivenOnlyEnd() throws Exception {
        Request request = new Request();
        request.addHeader("Range", "bytes=-5");
        PartialContentRangeParser parser = new PartialContentRangeParser(request, 11);
        assertEquals(5, parser.getStart());
        assertEquals(10, parser.getEnd());
    }

    @Test
    public void testParserReturnsRangeGivenOnlyStart() throws Exception {
        Request request = new Request();
        request.addHeader("Range", "bytes=5-");
        PartialContentRangeParser parser = new PartialContentRangeParser(request, 11);
        assertEquals(5, parser.getStart());
        assertEquals(10, parser.getEnd());
    }
}