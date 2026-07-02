package it.unibo.javacrush.controller.impl.commands;

import java.util.function.Consumer;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.javacrush.controller.api.Command;
import it.unibo.javacrush.controller.api.GameController;
import it.unibo.javacrush.controller.impl.GameControllerImpl;
import it.unibo.javacrush.model.api.LevelManager;
import it.unibo.javacrush.view.api.GameView;
import it.unibo.javacrush.view.api.SceneManager;

/**
 * Command to start a level.
 */
public final class StartLevelCommand implements Command {

    private final SceneManager sceneManager;
    private final LevelManager levelManager;
    private final int idLevel;
    private final Consumer<GameController> consumer;
    private final GameView gameView;

    /**
     * Constructor for the StartLevelCommand class.
     * 
     * @param sceneManager the scene manager used to change the view to the game screen
     * @param levelManager the level manager used to get the level to start
     * @param idLevel the id of the level to start
     * @param consumer a consumer that accepts the created GameController,
     *      used to set the current game controller in the AppController
     * @param gameView the game view used to display the game screen
     */
    @SuppressFBWarnings(
        value = "EI_EXPOSE_REP2", 
        justification = "The controller strictly needs the view reference to make updates"
    )
    public StartLevelCommand(
        final SceneManager sceneManager,
        final LevelManager levelManager,
        final int idLevel,
        final Consumer<GameController> consumer,
        final GameView gameView) {
        this.sceneManager = sceneManager;
        this.levelManager = levelManager;
        this.idLevel = idLevel;
        this.consumer = consumer;
        this.gameView = gameView;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute() {
        final GameController gameController = new GameControllerImpl(
            this.levelManager.startMatch(idLevel),
            this.gameView
        );

        consumer.accept(gameController);

        this.sceneManager.showGame(gameController);
    }

}
