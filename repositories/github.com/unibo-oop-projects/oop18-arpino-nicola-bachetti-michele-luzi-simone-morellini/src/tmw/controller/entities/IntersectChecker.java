package tmw.controller.entities;

import tmw.common.Dim2D;
import tmw.common.P2d;
import tmw.controller.world.WorldController;
import tmw.model.objects.BaseGameObject;

/**
 * This class represents an intersect checker. This object is used from the
 * entity who needs to move to check if the position they wish to occupied is
 * out of bounds or is blocked by an obstacle.
 */
public class IntersectChecker extends BaseGameObject {

    /**
     * Construct a new intersect checker.
     * 
     * @param pos       - the initial position of this object as a {@link P2d}
     * @param dimension - the dimension of this object as a {@link Dimension2D}
     */
    protected IntersectChecker(final P2d pos, final Dim2D dimension) {
        super(pos, dimension);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void resetDefaultDimension(final Dim2D dimension) {
        setDimension(new Dim2D(dimension.getWidth(), dimension.getHeight()));
    }

    /**
     * This method is used to check if the new position where the entity would like
     * to move is in the world area and is clear from the obstacles.
     * 
     * @param newPosition     - the position to check
     * @param worldController - used to get the information to check the new
     *                        position
     * @return true if the position can be occupied, false otherwise
     */
    public boolean isPositionClear(final P2d newPosition, final WorldController worldController) {
        if ((newPosition.getX() + this.getBoundary().getWidth()) > worldController.getGameWorld().getWorldArea()
                .getWidth()
                || (newPosition.getY() + this.getBoundary().getHeight()) > worldController.getGameWorld().getWorldArea()
                        .getHeight()
                || newPosition.getX() < 0 || newPosition.getY() < 0) {
            return false;
        }

        this.setPos(newPosition);

        for (final BaseGameObject obs : worldController.getObstacleLoaded()) {
            if (this.intersect(obs)) {
                return false;
            }
        }

        return true;
    }

}
