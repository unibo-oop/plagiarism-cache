package model;

import java.util.HashSet;
import java.util.Set;

/**
 * A utility class containing a function to get the possible targets of an
 * action.
 * 
 * @author Ryan Perrina
 *
 */
public class Logics {

	private Table table;

	public Logics(final Table table) {
		this.table = table;
	}

	/**
	 * Returns the possible targets of the current player.
	 * 
	 * @return a set of players
	 */
	public Set<Player> getTargets() {
		Player currentPlayer = this.table.getCurrentPlayer();
		Set<Player> targets = new HashSet<Player>();

		Player cur = currentPlayer;
		for (int i = 1; i <= currentPlayer.getSight(); i++) {
			var playerSx = this.table.getPlayers().getNextOf(cur);
			i = i + playerSx.getRetreat();
			if (i <= currentPlayer.getSight()) {
				targets.add(playerSx);
			}
			cur = this.table.getPlayers().getNextOf(cur);
		}

		cur = currentPlayer;
		for (int i = 1; i <= currentPlayer.getSight(); i++) {
			var playerDx = this.table.getPlayers().getPrevOf(cur);
			i = i + playerDx.getRetreat();
			if (i <= currentPlayer.getSight()) {
				targets.add(playerDx);
			}
			cur = this.table.getPlayers().getPrevOf(cur);
		}
		targets.remove(currentPlayer);
		return targets;
	}
}
