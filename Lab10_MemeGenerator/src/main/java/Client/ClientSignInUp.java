package Client;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class ClientSignInUp {

    @FXML
    private Button signInButton;

    @FXML
    private TextField signUpUsername;

    @FXML
    private TextField signUpAuthorPseudonim;

    @FXML
    private PasswordField signUpPassword;

    @FXML
    private PasswordField signUpPasswordConfirm;

    @FXML
    private Button signUpButton;

    @FXML
    private Button backButton;

    @FXML
    private TextField signInUsername;

    @FXML
    private PasswordField signInPassword;

    @FXML
    public void goBackToCreate(ActionEvent event) throws IOException {
        Parent createMemeParent = FXMLLoader.load(getClass().getResource("/ClientCreateMeme.fxml"));
        Scene createMemeScene = new Scene(createMemeParent);

        Stage window = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        window.setScene(createMemeScene);
        window.show();
    }

    @FXML
    public void signIn(ActionEvent event) {

    }

    @FXML
    public void signUp(ActionEvent event) {

    }


}
