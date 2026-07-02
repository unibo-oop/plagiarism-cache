package aboidsim.controller;

import org.junit.Assert;
import org.junit.Test;

import aboidsim.model.DefaultEnvironmentImpl;
import aboidsim.model.Model;
import aboidsim.model.ModelImpl;
import aboidsim.model.RuleImpl;
import aboidsim.util.Input;
import aboidsim.util.InputInfo;
import aboidsim.util.Vector;

/**
 * Testing class. In this class input operation will be tested.
 */
public class TestInputResolver {
	private final Model m = new ModelImpl();
	private final InputResolver inputResolver = i -> {
		if (i.getInput().equals(Input.CREATE_BOID)) {
			this.m.getSimulation().createBoid(i.getPosition(), i.getNumber().intValue());
		} else if (i.getInput().equals(Input.DESTROY_BOID)) {
			this.m.getSimulation().destroyBoid(i.getPosition());
		} else if (i.getInput().equals(Input.TOGGLE_RULE)) {
			this.m.getSimulation().toggleRule(i.getNumber().intValue());
		} else if (i.getInput().equals(Input.LOAD_ENV)) {
			this.m.getSimulation().loadDefaultEnvironment(i.getNumber().intValue());
		}
	};

	/**
	 * Test on CREATE input.
	 */
	@Test
	public void testOnBoidCreation() {
		this.m.getSimulation().getEnvironment().clear();
		Assert.assertTrue(this.m.getSimulation().getEnvironment().isEmpty());
		try {
			final InputInfo wrongInput = new InputInfo(Input.CREATE_BOID);
			this.inputResolver.resolveInput(wrongInput);
		} catch (final IllegalArgumentException ex) {
			Assert.assertTrue("Wrong arguments for that constructor", true);
		}
		final InputInfo rightInput = new InputInfo(Input.CREATE_BOID, 5, new Vector(200, 200));
		this.inputResolver.resolveInput(rightInput);
		System.out.println("A boid has been created");
		Assert.assertTrue("Size of the environment is 1", this.m.getSimulation().getEnvironment().size() == 1);
	}

	/**
	 * Test on DESTROY input.
	 */
	@Test
	public void testOnBoidDestruction() {
		final Vector pos = new Vector(200, 200);
		this.m.getSimulation().getEnvironment().clear();
		this.m.getSimulation().createBoid(pos, 1);
		Assert.assertEquals(this.m.getSimulation().getEnvironment().size(), 1);
		try {
			final InputInfo wrongInput = new InputInfo(Input.DESTROY_BOID);
			this.inputResolver.resolveInput(wrongInput);
		} catch (final IllegalArgumentException ex) {
			Assert.assertTrue("Wrong arguments for that constructor", true);
		}
		final InputInfo rightInput = new InputInfo(Input.DESTROY_BOID, pos);
		this.inputResolver.resolveInput(rightInput);
		System.out.println("The boid has been destroyed");
		Assert.assertTrue("The environment is now empty", this.m.getSimulation().getEnvironment().isEmpty());
	}

	/**
	 * Test on TOGGLE input.
	 */
	@Test
	public void testOnToggleRule() {
		Assert.assertEquals(this.m.getSimulation().getActiveRuleSet().getRules().size(), 4);
		try {
			final InputInfo wrongInput = new InputInfo(Input.TOGGLE_RULE);
			this.inputResolver.resolveInput(wrongInput);
		} catch (final IllegalArgumentException ex) {
			Assert.assertTrue("Wrong arguments for that constructor", true);
		}
		final InputInfo rightInput = new InputInfo(Input.TOGGLE_RULE, RuleImpl.COHESION.getID().intValue());
		this.inputResolver.resolveInput(rightInput);
		System.out.println("COHESION rule removed");
		Assert.assertFalse(this.m.getSimulation().getActiveRuleSet().getRules().contains(RuleImpl.COHESION));
		System.out.println("Now we add that rule again");
		this.inputResolver.resolveInput(rightInput);
		System.out.println("COHESION rule added");
		Assert.assertTrue(this.m.getSimulation().getActiveRuleSet().getRules().contains(RuleImpl.COHESION));
		Assert.assertEquals(this.m.getSimulation().getActiveRuleSet().getRules().size(), 4);

	}

	/**
	 * Test on LOAD_ENV input.
	 */
	@Test
	public void testOnLoadEnvironment() {
		this.m.getSimulation().getEnvironment().clear();
		Assert.assertTrue(this.m.getSimulation().getEnvironment().isEmpty());
		System.out.println("We load an environment");
		try {
			final InputInfo wrongInput = new InputInfo(Input.LOAD_ENV, new Vector(200, 200));
			this.inputResolver.resolveInput(wrongInput);
		} catch (final IllegalArgumentException ex) {
			Assert.assertTrue("Wrong arguments for that constructor", true);
		}
		final InputInfo rightInput = new InputInfo(Input.LOAD_ENV, DefaultEnvironmentImpl.JURASSIC_WORLD.getIdEnv());
		this.inputResolver.resolveInput(rightInput);
		System.out.println("JURASSIC WORLD environment loaded");
		Assert.assertEquals(this.m.getSimulation().getEnvironment().size(),
				DefaultEnvironmentImpl.JURASSIC_WORLD.getDefaultEnvironment().size());
	}
}
