package clashclass.view.graphic;

import clashclass.commons.GameConstants;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * JavaFX implementation of a menu scene.
 * Responsible for initializing and displaying the main menu on the provided Stage.
 */
public class MenuJFX extends AbstractBaseScene {
    private final Stage stage;

    /**
     * Constructs the menu.
     *
     * @param stage the stage
     * @param window the window
     */
    MenuJFX(final Stage stage, final Window window) {
        super(window);
        this.stage = stage;
    }

    /**
     * Method that initialize the BaseScene.
     */
    @Override
    public void initializeScene() {
        final Pane root = new Pane();
        final Scene scene = new Scene(root, GameConstants.SCREEN_WIDTH, GameConstants.SCREEN_HEIGHT);

        this.stage.setTitle("clashclass");
        this.stage.setScene(scene);
        this.stage.show();
    }
}
