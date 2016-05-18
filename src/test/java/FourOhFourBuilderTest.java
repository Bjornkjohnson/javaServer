import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class FourOhFourBuilderTest {

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void testReturnsFourOhFour(){
        assertEquals("404 NOT FOUND", new FourOhFourBuilder().getResponse(new Request()).getStatus());
    }
}