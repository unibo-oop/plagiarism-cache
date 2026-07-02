package it.unibo.arkanoid.model;

import it.unibo.arkanoid.subject.BrickType;

/**
 * Builder of {@link Level}.
 * 
 */
public interface LevelBuilder {
    /**
     * 
     * @return gridWidth The grid's width of {@link Brick}.
     */
    int getGridWidth();

    /**
     * 
     * @return gridHeight The grid's height of {@link Brick}.
     */
    int getGridHeight();

    /**
     * Add the specify {@link BrickType} of {@link Brick} in the Grid in the specify
     * position.
     * 
     * @param x
     *            The x coordinate.
     * @param y
     *            The y coordinate.
     * @param brickType
     *            The specify type of Brick.
     * @return The LevelBuilder.
     */
    LevelBuilder addBrick(int x, int y, BrickType brickType);

    /**
     * Setter of the Width and Height of the game's world.
     * 
     * @param width
     *            The width to set.
     * @param height
     *            The height to set.
     * @return The LevelBuilder.
     */
    LevelBuilder setWordSize(double width, double height);

    /**
     * Setter of {@link PowerUpGenerator}.
     * 
     * @param powerUpGenerator
     *            The {@link PowerUpGenerator} to set.
     * @return The LevelBuilder.
     */
    LevelBuilder setPowerUpGenerator(PowerUpGenerator powerUpGenerator);

    /**
     * Build method of pattern Builder.
     * 
     * @return The {@link Level}.
     */
    Level build();

}
