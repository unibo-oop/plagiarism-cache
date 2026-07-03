package powerups;

import characters.Crashable;
import characters.CrossingCharacter;
import characters.Position;
import dodge.DodgeImpl;

public abstract class PowerUp extends CrossingCharacter implements Crashable {

	public PowerUp(final Position position) {
		super(position);
	}
	
	public abstract void benefitMod(final DodgeImpl dodge);
	
	@Override
	public void collides(final DodgeImpl dodge) {
		this.benefitMod(dodge);
	}
	
}
