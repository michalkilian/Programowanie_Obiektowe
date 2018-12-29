package Client;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type.Node;

public class ClientMenu {

    @FXML
    private Button createButton;

    @FXML
    private Button browseButton;

    @FXML
    public void changeSceneToCreateMeme(ActionEvent event) throws IOException {
        Parent createMemeParent = FXMLLoader.load(getClass().getResource("/ClientCreateMeme.fxml"));
        Scene createMemeScene = new Scene(createMemeParent);

        Stage window = (Stage)((javafx.scene.Node)event.getSource()).getScene().getWindow();
        window.setScene(createMemeScene);
        window.show();
   }

    public void changeSceneToBrowseMemes(ActionEvent event) throws IOException {
        Parent createMemeParent = FXMLLoader.load(getClass().getResource("/ClientBrowseMemes.fxml"));
        Scene createMemeScene = new Scene(createMemeParent);

        Stage window = (Stage)((javafx.scene.Node)event.getSource()).getScene().getWindow();
        window.setScene(createMemeScene);
        window.show();
    }
}
