package view.scene;

import java.io.IOException;

import controller.Controller;
import view.controller.ViewController;

/**
 * 
 * Implementation of the interface for setting the stage.
 *
 */
public interface SceneChanger {

    /**
     * This method allows to switch the stages.
     * @param width
     *          the scene width.
     * @param height
     *          the scene height.
     * @param controller
     *          the game {@link Controller}.
     * @throws IOException
     *          throw a new {@link IOException}.
     */
    void setStage(double width, double height, Controller controller) throws IOException;

    /**
     * Getter of the {@link ViewController}.
     * 
     * @return the {@link ViewController}.
     */
    ViewController getController();

}
