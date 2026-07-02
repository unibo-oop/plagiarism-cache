package zombieversity.model.entities.passive;

import javafx.geometry.Point2D;
import zombieversity.model.entities.AbstractEntity;
import zombieversity.model.entities.EntityType;

public abstract class PassiveEntity extends AbstractEntity {

    public PassiveEntity(final Point2D p2d, final EntityType type) {
        super(p2d, type);
    }

    @Override
    public final double getWidth() {
        return super.getWidth();
    }

    @Override
    public final double getHeight() {
        return super.getHeight();
    }
}
