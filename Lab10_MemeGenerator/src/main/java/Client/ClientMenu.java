package Client;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ClientMenu implements Initializable {

    ActiveSession user;

    @FXML
    private Button createButton;

    @FXML
    private Button browseButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (!ActiveSession.connected) {
            this.user = new ActiveSession();
            ActiveSession.connected = true;
        }

        //TODO: CONNECTING TO SERWER AND GENERATING ACTIVE USER IF USER NOT SET;
    }

    public void initUser(ActiveSession user) {
        this.user = user;
    }

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


}
