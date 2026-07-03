package game.obstacles;

import java.awt.Rectangle;

import game.GameImpl;
import utilities.Pair;

/**
 * Class used to find collisions between obstacles and other instances of {@link game.Entity}.
 */
public class ObstacleHitBox extends Rectangle {

    private static final long serialVersionUID = 1L;
    private final int sideLength;
    /**
     * Provides a {@link AbstractObstacle}-specific hitBox.
     * @param position position of the {@link AbstractObstacle}.
     * @param sideLength length of the side.
     */
    public ObstacleHitBox(final Pair<java.lang.Integer, java.lang.Integer> position, final int sideLength) { //must specify java.lang package to distinguish Double from java.awt.geom.Rectangle2d.Double
        super(position.getX().intValue() - sideLength / 2, position.getY().intValue() - sideLength / 2, sideLength, sideLength);
        this.sideLength = sideLength;
    }
    /**
     * Updates the location of the hitBox.
     * @param position new position of the {@link game.Entity}
     */
    public void updateHitBox(final Pair<java.lang.Integer, java.lang.Integer> position) {
        this.setLocation(position.getX().intValue() - this.sideLength / 2, position.getY().intValue() - this.sideLength / 2);
    }

    /**
     * @return whether the HitBox is out of the gameArea. The limits are the ones specified in {@link game.GameImpl} 
     * : {@link game.GameImpl#GAMEAREA_WIDTH}, {@link game.GameImpl#GAMEAREA_HEIGHT}.
     */
    public boolean isOutOfGameArea() {
        final Rectangle gameArea = new Rectangle(0, 0, GameImpl.GAMEAREA_WIDTH, GameImpl.GAMEAREA_HEIGHT);
        return !gameArea.contains(this) && !gameArea.intersects(this);
    }
}
