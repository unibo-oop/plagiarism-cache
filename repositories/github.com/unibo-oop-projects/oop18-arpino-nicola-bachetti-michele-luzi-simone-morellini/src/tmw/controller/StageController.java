package tmw.controller;

import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Interface that handle the stage.
 *
 */
public interface StageController {

    /**
     * @return the width of the stage.
     */
    double getWidth();

    /**
     * @return the height of the stage.
     */
    double getHeight();

    /**
     * @return the current scene.
     */
    Scene getScene();

    /**
     * Getter for the stage.
     * 
     * @return stage
     */
    Stage getStage();

    /**
     * Setter for the dimension.
     * 
     * @param width  stage width
     * @param height stage height
     */
    void setDimention(double width, double height);

    /**
     * set the scene received as a parameter.
     * 
     * @param scene scene
     */
    void setScene(Scene scene);

}
