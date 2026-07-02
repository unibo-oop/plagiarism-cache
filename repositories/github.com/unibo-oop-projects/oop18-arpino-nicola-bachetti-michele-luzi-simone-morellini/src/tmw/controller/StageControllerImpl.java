package tmw.controller;

import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Class that handle the stage.
 *
 */
public class StageControllerImpl implements StageController {

    private final Stage stage;

    /**
     * Public constructor.
     * 
     * @param stage stage
     */
    public StageControllerImpl(final Stage stage) {
        this.stage = stage;
        this.stage.setResizable(false);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getWidth() {
        return this.stage.getWidth();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getHeight() {
        return this.stage.getHeight();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Scene getScene() {
        return this.stage.getScene();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Stage getStage() {
        return this.stage;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setDimention(final double width, final double height) {
        this.stage.setWidth(width);
        this.stage.setHeight(height);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setScene(final Scene scene) {
        this.stage.setScene(scene);
        this.stage.centerOnScreen();
    }

}
