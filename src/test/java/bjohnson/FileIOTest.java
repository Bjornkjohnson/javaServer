package bjohnson;

import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertArrayEquals;

public class FileIOTest {
    private String filePath;

    @Before
    public void setUp(){
        filePath = System.getProperty("user.dir") + "/src/test/java/bjohnson/testResource";

    }

    @Test
    public void testReadingFromTextFile() throws Exception {
        String file = "/readerFile.txt";
        assertArrayEquals("Test Text!".getBytes(), FileIO.readFromFile(filePath + file));
    }

    @Test(expected=IOException.class)
    public void testReadThrowExceptionIfFileNotFound() throws Exception {
        String file = "/fakeFile.txt";
        FileIO.readFromFile(filePath + file);
    }

    @Test
    public void testWritingToFile() throws Exception {
        String file = "/writeTesterFile.txt";
        String testData = "Test Data!";
        FileIO.writeToFile(filePath + file, testData);
        assertArrayEquals(testData.getBytes(), FileIO.readFromFile(filePath + file));
        File writeFile = new File(filePath + file);
        writeFile.delete();
    }

    @Test
    public void TestReadPartialContent() throws Exception {
        String file = "/readerFile.txt";
        assertArrayEquals("Text!".getBytes(), FileIO.readPartialContents(filePath + file, "bytes=5-10"));
    }

    @Test
    public void TestDirectoryRead() throws Exception {
        String[] files = {"image.png", "partial_content.txt", "patch-content.txt", "readerFile.txt"};
        assertArrayEquals(files, FileIO.getDirectoryContents(filePath));
    }

    @Test
    public void TestAppendToFile() throws Exception {
        String file = "/appendFile.txt";
        FileIO.appendToFile(filePath + file, "I wrote This");
        FileIO.appendToFile(filePath + file, "and this");
        FileIO.appendToFile(filePath + file, "this too");
        assertArrayEquals("I wrote This\nand this\nthis too\n".getBytes(), FileIO.readFromFile(filePath + file));
        File appendFile = new File(filePath + file);
        appendFile.delete();
    }

}