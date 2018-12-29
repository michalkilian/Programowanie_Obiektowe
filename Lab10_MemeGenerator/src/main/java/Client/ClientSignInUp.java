package Client;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ClientSignInUp {


    @FXML
    public void goBackToMenu(ActionEvent event) throws IOException {
        Parent createMemeParent = FXMLLoader.load(getClass().getResource("/ClientCreateMeme.fxml"));
        Scene createMemeScene = new Scene(createMemeParent);

        Stage window = (Stage)((javafx.scene.Node)event.getSource()).getScene().getWindow();
        window.setScene(createMemeScene);
        window.show();
    }

    @FXML
    public void signIn(ActionEvent event) {
    }

    @FXML
    public void signUp(ActionEvent event){

    }


}
