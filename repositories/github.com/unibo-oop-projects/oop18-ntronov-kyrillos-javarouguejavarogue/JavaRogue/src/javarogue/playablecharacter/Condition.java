package javarogue.playablecharacter;
/**
 * Enumeration for the possible conditions that can affect a character.
 * Each condition is made up by a name and a modifier.
 */
public enum Condition {
	HEALTHY("Healthy", 1),
	BRUISED("Bruised", 0.80),
	LIGHTLY_WOUNDED("Lightly Wounded", 0.65),
	WOUNDED("Wounded", 0.50),
	BADLY_WOUNDED("Badly Wounded", 0.35),
	SEVERELY_WOUNDED("Severely Wounded", 0.20),
	USELESS("Useless", 0);
	
	private final String conditionName;
	private final double conditionModifier;
	
	private Condition(String conditionName, double conditionModifier) {
		this.conditionName = conditionName;
		this.conditionModifier = conditionModifier;
	}
	/**
	 * Method that
	 * @return the name of the condition.
	 */
	public String getConditionName() {
		return this.conditionName;
	}
	/**
	 * Method that 
	 * @return the condition modifier.
	 */
	public double getConditionModifier() {
		return this.conditionModifier;
	}
	
	public String toString() {
		return "Current Condition is " +this.getConditionName();
	}
}
