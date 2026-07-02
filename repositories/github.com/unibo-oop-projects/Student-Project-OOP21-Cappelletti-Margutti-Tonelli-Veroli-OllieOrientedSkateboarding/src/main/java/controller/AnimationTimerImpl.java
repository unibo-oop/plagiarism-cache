package controller;

import javafx.animation.AnimationTimer;

/**
 * 
 * Class operating as a game loop.
 * Extends {@link AnimationTimer}.
 *
 */
public class AnimationTimerImpl extends AnimationTimer {

    private final Controller controller;
    private boolean running;

    /**
     * Creates a new AnimationTimerImpl.
     * @param controller the application controller.
     */
    public AnimationTimerImpl(final Controller controller) {
        this.controller = controller;
        this.running = false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void handle(final long time) {
        this.controller.processInput();
        this.controller.update();
        this.controller.render();
    }

    /**
     * Checks if AnimationTimerImpl is running.
     * @return true if AnimationTimerImpl is Running, false otherwise.
     */
    public boolean isRunning() {
        return this.running;
    }

    /**
     * Sets if AnimationTimerImpl is running or not.
     * @param value value has to be true is running, false otherwise. 
     */
    public void setRunning(final boolean value) {
        this.running = value;
    }

}
