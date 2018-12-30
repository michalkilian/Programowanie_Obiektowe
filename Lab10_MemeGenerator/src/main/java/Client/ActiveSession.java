package Client;

import GeneralClasses.MessageToServer;

public class ActiveSession {
    public static boolean connected = false;

    private String autisticPseudo;

    public String getAutisticPseudo() {
        return autisticPseudo;
    }

    public void setAutisticPseudo(String autisticPseudo) {
        this.autisticPseudo = autisticPseudo;
    }

    public void sendMessageToServer(MessageToServer message){

    }
}
