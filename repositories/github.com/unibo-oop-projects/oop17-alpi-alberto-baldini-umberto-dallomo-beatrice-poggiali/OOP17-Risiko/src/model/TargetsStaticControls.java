package model;

import java.util.LinkedList;

import controller.Controller;
import lands.Lands.MyJButton;
import players.Player;

/**
 * 
 *  this class describe the six method with the specific checks to define if a target has been reached .
 */
public final class TargetsStaticControls {

	/**
	 * @param ter is the list of country
	 * @param p is the player in turn
	 * @return true if target reached else false.
	 */
	public static boolean AmN_Af(final LinkedList<MyJButton> ter, final Player p) {
		return Continent.totalAmericaN(ter, p) && Continent.totalAfrica(ter, p);
	}
	
	/**
	 * @param ter is the list of country
	 * @param p is the player in turn
	 * @return true if target reached else false.
	 */
	public static boolean AmN_Oc(final LinkedList<MyJButton> ter, final Player p) {
		return Continent.totalAmericaN(ter, p) && Continent.totalOceania(ter, p);
	}
	
	/**
	 * @param ter is the list of country
	 * @param p is the player in turn
	 * @return true if target reached else false.
	 */
	public static boolean As_Oc(final LinkedList<MyJButton> ter, final Player p) {
		return Continent.totalAsia(ter, p) && Continent.totalOceania(ter, p);
	}
	
	/**
	 * @param p is the player in turn
	 * @return true if target reached else false.
	 */
	public static boolean As_Af(final LinkedList<MyJButton> ter, final Player p) {
		return Continent.totalAsia(ter, p) && Continent.totalAfrica(ter, p);
	}
	
	/**
	 * @param ter is the list of country
	 * @param p is the player in turn
	 * @return true if target reached else false.
	 */
	public static boolean Eu_AmS_smt(final LinkedList<MyJButton> ter, final Player p) {
		return Continent.totalEuropa(ter, p) && Continent.totalAmericaS(ter, p) && (Continent.totalAfrica(ter, p) || Continent.totalAmericaN(ter, p) || Continent.totalAsia(ter, p) || Continent.totalOceania(ter, p));
	}
	
	/**
	 * @param ter is the list of country
	 * @param p is the player in turn
	 * @return true if target reached else false.
	 */
	public static boolean Eu_Oc_smt(final LinkedList<MyJButton> ter, final Player p) {
		return Continent.totalEuropa(ter, p) && Continent.totalOceania(ter, p) && (Continent.totalAfrica(ter, p) || Continent.totalAmericaN(ter, p) || Continent.totalAsia(ter, p) || Continent.totalAmericaS(ter, p));
	}
}
