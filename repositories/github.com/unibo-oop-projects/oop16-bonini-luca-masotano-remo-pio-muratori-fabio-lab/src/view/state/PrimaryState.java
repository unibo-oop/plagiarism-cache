package view.state;

import java.util.List;
import java.util.Stack;

import controller.Observer;
import util.SceneControllerLoaderProxy;
import util.SceneControllerLoaderProxy.FxmlID;
import view.SceneManager;

/**
 * This abstract class represents an "important" state. Those kind of states
 * modify the scene by setting themselves as the only content of the scene graph
 * and similarly setting themselves as the only state of the state stack.
 */
public abstract class PrimaryState extends AbstractGenericState {

    /**
     * Contructor of PrimaryState class.
     * 
     * @param controllerIdentifier
     *            the indentifier of a controller passed by subclasses.
     */
    public PrimaryState(final FxmlID controllerIdentifier) {
        super(SceneControllerLoaderProxy.get().getController(controllerIdentifier));
    }

    @Override
    public void modifyScene(final SceneManager manager) {
        manager.setStackHead(super.getController().getRoot());
    }

    @Override
    public void modifyStateStack(final Stack<ViewState> stateStack) {
        stateStack.clear();
        stateStack.push(this);
    }

    @Override
    public abstract List<Observer> getObservers();
}
