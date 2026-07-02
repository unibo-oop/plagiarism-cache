package aboidsim.model;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import aboidsim.util.Pair;

/**
 *
 * Simulation class. It contains all the environment.
 *
 */

public class ModelImpl implements Model {

	private final Environment simulation = EnvironmentImpl.getEnviromentImpl();

	@Override
	public Environment getSimulation() {
		return this.simulation;
	}

	@Override
	public List<String> getEntitiesNames() {
		return Arrays.stream(Entities.values()).map(entity -> entity.getName()).collect(Collectors.toList());
	}

	@Override
	public List<String> getRulesNames() {
		return Arrays.stream(RuleImpl.values()).map(r -> r.getName()).collect(Collectors.toList());
	}

	@Override
	public List<String> getAllEnvironmentsNames() {
		return Arrays.stream(DefaultEnvironmentImpl.values()).map(e -> e.getEnvironmentName()).collect(Collectors.toList());
	}

	@Override
	public List<Pair<Integer, String>> getLevelAndImages() {
		return Arrays.stream(Entities.values()).map(entity -> new Pair<>(entity.getId(), entity.getImage()))
				.collect(Collectors.toList());
	}
}
