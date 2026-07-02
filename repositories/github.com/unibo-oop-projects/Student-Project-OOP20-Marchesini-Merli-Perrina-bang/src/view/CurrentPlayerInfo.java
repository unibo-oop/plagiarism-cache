package view;

import java.util.List;

import libs.observe.ObservableElement;

/**
 * A class containing the information about a the current player.
 * 
 * @author Davide Merli
 *
 */
public class CurrentPlayerInfo extends PlayerInfo {

	private ObservableElement<List<String>> hand = new ObservableElement<>();

	public CurrentPlayerInfo(final String name, final int lifePoints, final String role, final List<String> blueCards) {
		super(name, lifePoints, role, blueCards);
	}

	public ObservableElement<List<String>> getHand() {
		return this.hand;
	}
}
