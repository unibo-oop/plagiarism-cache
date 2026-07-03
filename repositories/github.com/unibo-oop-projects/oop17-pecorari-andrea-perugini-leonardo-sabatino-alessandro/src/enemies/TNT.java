package enemies;

import characters.Position;
import dodge.DodgeImpl;

public class TNT extends Enemy {

	public TNT(final Position position) {
		super(position);
	}

	@Override
	public void damageMod(final DodgeImpl dodge) {
		if (dodge.getLife() == 1) {
			dodge.setLife(dodge.getLife() - 1);
		} else {
			dodge.setLife(dodge.getLife() - (dodge.getLife() - 1));
		}
	}
}
