package controller;

import controller.input.ProcessInput;
import controller.levels.Level;
import controller.objects.ControllerObjects;
import controller.output.ControllerOutput;
import model.Model;
import view.View;

/**
 * Controller's interface.
 */
public interface Controller {

    /**
     * Getter of the {@link ControllerOutput}.
     * 
     * @return the {@link ControllerOutput}.
     */
    ControllerOutput getControllerOutput();

    /**
     * Getter of the {@link ProcessInput}.
     * 
     * @return the {@link ProcessInput}.
     */
    ProcessInput getControllerInput();

    /**
     * Initialize the {@link Controller} fields.
     * 
     * @param world
     *            the game {@link World}.
     * @param view
     *            the game {@link View}.
     */
    void initializeController(Model world, View view);

    /**
     * Getter of the {@link Tank} managed in the controller.
     * 
     * @return the {@link ControllerTank}.
     */
    ControllerObjects getControllerObjects();

    /**
     * Getter of the levels.
     * 
     * @return the levels.
     */
    Level getLevel();

    /**
     * Method that allows to start the {@link GameLoopImpl}.
     */
    void startGameLoop();

    /**
     * Getter of the {@link GameLoop}.
     * 
     * @return the game loop.
     */
    GameLoop getGameLoop();

    /**
     * Method that initialize the {@link ControllerObjects} and the {@link AI}.
     */
    void initializeObjects();

    /**
     * Setter of the period of shot of the enemy {@link Tank}.
     * 
     * @param time
     *            the time in ms between two enemy shots.
     */
    void setTimeToShot(double time);

}
