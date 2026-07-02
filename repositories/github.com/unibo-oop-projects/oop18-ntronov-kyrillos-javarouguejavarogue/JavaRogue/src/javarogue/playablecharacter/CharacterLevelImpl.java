package javarogue.playablecharacter;

import java.util.List;

/**
 *  Class that implements the interface Level
 */
public class CharacterLevelImpl implements CharacterLevel {	
	
	private static final int[] EXPERIENCE_TO_LEVEL_UP = {10, 25, 50, 100, 200, 400, 800};
	private int currentLevel;
	private int totalExperience;
	private int currentExperience;
	private final int levelCap = EXPERIENCE_TO_LEVEL_UP.length;
	private PlayableCharacter character;
	private List<BodyPart> characterBodyParts;
	
	public CharacterLevelImpl() {
		this.currentLevel = 1;
		this.totalExperience = 0;
		this.currentExperience = 0;
	}

	@Override
	public int getLevel() {
		return this.currentLevel;
	}

	@Override
	public int getExperience() {
		return this.currentExperience;
	}

	@Override
	public void addExperience(int toAdd) {
		this.totalExperience += toAdd;
		int stillToAdd = toAdd;
		while (stillToAdd > 0 && this.currentLevel <= this.levelCap) {
			//-1 because the array is not 1 based, while the "0" for the level is 1
			if (EXPERIENCE_TO_LEVEL_UP[this.currentLevel - 1] <= this.totalExperience) {   
				stillToAdd -= EXPERIENCE_TO_LEVEL_UP[this.currentLevel - 1];
				raiseLevel();
			} else {
				this.currentExperience += stillToAdd;
				stillToAdd = 0;
			}		
		}
	}
	/**
	 * Permits a level to be raised.
	 */
	@Override
	public void raiseLevel() {
		this.currentLevel++;
		this.currentExperience = 0;
		for (final BodyPart bp : this.characterBodyParts) {
			bp.levelUpPart();
		}
		this.character.raiseStats();	
	}

	@Override
	public void addCharacter(PlayableCharacter character) {
		this.character = character;
		this.characterBodyParts = character.getBodyParts();
	}

	/**
	 * Returns the character.
	 * @return the character
	 */
	public PlayableCharacter getCharacter() {
		return this.character;
	}
}
