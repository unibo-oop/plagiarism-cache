package controller.player;

import java.awt.Point;
import java.util.HashSet;
import java.util.Set;
import controller.GameStatus;
import controller.GameStatusImpl;
import controller.LevelManager;
import controller.LevelManagerImpl;
import model.obstacle.Obstacle;
import model.obstacle.ObstacleImpl;

/**
 * Class that manages player's path and collisions with obstacles.
 *
 */
public final class PlayerPathImpl implements PlayerPath {

    private static final PlayerPathImpl SINGLETON = new PlayerPathImpl();
    private final LevelManager levelManager;
    private final Obstacle obstacle;
    private GameStatus gameStatus;
    private final Set<Point> playerPositions;

    /**
     * Constructor.
     */
    private PlayerPathImpl() {
        this.levelManager = LevelManagerImpl.get();
        this.obstacle = ObstacleImpl.get();
        this.playerPositions = new HashSet<Point>();
    }

    @Override
    public void savePath(final Point position) {
        this.playerPositions.add(position);
    }

    @Override
    public void checkPath(final Point position) {
        this.gameStatus = GameStatusImpl.get();

        if (this.playerPositions.contains(position)) {
            this.gameStatus.gameOver();
        }

        if (position.equals(this.levelManager.chooseLevel().getEndPosition())) {
            this.checkVictory();
        }
    }

    // Checks if the player has touched all playable tiles of the current level.
    private void checkVictory() {
        this.gameStatus = GameStatusImpl.get();

        if (this.playerPositions.containsAll(this.levelManager.chooseLevel().getPlayableMap())) {
            this.gameStatus.success();
        } else {
            this.gameStatus.gameOver();
        }
    }

    @Override
    public boolean checkObstacle(final Point position) {
        return this.obstacle.getObstaclePositions().entrySet().stream()
                .anyMatch(obstacle -> obstacle.getKey().equals(position));
    }

    @Override
    public boolean checkBound(final int x, final int y) {
        return (x < this.levelManager.chooseLevel().getMapBoundMinX()
                || x > this.levelManager.chooseLevel().getMapBoundMaxX()
                || y < this.levelManager.chooseLevel().getMapBoundMinY()
                || y > this.levelManager.chooseLevel().getMapBoundMaxY())
                && !(x == this.levelManager.chooseLevel().getCharStartCol()
                        && y <= this.levelManager.chooseLevel().getCharStartRow()
                        && x == this.levelManager.chooseLevel().getCharFinishCol()
                        && y >= this.levelManager.chooseLevel().getCharFinishRow()) ? true : false;
    }

    @Override
    public void clearPath() {
        this.playerPositions.clear();
    }

    @Override
    public Set<Point> getPlayerPath() {
        return this.playerPositions;
    }

    /**
     * Returns an instance of PlayerPathImpl.
     * 
     * @return an instance of PlayerPathImpl
     */
    public static PlayerPathImpl get() {
        return SINGLETON;
    }
}
