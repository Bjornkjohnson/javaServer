import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;

public class Server {
    private final Router router;
    private final String publicDir;
    private ServerSocket listener;
    private Socket clientSocket;
    private OutputStream out;
    BufferedReader in;

    public Server(Router router, String publicDir){
        this.router = router;
        this.publicDir = publicDir;
        System.out.println(this.publicDir);
    }

    public boolean isRunning(){
        return !listener.isClosed();
    }

    public void start(int port) {
        try {
            listener = new ServerSocket(port);
            while (isRunning()) {
                clientSocket = listener.accept();
                out = clientSocket.getOutputStream();
                in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                Request request = new RequestBuilder(in).buildRequest();
                Response response = router.getResponse(request.getRoute());
                byte[] responseBytes = response.buildStatusAndHeaderBytes();
                if (request.getURL().equals("/image.jpeg")){
                    BuildImageResponse builder = new BuildImageResponse(publicDir);
                    Response imageResponse = builder.getResponse(request);
                    out.write(imageResponse.buildStatusAndHeaderBytes());
                    out.write(imageResponse.getBody());
                    out.flush();
                } else if (request.getURL().equals("/image.png")){
                    File image = new File(publicDir + "/image.png");
                    byte[] fileContent = Files.readAllBytes(image.toPath());
                    out.write(responseBytes);
                    out.write(fileContent);
                    out.flush();
                } else if (request.getURL().equals("/image.gif")){
                    File image = new File(publicDir + "/image.gif");
                    byte[] fileContent = Files.readAllBytes(image.toPath());
                    out.write(responseBytes);
                    out.write(fileContent);
                    out.flush();
                } else if (request.getURL().equals("/file1")){
                    File image = new File(publicDir + "/file1");
                    byte[] fileContent = Files.readAllBytes(image.toPath());
                    out.write(responseBytes);
                    out.write(fileContent);
                    out.flush();
                }else {
                    out.write(responseBytes);
                    out.flush();
                }

                out.close();
            }
        } catch (Exception e){
            throw new RuntimeException(e);
        }

    }

    public void stop() {
        try {
            in.close();
            out.close();
            listener.close();
        }
        catch (IOException e) { throw new RuntimeException(e);}
    }

}
