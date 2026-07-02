package hollowmen.model.roomentity.hero;

import java.util.Collection;
import java.util.LinkedList;

import hollowmen.model.Achievement;
import hollowmen.model.Challenge;

/**
 * This class implements {@link Challenge}
 * <br>
 * NOTE: actually no {@link Achievement} given
 * @author pigio
 *
 */
public class ChallengeImpl implements Challenge{

	private Collection<Achievement> achieve;
	
	
	public ChallengeImpl() {
		this.achieve = new LinkedList<>();
	}
	
	/**
	 * {@inheritDoc Challenge}
	 */
	@Override
	public Collection<Achievement> getAchievements() {
		return this.achieve;
	}

}
