package hollowmen.model.roomentity.hero;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import hollowmen.model.Modifier;
import hollowmen.model.Parameter;
import hollowmen.model.TargetPointSystem;
import hollowmen.model.dungeon.ModifierImpl;
import hollowmen.model.utils.Constants;
import hollowmen.model.utils.Counter;
import hollowmen.utilities.ExceptionThrower;
import hollowmen.utilities.Pair;
import hollowmen.utilities.RandomSelector;

/**
 * This class implements {@link TargetPointSystem}<br>
 * This class wants his target in constructor and they can't be changed
 * @author pigio
 *
 */
public class StatPointSystem implements TargetPointSystem<Parameter>{

	private int point;
	private Map<String, Pair<Parameter, Counter>> system = new HashMap<>();
	
	/**
	 * This constructor build and empty {@code TargetPointSystem} with the given <b>targets</b>
	 * @param targets
	 */
	public StatPointSystem(Collection<Parameter> targets) {
		targets.stream().forEach(p -> this.system.put(p.getInfo().getName(),
				new Pair<>(p, new Counter())));
	}
	
	/**
	 * This constructor build a {@code TargetPointSystem} with the given <b>targetWithPoints</b> and set
	 * his unspent point using <b>unspentPoint</b>
	 * @param targetsWithPoints
	 * @param unspentPoint
	 */
	public StatPointSystem(Collection<Pair<Parameter, Integer>> targetsWithPoints, int unspentPoint) {
		for(Pair<Parameter, Integer> p : targetsWithPoints) {
			this.system.put(p.getX().getInfo().getName(), new Pair<>(p.getX(), new Counter()));
			for(int i = 0; i < p.getY(); i++) {
				this.spendPointOn(p.getX());
			}
		}
		this.point = unspentPoint;
	}
	
	/**
	 * {@inheritDoc TargetPointSystem}
	 */
	@Override
	public Collection<Parameter> getTargets() {
		return this.system.entrySet().stream()
				.map(e -> e.getValue().getX())
				.collect(Collectors.toList());
	}

	/**
	 * {@inheritDoc TargetPointSystem}
	 */
	@Override
	public int getUnspentPoint() {
		return this.point;
	}

	/**
	 * {@inheritDoc TargetPointSystem}
	 */
	@Override
	public void addPoint(int pointToAdd) {
		this.point += pointToAdd;
		while(this.point > 0) {
			this.spendPointOn((Parameter) RandomSelector.getAnyFrom(this.getTargets().stream().toArray()));
		}
	}

	/**
	 * {@inheritDoc TargetPointSystem}
	 */
	@Override
	public void spendPointOn(Parameter target) throws IllegalStateException, IllegalArgumentException {
		ExceptionThrower.checkIllegalState(this.point, p -> p <= 0);
		ExceptionThrower.checkIllegalArgument(target, t -> !this.system.containsKey(keyGen(t)));
		this.point --;
		this.system.get(keyGen(target)).getX().addModifier(this.generateMod(target));
	}

	/**
	 * {@inheritDoc TargetPointSystem}
	 */
	@Override
	public void retrievePointFrom(Parameter target) throws IllegalArgumentException {
		ExceptionThrower.checkIllegalArgument(target, t -> !this.system.containsKey(keyGen(t)));
		ExceptionThrower.checkIllegalState(target, t -> this.system.get(keyGen(t)).getY().getCount() <= 0);
		this.point ++;
		this.system.get(keyGen(target)).getX().removeModifier(this.generateMod(target));
	}

	private Modifier generateMod(Parameter target) {
		return new ModifierImpl(keyGen(target), Constants.STAT_INCREASE, Constants.DEFAULT_OP);
	}
	
	private String keyGen(Parameter p) {
		return p.getInfo().getName();
	}
	
}
