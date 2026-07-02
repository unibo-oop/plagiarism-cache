package it.tbt.view.javafx;

import it.tbt.controller.viewcontrollermanager.api.ViewController;
import it.tbt.view.api.GameView;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

/**
 * Abstract JavaFx View for defining common operations common to all GameViews
 * made in JavaFx.
 */

public abstract class AbstractJavaFxView implements GameView {
    private final Scene scene;

    /**
     * @param viewController the viewController used to map this view input to the handle input in the controller.
     * @param stage the stage onto which this view's scene is shown upon.
     * @param scene the scene onto which the view's render is drawn upon.
     */
    protected AbstractJavaFxView(final ViewController viewController, final Stage stage, final Scene scene) {
        this.scene = scene;
        scene.setCursor(Cursor.NONE);
        stage.setResizable(false);
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(final KeyEvent event) {
                viewController.onKeyPressed(event.getCode().getCode());
            }
        });
    }

    /**
     * @return the scene on to which this view is rendered.
     */
    protected Scene getScene() {
        return this.scene;
    }
}
