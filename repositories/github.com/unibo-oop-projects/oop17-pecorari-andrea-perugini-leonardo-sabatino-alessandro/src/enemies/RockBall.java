package enemies;

import characters.Position;
import dodge.DodgeImpl;

public class RockBall extends Enemy {

	public RockBall(final Position position) {
		super(position);
	}

	@Override
	public void damageMod(final DodgeImpl dodge) {
		dodge.setLife(dodge.getLife() - 2);
	}
	
}
