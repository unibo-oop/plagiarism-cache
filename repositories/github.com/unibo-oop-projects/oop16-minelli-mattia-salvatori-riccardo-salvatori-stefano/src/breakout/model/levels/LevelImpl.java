package breakout.model.levels;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import breakout.model.AdvancedFactory;
import breakout.model.entities.Brick;
import breakout.model.entities.BrickType;
import breakout.model.entities.Wall;
import breakout.model.entities.Wall.WallPos;
import breakout.view.graphics.Backgrounds;
import breakout.view.graphics.Colors;
import breakout.view.graphics.Images;
import javafx.scene.image.Image;
import javafx.util.Pair;

/**
 * Implementation of a decorated level.
 *
 */
public final class LevelImpl implements DecoratedLevel, Serializable {

    private static final long serialVersionUID = -4909904128628244456L;
    private String name;
    private transient Image image;
    private int spawn;
    private Backgrounds imageName;
    private static final int BRICK_PER_ROW = 20;
    private static final int ROWS = 12;
    private Grid<Pair<BrickType, Colors>> listaBrick = new Grid<>(ROWS, BRICK_PER_ROW);
    private transient Map<Wall, Colors> walls = new HashMap<>();

    /**
     * The constructor of level editor, set the wall in the scene.
     */
    public LevelImpl() {
        this.walls = new HashMap<>();
        this.addWall(AdvancedFactory.get().createWall(WallPos.UP), Colors.CLASSICGREY);
        this.addWall(AdvancedFactory.get().createWall(WallPos.LEFT), Colors.CLASSICGREY);
        this.addWall(AdvancedFactory.get().createWall(WallPos.RIGHT), Colors.CLASSICGREY);
    }

    /**
     * 
     * @return the Grid of all bricks
     */
    public Grid<Pair<BrickType, Colors>> getGrid() {
        return this.listaBrick;
    }

    /**
     * Clear the current level and creates a new one with the list in inpu.
     * 
     * @param listaBrick
     *            the level you want to set
     */
    public void setList(final Grid<Pair<BrickType, Colors>> listaBrick) {
        this.listaBrick = listaBrick;
    }

    /**
     * Add a brick in the level.
     * 
     * @param position
     *            the position in the grid
     * @param b
     *            the brick type
     * @param c
     *            the color
     */
    public void addBrick(final Pair<Integer, Integer> position, final BrickType b, final Colors c) {
        this.listaBrick.add(position.getKey(), position.getValue(), new Pair<>(b, c));
    }

    /**
     * Set spawn probability of the level.
     * 
     * @param p
     *            the probability of a powerup spawning from a brick
     */
    public void setSpawn(final int p) {
        this.spawn = p;
    }

    /**
     * Set the background of the level.
     * 
     * @param image
     *            the background of the level
     */
    public void setBackground(final Backgrounds image) {
        this.imageName = image;
    }

    /**
     * @return the list of all bricks and their color
     */
    public List<Pair<Brick, Colors>> getBricksWithColors() {
        final List<Pair<Brick, Colors>> toReturn = new ArrayList<>();
        this.listaBrick.getPresent().forEach((position, brick) -> toReturn
                .add(new Pair<>(AdvancedFactory.get().createBrick(brick.getKey(), position), brick.getValue())));
        return toReturn;
    }

    /**
     * @return the list of all bricks
     */
    public List<Brick> getBricks() {
        final List<Brick> toReturn = new ArrayList<>();
        this.listaBrick.getPresent().forEach(
                (position, brick) -> toReturn.add(AdvancedFactory.get().createBrick(brick.getKey(), position)));
        return toReturn;
    }

    /**
     *@return the list of all walls
     */
    public List<Wall> getWalls() {
        if (this.walls == null) {
            this.walls = new HashMap<>();
            this.addWall(AdvancedFactory.get().createWall(WallPos.UP), Colors.CLASSICGREY);
            this.addWall(AdvancedFactory.get().createWall(WallPos.LEFT), Colors.CLASSICGREY);
            this.addWall(AdvancedFactory.get().createWall(WallPos.RIGHT), Colors.CLASSICGREY);
        }
        return new ArrayList<>(this.walls.keySet().stream().collect(Collectors.toList()));
    }

    /**
     * Add a wall to the level.
     * 
     * @param wall
     *            the wall to add
     * @param color
     *            the color of the wall
     */
    public void addWall(final Wall wall, final Colors color) {
        this.walls.put(wall, color);
    }

    /**
     *@return the list of all walls and their color
     */
    public List<Pair<Wall, Colors>> getWallsWithColors() {
        return this.walls.keySet().stream().map(wall -> new Pair<>(wall, this.walls.get(wall)))
                .collect(Collectors.toList());
    }

    /**
     * @return the Background of the background image
     */
    public Backgrounds getBackground() {
        return this.imageName;
    }

    /**
     * Set the name of the level.
     * 
     * @param name
     *            the name
     */
    public void setName(final String name) {
        this.name = name;

    }

    @Override
    public int getSpawnProb() {
        return this.spawn;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public Image getBackgroundImage() {
        if (this.image == null) {
            this.image = Images.getImages().getBackgroundImage(this.imageName);
        }
        return this.image;
    }

}
