package view.state;

import java.util.Collections;
import java.util.List;
import java.util.Stack;

import controller.Observer;
import javafx.geometry.Pos;
import javafx.scene.layout.FlowPane;
import util.SceneControllerLoaderProxy;
import util.SceneControllerLoaderProxy.FxmlID;
import view.SceneManager;
import view.ViewManagerImpl;
import view.controller.ControllerFXML;

/**
 * This abstract class represents a "minor" state. Those kind of states modify
 * the scene by adding themselves to the scene graph on a window style (over the
 * current scene contents) and simply pushing themselves to the state stack.
 */
public class WindowState extends AbstractGenericState {

    /**
     * Constructor of WindowState class.
     * 
     * @param controllerIdentifier
     *            Those kind of states can initialized {@link ControllerFXML} on
     *            their own so the user class have to specify only the
     *            {@link FxmlID} value
     */
    public WindowState(final FxmlID controllerIdentifier) {
        super(SceneControllerLoaderProxy.get().getController(controllerIdentifier));
        super.getController().openAnimation();
    }

    @Override
    public void modifyScene(final SceneManager manager) {
        final FlowPane temp = new FlowPane();
        temp.setId("darkGlassPane");
        temp.setAlignment(Pos.CENTER);
        temp.getChildren().add(super.getController().getRoot());
        manager.pushLayer(temp);
    }

    @Override
    public void modifyStateStack(final Stack<ViewState> stateStack) {
        stateStack.push(this);
    }

    @Override
    public List<Observer> getObservers() {
        return Collections.emptyList();
    }

    @Override
    public void exitState() {
        super.getController().closeAnimation(() -> ViewManagerImpl.get().popState());
    }
}
