package it.tbt.model.world.collision;

import it.tbt.model.entities.SpatialEntity;

import java.util.List;

/**
 * CollisionDetector simple implementation, checks if the rectangles made by the spatial entities overlap.
 */

public class CollisionDetectorImpl implements CollisionDetector {

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean checkCollision(final SpatialEntity sp1, final SpatialEntity sp2) {
        final var first = this.getVertices(sp1);
        final var second = this.getVertices(sp2);
        return !(first.get(2) < second.get(0)
                ||
                second.get(2) < first.get(0)
                ||
                first.get(3) < second.get(1)
                ||
                second.get(3) < first.get(1));
    }

    /**
     * @param sp
     * @return a LinkedList of size 4 where the first element is the x coordinate of the left upper vertex,
     * the second is the y coordinate of the left upper vertex,
     * the third is the x coordinate of the right inferior vertex,
     * the forth is the y coordinate of the right inferior vertex.
     */
    private List<Integer> getVertices(final SpatialEntity sp) {
        return List.of(
                sp.getX() - (sp.getWidth() / 2),
                sp.getY() - (sp.getHeight() / 2),
                sp.getX() + (sp.getWidth() / 2),
                sp.getY() + (sp.getHeight() / 2)
        );
    }
}
