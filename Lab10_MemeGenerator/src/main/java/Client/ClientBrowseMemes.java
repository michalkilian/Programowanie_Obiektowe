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

public class ClientBrowseMemes implements Initializable {


    public ImageView memeImage;
    ActiveSession user;

    private ObservableList<Meme> memes = FXCollections.observableArrayList();
    private ChangeListener<Meme> picked = new ChangeListener<Meme>() {
        @Override
        public void changed(ObservableValue<? extends Meme> observable, Meme oldValue, Meme newValue) {
            author.setText(newValue.getAuthor());
            tag.setText(newValue.getTag());
            memeImage.setImage(newValue.getImage());
        }
    };


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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        valueList.setItems(memes);
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

    public void initUser(ActiveSession user) {
        this.user = user;
    }

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

    @FXML
    public void searchByMeme(ActionEvent event) {
        MessageToServer messageToServer = new MessageToServer("searchtitle");
        messageToServer.setFilter(memeNameTextField.getText());
        getMemes(messageToServer);
        if(user.getResponseHead()!= null){
            createResponseAlert(user.getResponseHead(), user.getResponseBody());
        }
    }

    @FXML
    public void searchByAuthor(ActionEvent event) {
        MessageToServer messageToServer = new MessageToServer("searchauthor");
        messageToServer.setFilter(authorTextField.getText());
        getMemes(messageToServer);
        if(user.getResponseHead()!= null){
            createResponseAlert(user.getResponseHead(), user.getResponseBody());
        }
    }



    @FXML
    public void searchByTag(ActionEvent event) {
        MessageToServer messageToServer = new MessageToServer("searchtag");
        messageToServer.setFilter(tagTextField.getText());
        getMemes(messageToServer);
        if(user.getResponseHead()!= null){
            createResponseAlert(user.getResponseHead(), user.getResponseBody());
        }
    }

    @FXML
    public void searchAll(ActionEvent event) {
        MessageToServer messageToServer = new MessageToServer("searchall");
        getMemes(messageToServer);
        if(user.getResponseHead()!= null){
            createResponseAlert(user.getResponseHead(), user.getResponseBody());
        }
    }

    private void getMemes(MessageToServer messageToServer) {
        try {
            user.sendMessageToServer(messageToServer);
            memes.clear();
            memes.addAll(user.getMemeList());
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void createResponseAlert(String responseHead, String responseBody) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(responseHead);
        alert.setHeaderText(responseBody);
        alert.showAndWait().ifPresent(rs -> {
        });
    }
}
