package model;

import java.util.LinkedList;

import lands.Lands.MyJButton;
import players.Player;

/**
 * this class describe the six method with the specific checks 
 * to define if a continent is totally possessed by one player . 
 *
 */
public class Continent {

	private static final int NUMSTATSAMERICAN = 9;
	private static final int NUMSTATSAMERICAS = 4;
	private static final int NUMSTATSAFRICA = 6;
	private static final int NUMSTATSOCEANIA = 4;
	private static final int NUMSTATSASIA = 12;
	private static final int NUMSTATSEUROPA = 7;
	private static int check;

	/**
	 * 
	 * @param terr is the list of bottons country .
	 * @param p is the actual player in turn .
	 * @return true if NordAmerica is totally owned by p else false .
	 */
	public static boolean totalAmericaN(final LinkedList<MyJButton> terr, final Player p) {
		check = 0;
		for (int i = 0; i < NUMSTATSAMERICAN; i++) {
			if (p.getColore() == terr.get(i).getOwner()) {
				check++;
			}
		}
		return check == NUMSTATSAMERICAN;
	}
	
	/**
	 * 
	 * @param terr is the list of bottons country .
	 * @param p is the actual player in turn .
	 * @return true if SudAmerica is totally owned by p else false .
	 */
	public static boolean totalAmericaS(final LinkedList<MyJButton> terr, final Player p) {
		check = 0;
		for (int i = NUMSTATSAMERICAN; i < (NUMSTATSAMERICAN + NUMSTATSAMERICAS); i++) {
			if (p.getColore() == terr.get(i).getOwner()) {
				check++;
			}
		}
		return check == NUMSTATSAMERICAS;
	}

	/**
	 * 
	 * @param terr is the list of bottons country .
	 * @param p is the actual player in turn .
	 * @return true if Europa is totally owned by p else false .
	 */
	public static boolean totalEuropa(final LinkedList<MyJButton> terr, final Player p) {
		check = 0;
		for (int i = (NUMSTATSAMERICAN + NUMSTATSAMERICAS); i < (NUMSTATSAMERICAN + NUMSTATSAMERICAS + NUMSTATSEUROPA); i++) {
			if (p.getColore() == terr.get(i).getOwner()) {
				check++;
			}
		}
		return check == NUMSTATSEUROPA;
	}

	/**
	 * 
	 * @param terr is the list of bottons country .
	 * @param p is the actual player in turn .
	 * @return true if Africa is totally owned by p else false .
	 */
	public static boolean totalAfrica(final LinkedList<MyJButton> terr, final Player p) {
		check = 0;
		for (int i = (NUMSTATSAMERICAN + NUMSTATSAMERICAS + NUMSTATSEUROPA); i < (NUMSTATSAMERICAN + NUMSTATSAMERICAS + NUMSTATSEUROPA + NUMSTATSAFRICA); i++) {
			if (p.getColore() == terr.get(i).getOwner()) {
				check++;
			}
		}
		return check == NUMSTATSAFRICA;
	}

	/**
	 * 
	 * @param terr is the list of bottons country .
	 * @param p is the actual player in turn .
	 * @return true if Asia is totally owned by p else false .
	 */
	public static boolean totalAsia(final LinkedList<MyJButton> terr, final Player p) {
		check = 0;
		for (int i = (NUMSTATSAMERICAN + NUMSTATSAMERICAS + NUMSTATSEUROPA 
				+ NUMSTATSAFRICA); i < (NUMSTATSAMERICAN + NUMSTATSAMERICAS 
						+ NUMSTATSEUROPA + NUMSTATSAFRICA + NUMSTATSASIA); i++) {
			if (p.getColore() == terr.get(i).getOwner()) {
				check++;
			}
		}
		return check == NUMSTATSASIA;
	}

	/**
	 * 
	 * @param terr is the list of bottons country .
	 * @param p is the actual player in turn .
	 * @return true if Oceania is totally owned by p else false .
	 */
	public static boolean totalOceania(final LinkedList<MyJButton> terr, final Player p) {
		check = 0;
		for (int i = (NUMSTATSAMERICAN + NUMSTATSAMERICAS + NUMSTATSEUROPA + NUMSTATSAFRICA + NUMSTATSASIA); i < (NUMSTATSAMERICAN + NUMSTATSAMERICAS + NUMSTATSEUROPA + NUMSTATSAFRICA + NUMSTATSASIA + NUMSTATSOCEANIA); i++) {
			if (p.getColore() == terr.get(i).getOwner()) {
				check++;
			}
		}
		return check == NUMSTATSOCEANIA;
	}

	
}
