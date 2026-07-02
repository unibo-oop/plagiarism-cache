package javarogue.behaviourmodules;
/**
 * Interface that allows a character (playable or enemy) to be damaged by opposing attacks and to damage opposing characters
 */
public interface Damage {
	/**
	 * Method that calculates the damage dealt by an attack.
	 * @param attackStat is the attack statistic of the offending character. May be influenced 
	 * 					 by skills or modifiers. The responsibility of giving the correct value
	 * 					 is on the user.
	 * @param DefenseStat is the defense statistic of the defending character. May be influenced 
	 * 					  by skills or modifiers. The responsibility of giving the correct value
	 * 					  is on the user.
	 * @return the damage given.
	 */
	public int getDamage(int attackStat, int DefenseStat);
}