package it.unibo.oop.model;

import static it.unibo.oop.utilities.CharactersSettings.WALL;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import it.unibo.oop.utilities.CharactersSettings;
import it.unibo.oop.utilities.Position;

/**
 * Class representing a game arena bounded by a {@link List} of {@link Wall}
 * where the {@link Entity} can be placed.
 */
public class Arena {

    private static final int EMPTY_SPACES = 2;

    private final List<Wall> boundsList;
    private final Rectangle playableRectangle;

    private final List<Position> spawnPoints;

    /**
     * Constructor that creates an {@link Arena} made by {@link Wall} inside a
     * defined rectangular panel
     * 
     * @param panelHeight
     *            The height of the panel
     * @param panelWidth
     *            The width of the panel
     * @param hudDimension
     *            The dimension of the HUD of informations about the
     *            {@link MainCharacter}
     */
    public Arena(final int panelHeight, final int panelWidth, final int hudDimension) {
        // Settings the arena walls
        this.boundsList = new ArrayList<>();
        final int drawableAreaHeight = panelHeight - hudDimension;

        final int heightRest = drawableAreaHeight % WALL.getHeight();
        final int widthRest = panelWidth % WALL.getWidth();

        final int heightBlocks = drawableAreaHeight / WALL.getHeight();
        final int widthBlocks = panelWidth / WALL.getWidth();
        // Creation of externs walls
        for (int offsetX = EMPTY_SPACES; offsetX < widthBlocks - EMPTY_SPACES; offsetX++) {
            for (int offsetY = EMPTY_SPACES; offsetY < heightBlocks - EMPTY_SPACES; offsetY++) {
                if (offsetX == EMPTY_SPACES || offsetY == EMPTY_SPACES || offsetX == widthBlocks - EMPTY_SPACES - 1
                        || offsetY == heightBlocks - EMPTY_SPACES - 1) {
                    this.boundsList.add(new Wall(widthRest / 2 + offsetX * WALL.getWidth() + WALL.getWidth() / 2,
                            hudDimension + heightRest / 2 + offsetY * WALL.getHeight() + WALL.getHeight() / 2));
                }
            }
        }
        // Getting playable rectangle
        final Position topLeftCorner = this.boundsList.get(0).getPosition();
        this.playableRectangle = new Rectangle(topLeftCorner.getIntX() + (int) WALL.getWidth() / 2,
                topLeftCorner.getIntY() + (int) WALL.getHeight() / 2,
                (int) (widthBlocks - 2 * EMPTY_SPACES - 2) * WALL.getWidth(),
                (int) (heightBlocks - 2 * EMPTY_SPACES - 2) * WALL.getHeight());

        this.spawnPoints = new ArrayList<>();
    }

    /**
     * Returns the {@link List} of the bounding {@link Wall} entities
     * @return {@link List} of bounds
     */
    public List<Wall> getBoundsList() {
        return this.boundsList;
    }

    /**
     * Getter that returns a {@link Rectangle} of the playable place inside the
     * {@link List} of {@link Wall}
     * @return A {@link Rectangle} rapresentig the playable Area
     */
    public Rectangle getPlayableRectangle() {
        return this.playableRectangle;
    }

    /**
     * Return the possible spawn points (NOT used for now)
     * 
     */
    public List<Position> getSpawnPoints() {
        return this.spawnPoints;
    }

    /**
     * Add a spawn point to the possible spawn points {@link List} (NOT used for now)
     * 
     * @param spawnPosition
     *            The spawn point that will be added
     */
    public void addSpawnPoint(final Position spawnPosition) {
        this.spawnPoints.add(spawnPosition);
    }

    /**
     * Gets a random spawn point from the list (NOT used for now)
     * @return a random spawn {@link Position}
     */
    public Position getRandomSpawnPoint() {
        final Random tmpRandom = new Random(this.spawnPoints.size());
        return this.spawnPoints.get(tmpRandom.nextInt());
    }

    /**
     * @return Upper Y
     */
    public double getUpperY() {
        return this.playableRectangle.getY();
    }

    /**
     * @return Lower Y
     */
    public double getLowerY() {
        return this.playableRectangle.getY() + this.playableRectangle.getHeight();
    }

    /**
     * @return Left X
     */
    public double getLeftX() {
        return this.playableRectangle.getX();
    }

    /**
     * @return Right X
     */
    public double getRightX() {
        return this.playableRectangle.getX() + this.playableRectangle.getWidth();
    }

    /**
     * Gets a position inside the playable {@link Rectangle}
     * @param characterType the type of Character to spawn inside the area
     * @return a {@link Position} inside the area
     */
    public Position getPositionInside(final CharactersSettings characterType) {
        final double newX = this.playableRectangle.getX() + characterType.getWidth() / 2;
        final double newY = this.playableRectangle.getY() + characterType.getHeight() / 2;
        final double newWidth = this.playableRectangle.getWidth() - characterType.getWidth();
        final double newHeight = this.playableRectangle.getHeight() - characterType.getHeight();
        return new Position(newX + new Random().nextInt((int) newWidth), newY + new Random().nextInt((int) newHeight));
    }

    /**
     * Checks if the parameter is inside or outside the bounding {@link Wall}
     */
    public boolean isInside(final Entity entity) {

        return (this.playableRectangle.contains(entity.getBounds()));
    }
}
