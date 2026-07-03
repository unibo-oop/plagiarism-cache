package breakout.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import breakout.controller.levels.ClassicLevel;
import breakout.model.ClassicMode;
import breakout.model.GameStatus;
import breakout.model.Model;
import breakout.model.entities.Brick;
import breakout.model.entities.Paddle;
import breakout.model.entities.Wall;
import breakout.model.levels.LevelImpl;
import breakout.model.physics.GameObject;
import breakout.view.ClassicGameScene;
import breakout.view.graphics.Colors;
import breakout.view.graphics.Images;
import breakout.view.graphics.Images.ColoredEntities;
import javafx.application.Platform;
import javafx.scene.image.Image;

/**
 * 
 *
 */
public final class ClassicGameLoop extends AbstractGameLoop {

    private final ClassicGameScene gameScene;
    private final ClassicMode model;
    private final Map<Brick, Image> bricks = new HashMap<>();
    private final Image ball = Images.getImages().getColoredImage(ColoredEntities.BALLS, Colors.CLASSICRED);
    private final Image paddle = Images.getImages().getColoredImage(ColoredEntities.PADDLES, Colors.CLASSICRED);
    private final Image wall = Images.getImages().getColoredImage(ColoredEntities.SIMPLE_BRICKS, Colors.CLASSICGREY);

    // Input
    private final InputHandler inputHandler;

    // Sounds
    private final SoundManager soundManager;

    /**
     * 
     * @param gameScene
     *            the scene where the loop has to run
     */
    public ClassicGameLoop(final ClassicGameScene gameScene) {
        super();
        final LevelImpl level = ClassicLevel.getClassicLevel();
        level.getBricksWithColors().forEach(pair -> {
            final Brick brick = pair.getKey();
            final Colors color = pair.getValue();
            bricks.put(brick, Images.getImages().getColoredImage(ColoredEntities.SIMPLE_BRICKS, color));
        });

        this.gameScene = gameScene;
        this.model = Model.createClassicGame(level);
        this.soundManager = object -> {
            if (object instanceof Brick) {
                Sounds.get().getSound(Sounds.AvailableSounds.CLASSIC_BRICK_HIT).play();
            } else if (object instanceof Paddle || object instanceof Wall) {
                Sounds.get().getSound(Sounds.AvailableSounds.CLASSIC_PADDLE_HIT).play();
            }
        };

        // Input
        this.inputHandler = input -> {
            switch (input) {
            case RIGHT:
                this.model.getPaddle().moveRight();
                break;
            case LEFT:
                this.model.getPaddle().moveLeft();
                break;
            case START:
                this.model.start();
                break;
            default:
                break;
            }
        };

    }

    @Override
    protected void render() {
        final Runnable render = () -> {
            gameScene.clearCanvas(null);
            model.getBricks().forEach(b -> {
                if (bricks.containsKey(b)) {
                    final Image toDraw = bricks.get(b);
                    gameScene.draw(toDraw, b.getPosition().getX(), b.getPosition().getY(), b.getBounds().getWidth(),
                            b.getBounds().getHeight());
                }
            });
            model.getBalls().forEach(b -> gameScene.draw(ball, b.getPosition().getX(), b.getPosition().getY(),
                    b.getBounds().getWidth(), b.getBounds().getHeight()));

            gameScene.draw(paddle, model.getPaddle().getPosition().getX(), model.getPaddle().getPosition().getY(),
                    model.getPaddle().getBounds().getWidth(), model.getPaddle().getBounds().getHeight());

            model.getWalls().forEach(w -> gameScene.draw(wall, w.getPosition().getX(), w.getPosition().getY(),
                    w.getBounds().getWidth(), w.getBounds().getHeight()));

            gameScene.updateScore(model.getScore());
            gameScene.updateLife(model.getLife());
            gameScene.updateLevel(String.valueOf(model.getLevelCount()));
        };
        Platform.runLater(render);

    }

    /**
     * {@inheritDoc}
     */

    @Override
    protected List<GameObject> updateGame(final double elapsed) {
        final List<GameObject> collisions = model.checkCollisions();
        model.updateAll(elapsed);
        if (model.getGameStatus().equals(GameStatus.Over)) {
            this.finish();
            gameScene.gameFinished();
        }
        return collisions;
    }

    /**
     * {@inheritDoc}
     */

    @Override
    protected void handleSounds(final List<GameObject> collisions) {
        this.soundManager.playAll(collisions);
    }

    /**
     * {@inheritDoc}
     */

    @Override
    protected synchronized void resolveInputs() {
        inputHandler.resolveAll(gameScene.getInputs());

    }
}
