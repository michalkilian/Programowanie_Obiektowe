package GeneralClasses;

import java.io.Serializable;

/**
 * Class that stores information that should be sent to server as a request to handle
 *
 * @author Michal Kilian
 */
public class MessageToServer implements Serializable {
    /**
     * Request to handle by server
     *
     * <p>
     * Example of requests: "creatememe", "signup"
     * </p>
     */
    private final String command;

    /**
     * ID of meme used when rating meme
     */
    private int memeId;

    /**
     * Meme object used when creating meme
     */
    private Meme meme;

    /**
     * Filter used when user want just part of memes from DB
     */
    private String filter;

    //Info about user
    /**
     *
     */
    private String username;

    /**
     *
     */
    private String password;

    /**
     *
     */
    private String autisticPseudo;


    public MessageToServer(String command) {
        this.command = command;
    }

    public String getCommand() {
        return command;
    }

    public Meme getMeme() {
        return meme;
    }

    public void setMeme(Meme meme) {
        this.meme = meme;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAutisticPseudo() {
        return autisticPseudo;
    }

    public void setAutisticPseudo(String autisticPseudo) {
        this.autisticPseudo = autisticPseudo;
    }

    public String getFilter() {
        return filter;
    }

    public void setFilter(String filter) {
        this.filter = filter;
    }

    public int getMemeId() {
        return memeId;
    }

    public void setMemeId(int memeId) {
        this.memeId = memeId;
    }
}
