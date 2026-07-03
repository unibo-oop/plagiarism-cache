package view.state;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import controller.ButtonObserver;
import controller.Observer;
import util.SceneControllerLoaderProxy.FxmlID;
import view.controller.ControllerFXML;
import view.controller.MenuSceneController;

/**
 * The view state representing the main menu.
 */
public final class MenuState extends PrimaryState {

    private final List<Observer> menuObservers = new ArrayList<>(Arrays.asList(new ButtonObserver()));

    /**
     * Constructor used to properly set the {@link ControllerFXML} variable used in
     * {@link PrimaryState}.
     */
    public MenuState() {
        super(FxmlID.MENU);
        ((MenuSceneController) super.getController()).checkLoadButton();
    }

    @Override
    public List<Observer> getObservers() {
        return menuObservers;
    }

    @Override
    public void exitState() {

    }
}