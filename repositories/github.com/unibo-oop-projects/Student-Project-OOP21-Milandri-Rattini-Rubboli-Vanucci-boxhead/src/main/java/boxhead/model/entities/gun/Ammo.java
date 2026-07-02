package boxhead.model.entities.gun;

import boxhead.model.entities.AbstractEntity;
import boxhead.model.entities.EntityType;
import javafx.geometry.Point2D;

/**
 * Class to model a single Ammo Box entity, that when is picked up refills all your ammos.
 */
public class Ammo extends AbstractEntity{

	public Ammo(final Point2D position, final double width, final double height) {
		super(position, EntityType.AMMO);
		super.setBoundingBox(height, width);
	}
}
