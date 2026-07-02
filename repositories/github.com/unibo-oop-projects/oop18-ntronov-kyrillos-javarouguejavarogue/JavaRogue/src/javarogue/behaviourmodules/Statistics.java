package javarogue.behaviourmodules;

import java.util.Map;
/**
 * interface that allows a character, enemy or playable, to have his statistics
 */
public interface Statistics {

	/**
	 * 
	 * @return the Map<StatNames, Integer> representing an object's statistics.
	 */
	public Map<StatNames, Integer> getStatistics();
}
