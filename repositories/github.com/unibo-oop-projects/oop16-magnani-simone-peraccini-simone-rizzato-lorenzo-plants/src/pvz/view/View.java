package pvz.view;

import java.util.List;

import javafx.application.Application;
import pvz.controller.ControllerInterface;
import pvz.model.GameStatus;
import pvz.model.entity.Entity;
import pvz.view.input.InputInterface;

/**
 * The class View! It starts the entirely screen.
 *
 */
public class View implements ViewInterface {

    private static ControllerInterface controller;
    // private static GameScreen gameScreen;

    /**
     * Public constructor! Set the controller.
     * 
     * @param controller
     *            controller to be set.
     */
    public View(final ControllerInterface controller) {
        this.setController(controller);
    }

    /**
     * Thread safe setter for the controller.
     * 
     * @param controller
     *            controller to be set.
     */
    private synchronized void setController(final ControllerInterface controller) {
        View.controller = controller;
    }

    @Override
    public void init() {
        //Application.launch(MainWindow.class);
    }

    @Override
    public void render(final List<Entity> listEntities) {

    }

    @Override
    public void notifyLevelEnd(final GameStatus state) {

    }

    @Override
    public List<InputInterface> getInput() {
        return null;
    }

    /**
     * Static method to get the controller from all scenes.
     * 
     * @return the controller.
     */
    static ControllerInterface getController() {
        return View.controller;
    }

}
