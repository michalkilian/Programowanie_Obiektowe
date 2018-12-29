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

public class ClientBrowseMemes implements Initializable {


    @FXML
    private Button backButton;

    @FXML
    public void goBackToMenu(ActionEvent event) throws IOException {
        Parent createMemeParent = FXMLLoader.load(getClass().getResource("/ClientMenu.fxml"));
        Scene createMemeScene = new Scene(createMemeParent);

        Stage window = (Stage)((javafx.scene.Node)event.getSource()).getScene().getWindow();
        window.setScene(createMemeScene);
        window.show();
    }

    @FXML
    public void searchByMeme(ActionEvent event) {
    }

    @FXML
    public void searchByAuthor(ActionEvent event) {
    }

    @FXML
    public void searchByTag(ActionEvent event) {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }


}
