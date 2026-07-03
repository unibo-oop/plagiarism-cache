package enemies;

import characters.Crashable;
import characters.CrossingCharacter;
import characters.Position;
import dodge.DodgeImpl;

public abstract class Enemy extends CrossingCharacter implements Crashable {

	/**
	 * 
	 * @param position
	 */
	public Enemy(Position position) {
		super(position);
	}

	/**
	 * 
	 * @param dodge
	 * It indicates the type of damage for Dodge when there's a collision with an enemy
	 */
	public abstract void damageMod(final DodgeImpl dodge);

	/**
	 * @param dodge
	 * This methods verifies if Dodge has got the immunity (thanks to AkuAku).In this case, after a collision with an enemy the immunity will disappear. Furthermore, the damage will be dealt
	 * 
	 */
	@Override
	public void collides(final DodgeImpl dodge) {
		if (dodge.getImmunity() == true) {
			dodge.removeImmunity();
		} else {
			this.damageMod(dodge);
		}
	}
}
