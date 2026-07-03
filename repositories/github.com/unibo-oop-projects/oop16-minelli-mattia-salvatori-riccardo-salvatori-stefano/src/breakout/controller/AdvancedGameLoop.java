package breakout.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import breakout.controller.Sounds.AvailableSounds;
import breakout.model.AdvancedMode;
import breakout.model.GameStatus;
import breakout.model.Model;
import breakout.model.entities.Brick;
import breakout.model.entities.BrickStructure;
import breakout.model.entities.Paddle;
import breakout.model.entities.PowerUp;
import breakout.model.entities.Projectile;
import breakout.model.entities.Wall;
import breakout.model.levels.DecoratedLevel;
import breakout.model.physics.GameObject;
import breakout.view.AdvancedGameScene;
import breakout.view.graphics.Colors;
import breakout.view.graphics.Images;
import breakout.view.graphics.Images.ColoredEntities;
import breakout.view.utils.Utils;
import javafx.application.Platform;
import javafx.scene.image.Image;

/**
 */
public class AdvancedGameLoop extends AbstractGameLoop {

    private static final int START_HINT_TIME = 2000;

    private final AdvancedGameScene gameScene;
    private final AdvancedMode model;
    private boolean gameStarted;
    private final Map<Brick, List<Image>> bricks = new HashMap<>();
    private final Image ball;
    private final Image ballFire = Images.getImages().getColoredImage(ColoredEntities.BALLS, Colors.FIRE);
    private final Image paddle;
    private final Image projectile = new Image(Utils.getPath("/Images/PowerUps/Projectile.png"));
    private final Iterator<DecoratedLevel> levels;
    private DecoratedLevel currentLevel;

    // Input
    private final InputHandler inputHandler;
    private final SoundManager soundManager;

    /**
     * 
     * @param gameScene
     *            the view where the loop has to run
     */
    public AdvancedGameLoop(final AdvancedGameScene gameScene) {
        super();
        this.model = Model.createAdvancedGame(new ArrayList<>(Controller.get().getLevelsToPlay()));
        this.gameScene = gameScene;
        this.levels = Controller.get().getLevelsToPlay().iterator();
        this.ball = Controller.get().getBallImage();
        this.paddle = Controller.get().getPaddleImage();
        this.currentLevel = this.levels.next();
        this.loadBrickImages();

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

        this.soundManager = object -> {
            if (object instanceof Brick) {
                if (((Brick) object).getType().getStructure().equals(BrickStructure.UNBREAKABLE)) {
                    Sounds.get().getSound(AvailableSounds.UNBREAKABLE_HIT).play();
                } else if (((Brick) object).getType().getStructure().equals(BrickStructure.HARD)) {
                    Sounds.get().getSound(AvailableSounds.BRICK_CRACK).play();
                } else {
                    Sounds.get().getSound(AvailableSounds.BRICK_HIT).play();
                }
            } else if (object instanceof Paddle) {
                Sounds.get().getSound(AvailableSounds.PADDLE_HIT).play();
            } else if (object instanceof Wall) {
                Sounds.get().getSound(AvailableSounds.WALL_HIT).play();
            } else if (object instanceof Projectile) {
                Sounds.get().getSound(AvailableSounds.PROJECTILE_EXPLOSION).play();
            } else if (object instanceof PowerUp) {
                Sounds.get().getSound(AvailableSounds.POWER_UP).play();
            }
        };

    }

    @Override
    protected void render() {
        final Runnable render = () -> {
            if (!gameStarted) {
                gameScene.showText("Press Space to Start", START_HINT_TIME);
                gameStarted = true;
            }

            // Clear the canvas and draw the background image of the level
            gameScene.clearCanvas(currentLevel.getBackgroundImage());

            // Draw all bricks still present in the level
            model.getBricks().forEach(b -> {
                if (bricks.containsKey(b)) {
                    final List<Image> toDraw = bricks.get(b);
                    gameScene.draw(toDraw.get(toDraw.size() - b.getRemainingLife()), b.getPosition().getX(),
                            b.getPosition().getY(), b.getBounds().getWidth(), b.getBounds().getHeight());
                }
            });

            // Draw all the balls
            model.getBalls().forEach(b -> {
                gameScene.draw(b.isOnFire() ? ballFire : ball, b.getPosition().getX(), b.getPosition().getY(),
                        b.getWidth(), b.getHeight());
            });

            // Draw the paddle
            gameScene.draw(paddle, model.getPaddle().getPosition().getX(), model.getPaddle().getPosition().getY(),
                    model.getPaddle().getWidth(), model.getPaddle().getHeight());

            // Draw the powerups
            model.getPowerUps().forEach(p -> {
                final Image powerUpImage = Images.getImages().getPowerUpImage(p.getEffectType());
                gameScene.draw(powerUpImage, p.getPosition().getX(), p.getPosition().getY(), powerUpImage.getWidth(),
                        powerUpImage.getHeight());
            });

            // Draw the buillets
            model.getBullets().forEach(p -> gameScene.draw(projectile, p.getPosition().getX(), p.getPosition().getY(),
                    projectile.getWidth(), projectile.getHeight()));

            // Update the active powerups list in the scene
            gameScene.updatePowerUp(model.getActivePowerUps().stream().map(p -> Images.getImages().getPowerUpImage(p))
                                         .collect(Collectors.toList()));

            gameScene.updateScore(model.getScore());
            gameScene.updateLife(model.getLife());
            gameScene.updateLevel(currentLevel.getName());
        };
        Platform.runLater(render);

    }

    @Override
    protected List<GameObject> updateGame(final double elapsed) {
        final List<GameObject> collisions = model.checkCollisions();
        model.updateAll(elapsed);
        if (model.getGameStatus().equals(GameStatus.Over)) {
            this.finish();
            gameScene.gameFinished();
        }
        if (!this.model.getCurrentLevel().equals(this.currentLevel) && this.levels.hasNext()) {
            this.currentLevel = this.levels.next();
            this.loadBrickImages();
        }
        return collisions;
    }

    @Override
    protected void handleSounds(final List<GameObject> collisions) {
        this.soundManager.playAll(collisions);
    }

    @Override
    protected void resolveInputs() {
        this.inputHandler.resolveAll(gameScene.getInputs());

    }

    /**
     * Loads brick images according to the level that has to be played
     */
    private void loadBrickImages() {
        this.bricks.clear();
        this.currentLevel.getBricksWithColors().stream().forEach(p -> {
            final Brick brick = p.getKey();
            final Colors color = p.getValue();
            final List<Image> images = new ArrayList<>();
            switch (brick.getType()) {
            case UNBREAKABLE_ADVANCED:
                images.addAll(
                        Arrays.asList(Images.getImages().getColoredImage(ColoredEntities.UNBREAKABLE_BRICKS, color)));
                break;
            case HARD_ADVANCED:
                images.addAll(Arrays.asList(
                        Images.getImages().getColoredImageWithDamage(ColoredEntities.HARD_BRICKS, color, 0),
                        Images.getImages().getColoredImageWithDamage(ColoredEntities.HARD_BRICKS, color, 1),
                        Images.getImages().getColoredImageWithDamage(ColoredEntities.HARD_BRICKS, color, 2)));
                break;
            case SIMPLE_ADVANCED:
                images.addAll(Arrays.asList(Images.getImages().getColoredImage(ColoredEntities.SIMPLE_BRICKS, color)));
                break;
            case ONE_CLASSIC:
                images.addAll(
                        Arrays.asList(Images.getImages().getColoredImage(ColoredEntities.UNBREAKABLE_BRICKS, color)));
                break;
            default:
                break;
            }
            this.bricks.put(brick, images);
        });
    }

}