package spacesurvival.model.collision.eventgenerator;

import spacesurvival.model.gameobject.GameObject;
import spacesurvival.model.gameobject.fireable.FireEnemy;
import spacesurvival.model.gameobject.main.MainObject;

import java.util.Optional;
import spacesurvival.model.World;
import spacesurvival.model.collision.bounding.BoundaryCollision;
import spacesurvival.model.collision.bounding.RectBoundingBox;
import spacesurvival.model.collision.event.hit.HitBorderEvent;
import spacesurvival.model.collision.event.hit.HitMainObject;

public class FireEnemyComponent implements EventComponent {

    /**
     * Update the physics of the fire enemy.
     * 
     * @param abstractObj the fire enemy
     * @param w represent the current world
     */
    @Override
    public void update(final GameObject abstractObj, final World w) {
        final FireEnemy fireEnemy = (FireEnemy) abstractObj;
        final RectBoundingBox boundingBox = w.getMainBBox();
        final Optional<BoundaryCollision> borderInfo = w.getCollisionController().checkWithBoundaries(fireEnemy.getPosition(), boundingBox);

        if (borderInfo.isPresent()) {
            final BoundaryCollision info = borderInfo.get();
            w.notifyWorldEvent(new HitBorderEvent(info.getWhere(), info.getEdge(), fireEnemy));
        }

        final Optional<MainObject> asteroid = w.getCollisionController().checkWithAsteroids(w.getAsteroids(), (RectBoundingBox) fireEnemy.getBoundingBox());
        if (asteroid.isPresent()) {
            w.notifyWorldEvent(new HitMainObject(fireEnemy, asteroid.get()));
        }
    }

}
