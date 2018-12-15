package SzymonServer;

/**
 * Created by student on 2018-12-11.
 */


import SzymonServer.exceptions.WrongCommandException;

import java.io.*;
import java.net.*;

public class Server {
    String login = "szymon";
    String password = "";
    int passwordLine = 0;
    BufferedReader br = null;
    String [] files = {"plik1", "plik2", "plik3"};

    public Server() {
        try {
            ClassLoader classLoader = getClass().getClassLoader();
            File file = new File(classLoader.getResource("secret.txt").getFile());
            br = new BufferedReader(new FileReader(file));
            this.password = br.readLine();
            this.passwordLine = Integer.parseInt(br.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws IOException {
        Server server = new Server();
        ServerSocket serverSocket = null;
        Socket clientSocket = null;
        try {
            serverSocket = new ServerSocket(3000);
        } catch (IOException e) {
            System.out.println("Could not listen on port: 3000");
            System.exit(-1);
        }

        while (true) {
            try {
                clientSocket = serverSocket.accept();
            } catch (IOException e) {
                System.out.println("I/O error: " + e);
            }

            new SzymonServerThread(clientSocket, server).start();


        }
    }

}

