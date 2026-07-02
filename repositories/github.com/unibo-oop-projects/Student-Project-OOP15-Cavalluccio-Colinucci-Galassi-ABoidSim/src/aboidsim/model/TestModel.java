package aboidsim.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import aboidsim.util.Vector;

/**
 * This is a testing class for the model.
 *
 */
public class TestModel {

	/**
	 * This method test the behavior of the environment according to create and destroy method in EnvironmentImpl.
	 */
	@org.junit.Test
	public void testEnvironment() {
		
		Environment environment = new EnvironmentImpl();
		Boid boid1 = new BoidImpl(new Vector(0, 0), 1);
		Boid boid2 = new BoidImpl(new Vector(100, 0), 10);
		Boid boid3 = new BoidImpl(new Vector(0, 100), 0);
		
		//Create and destroy
		assertEquals(environment.getEnvironment().size(), 0);
		environment.createBoid(boid1.getPosition(), boid1.getLevel());
		assertEquals(environment.getEnvironment().size(), 1);
		environment.createBoid(boid2.getPosition(), boid2.getLevel());
		assertEquals(environment.getEnvironment().size(), 2);
		environment.createBoid(boid3.getPosition(), boid3.getLevel());
		assertEquals(environment.getEnvironment().size(), 3);
		environment.destroyBoid(new Vector(0, 0));
		assertEquals(environment.getEnvironment().size(), 2);
		
		final Set<Integer> tempSet = environment.getSimulationComponents().stream()
				.map(boid -> boid.getY()).collect(Collectors.toSet());
		
		assertTrue(tempSet.contains(boid2.getLevel()));
		assertTrue(tempSet.contains(boid3.getLevel()));
		assertFalse(tempSet.contains(boid1.getLevel()));
	}
	
	/**
	 * This method test the behavior of some boids and their features showed in BoidImpl.
	 */
	
	@org.junit.Test
	public void testBoids() {
		
		Boid boid = new BoidImpl(new Vector(0, 0), 8);
		
		assertTrue(boid.isNotTree());
		assertTrue(boid.isPredator());
		
		Boid boid2 = new BoidImpl(new Vector(0, 0), 2);
		
		assertTrue(boid.isCollidingWith(boid2));
		boid2.decrementLifeWhenEaten();
		assertFalse(boid2.getLife() == Entities.HERBIVORE_L1.getLife());
		assertTrue(boid2.getLife() == Entities.HERBIVORE_L2.getLife() - 10);
		
		IntStream.range(0, 100).forEach(i -> boid2.decrementLife());
		IntStream.range(0, 10).forEach(i -> {
			boid.decrementLifeWhenEaten();
			boid.decrementLifeWhenEaten();
			boid.decrementLifeWhenEaten();
			boid.decrementLifeWhenEaten();
			boid.decrementLifeWhenEaten();
			boid.decrementLifeWhenEaten();
			boid.decrementLifeWhenEaten();
			boid.decrementLife();
		});
		
		assertTrue(boid.isHungry());
		assertFalse(boid2.isHungry());
		
		boid2.decrementLifeWhenEaten();
		boid2.decrementLifeWhenEaten();
		boid2.decrementLifeWhenEaten();
		boid2.decrementLifeWhenEaten();
		boid2.decrementLifeWhenEaten();
		
		assertTrue(boid2.isHungry());
	}
}
