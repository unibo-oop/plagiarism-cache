package it.unibo.arkanoid.view.controllers;

import it.unibo.arkanoid.controller.Controller;
import it.unibo.arkanoid.view.View;
import javafx.scene.Parent;

/**
 * 
 * This represent the Abstract Class for the JavaFx Scene controller.
 *
 */
public abstract class SubViewController { //NOPMD

    private Controller controller;
    private View view;
    private Parent root;

    /**
     * 
     * @param controller
     *            the controller of the game.
     * @param view
     *            the view of the game.
     */
    public void init(final Controller controller, final View view) {
        this.controller = controller;
        this.view = view;

    }

    /**
     * 
     * @return {@link Controller}.
     */
    protected Controller getController() {
        return controller;
    }

    /**
     * 
     * @return The Root of the Scene.
     */
    public Parent getRoot() {
        return root;
    }

    /**
     * 
     * @param root
     *            Set the Parent Root.
     */
    public void setRoot(final Parent root) {
        this.root = root;
    }

    /**
     * 
     * @return {@link View}
     */
    protected View getView() {
        return view;
    }

}
