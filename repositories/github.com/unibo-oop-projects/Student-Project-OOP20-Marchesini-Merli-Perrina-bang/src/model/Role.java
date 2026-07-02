package model;

/**
 * An enum difning the role of a player.
 * 
 * @author Davide Merli
 *
 */
public enum Role {
	SHERIFF, DEPUTY, OUTLAW, RENEGADE;

	@Override
	public String toString() {
		return this.name().toLowerCase();
	}
}
