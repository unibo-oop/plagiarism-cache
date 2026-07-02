package it.unibo.javacrush;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.javacrush.controller.api.AppController;
import it.unibo.javacrush.controller.api.GameController;
import it.unibo.javacrush.controller.impl.AppControllerImpl;
import it.unibo.javacrush.model.api.LevelManager;
import it.unibo.javacrush.model.impl.LevelManagerImpl;
import it.unibo.javacrush.view.api.GameView;
import it.unibo.javacrush.view.api.InstructionsView;
import it.unibo.javacrush.view.api.LevelsView;
import it.unibo.javacrush.view.api.MenuView;
import it.unibo.javacrush.view.api.SceneManager;
import it.unibo.javacrush.view.impl.GameViewImpl;
import it.unibo.javacrush.view.impl.InstructionsViewImpl;
import it.unibo.javacrush.view.impl.LevelsViewImpl;
import it.unibo.javacrush.view.impl.MenuViewImpl;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * This class represents the entry point of the application.
 */
public class JavaCrushApp extends Application implements SceneManager {

    private static final int WIDTH = 1000;
    private static final int HEIGHT = 620;
    private AppController appController;
    private final LevelManager levelManager = new LevelManagerImpl();
    private MenuView menuView;
    private LevelsView levelsView;
    private InstructionsView instructionsView;
    private GameView gameView;
    private Scene scene;

    /**
     * {@inheritDoc}
     */
    @Override
    public void start(final Stage s) throws Exception {

        final Stage stage = s;
        stage.setWidth(WIDTH);
        stage.setHeight(HEIGHT);

        this.appController = new AppControllerImpl(this, this.levelManager);

        this.menuView = new MenuViewImpl(this.appController);
        this.levelsView = new LevelsViewImpl(this.appController, this.levelManager);
        this.instructionsView = new InstructionsViewImpl(this.appController);
        this.gameView = new GameViewImpl();

        this.scene = new Scene(this.menuView.getView(), stage.getWidth(), stage.getHeight());
        stage.setTitle("JavaCrush");
        stage.setScene(this.scene);
        stage.show();

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void showMenu() {
        this.scene.setRoot(menuView.getView());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void showLevels() {
        this.scene.setRoot(levelsView.getView());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void showGame(final GameController gameController) {
        gameView.setController(gameController, this.appController);
        this.scene.setRoot(gameView.getView());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void quit() {
        this.showLevels();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void showInstructions() {
        this.scene.setRoot(instructionsView.getView());
    }

    /**
     * {@inheritDoc}
     */
    @SuppressFBWarnings(
        value = "EI_EXPOSE_REP", 
        justification = "The view component must be exposed to allow the controller to manage the UI."
    )
    @Override
    public GameView getGameView() {
        return this.gameView;
    }
}

