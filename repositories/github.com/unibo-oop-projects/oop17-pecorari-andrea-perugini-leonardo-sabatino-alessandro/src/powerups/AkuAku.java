package powerups;

import characters.Position;
import dodge.DodgeImpl;

public class AkuAku extends PowerUp {

	public AkuAku(final Position position) {
		super(position);
	}

	@Override
	public void benefitMod(final DodgeImpl dodge) {
		dodge.setImmunity();
	}
}
