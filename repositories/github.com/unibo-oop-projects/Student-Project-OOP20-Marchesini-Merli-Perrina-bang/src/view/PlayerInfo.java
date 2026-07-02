package view;

import java.util.List;

/**
 * A class containing the information about a player.
 * 
 * @author Davide Merli
 *
 */
public class PlayerInfo {
	protected String name;
	protected int lifePoints;
	protected String role;
	protected List<String> blueCards;

	public PlayerInfo(final String name, final int lifePoints, final String role, final List<String> blueCards) {
		this.name = name;
		this.lifePoints = lifePoints;
		this.role = role;
		this.blueCards = blueCards;
	}

	public String getName() {
		return this.name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public int getLifePoints() {
		return this.lifePoints;
	}

	public void setLifePoints(final int lifePoints) {
		this.lifePoints = lifePoints;
	}

	public String getRole() {
		return this.role;
	}

	public void setRole(final String role) {
		this.role = role;
	}

	public List<String> getBlueCards() {
		return this.blueCards;
	}

	public void setBlueCards(final List<String> blueCards) {
		this.blueCards = blueCards;
	}
}
