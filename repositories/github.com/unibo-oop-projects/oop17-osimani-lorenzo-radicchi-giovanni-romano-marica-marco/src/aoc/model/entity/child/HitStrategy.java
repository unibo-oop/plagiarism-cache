package aoc.model.entity.child;

import java.util.List;
import aoc.model.entity.EntityInterface;

/**
 * This interface describes a generic child, which are the enemies the player must defeat.
 * The child should recognize himself when he is hit.
 */
@FunctionalInterface
public interface HitStrategy {

    /**
     * This method checks if any of the projectiles intercepts this child.
     * 
     * @return a List, which contains all the projectiles that hit this child.
     * 		   Is empty if none of the projectiles hits this child.
     * 
     * @param entitiesList
     *            The list of all the living entities.
     */
	List<EntityInterface> hit (List<EntityInterface> entitiesList, ChildInterface child);

}
