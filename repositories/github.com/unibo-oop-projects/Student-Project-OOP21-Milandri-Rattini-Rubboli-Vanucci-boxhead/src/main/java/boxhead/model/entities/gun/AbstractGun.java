package boxhead.model.entities.gun;

import boxhead.model.entities.EntityType;
import javafx.geometry.Point2D;
import boxhead.model.entities.AbstractEntity;

public abstract class AbstractGun extends AbstractEntity implements Gun{

	private static final EntityType ENTITY_TYPE = EntityType.GUN;
	
	public AbstractGun(Point2D position) {
		super(position, ENTITY_TYPE);
	}
}
