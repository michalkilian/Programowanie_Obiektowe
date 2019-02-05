package Client;

import javafx.scene.image.ImageView;
import javafx.scene.image.Image;

/**
 * Custom ImageView class capable of storing path to displayed image
 *
 * @author Michal Kilian
 */
public class MyImageView extends ImageView {

    private String imagePath;

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public MyImageView(Image image) {
        setImage(image);

    }
}
