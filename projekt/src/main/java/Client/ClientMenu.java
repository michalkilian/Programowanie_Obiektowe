package Client;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


/**
 * Controller class for main menu scene
 *
 * @author Michal Kilian
 */
public class ClientMenu implements Initializable {

    /**
     * Active user and server connector
     */
    ActiveSession user;

    //FXML properties
    @FXML
    private Button createButton;

    @FXML
    private Button browseButton;

    @FXML
    private Label pseudoLabel;


    /**
     * Function called when scene is switched
     *
     * <p>
     *     If user is not initialized (after starting application) function initializes it.
     * </p>
     *
     * @param location
     * @param resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (!ActiveSession.connected) {
            this.user = new ActiveSession();
            ActiveSession.connected = true;
        }
    }

    /**
     * Function called when changing scene to save information about session between different controllers
     *
     * @param user active user
     */
    public void initUser(ActiveSession user) {
        this.user = user;
        this.pseudoLabel.setText(user.getAutisticPseudo());
    }

    /**
     * Switching scene to create meme scene
     *
     * <p>
     *     This function is called when "create" button is pressed.
     * </p>
     *
     * @param event
     * @throws IOException
     */
    @FXML
    public void changeSceneToCreateMeme(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ClientCreateMeme.fxml"));

        Parent createMemeParent = loader.load();
        Scene createMemeScene = new Scene(createMemeParent);

        Stage window = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        window.setScene(createMemeScene);

        ClientCreateMeme controller = loader.<ClientCreateMeme>getController();
        controller.initUser(user);
        window.show();
    }

    /**
     * Switching scene to browse memes scene
     *
     * <p>
     *     This function is called when "browse" button is pressed.
     * </p>
     *
     * @param event
     * @throws IOException
     */
    public void changeSceneToBrowseMemes(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ClientBrowseMemes.fxml"));

        Parent createMemeParent = loader.load();
        Scene createMemeScene = new Scene(createMemeParent);

        Stage window = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        window.setScene(createMemeScene);

        ClientBrowseMemes controller = loader.<ClientBrowseMemes>getController();
        controller.initUser(user);
        window.show();
    }


    /**
     * Switching scene to user profile scene
     *
     * <p>
     *     This function is called when "profile" button is pressed.
     * </p>
     *
     * @param event
     * @throws IOException
     */
    public void changeSceneToProfile(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ClientProfile.fxml"));

        Parent createMemeParent = loader.load();
        Scene createMemeScene = new Scene(createMemeParent);

        Stage window = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        window.setScene(createMemeScene);

        ClientProfile controller = loader.<ClientProfile>getController();
        controller.initUser(user);
        controller.initStats(user);
        window.show();
    }
}
