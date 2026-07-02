package spacesurvival.model.collision.eventgenerator;

import java.util.Optional;

import spacesurvival.model.gameobject.GameObject;
import spacesurvival.model.gameobject.main.Asteroid;
import spacesurvival.model.World;
import spacesurvival.model.collision.bounding.BoundaryCollision;
import spacesurvival.model.collision.bounding.RectBoundingBox;
import spacesurvival.model.collision.event.hit.HitBorderEvent;

public class AsteroidComponent implements EventComponent {

    /**
     * Update the physics of the asteroid.
     * 
     * @param abstractObj the asteroid
     * @param w represent the current world
     */
    @Override
    public void update(final GameObject abstractObj, final World w) {
        final Asteroid asteroid = (Asteroid) abstractObj;
        final RectBoundingBox boundingBox = w.getMainBBox();

        final Optional<BoundaryCollision> borderInfo = w.getCollisionController().checkWithBoundaries(asteroid.getPosition(), boundingBox);
        if (borderInfo.isPresent()) {
            final BoundaryCollision info = borderInfo.get();
            w.notifyWorldEvent(new HitBorderEvent(info.getWhere(), info.getEdge(), asteroid));
        }
    }
}
