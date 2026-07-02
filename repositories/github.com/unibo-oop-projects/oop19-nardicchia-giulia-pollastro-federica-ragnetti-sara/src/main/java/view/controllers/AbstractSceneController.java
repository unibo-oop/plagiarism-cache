package view.controllers;

import java.util.List;
import java.util.ArrayList;

import controller.Controller;
import javafx.scene.Parent;
import view.View;

/**
 * Represent the generic Class for the JavaFx Scene controller.
 *
 */
public abstract class AbstractSceneController implements FxController { // NOPMD

    private static List<String> gameList = new ArrayList<>();
    private Controller controller;
    private View view;
    private Parent root;

    /**
     * Gets the {@link View}.
     * 
     * @return the {@link View}
     */
    protected View getView() {
        return this.view;
    }

    /**
     * Gets the {@link Controller}.
     * 
     * @return the {@link Controller}
     */
    protected Controller getController() {
        return controller;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void initialize(final View view, final Controller controller) {
        this.view = view;
        this.controller = controller;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Parent getRoot() {
        return this.root;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setRoot(final Parent root) {
        this.root = root;
    }

    /**
     * Gets the minigames.
     * 
     * @return the minigames list associated with the selected area
     */
    protected List<String> getGameList() {
        return AbstractSceneController.gameList;
    }
}
