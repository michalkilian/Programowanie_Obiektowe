package GeneralClasses;

import javafx.scene.image.Image;

import java.io.InputStream;
import java.io.Serializable;

public class Meme {

    private String upperText;
    private String bottomText;
    private String tag;
    private String author;
    private String title;
    private InputStream image;


    public Meme(String upperText, String bottomText, String tag, String author, String title, InputStream image) {
        this.upperText = upperText;
        this.bottomText = bottomText;
        this.tag = tag;
        this.author = author;
        this.title = title;
        this.image = image;
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

    public InputStream getImage() {
        return image;
    }

    public void setImage(InputStream image) {
        this.image = image;
    }
}
