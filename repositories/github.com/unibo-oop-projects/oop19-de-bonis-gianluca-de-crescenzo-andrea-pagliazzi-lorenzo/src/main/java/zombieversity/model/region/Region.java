package zombieversity.model.region;

import java.util.Set;

import javafx.geometry.Point2D;
import zombieversity.model.entities.Entity;
import zombieversity.model.entities.passive.PassiveEntity;

public interface Region {

    void loadObstacles(Set<PassiveEntity> obstacles);

    void addObstacle(PassiveEntity o);
    void addEntity(Entity e);
    Set<Entity> outOfRegion();

    void computeCollision();

    boolean isInside(Point2D p);
    @Override
    String toString();
}
