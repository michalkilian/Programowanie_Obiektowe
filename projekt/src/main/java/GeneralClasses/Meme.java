package GeneralClasses;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;

import javax.imageio.ImageIO;
import java.io.*;

/**
 * Class representing meme object
 *
 * <p>
 * It stores information about meme and implements custom serialization functions
 * making it possible to send and retrieve through sockets
 * </p>
 *
 * @author Michal Kilian
 */
public class Meme implements Serializable {


    private String upperText;
    private String bottomText;
    private String tag;
    private String author;
    private String title;
    private transient Image image;

    private int memeID;
    private String rating;

    /**
     * Constructor used when client creates meme and it has no upper and bottom text
     *
     * @param upperText  text that will be placed on the top of the picture
     * @param bottomText text that will be placed on the bottom of the picture
     * @param tag        text that will make meme possible to find by specific tag
     * @param author     pseudonym of user that created meme
     * @param title      title of the meme
     * @param image      image of the meme
     */
    public Meme(String upperText, String bottomText, String tag, String author, String title, Image image) {
        this.upperText = upperText;
        this.bottomText = bottomText;
        this.tag = tag;
        this.author = author;
        this.title = title;
        this.image = image;
    }

    /**
     * Constructor used when server creates meme and it already has upper and bottom text
     *
     * @param tag    text that will make meme possible to find by specific tag
     * @param author pseudonym of user that created meme
     * @param title  title of the meme
     * @param image  image of the meme
     */
    public Meme(String tag, String author, String title, Image image) { //If meme already has upper and bottom text
        this.tag = tag;
        this.author = author;
        this.title = title;
        this.image = image;
    }

    /**
     * Deserialize object
     *
     * @param s
     * @throws IOException
     * @throws ClassNotFoundException
     */
    private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
        s.defaultReadObject();
        image = SwingFXUtils.toFXImage(ImageIO.read(s), null);
    }

    /**
     * Serialize object
     *
     * @param s
     * @throws IOException
     */
    private void writeObject(ObjectOutputStream s) throws IOException {
        s.defaultWriteObject();
        ImageIO.write(SwingFXUtils.fromFXImage(image, null), "jpg", s);
    }

    public String getUpperText() {
        return upperText;
    }

    public String getBottomText() {
        return bottomText;
    }

    public String getTag() {
        return tag;
    }

    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public Image getImage() {
        return image;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public int getMemeID() {
        return memeID;
    }

    public void setMemeID(int memeID) {
        this.memeID = memeID;
    }
}
