package it.unibo.arkanoid.model;

import java.util.Arrays;

import it.unibo.arkanoid.subject.BrickType;
import it.unibo.arkanoid.subject.PowerUpType;

/**
 * Implementation of {@link LevelBuilder} interface.
 *
 */
public class LevelBuilderImpl implements LevelBuilder {

    private static final int GRIDWIDTH = 10;
    private static final int GRIDHEIGHT = 10;
    private static final double DEFAULT_WIDTH = 180;
    private static final double DEFAULT_HEIGHT = 120;
    private static final int SPAWN_POWERUP_PROBABILITY = 6;

    private double width;
    private double height;
    private PowerUpGenerator powerUpGenerator;

    private final BrickType[][] brickGrid;

    /**
     * 
     */
    public LevelBuilderImpl() {
        this.brickGrid = new BrickType[GRIDWIDTH][GRIDHEIGHT];
        for (int i = 0; i < GRIDWIDTH; i++) {
            for (int j = 0; j < GRIDHEIGHT; j++) {
                this.brickGrid[i][j] = BrickType.NO_BRICK;
            }
        }
        this.powerUpGenerator = new RandomPowerUpGenerator(Arrays.asList(PowerUpType.values()), SPAWN_POWERUP_PROBABILITY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getGridWidth() {
        return GRIDWIDTH;

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getGridHeight() {
        return GRIDHEIGHT;

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LevelBuilder addBrick(final int x, final int y, final BrickType brickType) {
        this.brickGrid[x][y] = brickType;
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LevelBuilder setWordSize(final double width, final double height) {
        this.width = width;
        this.height = height;
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LevelBuilder setPowerUpGenerator(final PowerUpGenerator powerUpGenerator) {
        this.powerUpGenerator = powerUpGenerator;
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Level build() {
        return new LevelImpl(this.width, this.height, this.brickGrid, this.powerUpGenerator);
    }

    /**
     * @return the defaultWidth
     */
    public static double getDefaultWidth() {
        return DEFAULT_WIDTH;
    }

    /**
     * @return the defaultHeight
     */
    public static double getDefaultHeight() {
        return DEFAULT_HEIGHT;
    }

}
