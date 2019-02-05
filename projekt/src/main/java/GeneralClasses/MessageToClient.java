package GeneralClasses;

import java.io.Serializable;
import java.util.ArrayList;

public class MessageToClient implements Serializable {
    private final String response;
    private String autisticPseudo;
    private String username;
    private ArrayList<Meme> memeList;
    private String karma;
    private String registerDate;
    private String numberOfMemes;
    private String topMemeKarma;


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

    public String getKarma() {
        return karma;
    }

    public void setKarma(String karma) {
        this.karma = karma;
    }

    public String getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(String registerDate) {
        this.registerDate = registerDate;
    }

    public String getNumberOfMemes() {
        return numberOfMemes;
    }

    public void setNumberOfMemes(String numberOfMemes) {
        this.numberOfMemes = numberOfMemes;
    }

    public String getTopMemeKarma() {
        return topMemeKarma;
    }

    public void setTopMemeKarma(String topMemeKarma) {
        this.topMemeKarma = topMemeKarma;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
