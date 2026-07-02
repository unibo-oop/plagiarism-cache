package aboidsim.model;

import java.util.Set;

import aboidsim.util.Vector;

/**
 * Functional interface.
 *
 */
@FunctionalInterface
public interface Rule {

	/**
	 * This methods apply the rule to determine how a boid should move according
	 * to the rule.
	 *
	 * @param theBoid
	 *            the boid that will make a decision regarding its movement.
	 * @param closeBoids
	 *            the set containing all the nearest boids.
	 * @return the Vector that describes the boid's movement.
	 */
	Vector apply(final Boid theBoid, final Set<Boid> closeBoids);
}
