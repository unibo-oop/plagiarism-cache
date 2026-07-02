package controller.menu;

import com.google.common.eventbus.Subscribe;

import common.events.SceneEvent;

/**
 * The main application controller
 */
public interface MainController {

    /**
     * Method with the starting logic.
     */
    void start();

    /**
     * Method to handle SceneEvent from an EventBus.
     * 
     * @param event
     */
    @Subscribe
    void handleSceneEvent(final SceneEvent event);
}
