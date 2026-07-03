package enemies;

import characters.Position;
import dodge.DodgeImpl;

public class Meteorite extends Enemy {

	public Meteorite(final Position position) {
		super(position);
	}

	@Override
	public void damageMod(final DodgeImpl dodge) {
		dodge.setLife(dodge.getLife() - 3);
	}	
}
