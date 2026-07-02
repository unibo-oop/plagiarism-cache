package controller;

import javafx.animation.AnimationTimer;
import view.AudioPlayer;
import view.GameView;
import view.PauseMenu;
import view.View;

/**
 * 
 * Implementation of GameController.
 *
 */
public final class GameControllerImpl implements GameController {
    private static final int NANOSECOND_PER_FRAME = 32000;

    private long lastUpdate = 0;
    private final AnimationTimer animationTimer;
    private final MainController controller;
    private final EntityController entityController;
    private final GameView gameView;
    private final EntityFactory factory = new EntityFactory();
    private final CollisionController collision;
    private final MovementController movement;

    /**
     * Constructor.
     * 
     * @param view        View of the game.
     * @param input       Input handler.
     * @param controller  The main controller.
     * @param audioPlayer The audio player.
     */
    public GameControllerImpl(final View view, final KeyboardInput input, final MainController controller,
            final AudioPlayer audioPlayer) {
        this.controller = controller;
        collision = new CollisionController(controller, audioPlayer);
        entityController = new EntityControllerImpl(new MovementController(factory, collision), collision);
        movement = new MovementController(factory, collision);
        gameView = view.setGameView();
        input.setParameters(factory, movement);
        animationTimer = new AnimationTimer() {
            public void handle(final long now) {
                if (now - lastUpdate >= NANOSECOND_PER_FRAME) {
                    gameView.update(factory);
                    entityController.switchPalaceStatus(factory.getPalace(), factory.getStuntman());
                    entityController.spawnHawk(factory.getHawks());
                    entityController.spawnVase(factory.getPalace(), factory.getVases());
                    entityController.spawnBonus(factory.getPalace(), factory.getBonus());
                    lastUpdate = now;
                }
            }
        };
        start();
    }

    @Override
    public void start() {
        animationTimer.start();
        if (gameView.getChildren().size() == 4) {
            gameView.getChildren().remove(3);
            gameView.getChildren().remove(2);
        }
    }

    @Override
    public void pause() {
        animationTimer.stop();
        new PauseMenu(gameView, controller);
    }

    @Override
    public void stop() {
        animationTimer.stop();
    }
}
