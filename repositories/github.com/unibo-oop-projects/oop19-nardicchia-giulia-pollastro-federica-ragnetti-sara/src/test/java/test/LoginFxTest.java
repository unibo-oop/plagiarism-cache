package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.io.IOException;

import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import view.SceneType;

/**
 * Test for the correct initialization of Login Scene.
 *
 */
public class LoginFxTest extends ApplicationTest {

    private static final String TITLE = "Brain Trainer";
    private Parent root; 

    /**
     * Load the scene using the file path.
     */
    @Override
    public void start(final Stage stage) {
        final FXMLLoader loader = new FXMLLoader(getClass().getResource(SceneType.LOGIN.getFilePath()));
        try {
            this.root = loader.load(this.getClass().getResourceAsStream(SceneType.LOGIN.getFilePath()));
            stage.setScene(new Scene(this.root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testGrid() {
        final GridPane grid = from(this.root).lookup("#grid").query();
        final ImageView logo = from(this.root).lookup("#logo").query();
        final Label title = (Label) grid.getChildren().get(0);

        assertNotEquals(logo.getFitWidth(), 0);
        assertNotEquals(logo.getFitHeight(), 0);
        assertEquals(title.getText(), TITLE);
    }
}
