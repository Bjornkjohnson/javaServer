package bjohnson.ResponseHandlers;

import bjohnson.Request;

import javax.xml.bind.DatatypeConverter;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.security.MessageDigest;
import java.util.Arrays;

public class FileWriterResponseBuilder implements ResponseBuilderInterface {
    private Response response;
    private String filePath;
    private String fullPath;

    public FileWriterResponseBuilder(String filePath) {
        this.filePath = filePath;
    }

    public void writeToFile(Request request) {
        fullPath = filePath + request.getURL();
        File outFile = new File(fullPath);


        try {
            if (!outFile.exists()) {
                outFile.createNewFile();
            }

            FileWriter fw = new FileWriter(outFile.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(request.getBody());
            bw.close();

        } catch (IOException e) {

            response = new FourOhFourResponseBuilder().getResponse(request);
        }
    }

    @Override
    public Response getResponse(Request request) {
        this.response = new Response();
        if (isPatch(request.getMethod())){
            getPatchResponse(request);
        } else {
            writeToFile(request);
        }
        return response;
    }

    private void getPatchResponse(Request request) {
        if (isPatchable(request)){
            response.setStatus("204 No Content");
            writeToFile(request);
        }
    }

    private boolean isPatchable(Request request) {

        try {
            MessageDigest mDigest = MessageDigest.getInstance("SHA1");
            byte[] fileContentsHash = mDigest.digest(getFileContents(request));
            byte[] requestHash = DatatypeConverter.parseHexBinary(request.getHeaders().get("If-Match"));
            if (Arrays.equals(requestHash, fileContentsHash)){
                return true;
            }
        }catch (Exception e){
            System.out.println(e);
        }
        response.setStatus("400 Bad Request");
        return false;
    }

    private byte[] getFileContents(Request request) throws IOException {
        File patchFile = new File(filePath + request.getURL());
        return Files.readAllBytes(patchFile.toPath());
    }

    private boolean isPatch(String method) {
        return method.equals("PATCH");
    }
}
