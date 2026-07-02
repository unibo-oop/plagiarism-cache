package view;

import javafx.stage.Stage;
import view.scene.GenericScene;

/**
 * Class that manage different types of view.
 * 
 */
public interface ViewManager {

    /**
     * Push generic scene in scene stack.
     * @param scene Scene to push.
     */
    void push(GenericScene scene);

    /**
     * Pop last added-element in scene stack. 
     */
    void pop();

    /**
     * Set stage height.
     * @param h Height.
     */
    void setHeight(double h);

    /**
     * Set stage width.
     * @param w Width.
     */
    void setWidth(double w);

    /**
     * Active scene.
     * @return Get current scene.
     */
    GenericScene getCurrentScene();

    /**
     * Get height of stage.
     * @return Height of the stage.
     */
    double getStageHeight();

    /**
     * Get width of the stage.
     * @return Width of the stage.
     */
    double getStageWidth();

    /**
     * Set stage.
     * @param primaryStage stage.
     */
    void setMainStage(Stage primaryStage);

    /**
     * Get started height.
     * @return initial height.
     */
    double getStartedHeight();

    /**
     * Get started width.
     * @return initial width.
     */
    double getStartedWidth();

    /**
     * Get main stage.
     * @return main stage.
     */
    Stage getStage();
}
