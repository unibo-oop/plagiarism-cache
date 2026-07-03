package powerups;

import characters.Position;
import dodge.DodgeImpl;

public class Life extends PowerUp {

	public Life(final Position position) {
		super(position);
	}

	@Override
	public void benefitMod(final DodgeImpl dodge) {
		dodge.setLife(dodge.getLife() + 1);
	}
}
