package Client;

import Client.Exceptions.EmptyFieldException;
import Client.Exceptions.PasswordsNotMatchingException;
import GeneralClasses.MessageToServer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

public class ClientSignInUp {

    ActiveSession user;

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


    public void initUser(ActiveSession user) {
        this.user = user;
    }

    @FXML
    public void goBackToCreate(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ClientCreateMeme.fxml"));

        Parent createMemeParent = loader.load();
        Scene createMemeScene = new Scene(createMemeParent);

        Stage window = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        window.setScene(createMemeScene);

        ClientCreateMeme controller = loader.<ClientCreateMeme>getController();
        controller.initUser(user);
        window.show();

    }

    @FXML
    public void signIn(ActionEvent event) {
        try {

            String username = signInUsername.getText();
            String password = signInPassword.getText();
            if (username.equals("") || password.equals("")) {
                throw new EmptyFieldException();
            }

            MessageToServer messageToServer = new MessageToServer("signin");
            messageToServer.setUsername(username);
            messageToServer.setPassword(password);
            user.sendMessageToServer(messageToServer);
            createResponseAlert(user.getResponseHead(), user.getResponseBody());

        } catch (EmptyFieldException e) {
            createResponseAlert("Can't Sign In", "Please insert login and password");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        clearFields();

    }


    @FXML
    public void signUp(ActionEvent event) {
        try {

            String username = signUpUsername.getText();
            String password = signUpPassword.getText();
            String passwordConfirmation = signUpPasswordConfirm.getText();
            String autisticPseudo = signUpAuthorPseudonim.getText();

            if (username.equals("") || password.equals("") || passwordConfirmation.equals("") || autisticPseudo.equals("")) {
                throw new EmptyFieldException();
            }
            if (!password.equals(passwordConfirmation)) {
                throw new PasswordsNotMatchingException();
            }

            MessageToServer messageToServer = new MessageToServer("signup");
            messageToServer.setUsername(username);
            messageToServer.setPassword(password);
            messageToServer.setAutisticPseudo(autisticPseudo);
            user.sendMessageToServer(messageToServer);
            createResponseAlert(user.getResponseHead(), user.getResponseBody());


        } catch (PasswordsNotMatchingException e) {
            createResponseAlert("Can't Sign Up", "Passwords Not Matching");

        } catch (EmptyFieldException e) {
            createResponseAlert("Can't Sign Up", "Please fill all fields");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        clearFields();

    }

    private void createResponseAlert(String responseHead, String responseBody) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(responseHead);
        alert.setHeaderText(responseBody);
        alert.showAndWait().ifPresent(rs -> {
        });
    }

    private void clearFields() {
        signUpUsername.setText("");
        signUpAuthorPseudonim.setText("");
        signUpPassword.setText("");
        signUpPasswordConfirm.setText("");
        signInUsername.setText("");
        signInPassword.setText("");
    }


}