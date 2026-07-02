package javarogue.playablecharacter;

import java.util.Map;

import javarogue.behaviourmodules.StatNames;

/**
 * Interface that models the concept of a body part
 */
public interface BodyPart {

	/**
	 * Method that
	 * @return the name of the body part
	 */
	String getName();
	
	/**
	 * Method that
	 * @return the current condition the body part is in
	 */
	Condition getCondition();
	
	/**
	 * Method that
	 * @return the currently equipped object
	 */
	//Optional<GameObject> getEquippedObject();
	
	/**
	 * Method that
	 * @return the current set of statistics
	 */
	Map<StatNames, Integer> getStatistics();
	
	/**
	 * Method that allows the possibility to equip an object
	 * @param toEquip the object to be equipped
	 */
	//void equipObject(GameObject toEquip);
	/**
	 * Method that allows the bodyPart to levelUp and raise its stats
	 */
	void levelUpPart();
}
