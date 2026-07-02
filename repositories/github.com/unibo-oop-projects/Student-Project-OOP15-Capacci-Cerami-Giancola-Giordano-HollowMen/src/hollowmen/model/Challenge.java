package hollowmen.model;

import java.util.Collection;

/**
 * This interface represents an object that holds all the {@link Achievement} for the
 * {@code HeroClass}
 * 
 * @author pigio
 *
 */
public interface Challenge{

	/**
	 * This method give all the {@code Challenge} of this 
	 * @return
	 */
	public Collection<Achievement> getAchievements();
	
}
