package javarogue.playablecharacter;

import javarogue.behaviourmodules.StatNames;

/**
 * Enumeration for the possible skills.
 * Each skill is made up by the statistic that is affected and his relative modifier (an int).
 */
public enum Skill {
	NONE(StatNames.ATTACK, 0);
	
	private final StatNames modifiedStat;
	private final int statModifier;
	
	private Skill(StatNames modifiedStat, int statModifier) {
		this.modifiedStat = modifiedStat;
		this.statModifier = statModifier;
	}
	/**
	 * Method that
	 * @return the stat affected by the skill.
	 */
	public StatNames getModifiedStat() {
		return this.modifiedStat;
	}
	/**
	 * Method that
	 * @return the stat modifier.
	 */
	public int getStatModifier() {
		return this.statModifier;
	}
}
