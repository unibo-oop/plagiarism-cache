package zombieversity.model.entities.passive;

import javafx.geometry.Point2D;
import zombieversity.model.entities.EntityType;

public class PlayerSpawn extends PassiveEntity {

    public PlayerSpawn(final Point2D p2d, final double w, final double h) {
        super(p2d, EntityType.OBSTACLE);
        super.setBBox(w, h);
    }
}
