package model.player;

import java.awt.Point;
import java.util.Map;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.obstacle.Obstacle;
import model.obstacle.ObstacleImpl;
import controller.LevelManager;
import controller.LevelManagerImpl;
import controller.player.PlayerPath;
import controller.player.PlayerPathImpl;
import utilities.Directions;
import utilities.Utilities;

/**
 * Class that manages player's movement.
 *
 */
// CHECKSTYLE: MagicNumber OFF
public final class PlayerImpl implements Player {

    private static final PlayerImpl SINGLETON = new PlayerImpl();
    private final PlayerPath playerPath;
    private final LevelManager levelManager;
    private final Obstacle obstacle;
    private final Point position = new Point();
    private ImageView charFrame;

    /**
     * Constructor.
     */
    private PlayerImpl() {
        this.playerPath = PlayerPathImpl.get();
        this.levelManager = LevelManagerImpl.get();
        this.obstacle = ObstacleImpl.get();
    }

    @Override
    public void move(final Directions direction) {
        switch (direction) {
        case UP:
            this.updatePlayerPosition(0, -Utilities.PLAYER_MOVEMENT_DELTA);
            break;
        case DOWN:
            this.updatePlayerPosition(0, Utilities.PLAYER_MOVEMENT_DELTA);
            break;
        case LEFT:
            this.updatePlayerPosition(-Utilities.PLAYER_MOVEMENT_DELTA, 0);
            break;
        case RIGHT:
            this.updatePlayerPosition(Utilities.PLAYER_MOVEMENT_DELTA, 0);
            break;
        default:
            break;
        }
    }

    @Override
    public Point getPlayerPosition() {
        return this.position.getLocation();
    }

    @Override
    public void setPlayerPosition(final int x, final int y) {
        this.position.setLocation(x, y);
    }

    // Moves the player by the given x and y parameters.
    private void updatePlayerPosition(final int x, final int y) {
        if (!this.playerPath.checkBound(this.getPlayerPosition().x + x, this.getPlayerPosition().y + y)
                && !this.playerPath
                        .checkObstacle(new Point(this.getPlayerPosition().x + x, this.getPlayerPosition().y + y))) {
            this.playerPath.savePath(this.getPlayerPosition());
            this.levelManager.chooseLevel().getGrid().getChildren().remove(this.charFrame);
            this.levelManager
                    .chooseLevel().getGrid().add(
                            new ImageView(new Image(this.levelManager.chooseLevel().getTilePath(), Utilities.W / 22,
                                    Utilities.H / 22, false, false)),
                            this.getPlayerPosition().x, this.getPlayerPosition().y);
            this.position.translate(x, y);
            this.levelManager.chooseLevel().getGrid().add(this.charFrame, this.getPlayerPosition().x,
                    this.getPlayerPosition().y);
            this.updateObstacleLayer();
            this.playerPath.checkPath(this.getPlayerPosition());
        }
    }

    // Fixes a layer issue with a step tile effect when the player is behind an
    // obstacle.
    private void updateObstacleLayer() {
        for (final Map.Entry<Point, ImageView> obstacle : this.obstacle.getObstaclePositions().entrySet()) {
            if ((((Integer) this.getPlayerPosition().x).equals((int) obstacle.getKey().getX())
                    && ((Integer) this.getPlayerPosition().y)
                            .equals((int) obstacle.getKey().getY() - Utilities.PLAYER_MOVEMENT_DELTA))
                    || (((Integer) this.getPlayerPosition().x).equals((int) obstacle.getKey().getX())
                            && ((Integer) this.getPlayerPosition().y)
                                    .equals((int) obstacle.getKey().getY() - (Utilities.PLAYER_MOVEMENT_DELTA * 2)))
                    || (((Integer) this.getPlayerPosition().x)
                            .equals((int) obstacle.getKey().getX() - Utilities.PLAYER_MOVEMENT_DELTA)
                            && ((Integer) this.getPlayerPosition().y)
                                    .equals((int) obstacle.getKey().getY() - Utilities.PLAYER_MOVEMENT_DELTA))
                    || (((Integer) this.getPlayerPosition().x)
                            .equals((int) obstacle.getKey().getX() + Utilities.PLAYER_MOVEMENT_DELTA)
                            && ((Integer) this.getPlayerPosition().y)
                                    .equals((int) obstacle.getKey().getY() - Utilities.PLAYER_MOVEMENT_DELTA))) {
                this.levelManager.chooseLevel().getGrid().getChildren().remove(obstacle.getValue());
                this.levelManager.chooseLevel().getGrid().add(obstacle.getValue(), (int) obstacle.getKey().getX(),
                        (int) obstacle.getKey().getY());
            }
        }
    }

    @Override
    public void setCharFrame(final ImageView charFrame) {
        this.charFrame = charFrame;
    }

    /**
     * Returns an instance of PlayerImpl.
     * 
     * @return an instance of PlayerImpl
     */
    public static PlayerImpl get() {
        return SINGLETON;
    }
}
