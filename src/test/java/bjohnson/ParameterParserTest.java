package bjohnson;

import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.*;

public class ParameterParserTest {
    private final String rawParams = "/parameters?variable_1=Operators%20%3C%2C%20%3E%2C%20%3D%2C%20!%3D%3B%20%2B%2C%20-%2C%20*%2C%20%26%2C%20%40%2C%20%23%2C%20%24%2C%20%5B%2C%20%5D%3A%20%22is%20that%20all%22%3F&variable_2=stuff";;
    private final String parsedParams1 = "variable_1=Operators <, >, =, !=; +, -, *, &, @, #, $, [, ]: \"is that all\"?";
    private final String parsedParams2 = "variable_2=stuff";
    private HashMap<String, String> decoder;

    @Before
    public void setUp() throws Exception{

    }


    @Test
    public void TestReturnsArrayOfSizeOneForSimpleString() throws Exception {
        ParameterParser parameterParser = new ParameterParser();
        assertEquals(1, parameterParser.parseParameters("/route?params").length);
    }

    @Test
    public void TestRemovesRoute() throws Exception {
        ParameterParser parameterParser = new ParameterParser();
        assertEquals("params", parameterParser.parseParameters("/route?params")[0]);
    }

    @Test
    public void TestParserSplitsOnAmpersand() throws Exception {
        ParameterParser parameterParser = new ParameterParser();
        assertEquals(2, parameterParser.parseParameters("/route?params&otherParams").length);
        assertEquals("params", parameterParser.parseParameters("/route?params&otherParams")[0]);
        assertEquals("otherParams", parameterParser.parseParameters("/route?params&otherParams")[1]);

    }

    @Test
    public void TestParserReplacesAllCharactersGivenAHash() throws Exception {
        ParameterParser parameterParser = new ParameterParser();
        String paramsArray[] = parameterParser.parseParameters(rawParams);
        assertEquals(2, paramsArray.length);
        assertEquals(parsedParams1, paramsArray[0]);
        assertEquals(parsedParams2, paramsArray[1]);

    }

}