package GeneralClasses;

import java.io.Serializable;

public class MessageToServer implements Serializable {
    private final String command;
    private Meme meme;
    private String username;
    private String password;
    private String autisticPseudo;
    private String filter;

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
}
