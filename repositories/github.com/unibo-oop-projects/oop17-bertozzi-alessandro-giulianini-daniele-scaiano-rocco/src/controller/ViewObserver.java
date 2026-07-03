package controller;

import view.GamePanel;
import view.ViewState;

/**
 * Interface for the observer which will be a filter between view
 * and controller in order to respect MVC.
 */
public interface ViewObserver {
    /**
     * Updates the status of the observer. 
     * It must be called when a certain change happen in the view.
     * In this case we're going to wait for the input "start game".
     * 
     * @param panel the panel that notifies a change
     * @param state the state of the view
     */
    void update(GamePanel panel, ViewState state);
}
