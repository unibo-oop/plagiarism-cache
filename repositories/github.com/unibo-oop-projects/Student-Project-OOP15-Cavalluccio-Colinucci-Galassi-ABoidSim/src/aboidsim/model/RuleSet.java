package aboidsim.model;

import java.util.HashSet;
import java.util.Set;

import aboidsim.util.Vector;

/**
 * Container class that represent a set of Rule implementation.
 *
 */
public class RuleSet {

	private final Set<RuleImpl> rules;

	/**
	 * Basic constructor with no arguments.
	 */
	RuleSet() {
		this.rules = new HashSet<>();
		this.rules.add(RuleImpl.ALIGNMENT);
		this.rules.add(RuleImpl.COHESION);
		this.rules.add(RuleImpl.SEPARATION);
		this.rules.add(RuleImpl.EVASION);
	}

	/**
	 * Constructor witch sets the rules according the the argument.
	 *
	 * @param newRules
	 *            the rules to initialize the set with.
	 */
	RuleSet(final Set<RuleImpl> newRules) {
		this.rules = newRules;
	}

	/**
	 * Getter. This methods return the set of rules
	 *
	 * @return the set of rules
	 */
	public Set<RuleImpl> getRules() {
		return this.rules;
	}

	/**
	 * This method adds a rule.
	 *
	 * @param rule
	 *            the rule which will be added to the set
	 */
	public void addRule(final RuleImpl rule) {
		if (!this.rules.contains(rule)) {
			this.rules.add(rule);
		}
	}

	/**
	 * This method removes a rule.
	 *
	 * @param rule
	 *            the rule which will be removed from the set
	 */
	public void removeRule(final RuleImpl rule) {
		if (this.rules.contains(rule)) {
			this.rules.remove(rule);
		}
	}

	/**
	 * This method removes any rules, making the set empty.
	 *
	 */
	public void clearSet() {
		this.rules.clear();
	}

	/**
	 * This method applies any rule contained in the set
	 *
	 * @return a Vector that represent the movement decision made by a boid.
	 */
	Vector applyRules(final Boid theBoid, final Set<Boid> boids) {
		final Vector vect = new Vector(0.0, 0.0);
		if (boids.isEmpty()) {
			return vect;
		} else {
			for (final RuleImpl r : this.rules) {
				vect.add(r.apply(theBoid, boids));
			}
		}
		return vect;
	}
}
