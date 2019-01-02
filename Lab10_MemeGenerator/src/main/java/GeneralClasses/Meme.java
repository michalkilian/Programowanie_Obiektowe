package GeneralClasses;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;

import javax.imageio.ImageIO;
import java.io.*;

public class Meme implements Serializable{

    private String upperText;
    private String bottomText;
    private String tag;
    private String author;
    private String title;
    private transient Image image;


    public Meme(String upperText, String bottomText, String tag, String author, String title, Image image) {
        this.upperText = upperText;
        this.bottomText = bottomText;
        this.tag = tag;
        this.author = author;
        this.title = title;
        this.image = image;
    }
    public Meme(String tag, String author, String title, Image image){ //If meme already has upper and bottom text
        this.tag = tag;
        this.author = author;
        this.title = title;
        this.image = image;
    }

    private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
        s.defaultReadObject();
        image = SwingFXUtils.toFXImage(ImageIO.read(s), null);
    }

    private void writeObject(ObjectOutputStream s) throws IOException {
        s.defaultWriteObject();
        ImageIO.write(SwingFXUtils.fromFXImage(image, null), "jpg", s);
    }

    public String getUpperText() {
        return upperText;
    }

    public void setUpperText(String upperText) {
        this.upperText = upperText;
    }

    public String getBottomText() {
        return bottomText;
    }

    public void setBottomText(String bottomText) {
        this.bottomText = bottomText;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }
}
