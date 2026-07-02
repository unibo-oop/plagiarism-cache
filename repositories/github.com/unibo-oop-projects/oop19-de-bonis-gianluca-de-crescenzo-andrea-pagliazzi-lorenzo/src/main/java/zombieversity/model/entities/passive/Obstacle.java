package zombieversity.model.entities.passive;

import javafx.geometry.Point2D;
import zombieversity.model.entities.EntityType;

public class Obstacle extends PassiveEntity {

    public Obstacle(final Point2D pos, final double w, final double h) {
        super(pos, EntityType.OBSTACLE);
        super.setBBox(w, h);
    }
}
