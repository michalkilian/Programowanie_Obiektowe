package Client;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ClientCreateMeme implements Initializable {

    ActiveUser user;

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

    @FXML
    public void goBackToMenu(ActionEvent event) throws IOException {
        Parent createMemeParent = FXMLLoader.load(getClass().getResource("/ClientMenu.fxml"));
        Scene createMemeScene = new Scene(createMemeParent);

        Stage window = (Stage)((javafx.scene.Node)event.getSource()).getScene().getWindow();
        window.setScene(createMemeScene);
        window.show();
    }

    @FXML
    public void goToSignInUp(ActionEvent event) throws IOException {
        Parent createMemeParent = FXMLLoader.load(getClass().getResource("/ClientSignInUp.fxml"));
        Scene createMemeScene = new Scene(createMemeParent);

        Stage window = (Stage)((javafx.scene.Node)event.getSource()).getScene().getWindow();
        window.setScene(createMemeScene);
        window.show();
    }

    @FXML
    public void pickOwnPicture(ActionEvent event){
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Image Files", "*.jpg", "*.png"));
        File selectedFile = fc.showOpenDialog(null);

        if(selectedFile != null){

            Image img = new Image(selectedFile.toURI().toString());

            ImageView mainImageView = new ImageView(img);
            mainImageView.setPreserveRatio(true);
            mainImageView.setFitWidth(imageStackPane.getWidth());
            mainImageView.setFitHeight(imageStackPane.getHeight());
            imageStackPane.getChildren().clear();
            imageStackPane.getChildren().add(mainImageView);




        }
    }


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
            imageView.setFitHeight(200);
            imageView.setFitWidth(190);
            imageView.setPreserveRatio(true);
            tilePane.getChildren().addAll(imageView);
            addEventToImageView(imageView);

        }

        imageSlider.setContent(tilePane);

    }

    public void addEventToImageView(ImageView image){
        image.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

                MyImageView imageView = (MyImageView) event.getSource();

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

    public void initUser(ActiveUser user) {
        this.user = user;
    }
}
