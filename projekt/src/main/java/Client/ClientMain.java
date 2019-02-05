package Client;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * Main class of client side of application
 *
 */
public class ClientMain extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Creates application window
     *
     * @param primaryStage
     * @throws Exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/ClientMenu.fxml"));
        primaryStage.setTitle("MEME Generator");
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.getIcons().add(new Image(String.valueOf(getClass().getResource("/pepe_icon.jpg"))));
        primaryStage.show();
    }
}
