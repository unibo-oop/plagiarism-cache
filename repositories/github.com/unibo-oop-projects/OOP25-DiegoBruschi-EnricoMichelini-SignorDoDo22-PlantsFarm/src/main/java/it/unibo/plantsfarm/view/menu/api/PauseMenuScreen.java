package it.unibo.plantsfarm.view.menu.api;

import java.awt.event.ActionListener;

/**
 * Interface defining Pause Menu view component.
 */
public interface PauseMenuScreen {

    /**
     * Sets the action for the Resume button.
     *
     * @param listener The action listener.
     */
    void setResumeButton(ActionListener listener);

    /**
     * Sets the action for the Commands button.
     *
     * @param listener The action listener.
     */
    void setCommandsButton(ActionListener listener);

    /**
     * Sets the action for the Reset button.
     *
     * @param listener The action listener.
     */
    void setResetButton(ActionListener listener);

    /**
     * Sets the action for the Extra button.
     *
     * @param listener The action listener.
     */
    void setExtraButton(ActionListener listener);

    /**
     * Sets the action for the Credits button.
     *
     * @param listener The action listener.
     */
    void setCreditsButton(ActionListener listener);

    /**
     * Sets the action for the Exit button.
     *
     * @param listener The action listener.
     */
    void setExitButton(ActionListener listener);

    /**
     * Shows the pause menu window.
     */
    void show();

    /**
     * Closes the pause menu window.
     */
    void close();

}
