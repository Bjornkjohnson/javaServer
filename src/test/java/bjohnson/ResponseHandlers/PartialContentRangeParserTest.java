package bjohnson.ResponseHandlers;

import org.junit.Test;

import static org.junit.Assert.*;

public class PartialContentRangeParserTest {
    @Test
    public void testParserReturnsRangeGivenStartAndEnd() throws Exception {
        PartialContentRangeParser parser = new PartialContentRangeParser("bytes=3-5", 11);
        assertEquals(3, parser.getStart());
        assertEquals(6, parser.getEnd());
    }

    @Test
    public void testParserReturnsRangeGivenOnlyEnd() throws Exception {
        PartialContentRangeParser parser = new PartialContentRangeParser("bytes=-5", 11);
        assertEquals(6, parser.getStart());
        assertEquals(11, parser.getEnd());
    }

    @Test
    public void testParserReturnsRangeGivenOnlyStart() throws Exception {
        PartialContentRangeParser parser = new PartialContentRangeParser("bytes=5-", 11);
        assertEquals(5, parser.getStart());
        assertEquals(11, parser.getEnd());
    }
}