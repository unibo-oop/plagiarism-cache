package main.java.com.view;

import javax.swing.JFrame;

/**
 * Interface that models a basic window for the application.
 *
 */
public interface BasicWindow {

    /**
     * Shows the window on the screen.
     */
    void show();

    /**
     * Sets the {@link GameObserver} for this window.
     * @param obs the observer
     */
    void setObserver(GameObserver obs);

    /**
     * 
     * @return the frame of this BasicWindow.
     */
    JFrame getFrame();
}
