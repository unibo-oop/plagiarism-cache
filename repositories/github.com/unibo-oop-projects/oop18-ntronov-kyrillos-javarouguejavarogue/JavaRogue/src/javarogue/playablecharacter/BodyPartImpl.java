package javarogue.playablecharacter;
import java.util.Collections;
import java.util.HashMap;
/**
 * Class that implements the interface BodyPart
 */
import java.util.Map;

import javarogue.behaviourmodules.StatNames;

public class BodyPartImpl implements BodyPart{

	private final String name;
	private final Condition condition;
	private final Map<StatNames, Integer> statistics;
	private final Map<StatNames, Integer> levelUpStats;
	//private Optional<GameObject> equippedObject
	
	public BodyPartImpl(String name, Map<StatNames, Integer> baseStats) {	
		this.name = name;
		this.condition = Condition.HEALTHY;
		this.statistics = baseStats;
		this.levelUpStats = new HashMap<>();
		fillLevelUpStatsMap();
	}
	/**
	 * Fills the level up map.
	 */
	private void fillLevelUpStatsMap() {
		this.levelUpStats.put(StatNames.HP, 2);
		this.levelUpStats.put(StatNames.ATTACK, 1);
		this.levelUpStats.put(StatNames.DEFENSE, 1);
		
	}
	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public Condition getCondition() {
		return this.condition;
	}

	@Override
	public Map<StatNames, Integer> getStatistics() {
		return Collections.unmodifiableMap(this.statistics);
	}
	@Override
	public void levelUpPart() {
		levelUpStats.forEach((key, value) -> this.statistics.merge(key, value, Integer::sum));
	}

}
