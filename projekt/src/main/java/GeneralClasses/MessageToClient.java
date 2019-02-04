package GeneralClasses;

import java.io.Serializable;
import java.util.ArrayList;

public class MessageToClient implements Serializable {
    private final String response;
    private String autisticPseudo;
    private ArrayList<Meme> memeList;


    public MessageToClient(String response) {
        this.response = response;
    }

    public String getResponse() {
        return response;
    }


    public ArrayList<Meme> getMemeList() {
        return memeList;
    }

    public void setMemeList(ArrayList<Meme> memeList) {
        this.memeList = memeList;
    }

    public String getAutisticPseudo() {
        return autisticPseudo;
    }

    public void setAutisticPseudo(String autisticPseudo) {
        this.autisticPseudo = autisticPseudo;
    }
}
