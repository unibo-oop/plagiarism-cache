package aboidsim.model;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

import aboidsim.util.Pair;
import aboidsim.util.Vector;

/**
 *
 * This class show all the components of the simulation environment. Singleton
 * pattern used.
 *
 */
public final class EnvironmentImpl implements Environment {

	private static final EnvironmentImpl ENVIRONMENT_IMPL = new EnvironmentImpl();
	private Set<Boid> environment = new HashSet<>();
	private static final double COLLISION_RADIUS = 30.0; // DEBUG
	private final RuleSet activeRuleSet = new RuleSet();
	// Dimension will change when the application starts.
	private static Pair<Integer, Integer> simulationDimension = new Pair<Integer, Integer>(0, 0);

	/**
	 * Constructor.
	 */
	EnvironmentImpl() {
	}

	static EnvironmentImpl getEnviromentImpl() {
		return EnvironmentImpl.ENVIRONMENT_IMPL;
	}

	@Override
	public void createBoid(final Vector pos, final int liv) {
		final Boid boid = new BoidImpl(pos, liv);
		this.environment.add(boid);
	}

	@Override
	public void destroyBoid(final Vector pos) {
		final Optional<Boid> b = this.environment.parallelStream()
				.filter(boid -> boid.getPosition().dist(pos) < EnvironmentImpl.COLLISION_RADIUS).findFirst();
		if (b.isPresent()) {
			this.environment.remove(b.get());
		}
	}

	@Override
	public void checkNearBoids() {
		this.environment.stream().forEach(boid -> {

			final Set<Boid> tempBoids = this.environment.parallelStream().filter(b -> !b.equals(boid))
					.filter(bo -> boid.getPosition().dist(bo.getPosition()) < boid.getInfluenceRadius())
					.collect(Collectors.toSet());

			boid.getSameLevelNearBoids().clear();

			if (boid.isNotTree()) {
				boid.getSameLevelNearBoids()
						.addAll(tempBoids.parallelStream().filter(b -> boid.getLevel() == b.getLevel())
								.filter(b -> b.getSameLevelNearBoids().size() < b.getMaxMembers())
								.limit(boid.getMaxMembers()).collect(Collectors.toSet()));
			}

			boid.getOtherLevelNearBoids().clear();
			boid.getOtherLevelNearBoids().addAll(tempBoids.parallelStream().filter(b -> boid.getLevel() != b.getLevel())
					.collect(Collectors.toSet()));
		});
	}

	@Override
	public void toggleRule(final int ruleId) {
		final RuleImpl rule = Arrays.stream(RuleImpl.values()).filter(r -> r.getID() == ruleId).findFirst().get();
		if (this.activeRuleSet.getRules().contains(rule)) {
			this.activeRuleSet.removeRule(rule);
		} else {
			this.activeRuleSet.addRule(rule);
		}
	}

	@Override
	public RuleSet getActiveRuleSet() {
		return this.activeRuleSet;
	}

	@Override
	public void setScreenDimension(final Pair<Integer, Integer> dimension) {
		EnvironmentImpl.simulationDimension.setX(dimension.getX());
		EnvironmentImpl.simulationDimension.setY(dimension.getY());
	}

	@Override
	public Set<Pair<Pair<Vector, Double>, Integer>> getSimulationComponents() {
		return this.environment.parallelStream()
				.map(boid -> new Pair<>(new Pair<>(boid.getPosition(), boid.getRotationAngle()), boid.getLevel()))
				.collect(Collectors.toSet());
	}

	@Override
	public Set<Boid> getEnvironment() {
		return this.environment;
	}

	@Override
	public void loadDefaultEnvironment(final int idEnv) {
		this.environment = Arrays.stream(DefaultEnvironmentImpl.values()).filter(env -> env.getIdEnv() == idEnv)
				.findFirst().get().getDefaultEnvironment();
	}

	/**
	 * This method is called once every cycle. It updates the position of every
	 * boid according to the rules.
	 */
	@Override
	public void updateEnvironment() {
		// The flock checking is done in the loop
		/*
		 * This set will prevent any ConcurrentModificationException
		 */
		final Set<Boid> toRemove = new HashSet<>();
		for (final Boid boid : this.environment) {
			final Vector sumVector = new Vector(0.0, 0.0);
			if (boid.isNotTree()) {
				boid.decrementLife(); // Life is decremented here
			}
			if (boid.getLife() <= 0) {
				/*
				 * If the boid is dead, we remove it from the simulation
				 */
				toRemove.add(boid);
			}

			final Set<Boid> closeOtherLevelBoids = boid.getOtherLevelNearBoids();
			Set<Boid> closePredators;
			/*
			 * We have to find the boid possible predators. If the boid is a
			 * tree, its predators are those boids whose level is between 1 and
			 * 5. If the boid is not a tree, its predators are those boids whose
			 * level is bigger than 5 and bigger than the boid's level.
			 */
			if (boid.isNotTree()) {
				closePredators = closeOtherLevelBoids.stream().filter(pred -> pred.isHungry())
						.filter(pred -> (pred.isPredator() && (boid.getLevel() < pred.getLevel())))
						.collect(Collectors.toSet());
			} else {
				closePredators = closeOtherLevelBoids.stream().filter(pred -> pred.isHungry())
						.filter(pred -> pred.getLevel() >= Entities.HERBIVORE_L1.getId()
								&& pred.getLevel() <= Entities.HERBIVORE_L5.getId())
						.collect(Collectors.toSet());
			}
			for (final Boid pred : closePredators) {
				if (boid.isCollidingWith(pred)) {
					boid.decrementLifeWhenEaten();
					pred.incrementLife();
				}
			}
			/*
			 * If the boid is a Tree Boid, there is nothing left to do
			 */
			if (boid.isNotTree()) {
				// If the boid is still alive
				final Set<Boid> closeSameLevelBoids = boid.getSameLevelNearBoids();
				if (!closePredators.isEmpty() && this.activeRuleSet.getRules().contains(RuleImpl.EVASION)) {
					// Safety has the bigger priority
					sumVector.add(RuleImpl.EVASION.apply(boid, closePredators));
				} else {
					// The boid seeks a target to eat
					if (!closeOtherLevelBoids.isEmpty() && closePredators.isEmpty() && boid.isHungry()) {
						// If there are no predators around
						Optional<Boid> prey = Optional.empty();
						if (boid.isPredator()) {
							/*
							 * The predator boid will look for any lower level
							 * (except the tree) in his radius. HE MAY WANT TO
							 * CHANGE TARGET
							 */
							prey = closeOtherLevelBoids.stream().filter(b -> b.isNotTree())
									.filter(b -> b.getLevel() < boid.getLevel()).findFirst();
						} else {
							// This boid is an herbivore
							prey = closeOtherLevelBoids.stream().filter(b -> !b.isNotTree()).findFirst();
						}
						if (prey.isPresent()) {
							/*
							 * If there is an available prey, we want the boid
							 * to approach it
							 */
							final Vector desiredDirection = Vector.sub(prey.get().getPosition(), boid.getPosition());
							desiredDirection.norm();
							desiredDirection.mul(boid.getMaxSpeed());
							final double distance = boid.getPosition().dist(prey.get().getPosition());
							desiredDirection.limitTo(distance);
							/*
							 * We want the boid to steer towards the target
							 */
							final Vector steer = Vector.sub(desiredDirection, boid.getVelocity());
							steer.limitTo(BoidImpl.MAX_FORCE);
							sumVector.add(steer);
						}
					} else {
						/*
						 * If there are some same level boids around and the
						 * boid is not seeking food
						 */
						if (!closeSameLevelBoids.isEmpty() && !this.activeRuleSet.getRules().isEmpty()) {
							if (this.activeRuleSet.getRules().contains(RuleImpl.ALIGNMENT)) {
								sumVector.add(RuleImpl.ALIGNMENT.apply(boid, closeSameLevelBoids));
							}
							if (this.activeRuleSet.getRules().contains(RuleImpl.COHESION)) {
								sumVector.add(RuleImpl.COHESION.apply(boid, closeSameLevelBoids));
							}
							if (this.activeRuleSet.getRules().contains(RuleImpl.SEPARATION)) {
								sumVector.add(RuleImpl.SEPARATION.apply(boid, closeSameLevelBoids));
							}
						} else {
							/*
							 * Wander. This movement is described as a random
							 * yet believable movement. There are no rapid turns
							 * and it feels more "real"
							 */
							// We create a circle at the right distance
							final Vector circleOrigin = new Vector(boid.getVelocity().getX(), boid.getVelocity().getY());
							circleOrigin.norm();
							circleOrigin.scaleTo(BoidImpl.WANDER_CIRCLE_DISTANCE);
							// We create a normalized vector parallel to the
							// y-axis and we scale it to the circle radius
							final Vector vec = new Vector(0.0, 1.0);
							vec.scaleTo(BoidImpl.WANDER_CIRCLE_RADIUS);
							// We set a random angle
							final Random rng = new Random();
							// The angle is already in radians
							final double angle = rng.doubles(0, Math.PI * 2).findAny().getAsDouble();
							vec.setY(Math.sin(angle) * vec.magnitude());
							vec.setX(Math.cos(angle) * vec.magnitude());
							/*
							 * We add the modified vector and we steer towards
							 * it
							 */
							circleOrigin.add(vec);
							circleOrigin.add(boid.getPosition());
							final Vector desiredDirection = Vector.sub(circleOrigin, boid.getPosition());
							desiredDirection.norm();
							desiredDirection.mul(boid.getMaxSpeed());
							final Vector steer = Vector.sub(desiredDirection, boid.getVelocity());
							steer.limitTo(BoidImpl.MAX_FORCE);
							sumVector.add(steer);
						}
					}
				}

				// sumVector.mul(boid.getAverageSpeed());
				// We add the combining movements to the boid position
				boid.getAcceleration().add(sumVector);
				boid.getVelocity().add(boid.getAcceleration());
				boid.getVelocity().limitTo(boid.getMaxSpeed());
				boid.getPosition().add(boid.getVelocity());
				// Acceleration must be reset to 0
				boid.getAcceleration().mul(0.0);
				this.checkBorders(boid);
			}
		}
		// Dead boids will be removed
		this.environment.removeAll(toRemove);
	}

	/**
	 * This method fixes the position of a boid which has gone out of borders.
	 *
	 * @param boid
	 */
	private void checkBorders(final Boid boid) {
		if (boid.getPosition().getX() <= 0) {
			boid.getPosition().setX(EnvironmentImpl.simulationDimension.getX());
		} else if (boid.getPosition().getX() >= EnvironmentImpl.simulationDimension.getX()) {
			boid.getPosition().setX(0.0);
		}
		if (boid.getPosition().getY() <= 0) {
			boid.getPosition().setY(EnvironmentImpl.simulationDimension.getY());
		} else if (boid.getPosition().getY() >= EnvironmentImpl.simulationDimension.getY()) {
			boid.getPosition().setY(0.0);
		}
	}

	/**
	 *
	 * @return Collision radius of the environment.
	 */
	static double getCollisionRadius() {
		return EnvironmentImpl.COLLISION_RADIUS;
	}

	/**
	 *
	 * @return simulation dimension.
	 */
	static Pair<Integer, Integer> getSimulationDimension() {
		return EnvironmentImpl.simulationDimension;
	}
}
