package fargoal.model.core;

import java.util.logging.Logger;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import fargoal.controller.input.api.KeyboardInputController;
import fargoal.model.manager.api.SceneManager;
import fargoal.model.manager.impl.TitleScreenManager;
import fargoal.view.api.View;
import fargoal.view.impl.SwingView;

/**
 * Core of the application, this class contains the update cycle of both model and view.
 * This class also contains the frame limitations of the application.
 */
public class GameEngine {

    private static final int PERIOD = 20;
    private SceneManager manager;
    private final View view;
    private final KeyboardInputController controller;
    private long elapsed;

    /**
     * Constructor that initializes al needed values.
     */
    public GameEngine() {
        this.controller = new KeyboardInputController();
        this.view = new SwingView(controller);
        this.manager = new TitleScreenManager(this);
        this.finishInitialization();
    }

    private void finishInitialization() {
        this.view.setUp();
    }

    /**
     * The main method of the engine, it has the update cycle.
     * Once this method starts it can only be stopped by the method {@link #stop()}
     */
    public void start() {
        long previousCycleStartTime = System.currentTimeMillis();
        while (true) {
            final long currentCycleStartTime = System.currentTimeMillis();
            elapsed = currentCycleStartTime - previousCycleStartTime;
            manager.update(this);
            view.update();
            waitToNextFrame(currentCycleStartTime);
            previousCycleStartTime = currentCycleStartTime;
        }
    }

    private void waitToNextFrame(final long startTime) {
        final long delta = System.currentTimeMillis() - startTime;
        if (delta < PERIOD) {
            try {
                Thread.sleep(PERIOD - delta);
            } catch (IllegalArgumentException e) { 
                Logger.getAnonymousLogger().warning("IllegalArgumentException" + e.getMessage());
            } catch (InterruptedException e) {
                Logger.getAnonymousLogger().warning("InterruptedException: " + e.getMessage());
            }
        }
    }

    /**
     * The method that stops the application.
     */
    @SuppressFBWarnings(
        value = {"DM_EXIT"},
        justification = "This method has the only purpose of shutting down the virtual machine"
            + "it is only called when the program has to terminate"
    )
    public void stop() {
        System.exit(0);
    }

    /**
     * A getter to obtain the amount of elapsed time between two updates.
     * @return - the elapsed time 
     */
    public long getElapsedTime() {
        return this.elapsed;
    }

    /**
     * A method to set the current scene manager that needs to be updated.
     * @param manager - the manager that needs to be updated {@link SceneManager}
     */
    @SuppressFBWarnings(
        value = {"EI_EXPOSE_REP2"},
        justification = "This method is needed to change the scene that needs to be updated"
            + "so a reference to the scene itself is needed"
    )
    public void setSceneManager(final SceneManager manager) {
        this.manager = manager;
    }

    /**
     * A method the returns the keyboardController associated with its elements.
     * @return - The KeyboardInputController of the engines elements {@link KeyboardInputController}
     */
    public KeyboardInputController getController() {
        return this.controller;
    }

    /**
     * A method to obtain the View associated with this engine.
     * @return - The view the engine updates {@link View}
     */
    @SuppressFBWarnings(
        value = {"EI_EXPOSE_REP"},
        justification = "To be able to be seen the elements need a reference to the view"
            + "that is being updated"
    )
    public View getView() {
        return this.view;
    }
}
