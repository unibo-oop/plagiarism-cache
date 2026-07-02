package aoc.model.entity.child;

import java.util.List;
import aoc.model.WorldConstants;
import aoc.model.entity.EntityInterface;
import aoc.model.level.Level;
import aoc.utilities.Direction;
import aoc.utilities.Vector;

/**
 * This class implements a child factory.
 * It mainly creates a new child from the informations that receives from the caller.
 */
public class ChildFactory implements ChildFactoryInterface {
	
	public enum IsHitStrategy implements HitStrategy{
		
		NOTHING_STRATEGY ((l, c) -> {
		    return l;
		}),
		
		STUDIED_STRATEGY ((l, c) -> {
		    if (!l.isEmpty()) {
                        if (c.getSpeed().getY() == 0) {
                                // set initial direction
                                int modifier = c.getPosition().getY() <= ((WorldConstants.WORLD_HEIGHT * WorldConstants.CELL_WIDTH) / 2) ? Direction.DOWN.getDir() : Direction.UP.getDir();
                                c.setSpeed(c.getSpeed().increaseY(WorldConstants.SLOW_SPEED * modifier));
                        } else {
                                c.setSpeed(new Vector(c.getSpeed().getX(), c.getSpeed().getY() * (-1)));
                        }
                }
                return l;
		});
	    
	    HitStrategy strategy;
	    
	    private IsHitStrategy(final HitStrategy strategy) {
	        this.strategy = strategy;
	    }

        @Override
        public List<EntityInterface> hit(List<EntityInterface> entitiesList, final ChildInterface child) {
            return this.strategy.hit(entitiesList, child);
        }
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public EntityInterface spawnChild(final Children type, final Vector position, final Level level) {
		return new Child(position, type, level);
	}
}
