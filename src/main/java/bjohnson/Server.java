package bjohnson;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

class Server {
    private final Router router;
    private final String publicDir;
    private RequestLogger logger;
    private ServerSocket listener;

    public Server(Router router, String publicDir, RequestLogger logger){
        this.router = router;
        this.publicDir = publicDir;
        this.logger = logger;
        System.out.println(this.publicDir);
    }

    public boolean isRunning(){
        return !listener.isClosed();
    }

    public void start(int port) {
        try {
            listener = new ServerSocket(port);
            Executor threadPool = Executors.newFixedThreadPool(5);
            while (isRunning()) {
                Socket clientSocket = listener.accept();
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                OutputStream out = clientSocket.getOutputStream();
                threadPool.execute(new ServerThread(in, out, router, logger));
            }
        } catch (Exception e){
            e.printStackTrace();
        }

    }

}
