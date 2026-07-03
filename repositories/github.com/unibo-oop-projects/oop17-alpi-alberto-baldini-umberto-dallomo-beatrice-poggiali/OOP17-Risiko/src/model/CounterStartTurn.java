package model;

import java.util.LinkedList;

import lands.Lands.MyJButton;
import model.Continent;
import players.Player;

/**
 * 
 * this class count the number of armies to add in a normal turn 
 * checking the continent and the numbers of country .
 */
public class CounterStartTurn {

	private static int armiesToAdd = 0;
	private final static int AmericaN = 5;
	private final static int AmericaS = 2;
	private final static int Europa = 5;
	private final static int Africa = 3;
	private final static int Asia = 7;
	private final static int Oceania = 2;

	/**
	 * 
	 * @param terr is the list of bottons country .
	 * @param p is the actual player in turn .
	 * @return the number of armies to add .
	 */
	public static int CST(final LinkedList<MyJButton> terr, final Player p) {

		armiesToAdd = 0;

		int numtot = 0;

		for (MyJButton a: terr) {

			if (a.getOwner() == p.getColore()) {
				numtot++;
			}
		}

		armiesToAdd = numtot / 3;

		if (Continent.totalAmericaN(terr, p)) {
			armiesToAdd += AmericaN;
		}

		if (Continent.totalAmericaS(terr, p)) {
			armiesToAdd += AmericaS;
		}

		if (Continent.totalEuropa(terr, p)) {
			armiesToAdd += Europa;
		}

		if (Continent.totalAfrica(terr, p)) {
			armiesToAdd += Africa;
		}

		if (Continent.totalAsia(terr, p)) {
			armiesToAdd += Asia;
		}

		if (Continent.totalOceania(terr, p)) {
			armiesToAdd += Oceania;
		}

		return armiesToAdd;

	}

}
