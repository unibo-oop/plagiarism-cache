package view.controller;

import java.util.Objects;

import view.viewmanager.ViewManager;

/**
 * Abstract class for modeling all the scenes controllers.
 */
public class AbstractController {
    /**
     * The ViewManager is shared by all controllers.
     */
    private final ViewManager loader;

    /**
     * @param loader the loader of all the scenes.
     */
    public AbstractController(final ViewManager loader) {
        this.loader = Objects.requireNonNull(loader);
    }

    /**
     * Get the ViewManager.
     * 
     * @return the ViewManager
     */
    public ViewManager getViewManager() {
        return this.loader;
    }
}
