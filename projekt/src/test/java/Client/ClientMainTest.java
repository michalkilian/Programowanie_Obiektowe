package Client;

import com.sun.javafx.robot.FXRobot;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.testfx.api.FxRobotException;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit.ApplicationTest;

import java.io.IOException;

import static org.junit.Assert.*;

public class ClientMainTest extends ApplicationTest {

    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/ClientMenu.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        stage.toFront();

    }

    @Before
    public void setUp() throws Exception {


    }

    @After
    public void tearDown() throws Exception {
        FxToolkit.hideStage();
        release(new KeyCode[]{});
        release(new MouseButton[]{});
    }

    @Test
    public void testChangingSceneToCreateMeme(){
        clickOn("#createButton");
        assert(targetWindow().getScene()!=null);
    }

    @Test
    public void testChangingSceneToBrowseMemes(){
        clickOn("#browseButton");
        assert(targetWindow().getScene()!=null);
    }

    @Test(expected = FxRobotException.class)
    public void testClickingOnUnexistingButton(){
        clickOn("#Button");

    }
}