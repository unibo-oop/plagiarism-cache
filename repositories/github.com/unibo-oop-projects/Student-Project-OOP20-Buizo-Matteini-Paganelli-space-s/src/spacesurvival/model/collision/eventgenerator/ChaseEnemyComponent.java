package spacesurvival.model.collision.eventgenerator;

import spacesurvival.model.gameobject.GameObject;
import spacesurvival.model.gameobject.main.ChaseEnemy;
import spacesurvival.model.gameobject.main.MainObject;

import java.util.Optional;
import spacesurvival.model.World;
import spacesurvival.model.collision.bounding.BoundaryCollision;
import spacesurvival.model.collision.bounding.RectBoundingBox;
import spacesurvival.model.collision.event.hit.HitBorderEvent;
import spacesurvival.model.collision.event.hit.HitMainObject;

public class ChaseEnemyComponent implements EventComponent {

    /**
     * Update the physics of the chase enemy.
     * 
     * @param abstractObj the ship
     * @param w represent the current world
     */
    @Override
    public void update(final GameObject abstractObj, final World w) {
        final ChaseEnemy chaseEnemy = (ChaseEnemy) abstractObj;
        final RectBoundingBox boundingBox = w.getMainBBox();
        final Optional<BoundaryCollision> borderInfo = w.getCollisionController().checkWithBoundaries(chaseEnemy.getPosition(), boundingBox);

        if (borderInfo.isPresent()) {
            final BoundaryCollision info = borderInfo.get();
            w.notifyWorldEvent(new HitBorderEvent(info.getWhere(), info.getEdge(), chaseEnemy));
        }
        final Optional<MainObject> asteroid = w.getCollisionController().checkWithAsteroids(w.getAsteroids(), (RectBoundingBox) chaseEnemy.getBoundingBox());
        if (asteroid.isPresent()) {
            w.notifyWorldEvent(new HitMainObject(chaseEnemy, asteroid.get()));
        }

    }

}
