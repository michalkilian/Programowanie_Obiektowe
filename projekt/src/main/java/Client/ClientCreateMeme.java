package Client;

import Client.Exceptions.EmptyFieldException;
import GeneralClasses.Meme;
import GeneralClasses.MessageToServer;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;


/**
 * Controller class for creating memes scene
 *
 * @author Michal Kilian
 */
public class ClientCreateMeme implements Initializable {

    /**
     * Active user and server connector
     */
    ActiveSession user;
    String pathToFile;

    //FXML properties
    @FXML
    private TextField upperTextField;

    @FXML
    private TextField bottomTextField;

    @FXML
    private TextField titleTextField;

    @FXML
    private TextField tagTextField;

    @FXML
    private StackPane imageStackPane;

    @FXML
    private ScrollPane imageSlider;

    @FXML
    private Button backButton;

    @FXML
    private Button signInUpButton;

    @FXML
    private Button ownPictureButton;

    /**
     * Function called when scene is switched
     *
     * <p>
     *     Function sets content of gallery using sample memes stored in resource folder.
     * </p>
     *
     * @param location
     * @param resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        ClassLoader classLoader = getClass().getClassLoader();

        File folder = new File(classLoader.getResource("sampleMemes").getFile());
        File[] listOfFiles = folder.listFiles();
        TilePane tilePane = new TilePane();
        tilePane.setHgap(5);
        tilePane.setVgap(5);
        tilePane.setMaxWidth(200);

        for (int i = 0; i < listOfFiles.length; ++i) {
            Image image = new Image(listOfFiles[i].toURI().toString());
            MyImageView imageView = new MyImageView(image);
            imageView.setImagePath(listOfFiles[i].toURI().toString());
            imageView.setId(listOfFiles[i].getAbsolutePath());
            imageView.setFitHeight(200);
            imageView.setFitWidth(190);
            imageView.setPreserveRatio(true);
            tilePane.getChildren().addAll(imageView);
            addEventToImageView(imageView);

        }

        imageSlider.setContent(tilePane);

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
//        Parent createMemeParent = FXMLLoader.load(getClass().getResource("/ClientMenu.fxml"));
//        Scene createMemeScene = new Scene(createMemeParent);
//
//        Stage window = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
//        window.setScene(createMemeScene);
//        window.show();

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
     * Switching scene to sign in/up
     *
     * <p>
     *     This function is called when "sign in/sign up" button is pressed.
     * </p>
     *
     * @param event
     * @throws IOException
     */
    @FXML
    public void goToSignInUp(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ClientSignInUp.fxml"));

        Parent createMemeParent = loader.load();
        Scene createMemeScene = new Scene(createMemeParent);

        Stage window = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        window.setScene(createMemeScene);

        ClientSignInUp controller = loader.<ClientSignInUp>getController();
        controller.initUser(user);
        window.show();
    }

    /**
     * Set meme image to picture from drive
     *
     * @param event
     */
    @FXML
    public void pickOwnPicture(ActionEvent event) {
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Image Files", "*.jpg", "*.png"));
        File selectedFile = fc.showOpenDialog(null);


        if (selectedFile != null) {
            pathToFile = selectedFile.getAbsolutePath();;
            Image img = new Image(selectedFile.toURI().toString());

            ImageView mainImageView = new ImageView(img);
            mainImageView.setPreserveRatio(true);
            mainImageView.setFitWidth(imageStackPane.getWidth());
            mainImageView.setFitHeight(imageStackPane.getHeight());
            imageStackPane.getChildren().clear();
            imageStackPane.getChildren().add(mainImageView);


        }
    }

    /**
     * Set meme image to picture from application gallery when picked
     *
     * @param image image picked by user from application gallery
     */
    public void addEventToImageView(ImageView image) {
        image.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

                MyImageView imageView = (MyImageView) event.getSource();
                pathToFile = imageView.getId();
                Image img = new Image(imageView.getImagePath());

                ImageView mainImageView = new ImageView(img);
                mainImageView.setPreserveRatio(true);
                mainImageView.setFitWidth(imageStackPane.getWidth());
                mainImageView.setFitHeight(imageStackPane.getHeight());
                imageStackPane.getChildren().clear();
                imageStackPane.getChildren().add(mainImageView);

            }
        });
    }


    /**
     * Gather information from fields and send request to server for creating meme
     *
     * <p>
     *     Function called when pressing "create MEME" button. Gathers information from FXML properties, create Meme
     *     object and send in to server using {@link #user} property. Meme can't be created if photo or title is empty.
     * </p>
     *
     * @param event
     */
    @FXML
    public void createMeme(ActionEvent event) {
        ObservableList<Node> stackPaneContent = imageStackPane.getChildren();

        try {
            ImageView imageView = (ImageView) stackPaneContent.get(0);
            Image image = imageView.getImage();
            String upperText = upperTextField.getText();
            String bottomText = bottomTextField.getText();
            String titleText = titleTextField.getText();
            if (titleText.equals("")) {
                throw new EmptyFieldException();
            }
            String tagText = tagTextField.getText();
            String author = user.getAutisticPseudo();

            Meme meme = new Meme(upperText, bottomText, tagText, author, titleText, image);

            MessageToServer messageToServer = new MessageToServer("create");
            messageToServer.setMeme(meme);
            if (user.getAutisticPseudo() != null) {
                messageToServer.setAutisticPseudo(user.getAutisticPseudo());
            }
            user.sendMessageToServer(messageToServer);
            createResponseAlert(user.getResponseHead(), user.getResponseBody());


        } catch (IndexOutOfBoundsException e) {
            createResponseAlert("Can't Create MEME", "Please pick a photo");

        } catch (EmptyFieldException e) {
            createResponseAlert("Can't Create MEME", "Please insert a title");
            return;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        clearFields();
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
     * Clear content of FXML properties
     */
    private void clearFields() {
        this.upperTextField.setText("");
        this.bottomTextField.setText("");
        this.titleTextField.setText("");
        this.tagTextField.setText("");
        this.imageStackPane.getChildren().clear();
    }
}
