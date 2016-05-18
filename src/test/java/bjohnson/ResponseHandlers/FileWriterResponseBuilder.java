package bjohnson.ResponseHandlers;

import bjohnson.Request;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;

public class FileWriterResponseBuilder implements ResponseBuilderInterface {
    private Response response;
    private String filePath;

    public FileWriterResponseBuilder(String filePath) {
        this.response = new Response();
        this.filePath = filePath;
    }

    public void writeToFile(Request request) {
        System.out.println("Enter function");

        String fullPath = filePath + request.getURL();
        File outFile = new File(fullPath);
        System.out.println(fullPath);

        try {
            if (!outFile.exists()) {
                System.out.println("Make File");
                outFile.createNewFile();
                System.out.println("File Made");

            }

            FileWriter fw = new FileWriter(outFile.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write("HELLO");
            bw.close();
        } catch (IOException e) {

            response = new FourOhFourResponseBuilder().getResponse(request);
        }
    }

    @Override
    public Response getResponse(Request request) {
        writeToFile(request);
        return response;
    }
}
