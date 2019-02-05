package Client;

import GeneralClasses.Meme;
import GeneralClasses.MessageToServer;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


/**
 * Controller class for browsing memes scene
 *
 * @author Michal Kilian
 */
public class ClientBrowseMemes implements Initializable {

    /**
     * Active user and server connector
     */
    ActiveSession user;

    /**
     * Currently selected and displayed in ImageView meme
     */
    Meme activeMeme;

    @FXML
    public ImageView memeImage;

    @FXML
    public Label memeRating;


    /**
     * Collection of memes retrieved from server
     */
    private ObservableList<Meme> memes = FXCollections.observableArrayList();

    /**
     * Changing selected meme listener
     *
     * <p>
     *     When selected meme is changed it sets properties values.
     * </p>
     */
    private ChangeListener<Meme> picked = new ChangeListener<Meme>() {
        @Override
        public void changed(ObservableValue<? extends Meme> observable, Meme oldValue, Meme newValue) {
            author.setText(newValue.getAuthor());
            tag.setText(newValue.getTag());
            memeImage.setImage(newValue.getImage());
            memeRating.setText(newValue.getRating());
            activeMeme = newValue;
        }
    };

    //FXML properties
    @FXML
    public ListView<Meme> valueList;

    @FXML
    private Button backButton;

    @FXML
    private TextField memeNameTextField;

    @FXML
    private TextField tagTextField;

    @FXML
    private TextField authorTextField;

    @FXML
    private TextField author;

    @FXML
    private TextField tag;

    /**
     * Function called when scene is switched
     *
     * <p>
     *     If user is defined it initialized memes already retrieved from server.
     * </p>
     *
     * @param location
     * @param resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        valueList.setItems(memes);
        if (user != null) {
            memes.addAll(user.getMemeList());
        }
        valueList.setCellFactory(param -> new ListCell<Meme>() {
            @Override
            protected void updateItem(Meme item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(item.getTitle());
                }
            }
        });
        valueList.getSelectionModel().selectedItemProperty().addListener(picked);
    }

    /**
     * Function called when changing scene to save information about session between different controllers
     *
     * @param user active user
     */
    public void initUser(ActiveSession user) {
        this.user = user;
        memes.addAll(user.getMemeList());
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
    @FXML
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

    /**
     * Prepare request to server and retrieve memes with given title
     *
     * <p>
     *     Function gathers information from {@link #memeNameTextField}, prepares message by setting command and
     *     filter properties then send it using {@link #getMemes(MessageToServer)}
     * </p>
     *
     * @param event
     */
    @FXML
    public void searchByMeme(ActionEvent event) {
        MessageToServer messageToServer = new MessageToServer("searchtitle");
        messageToServer.setFilter(memeNameTextField.getText());
        getMemes(messageToServer);
        if (user.getResponseHead() != null) {
            createResponseAlert(user.getResponseHead(), user.getResponseBody());
        }
    }

    /**
     * Prepare request to server and retrieve memes created by given author
     *
     * <p>
     *     Function gathers information about desired author from {@link #authorTextField}, prepares message by
     *     setting command and filter properties then send it using {@link #getMemes(MessageToServer)}
     * </p>
     *
     * @param event
     */
    @FXML
    public void searchByAuthor(ActionEvent event) {
        MessageToServer messageToServer = new MessageToServer("searchauthor");
        messageToServer.setFilter(authorTextField.getText());
        getMemes(messageToServer);
        if (user.getResponseHead() != null) {
            createResponseAlert(user.getResponseHead(), user.getResponseBody());
        }
    }

    /**
     * Prepare request to server and retrieve memes with given tag
     *
     * <p>
     *     Function gathers information about desired tag from {@link #tagTextField}, prepares message by
     *     setting command and filter properties then send it using {@link #getMemes(MessageToServer)}
     * </p>
     *
     * @param event
     */
    @FXML
    public void searchByTag(ActionEvent event) {
        MessageToServer messageToServer = new MessageToServer("searchtag");
        messageToServer.setFilter(tagTextField.getText());
        getMemes(messageToServer);
        if (user.getResponseHead() != null) {
            createResponseAlert(user.getResponseHead(), user.getResponseBody());
        }
    }

    /**
     * Prepare request to server and retrieve all memes from database
     *
     * <p>
     *     Function prepares message by setting command then send it using {@link #getMemes(MessageToServer)}
     * </p>
     *
     * @param event
     */
    @FXML
    public void searchAll(ActionEvent event) {
        MessageToServer messageToServer = new MessageToServer("searchall");
        getMemes(messageToServer);
        if (user.getResponseHead() != null) {
            createResponseAlert(user.getResponseHead(), user.getResponseBody());
        }
    }

    /**
     * Send request with optional filters to server and add memes retrieved in response to library     *
     *
     * @param messageToServer message that is going to be sent to server
     */
    private void getMemes(MessageToServer messageToServer) {
        try {
            user.sendMessageToServer(messageToServer);
            memes.clear();
            memes.addAll(user.getMemeList());
        } catch (IOException | ClassNotFoundException | NullPointerException e) {
            createResponseAlert("Error during loading memes", "Contact admins or try again later");
        }
    }


    /**
     * Create pop-up alert
     *
     * @param responseHead text displayed on alert's top bar
     * @param responseBody text displayed on alert's body
     */
    private void createResponseAlert(String responseHead, String responseBody) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(responseHead);
        alert.setHeaderText(responseBody);
        alert.showAndWait().ifPresent(rs -> {
        });
    }

    /**
     * Prepare and send request to server for upvote selected meme
     *
     * <p>
     *     Message to server consists of username used to retrieve userID from DB and memeID. If success meme rating
     *     will be increased by 1. Guest user can't rate meme. User can't rate same meme twice.
     * </p>
     *
     * @param event
     */
    public void rateMeme(ActionEvent event) {
        if (user.getUsername() != null) {
            try {
                MessageToServer messageToServer = new MessageToServer("ratememe");
                messageToServer.setUsername(user.getUsername());
                messageToServer.setMemeId(activeMeme.getMemeID());
                user.sendMessageToServer(messageToServer);
                System.out.println(user.getResponseHead());
                if (user.getResponseHead().equals("ratememesuccess")) {
                    activeMeme.setRating(String.valueOf(Integer.parseInt(activeMeme.getRating()) + 1));
                    memeRating.setText(activeMeme.getRating());
                } else if (user.getResponseHead().equals("ratememeerror")) {
                    createResponseAlert("You can't rate meme twice", "");
                } else {
                    createResponseAlert("Error during rating meme", "Contact admins or try again later");
                }
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            createResponseAlert("You must be logged in to do this", "");
        }
    }
}
