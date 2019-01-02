package Client;

/**
 * Klasa służy do wykonywania komunkacji klient serwer i przechowywania danych o zalogowanym użytwkoniku
 *
 * @author Michał Kilian
 */

import GeneralClasses.Meme;
import GeneralClasses.MessageToClient;
import GeneralClasses.MessageToServer;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class ActiveSession {
    Socket echoSocket = null;
    ObjectOutputStream out = null;
    ObjectInputStream in = null;

    public static boolean connected = false;

    private String autisticPseudo = "";

    private String responseHead = "";
    private String responseBody = "";

    private ArrayList<Meme> memeList = new ArrayList<>();

    public String getAutisticPseudo() {
        return autisticPseudo;
    }

    public void setAutisticPseudo(String autisticPseudo) {
        this.autisticPseudo = autisticPseudo;
    }

    public String getResponseHead() {
        return responseHead;
    }

    public void setResponseHead(String responseHead) {
        this.responseHead = responseHead;
    }

    public String getResponseBody() {
        return responseBody;
    }

    public void setResponseBody(String responseBody) {
        this.responseBody = responseBody;
    }

    public ArrayList<Meme> getMemeList() {
        return memeList;
    }

    public void setMemeList(ArrayList<Meme> memeList) {
        this.memeList = memeList;
    }

    /**
     * Funkcja łączy się z serwerem i przesyła obiekt klasy MessageToServer na serwer
     *
     * @param message obiekt zawierający wszystkie informacje potrzebne do przetworzenia polecenia czyli
     *                nazwę komendy i/lub mem dane autora napisy
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public void sendMessageToServer(MessageToServer message) throws IOException, ClassNotFoundException {
        connectToServer();
        out.writeObject(message);
        MessageToClient returnedMessage = (MessageToClient) in.readObject();
        handleMessage(returnedMessage);
    }


    public void connectToServer() {
        echoSocket = null;
        out = null;
        in = null;

        try {
            echoSocket = new Socket("localhost", 9000);
            out = new ObjectOutputStream(echoSocket.getOutputStream());
            in = new ObjectInputStream(echoSocket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void handleMessage(MessageToClient returnedMessage) {
        responseHead = null;
        System.out.println(returnedMessage.getResponse());
        switch (returnedMessage.getResponse()) {
            case "createsuccess":
                responseHead = "Creation Successful";
                responseBody = "Meme created and added to base";
                break;
            case "createerror":
                responseHead = "Creation Failed";
                break;
            case "signinsuccess":
                responseHead = "Sign In Success";
                responseBody = "Logged as " + returnedMessage.getAutisticPseudo();
                this.autisticPseudo = returnedMessage.getAutisticPseudo();
                break;
            case "signinerror":
                responseHead = "Sign In Failed";
                responseBody = "";
                break;
            case "signupsuccess":
                responseHead = "Sign Up Success";
                responseBody = "Logged as " + returnedMessage.getAutisticPseudo();
                this.autisticPseudo = returnedMessage.getAutisticPseudo();
                break;
            case "signuperror":
                responseHead = "Sign Up Failed";
                responseBody = "";
                break;
            case "searchallsuccess":
            case "searchtagsuccess":
            case "searchauthorsuccess":
            case "searchtitlesuccess":
                memeList = returnedMessage.getMemeList();
                break;
            case "searchallerror":
            case "searchauthorerror":
            case "searchtitleerror":
            case "searchtagerror":
                responseHead = "Error";
                responseBody = "Can't load memes";
                break;
        }
    }


}
