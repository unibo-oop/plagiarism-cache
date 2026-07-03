package enemies;

import characters.Position;
import dodge.DodgeImpl;

public class Henchman extends Enemy {

	public Henchman(final Position position) {
		super(position);
	}

	public void damageMod(final DodgeImpl dodge) {
		dodge.setLife(dodge.getLife() - 1);
	}
}
