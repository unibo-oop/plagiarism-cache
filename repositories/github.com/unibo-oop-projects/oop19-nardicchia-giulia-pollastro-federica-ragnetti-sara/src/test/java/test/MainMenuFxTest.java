package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import view.SceneType;

/**
 * Test for the correct initialization of MainMenu Scene components.
 *
 */
public class MainMenuFxTest extends ApplicationTest {

    private static final int COMPONENT_COUNT = 6;
    private Parent root; 

    /**
     * Load the scene using the file path.
     */
    @Override
    public void start(final Stage stage) {
        final FXMLLoader loader = new FXMLLoader(getClass().getResource(SceneType.MAIN_MENU.getFilePath()));
        try {
            this.root = loader.load(this.getClass().getResourceAsStream(SceneType.MAIN_MENU.getFilePath()));
            stage.setScene(new Scene(this.root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testGrid() {
        final GridPane grid = from(this.root).lookup("#menuGrid").query();
        final Set<Node> nodes = new HashSet<>();
        for (final Node b : grid.getChildren()) {
            nodes.add(b);
            if (b instanceof Button) {
                assertFalse(((Button) b).getText().isEmpty());
            } else if (b instanceof ImageView) {
               assertEquals(((ImageView) b).getFitHeight(), 0);
               assertEquals(((ImageView) b).getFitWidth(), 0);
            }
        }
        assertEquals(grid.getChildren().size(), COMPONENT_COUNT);
    }
}
