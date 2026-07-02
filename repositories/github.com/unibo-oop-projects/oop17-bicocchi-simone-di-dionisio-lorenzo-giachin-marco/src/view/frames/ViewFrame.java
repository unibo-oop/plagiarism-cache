package view.frames;

import controller.ViewManager;
import javafx.scene.Parent;
import javafx.stage.Stage;
import view.ViewImpl;

/**
 * Standard frame of the view.
 */
public interface ViewFrame {

    /**
     * Starts the view.
     * 
     * @param primaryStage
     *            is the stage of the ViewFrame
     * @throws Exception 
     */
    void start(Stage primaryStage) throws Exception;

    /**
     * Sets layout property.
     */
    void setFrame();

    /**
     * Sets the stage of the ViewFrame.
     * 
     * @param newStage
     *            is the stage of the VireFrame
     */
    void setStage(Stage newStage);

    /**
     * @return Stage
     */
    Stage getStage();

    /**
     * Sets the scene for the stage.
     * 
     * @param pane
     *            is the Node to create the Scene
     * @param width
     *            is the width of the Scene
     * @param height
     *            is the height of the Scene
     */
    void setScene(Parent pane, double width, double height);

    /**
     * Operation to close the stage and clear all its children.
     */
    void clearStage();

    /**
     * Operation to be performed when closing the application from a specific stage.
     */
    void setExitOperation();

    /**
     * Setter for the viewManager.
     * 
     * @param newManager
     *            is the manager of the View
     */
    void setView(ViewImpl newManager);

    /**
     * Setter for the controller.
     * 
     * @param newController
     *            is the controller of the View
     */
    void setController(ViewManager newController);
}
