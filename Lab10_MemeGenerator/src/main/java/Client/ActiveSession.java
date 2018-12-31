package Client;

import GeneralClasses.MessageToClient;
import GeneralClasses.MessageToServer;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

public class ActiveSession {
    Socket echoSocket = null;
    ObjectOutputStream out = null;
    ObjectInputStream in = null;

    public static boolean connected = false;

    private String autisticPseudo;

    public String getAutisticPseudo() {
        return autisticPseudo;
    }

    public void setAutisticPseudo(String autisticPseudo) {
        this.autisticPseudo = autisticPseudo;
    }

    public void sendMessageToServer(MessageToServer message) throws IOException, ClassNotFoundException {
        connectToServer();
        out.writeObject(message);
        MessageToClient returnedMessage = (MessageToClient) in.readObject();
        handleMessage(returnedMessage);
    }


    public void connectToServer(){
        echoSocket = null;
        out = null;
        in = null;

        try{
            echoSocket = new Socket("localhost", 9000);
            out = new ObjectOutputStream(echoSocket.getOutputStream());
            in = new ObjectInputStream(echoSocket.getInputStream());
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void handleMessage(MessageToClient returnedMessage) {
    }
}
