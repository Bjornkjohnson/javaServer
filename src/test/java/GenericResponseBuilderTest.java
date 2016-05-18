import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class GenericResponseBuilderTest {

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void testRuturns200OK(){
        assertEquals("200 OK", new GenericResponseBuilder().getResponse(new Request()).getStatus());
    }
}