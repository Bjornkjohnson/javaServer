package bjohnson;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;

public class FileIO {
    public static byte[] readFromFile(String filePath) throws IOException {
        File file = new File(filePath);
        byte[] fileContent = Files.readAllBytes(file.toPath());
        return fileContent;
    }

    public static void writeToFile(String filePath, String data) throws IOException {
        File outFile = new File(filePath);
        FileWriter fw = new FileWriter(outFile.getAbsoluteFile());
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(data);
        bw.close();
        fw.close();
    }
}