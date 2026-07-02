package vg.view;

import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import vg.view.utils.KeyEventHandler;
import java.util.Stack;

/**
 * ViewManager is responsible to manage and the navigation stack from main page;
 * it keeps Stage and set/remove scene to be shown and notify the controller of visible view (scene)
 * about the keyEvent registered by EventHandler of stage.
 */
public final class ViewManagerImpl implements ViewManager {
    /**
     * JavaFX Stage of application.
     */
    private final Stage stage;
    /**
     * Stack of Views and their Controller. The last one is the showing view in application.
     */
    private final Stack<View> sceneStack;
    /**
     * Key Event Handler that propagate event to view's controller.
     * {@see KeyEventHandler}.
     */
    private final KeyEventHandler keyEventHandler;

    public ViewManagerImpl(final Stage stage, final KeyEventHandler keyEventHandler) {
        this.stage = stage;
        this.sceneStack = new Stack<>();
        this.keyEventHandler = keyEventHandler;
        stage.addEventHandler(KeyEvent.ANY, keyEventHandler);
    }

    private void showScene() {
        View lastView = this.sceneStack.lastElement();
        this.stage.setScene(lastView.getScene());
        this.keyEventHandler.setSceneController(lastView.getSceneController());
    }

    @Override
    public void addView(final View view) {
        this.sceneStack.push(view);
        showScene();
    }

    @Override
    public void popView() {
        if (this.sceneStack.size() > 1) {
            this.sceneStack.pop();
            showScene();
        }
    }

    @Override
    public void backHome() {
        View rootView = this.sceneStack.firstElement();
        this.sceneStack.removeAllElements();
        this.sceneStack.add(rootView);
        showScene();
    }

    public Stage getStage() {
        return this.stage;
    }
}
