package powerups;

import characters.Position;
import dodge.DodgeImpl;

public class WumpaFruit extends PowerUp {
	
	public WumpaFruit(final Position position) {
		super(position);
	}

	@Override
	public void benefitMod(final DodgeImpl dodge) {
		dodge.addWumpas();
	}

}
