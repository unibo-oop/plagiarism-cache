package view;

import java.util.Objects;
import java.util.Optional;
import java.util.Stack;

import controller.ClosureObserver;
import controller.event.Event;
import controller.event.StandardEvent;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import util.SceneControllerLoaderProxy.FxmlID;
import view.state.MenuState;
import view.state.ViewState;
import view.state.WindowState;

/**
 * Class used to initialize the javafx thread and implements
 * {@link ViewManager}. This is achieved by using the state pattern; moreover,
 * states are changed using a stack policy and the current state is always the
 * top one.
 */
public class ViewManagerImpl extends Application implements ViewManager {

    private Stage stage;
    private final Stack<ViewState> stateStack = new Stack<>();
    private ObserverManager subject;
    private SceneManager sceneManager;
    private static boolean gameRunning;
    private static ViewManagerImpl singleton;

    @Override
    public void start(final Stage stage) {
        setSingleton(this);

        this.stage = stage;

        this.subject = new ObserverManagerImpl();
        this.subject.addObserver(new ClosureObserver());

        sceneManager = new SceneManagerImpl(stage);

        this.pushState(new MenuState());

        stage.show();
        stage.setOnCloseRequest(e -> {
            this.sendViewEvent(new StandardEvent("CLOSE_REQUEST"));
        });

        // disabled space autoclick to resolve bug.
        stage.addEventFilter(KeyEvent.ANY, k -> {
            if (k.getCode().equals(KeyCode.SPACE)) {
                k.consume();
            }
        });
    }

    @Override
    public void pushState(final ViewState newState) {
        this.updateCurrentState(
                this.stateStack.isEmpty() ? Optional.empty() : Optional.of(this.stateStack.lastElement()),
                Optional.of(newState));

        newState.modifyStateStack(this.stateStack);
        this.stateStack.lastElement().modifyScene(this.sceneManager);
    }

    @Override
    public void popState() {
        if (this.stateStack.isEmpty()) {
            throw new IllegalStateException();
        }

        this.updateCurrentState(Optional.of(this.stateStack.lastElement()),
                this.stateStack.size() == 1 ? Optional.empty() : Optional.of(stateStack.get(stateStack.size() - 2)));

        this.stateStack.pop();
        this.sceneManager.popLayer();
    }

    @Override
    public void sendViewEvent(final Event event) {
        subject.notifyAll(event);
    }

    @Override
    public void receiveGameEvent(final String string) {
        if (string.equals("go")) {
            Platform.runLater(() -> this.pushState(new WindowState(FxmlID.GAME_OVER)));
        }
    }

    @Override
    public void resizeScene() {
        this.sceneManager.scaleRootContents();
    }

    @Override
    public boolean isGameRunning() {
        return gameRunning;
    }

    private void updateCurrentState(final Optional<ViewState> oldState, final Optional<ViewState> newState) {
        if (oldState.isPresent()) {
            oldState.get().getObservers().stream().forEach(e -> this.subject.removeObserver(e));
            this.stage.getScene().removeEventHandler(KeyEvent.ANY, oldState.get().getKeyEventHandler());
        }
        if (newState.isPresent()) {
            newState.get().getObservers().stream().forEach(e -> this.subject.addObserver(e));
            this.stage.getScene().addEventHandler(KeyEvent.ANY, newState.get().getKeyEventHandler());
        }
    }

    /**
     * Starts the javafx main class.
     * 
     * @param args
     *            the optional args passed at the lauch of the application
     */
    public static void initMainGUI(final Optional<String[]> args) {
        launch();
    }

    /**
     * Getter of the singleton of this class; if the javafx thread is not already
     * lauched a IllegalStateException is thrown.
     *
     * @return an istance of MainGUI
     */
    public static ViewManagerImpl get() {
        if (Objects.isNull(singleton)) {
            throw new IllegalStateException("Class not initialized");
        }
        return singleton;
    }

    /**
     * Setter of gameRunning.
     * 
     * @param value
     *            the value to set
     */
    public static void setGameRunning(final boolean value) {
        gameRunning = value;
    }

    private static void setSingleton(final ViewManagerImpl value) {
        singleton = value;
    }
}
