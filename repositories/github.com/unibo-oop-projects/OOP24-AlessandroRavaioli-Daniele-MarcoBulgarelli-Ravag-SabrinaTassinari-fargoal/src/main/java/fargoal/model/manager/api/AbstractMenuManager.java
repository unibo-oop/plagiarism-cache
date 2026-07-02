package fargoal.model.manager.api;

import fargoal.controller.input.api.KeyboardInputController;
import fargoal.controller.input.api.MenuInputComponent;
import fargoal.model.commons.Timer;
import fargoal.model.core.GameEngine;

/**
 * Abstract class that the defines the common actions of every {@link MenuManager}.
 */
public abstract class AbstractMenuManager implements MenuManager {

    private static final int MILLIS_TO_WAIT = 150;

    private final Timer selectionTimer;
    private final MenuInputComponent inputComponent;
    private final KeyboardInputController ctrl;
    private int selected;

    /**
     * This constructor that initializes all internal variables and starts an input timer.
     * @param engine - the game engine containing 
     * @param inputComponent - the component that processes inputs on the menu
     */
    protected AbstractMenuManager(final GameEngine engine, final MenuInputComponent inputComponent) {
        this.selected = 1;
        this.inputComponent = inputComponent;
        this.selectionTimer = new Timer();
        this.ctrl = engine.getController();
        this.selectionTimer.setTime(MILLIS_TO_WAIT);
    }

    /** {@inheritDoc}*/
    @Override
    public void increaseSelected() {
        this.selected++;
        if (loopCondition()) {
            loopSelected();
        }
    }

    /** {@inheritDoc} */
    @Override
    public void decreaseSelected() {
        this.selected--;
        if (loopCondition()) {
            loopSelected();
        }
    }

    /** {@inheritDoc} */
    @Override
    public void update(final GameEngine engine) {
        if (this.selectionTimer.updateTime(engine.getElapsedTime()) == 0) {
            this.inputComponent.update(this, this.ctrl);
            this.selectionTimer.setTime(MILLIS_TO_WAIT); 
        }
    }

    /**
     * Method that returns the value of the field {@link #selected}.
     * @return - the value of selected {@link #selected}
     */
    protected int getSelected() {
        return this.selected;
    }

    /**
     * Method to set {@link #selected} to a new value.
     * @param newValue - the new value
     */
    protected void setSelected(final int newValue) {
        this.selected = newValue;
    }

    /**
     * Method that defines the condition for which the value of {@link #selected} loops.
     * @return - true if the the value should loop, false otherwhise
     */
    protected abstract boolean loopCondition();

    /**
     * Method that makes {@link #selected} loop.
     */
    protected abstract void loopSelected();
}
