package bzzbomber.model;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import bzzbomber.game.Game;
import bzzbomber.model.entities.Block;
import bzzbomber.model.entities.EntityManager;
import bzzbomber.model.entities.HealthPoint;
import bzzbomber.model.entities.Insects;
import bzzbomber.model.entities.InsectsImpl;
import bzzbomber.model.utilities.FileLoader;
import bzzbomber.view.TileImg;

/**
 * Represents one of the game levels.
 */
public final class Level {

    /**
     * Array of file paths each representing level structure.
     */
    public static final String[] LEVELS_FILE = new String[] { "/Map/level1.map", "/Map/level2.map", "/Map/level3.map" };

    private static final String[] INSECTS_POSITIONS = new String[] { "/Positions/insects_pos_lvl1.txt",
            "/Positions/insects_pos_lvl2.txt", "/Positions/insects_pos_lvl3.txt" };
    private static final String[] BOX_POSITIONS = new String[] { "/Positions/box_pos_lvl1.txt",
            "/Positions/box_pos_lvl2.txt", "/Positions/box_pos_lvl3.txt" };
    private static final String[] DOOR_POSITIONS = new String[] { "/Positions/door_pos_lvl1.txt",
            "/Positions/door_pos_lvl2.txt", "/Positions/door_pos_lvl3.txt" };
    private static final String[] HEALTH_POSITIONS = new String[] { "/Positions/health_pos_lvl1.txt",
            "/Positions/health_pos_lvl2.txt", "/Positions/health_pos_lvl3.txt" };

    private final int[][] gameMap;
    private final EntityManager entityManager;

    private final List<Point> insectsPositions;
    private final List<Point> boxPositions;
    private final List<Point> doorPositions;
    private final List<Point> healthPositions;

    private final Model model;

    /**
     * @param m
     *            Reference of Model
     * @param levelNum
     *            Level number
     */
    public Level(final Model m, final int levelNum) {
        this.model = m;
        this.gameMap = FileLoader.loadMap(LEVELS_FILE[levelNum]);

        this.insectsPositions = new ArrayList<>();
        this.boxPositions = new ArrayList<>();
        this.doorPositions = new ArrayList<>();
        this.healthPositions = new ArrayList<>();
        this.entityManager = new EntityManager();

        this.insectsPositions.addAll(
                FileLoader.loadStartPositionsEntities(INSECTS_POSITIONS[levelNum], Game.TILE_WIDTH, Game.TILE_HEIGHT));
        this.boxPositions.addAll(
                FileLoader.loadStartPositionsEntities(BOX_POSITIONS[levelNum], Game.TILE_WIDTH, Game.TILE_HEIGHT));
        this.doorPositions.addAll(
                FileLoader.loadStartPositionsEntities(DOOR_POSITIONS[levelNum], Game.TILE_WIDTH, Game.TILE_HEIGHT));
        this.healthPositions.addAll(
                FileLoader.loadStartPositionsEntities(HEALTH_POSITIONS[levelNum], Game.TILE_WIDTH, Game.TILE_HEIGHT));

        this.createInsect();
        this.createBox();
        this.createHealth();

    }

    /**
     * Generates new insects.
     */
    public void createInsect() {
        for (int i = 0; i < this.insectsPositions.size(); i++) {
            this.entityManager.addEntity(new InsectsImpl(this.insectsPositions.get(i), this.model));
        }
    }

    /**
     * Generates new boxes.
     */
    public void createBox() {
        for (int i = 0; i < this.boxPositions.size(); i++) {
            this.entityManager.addEntity(new Block(this.boxPositions.get(i)));
        }
    }

    /**
     * Generates new health points.
     */
    public void createHealth() {
        for (int i = 0; i < this.healthPositions.size(); i++) {
            this.entityManager.addEntity(new HealthPoint(this.healthPositions.get(i)));
        }
    }

    /**
     * @return A 2D matrix of integer representing the gameMap.
     */
    public int[][] getGameMap() {
        final int[][] defensiveCopy = new int[this.gameMap.length][this.gameMap[0].length];
        for (int i = 0; i < this.gameMap.length; i++) {
            defensiveCopy[i] = this.gameMap[i].clone();
        }
        return defensiveCopy;
    }

    /**
     * @return EntityManager of this level.
     */
    public EntityManager getEntityManager() {
        return entityManager;
    }

    /**
     * @return Number of rows of this level.
     */
    public int getRows() {
        return gameMap.length;
    }

    /**
     * @return Number of columns of this level.
     */
    public int getCols() {
        if (this.getRows() > 0) {
            return gameMap[0].length;
        }
        return 0;
    }

    /**
     * @return Insect positions of this level (defensive copy).
     */
    public List<Point> getInsectsPositions() {
        return Collections.unmodifiableList(this.insectsPositions);
    }

    /**
     * @return Box positions of this level (defensive copy).
     */
    public List<Point> getBoxPositions() {
        return Collections.unmodifiableList(this.boxPositions);
    }

    /**
     * @return Door positions of this level (defensive copy).
     */
    public List<Point> getDoorPositions() {
        return Collections.unmodifiableList(this.doorPositions);
    }

    /**
     * @return Healt point positions of this level (defensive copy).
     */
    public List<Point> getHealthPositions() {
        return Collections.unmodifiableList(this.healthPositions);
    }

    /**
     * @return Number of insects in this level.
     */
    public int getNumOfInsects() {
        return (int) this.entityManager.getWorldEntities().stream().filter((e) -> e instanceof Insects).count();
    }

    /**
     * @param hitbox
     *            hitbox
     * @return Whether hitbox is colliding with wall tiles in this level.
     */
    public boolean intersectsWithWall(final Rectangle hitbox) {
        for (int tmpRow = 0; tmpRow < this.getRows(); tmpRow++) {
            for (int tmpCol = 0; tmpCol < this.getCols(); tmpCol++) {
                final Rectangle tmpRect = new Rectangle(tmpCol * Game.TILE_WIDTH, tmpRow * Game.TILE_HEIGHT,
                        Game.TILE_WIDTH, Game.TILE_HEIGHT);
                if (tmpRect.intersects(hitbox) && this.gameMap[tmpRow][tmpCol] == TileImg.WALL.getVal().getFirst()) {
                    return true;
                }
            }
        }
        return false;
    }
}
