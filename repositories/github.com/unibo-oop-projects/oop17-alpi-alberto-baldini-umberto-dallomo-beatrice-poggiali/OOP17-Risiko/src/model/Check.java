package model;

import controller.Controller;
import lands.Lands.MyJButton;

/**
 * 
 * this class contains all the checks that the model uses in all his class .
 *
 */
public abstract class Check {

	private static final String FATK = "Premi lo stato con cui vuoi attaccare";
	private static final String SATK = "Premi lo stato che vuoi attaccare";
	private static final String FMOV = "Premi stato da cui vuoi spostare le armate";
	private static final String SMOV = "Premi lo stato dove vuoi spostare le armate";
	private static final String MOVSTOP = "Premi: stato conquistato per spostare un'armata / stato attaccante per annullare lo spostamento";

	/**
	 * 
	 * @param a is the botton where the player is placing the armies .
	 * @param tot is how many armies the player is placing .
	 */
	public static void place(final MyJButton a, final int tot) {
		a.setArmies(a.getArmies() + tot);
	}

	/**
	 * @param a is a country .
	 * @return true if a has two or more armies .
	 */
	public static boolean minNumArmiesToAtk(final MyJButton a) {
		return a.getArmies() > 1;
	}

	/**
	 * @param a is the out_move country .
	 * @param b is the in_move country.
	 * @param tot is the number of armies moved.
	 */
	public static void move(final MyJButton a, final MyJButton b, final int tot) {
		b.setArmies((b.getArmies() + tot));
		a.setArmies((a.getArmies() - tot));
	}

	/**
	 * 
	 * @param b .
	 * @return .
	 */
	public static boolean isDead(final MyJButton b) {
		return b.getArmies() == 0;
	}

	/**
	 * @param a is a country .
	 * @param b is a country .
	 * @return true if has different owner .
	 */
	public static boolean differentOwner(final MyJButton a, final MyJButton b) {
		return a.getOwner() != b.getOwner();
	}
	
	/**
	 * 
	 * @return true if the actual player in turn has min one country can attack else false.
	 */
	public static boolean hasOneMinCountryCanAttak() {
		for (MyJButton a : ActionsImpl.getTerritori()) {
			if (a.getOwner() == Controller.getActualPlayer().getColore() && hasOneMinDifferentOwnerBorder(a) && minNumArmiesToAtk(a)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 
	 * @return true if the actual player in turn has min one country can move else false.
	 */
	public static boolean hasOneMinCountryCanMove() {
		for (MyJButton a : ActionsImpl.getTerritori()) {
			if (a.getOwner() == Controller.getActualPlayer().getColore() && hasOneMinEqualOwnerBorder(a) && a.getArmies() > 1) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 
	 * @param a is a country.
	 * @return true if a has min one enemy border else false.
	 */
	public static boolean hasOneMinDifferentOwnerBorder(final MyJButton a) {
		for (int i = 0; i < a.getConfini().length; i++) {
			if (Controller.getActualPlayer().getColore() != ActionsImpl.getTerritori().get(a.getConfini()[i] - 1).getOwner()) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 
	 * @param a is a country.
	 * @return true if a has min one friend border else false.
	 */
	public static boolean hasOneMinEqualOwnerBorder(final MyJButton a) {
		for (int i = 0; i < a.getConfini().length; i++) {
			if (Controller.getActualPlayer().getColore() == ActionsImpl.getTerritori().get(a.getConfini()[i] - 1).getOwner()) {
				return true;
			}
		}
		return false;
	}

	/**
	 * this method deactivate all country botton .
	 */
	public static void deactivateAll() {
		for (MyJButton a : ActionsImpl.getTerritori()) {
			a.setEnabled(false);
		}
	}

	/**
	 * this method activate only contry of the actual player in turn .
	 */
	public static void activateOnlyOwn() {
		for (MyJButton a : ActionsImpl.getTerritori()) {
			if (a.getOwner() == Controller.getActualPlayer().getColore()) {
				a.setEnabled(true);
			} else {
				a.setEnabled(false);
			}
		}
	}

	/**
	 * this method activate only the country that can can attack .
	 */
	public static void intent3() {

		for (MyJButton a : ActionsImpl.getTerritori()) {
			if (Controller.getActualPlayer().getColore() == a.getOwner() && a.getArmies() > 1
					&& Check.hasOneMinDifferentOwnerBorder(a)) {
				a.setEnabled(true);
			} else {
				a.setEnabled(false);
			}
		}
		controller.Controller.updateComandi(FATK);
	}

	/**
	 * 
	 * @param a is a country.
	 * this method activate only country can attacked by a .
	 */
	public static void intent4(final MyJButton a) {
		deactivateAll();
		for (int i = 0; i < a.getConfini().length; i++) {
			if (a.getOwner() != ActionsImpl.getTerritori().get(a.getConfini()[i] - 1).getOwner()) {
				ActionsImpl.getTerritori().get(a.getConfini()[i] - 1).setEnabled(true);
			}
		}
		controller.Controller.updateComandi(SATK);
	}

	/**
	 * this method activate only country can move .
	 */
	public static void intent5() {
		for (MyJButton a : ActionsImpl.getTerritori()) {
			if ((a.getOwner() == Controller.getActualPlayer().getColore()) && (hasOneMinEqualOwnerBorder(a)) && (a.getArmies() > 1)) {
				a.setEnabled(true);
			} else {
				a.setEnabled(false);
			}
		}
		controller.Controller.updateComandi(FMOV);
	}

	/**
	 * 
	 * @param a is a country.
	 * this method activate only country can in_move by a .
	 */
	public static void intent6(final MyJButton a) {
		deactivateAll();
		for (int i = 0; i < a.getConfini().length; i++) {
			if (a.getOwner() == ActionsImpl.getTerritori().get(a.getConfini()[i] - 1).getOwner()) {
				ActionsImpl.getTerritori().get(a.getConfini()[i] - 1).setEnabled(true);
			}
		}
		controller.Controller.updateComandi(SMOV);
	}

	/**
	 * 
	 * @param a is a country.
	 * @param b is a country.
	 * this method activate only two country where armies is moving .
	 */
	public static void intent7(final MyJButton a, final MyJButton b) {
		deactivateAll();
		a.setEnabled(true);
		b.setEnabled(true);
		controller.Controller.updateComandi(MOVSTOP);
	}
}
