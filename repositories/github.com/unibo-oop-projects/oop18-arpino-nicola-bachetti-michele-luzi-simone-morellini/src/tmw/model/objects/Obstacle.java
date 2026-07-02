package tmw.model.objects;

import tmw.common.Dim2D;
import tmw.common.P2d;

/**
 * This Class represents a normal obstacle in the game. It extends the Class
 * {@link BaseGameObject}.
 *
 */
public class Obstacle extends BaseGameObject {

    private static final double DIMENSION_PROPORTION_OBSTACLE = 0.04;

    /**
     * Construct an Obstacle in certain position and with a certain dimension that
     * depends by the resolution of the game.
     * 
     * @param pos       - the {@link P2d} which represents the position where the
     *                  obstacle is located
     * @param fieldSize - the size used to compute the dimension of the object
     */
    public Obstacle(final P2d pos, final Dim2D fieldSize) {
        super(pos, new Dim2D(DIMENSION_PROPORTION_OBSTACLE * fieldSize.getWidth(),
                DIMENSION_PROPORTION_OBSTACLE * fieldSize.getWidth()));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void resetDefaultDimension(final Dim2D dimension) {
        setDimension(new Dim2D(DIMENSION_PROPORTION_OBSTACLE * dimension.getWidth(),
                DIMENSION_PROPORTION_OBSTACLE * dimension.getWidth()));
    }
}
