package hollowmen.model;

import java.util.Collection;

/**
 * This interface represents the {@code HeroClass} intended as the type of {@link Hero} the player is playing<br>
 * It holds information about the {@link SkillTree} the base {@link Parameter} and the {@link Achievement}
 * @author pigio
 *
 */
public interface HeroClass extends InformationUser{
	
	public enum Name {
		WARRIOR,
		ASSASSIN,
		MAGE;
		
		@Override
		public String toString(){
			return this.name().toLowerCase();
		}
	}
	
	/**
	 * This method give the Hero's {@code SkillTree}
	 * @return {@link SkillTree}
	 */
	public SkillTree getSkillTree();
	
	/**
	 * This method give the Hero's base {@code Parameter}
	 * @return {@link Collection}<{@link Parameter}> represent what the {@link Hero}'s 
	 * parameters are and their base value
	 */
	public Collection<Parameter> getBaseParam();
	
	/**
	 * This method give the {@code HeroClass}'s {@code Challenge}
	 * @return {@link Challenge}
	 */
	
	public Challenge getChallenge();
}
