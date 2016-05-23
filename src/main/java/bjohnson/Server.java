package bjohnson;

import bjohnson.ResponseHandlers.Response;
import bjohnson.ResponseHandlers.ResponseBuilderInterface;

import java.io.*;
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

    private boolean isRunning(){
        return !listener.isClosed();
    }

    public void start(int port) {
        try {
            listener = new ServerSocket(port);
            Executor threadPool = Executors.newFixedThreadPool(50);
            while (isRunning()) {
                Socket clientSocket = listener.accept();
                threadPool.execute(new ServerThread(clientSocket, router, logger));
            }
        } catch (Exception e){
            throw new RuntimeException(e);
        }

    }

}
