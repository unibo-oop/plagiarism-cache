package aoc.model.entity.child;

import java.util.List;
import java.util.stream.Collectors;
import aoc.model.WorldConstants;
import aoc.model.entity.EntityInterface;
import aoc.model.entity.WithLife;
import aoc.model.entity.slipper.SlipperInterface;

/**
 * This interface describes a generic child, which are the enemies the player must defeat.
 * The child should recognize himself when he is hit.
 */
public interface ChildInterface extends WithLife{

    /**
     * This method checks if any of the projectiles intercepts this child.
     * 
     * @return a List, which contains all the projectiles that hit this child.
     * 		   Is empty if none of the projectiles hits this child.
     * 
     * @param entitiesList
     *            The list of all the living entities.
     */
	List<EntityInterface> isHit (List<EntityInterface> entitiesList);
	
	default List<EntityInterface> hitterListChecker (List<EntityInterface> entitiesList) {
		return entitiesList.stream()
						   .filter(e -> e instanceof SlipperInterface)
						   .filter(e -> e.getPosition().getX() >= this.getPosition().getX() && 
										e.getPosition().getX() <= this.getPosition().getX() + WorldConstants.HIT_RANGE &&
										e.getPosition().getY() >= this.getPosition().getY() - WorldConstants.CHILD_HEIGHT &&
										e.getPosition().getY() <= this.getPosition().getY() + WorldConstants.CHILD_HEIGHT)
						   .collect(Collectors.toList());
	}
}
