package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * Runnable server class which uses thread pool to handle incoming requests
 *
 * @author Michal Kilian
 */
public class Server implements Runnable {
    protected int serverPort;
    protected ServerSocket serverSocket = null;
    protected Thread runningThread = null;
    protected ExecutorService threadPool =
            Executors.newFixedThreadPool(10);

    public Server(int port) {
        this.serverPort = port;
    }

    public static void main(String[] args) {
        Server server = new Server(9000);
        new Thread(server).start();
    }

    @Override
    public void run() {
        synchronized (this) {
            this.runningThread = Thread.currentThread();
        }
        openServerSocket();
        while (true) {
            Socket clientSocket = null;
            try {
                clientSocket = this.serverSocket.accept();
            } catch (IOException e) {
                e.printStackTrace();
            }
            this.threadPool.execute(new WorkerRunnable(clientSocket));
        }
    }


    private void openServerSocket() {
        try {
            this.serverSocket = new ServerSocket(this.serverPort);
        } catch (IOException e) {
            throw new RuntimeException("Cannot open port " + this.serverPort, e);
        }
    }
}
