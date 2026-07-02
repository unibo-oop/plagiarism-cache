package it.unibo.oop.manpac.view.screens.menu;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Stage;

/**
 * Interface to notify the observer when a certain event occurs.
 */
public interface ObservableScreen extends Screen {

    /**
     * Get the stage where all the objects of the view are contained.
     * 
     * @return The stage
     */
    Stage getStage();

    /**
     * Set the screen observer.
     * 
     * @param screenObserver The Observer
     */
    void setObserver(ScreenObserver screenObserver);
}
