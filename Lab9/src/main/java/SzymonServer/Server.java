package SzymonServer;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;

public class Server {
    static String login = "szymon";
    static String password = "szczurzyc";
    static String[] files = {"plik1", "plik2", "plik3"};
    static LinkedList<String> loggedUsers = new LinkedList<>();

    public Server() {

    }

    public static void main(String[] args) throws IOException {

        ServerSocket serverSocket = null;
        Socket clientSocket = null;
        try {
            serverSocket = new ServerSocket(3000);
        } catch (IOException e) {
            System.out.println("Could not listen on port: 3000");
            System.exit(-1);
        }
        try {
            while (true) {

                clientSocket = serverSocket.accept();
                System.out.println("SRV: ACCEPTED CLIENT");
                new SzymonServerThread(clientSocket).start();
                System.out.println("SRV: STARTED THREAD");
            }
        } catch (IOException e) {
            System.out.println("I/O error: " + e);
        }
        clientSocket.close();
        serverSocket.close();
    }

}

