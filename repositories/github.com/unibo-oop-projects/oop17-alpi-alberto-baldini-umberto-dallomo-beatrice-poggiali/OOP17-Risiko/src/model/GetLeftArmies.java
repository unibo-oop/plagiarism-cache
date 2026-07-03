package model;

import java.util.LinkedList;

import lands.Lands.MyJButton;
import players.Player;

/**
 *  this class is used to set how many armies are to be placed at the beginning the game .
 *
 */
public class GetLeftArmies {

	private static final int NUMARMIESTHREEPLAYER = 35;
	private static final int NUMARMIESFOURPLAYER = 30;
	private static final int NUMARMIESFIVEPLAYER = 25;
	private static final int NUMARMIESSIXPLAYER = 20;
	private static final int THREEPLAYERS = 3;
	private static final int FOURPLAYERS = 4;
	private static final int FIVEPLAYERS = 5;
	private static final int SIXPLAYERS = 6;

	/**
	 * 
	 * @param terr is the list of bottons country .
	 * @param totplayers is the size of the players list .
	 * @param p is the actual player in turn .
	 * @return the number of armies to add.
	 */
	public static int GLA(final LinkedList<MyJButton> terr, final int totplayers, final Player p) {

		int leftArmies = 0;
		int usedArmies = terr.stream().filter(a -> a.getOwner() == p.getColore()).toArray().length;

		switch (totplayers) {

		case THREEPLAYERS:
			leftArmies = (NUMARMIESTHREEPLAYER - usedArmies);
			break;

		case FOURPLAYERS:
			leftArmies = (NUMARMIESFOURPLAYER - usedArmies);
			break;

		case FIVEPLAYERS:
			leftArmies = (NUMARMIESFIVEPLAYER - usedArmies);
			break;

		case SIXPLAYERS:
			leftArmies = (NUMARMIESSIXPLAYER - usedArmies);
			break;

		default:
			break;
		}

		return leftArmies;

	}
}
