package GeneralClasses;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Class that stores information that should be sent to client after handling request
 *
 * @author Michal Kilian
 */
public class MessageToClient implements Serializable {


    /**
     * Server response based on handling request result
     *
     * <p>
     * Example of responses: "signinsucces", "signuperror".
     * </p>
     */
    private final String response;

    /**
     * Active user pseudonym
     *
     * <p>
     * This field is used after successful signing in or signing up
     * </p>
     */
    private String autisticPseudo;

    /**
     * Active user username
     *
     * <p>
     * This field is used after successful signing in or signing up
     * </p>
     */
    private String username;

    /**
     * List of memes retrieved from SQL request
     */
    private ArrayList<Meme> memeList;

    /**
     * Sum of active user memes' likes
     *
     * <p>
     * This field is used when sending active user statistics
     * </p>
     */
    private String karma;

    /**
     * Date when active user created account
     *
     * <p>
     * This field is used when sending active user statistics
     * </p>
     */
    private String registerDate;

    /**
     * Number of memes created by active user
     *
     * <p>
     * This field is used when sending active user statistics
     * </p>
     */
    private String numberOfMemes;

    /**
     * Max value of likes obtained by meme created by active user
     *
     * <p>
     * This field is used when sending active user statistics
     * </p>
     */
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
