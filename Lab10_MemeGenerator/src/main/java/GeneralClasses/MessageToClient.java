package GeneralClasses;

import java.io.Serializable;
import java.util.ArrayList;

public class MessageToClient implements Serializable {
    private final String response;
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
}
