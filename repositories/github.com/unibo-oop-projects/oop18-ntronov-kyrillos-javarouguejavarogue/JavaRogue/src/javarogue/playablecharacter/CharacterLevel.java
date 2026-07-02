package javarogue.playablecharacter;
/**
 * Interface that models the concept of level, that can grow up and, 
 * after adding a certain amount of experience, goes up.
 * It is also the Subject interface of an Observer Pattern. The observer is the class affected by the level.
 */
public interface CharacterLevel {

	/**
	 * Method that
	 * @return the object's level
	 */
	public int getLevel();
	/**
	 * Method that
	 * @return the amount of total experience earned
	 */
	public int getExperience();
	/**
	 * Method that permits to add a certain amount of experience
	 * @param toAdd the experience to be added to the total
	 */
	public void addExperience(int toAdd);
	/**
	 * Method that registers the character. It is the add method of the subject
	 * @param character the character.
	 */
	public void addCharacter(PlayableCharacter character);
	/**
	 * Method that tells the character to levelUp
	 */
	public void raiseLevel();
}
