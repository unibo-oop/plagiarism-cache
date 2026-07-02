package aboidsim.model;


import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.stream.IntStream;

import aboidsim.util.Vector;

/**
 * This is an enumeration that shows all the available environments.
 * A user can set one of this environment.
 *
 */
public enum DefaultEnvironmentImpl implements DefaultEnvironment {
	
	/**
	 * Environment with just predator boids.
	 */
	JURASSIC_WORLD("JURASSIC WORLD", 0) {
		@Override
		public Set<Boid> getDefaultEnvironment() {
			final Set<Boid> environment = new HashSet<>();
			final Random r = new Random();
			final int rangeMaxX = EnvironmentImpl.getSimulationDimension().getX();
			final int rangeMaxY = EnvironmentImpl.getSimulationDimension().getY();

			IntStream.range(Entities.TREE_L0.getId(), Entities.HERBIVORE_L4.getId()).forEach(x -> {
				IntStream.range(Entities.PREDATOR_L6.getId(), Entities.PREDATOR_L10.getId() + 1).forEach(i -> {
					environment.add(new BoidImpl(new Vector(rangeMaxX * r.nextDouble(), rangeMaxY * r.nextDouble()), i));
				});
			});
			return environment;
		}
	},
	
	/**
	 * Environment with just herbivore. "Pacific" because herbivores don't eat each other.
	 */
	PACIFIC_WORLD("PACIFIC WORLD", 1)	{
		@Override
		public Set<Boid> getDefaultEnvironment() {
			final Set<Boid> environment = new HashSet<>();
			final Random r = new Random();
			final int rangeMaxX = EnvironmentImpl.getSimulationDimension().getX();
			final int rangeMaxY = EnvironmentImpl.getSimulationDimension().getY();

			IntStream.range(Entities.TREE_L0.getId(), Entities.HERBIVORE_L4.getId()).forEach(x -> {
				IntStream.range(Entities.HERBIVORE_L1.getId(), Entities.HERBIVORE_L5.getId() + 1).forEach(i -> {
					environment.add(new BoidImpl(new Vector(rangeMaxX * r.nextDouble(), rangeMaxY * r.nextDouble()), i));
				});
			});
			return environment;
		}
	}, 
	
	/**
	 * Environment with trees and herbivores.
	 */
	FOREST("FOREST", 2) {
		@Override
		public Set<Boid> getDefaultEnvironment() {
			final Set<Boid> environment = new HashSet<>();
			final Random r = new Random();
			final int rangeMaxX = EnvironmentImpl.getSimulationDimension().getX();
			final int rangeMaxY = EnvironmentImpl.getSimulationDimension().getY();
			final Vector center = new Vector(EnvironmentImpl.getSimulationDimension().getX() / 2, 
					EnvironmentImpl.getSimulationDimension().getY() / 2);
			
			IntStream.range(Entities.HERBIVORE_L1.getId(), Entities.HERBIVORE_L3.getId()).forEach(i-> {
				
				environment.add(new BoidImpl(new Vector(center.getX() + i * 100, center.getY()), Entities.TREE_L0.getId()));
				
				environment.add(new BoidImpl(new Vector(center.getX(), center.getY() + (i * 100)), Entities.TREE_L0.getId()));
				
				environment.add(new BoidImpl(new Vector(center.getX() - (i * 100), center.getY()), Entities.TREE_L0.getId()));
				
				environment.add(new BoidImpl(new Vector(center.getX(), center.getY() - (i * 100)), Entities.TREE_L0.getId()));
			});
			
			final Vector center1 = new Vector(center.getX() / 2, center.getY() / 2);
			environment.add(new BoidImpl(center1, Entities.TREE_L0.getId()));
			
			final Vector center2 = new Vector(center.getX() + (center.getX() / 2), center.getY() / 2);
			environment.add(new BoidImpl(center2, Entities.TREE_L0.getId()));
			
			final Vector center3 = new Vector(center.getX() / 2, center.getY() + (center.getY() / 2));
			environment.add(new BoidImpl(center3, Entities.TREE_L0.getId()));
			
			final Vector center4 = new Vector(center.getX() + (center.getX() / 2), center.getY() + (center.getY() / 2));
			environment.add(new BoidImpl(center4, Entities.TREE_L0.getId()));
			
			environment.add(new BoidImpl(center, Entities.TREE_L0.getId()));
			
			
			/******************** Insert Herbivore boids ***********************************/
			
			IntStream.range(Entities.TREE_L0.getId(), Entities.HERBIVORE_L3.getId()).forEach(x -> {
				IntStream.range(Entities.HERBIVORE_L1.getId(), Entities.HERBIVORE_L5.getId() + 1).forEach(i -> {
					environment.add(new BoidImpl(new Vector(rangeMaxX * r.nextDouble(), rangeMaxY * r.nextDouble()), i));
				});
			});
			return environment;
		}
	},
	
	/**
	 * Standard environment.
	 */
	STANDARD("STANDARD", 3) {
		@Override
		public Set<Boid> getDefaultEnvironment() {
			final Set<Boid> environment = new HashSet<>();
			final Random r = new Random();
			final int rangeMaxX = EnvironmentImpl.getSimulationDimension().getX();
			final int rangeMaxY = EnvironmentImpl.getSimulationDimension().getY();

			IntStream.range(Entities.TREE_L0.getId(), Entities.HERBIVORE_L2.getId()).forEach(x -> {
				IntStream.range(Entities.TREE_L0.getId(), Entities.PREDATOR_L10.getId() + 1).forEach(i -> {
					environment.add(new BoidImpl(new Vector(rangeMaxX * r.nextDouble(), rangeMaxY * r.nextDouble()), i));
				});
			});
			return environment;
		}
	};
	
	
	
	private final String environmentName;
	private final int idEnv;	
	
	/**
	 * Private constructor.
	 * @param envName
	 * @param id
	 */
	DefaultEnvironmentImpl(final String envName, final Integer id) {
		this.environmentName = envName;
		this.idEnv = id;
	}
	
	/**
	 * 
	 * @return Environment name
	 */
	public String getEnvironmentName() {
		return this.environmentName;
	}
	
	/**
	 * 
	 * @return Environment ID
	 */
	public int getIdEnv() {
		return this.idEnv;
	}
}
