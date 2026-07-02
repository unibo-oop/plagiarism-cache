package aoc.model.entity.child;

import java.util.List;
import aoc.utilities.Vector;
import aoc.model.WorldConstants;
import aoc.model.entity.EntityInterface;
import aoc.model.entity.EntityWithLife;
import aoc.model.entity.child.ChildFactory.IsHitStrategy;
import aoc.model.entity.slipper.SlipperInterface;
import aoc.model.level.Level;

/**
 * Defines the base implementation for all children.
 * Specialized types of children will override his basic methods.
 */
public class Child extends EntityWithLife implements ChildInterface {
	
	private Level level;
	
	private IsHitStrategy hitStrategy;
	
	public Child(final Vector position, final Children type, final Level level) {
		super(position, new Vector(type.getXSpeed(), type.getYSpeed()), type.name(), type.getLife());
		this.level = level;
		this.hitStrategy = type.getIsHit();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<EntityInterface> isHit(List<EntityInterface> entitiesList) {
	    return this.hitStrategy.hit(this.hitterListChecker(entitiesList), this);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void update() {
		super.update();
		// check boundaries
		if(this.getPosition().getY() < WorldConstants.ROW_CENTERS.get(0) || 
		   this.getPosition().getY() > WorldConstants.ROW_CENTERS.get(WorldConstants.WORLD_HEIGHT - 1)) {
			this.setSpeed(new Vector(this.getSpeed().getX(), 0));
		}
		List<EntityInterface> collider = this.isHit(level.getEntityList());
		collider.forEach(x -> this.damaged(((SlipperInterface) x).hit()));
	}
}

