package Client;

import GeneralClasses.MessageToServer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


/**
 * Controller class for user profile scene
 *
 * @author Michal Kilian
 */
public class ClientProfile implements Initializable {

    /**
     * Active user and server connector
     */
    ActiveSession user;

    //FXML properties
    @FXML
    public Label usernameLabel;

    @FXML
    public Label registeredDate;

    @FXML
    public Label createdMemes;

    @FXML
    public Label topMemeRating;

    @FXML
    public Label totalKarma;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    /**
     * Function called when changing scene to save information about session between different controllers
     *
     * @param user active user
     */
    public void initUser(ActiveSession user) {
        this.user = user;
    }

    /**
     * Send request to server, retrieve statistics about active user and initialize FXML properties
     *
     * @param user active user
     */
    public void initStats(ActiveSession user) {
        if (user.getUsername() != null) {
            try {
                MessageToServer messageToServer = new MessageToServer("getstats");
                messageToServer.setUsername(user.getUsername());
                user.sendMessageToServer(messageToServer);
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        usernameLabel.setText(user.getAutisticPseudo());
        createdMemes.setText(user.getNumberOfMemes());
        totalKarma.setText(user.getKarma());
        topMemeRating.setText(user.getTopMemeKarma());
        registeredDate.setText(user.getRegisterDate());
    }

    /**
     * Switching scene to main menu
     *
     * <p>
     *     This function is called when "back" button is pressed.
     * </p>
     *
     * @param event
     * @throws IOException
     */
    public void goBackToMenu(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ClientMenu.fxml"));

        Parent createMemeParent = loader.load();
        Scene createMemeScene = new Scene(createMemeParent);

        Stage window = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        window.setScene(createMemeScene);

        ClientMenu controller = loader.<ClientMenu>getController();
        controller.initUser(user);
        window.show();
    }



}