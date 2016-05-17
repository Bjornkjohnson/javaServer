import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;

public class Server {
    private final Router router;
    private ServerSocket listener;
    private Socket clientSocket;
    private OutputStream out;
    BufferedReader in;

    public Server(Router router){
        this.router = router;
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
                byte[] responseBytes = response.buildResponseString().getBytes();
                if (request.getURL().equals("/image.jpeg")){
                    File image = new File("/Users/bjornjohnson/dev/cob_spec/public/image.jpeg");
                    byte[] fileContent = Files.readAllBytes(image.toPath());
                    out.write(responseBytes);
                    out.write(fileContent);
                } else if (request.getURL().equals("/image.png")){
                    File image = new File("/Users/bjornjohnson/dev/cob_spec/public/image.png");
                    byte[] fileContent = Files.readAllBytes(image.toPath());
                    out.write(responseBytes);
                    out.write(fileContent);
                } else if (request.getURL().equals("/image.gif")){
                    File image = new File("/Users/bjornjohnson/dev/cob_spec/public/image.gif");
                    byte[] fileContent = Files.readAllBytes(image.toPath());
                    out.write(responseBytes);
                    out.write(fileContent);
                } else if (request.getURL().equals("/file1")){
                    File image = new File("/Users/bjornjohnson/dev/cob_spec/public/file1");
                    byte[] fileContent = Files.readAllBytes(image.toPath());
                    out.write(responseBytes);
                    out.write(fileContent);
                }else {
                    out.write(responseBytes);
                }

                System.out.println(response.buildResponseString());


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
