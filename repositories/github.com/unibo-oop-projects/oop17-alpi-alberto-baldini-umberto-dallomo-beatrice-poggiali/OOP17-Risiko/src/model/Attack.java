package model;

import lands.Lands.MyJButton;
import players.CircList;
import players.Player;
import players.PlayerImpl;

/**
 * 
 * this method is used to control the attack between two country.
 *
 */
public class Attack {

	private static int numArmiesAtk = 0;
	private static int numArmiesDef = 0;

	/**
	 * @param a is the attacker country.
	 * @param b is the attacked country.
	 * @param pp is the list of the players.
	 */
	public static void attack(final MyJButton a, final MyJButton b, final CircList<PlayerImpl> pp) {
		numArmiesAtk = 0;
		numArmiesDef = 0;

		numArmiesAtk = a.getArmies() - 1; // numero possibili armate che possono attaccare

		numArmiesAtk = normalize(numArmiesAtk);
		numArmiesDef = normalize(b.getArmies());

		RandomNumbers.RNG(a, b, numArmiesAtk, numArmiesDef);

		if (Check.isDead(b)) {

			if (ActionsImpl.getTerritori().stream().filter(x -> x.getOwner() == b.getOwner()).count() == 1) {
				for (Player p: pp) {
					if (p.getColore() == b.getOwner()) {
						pp.remove(p);
						break;
					}
				}
			}

			b.setOwner(a.getOwner());
			Check.move(a, b, numArmiesAtk);

			//controllo se l'obbiettivo di A era uccidere B

//			for(Player p: P) {
//				if(p.getColore()==A.getOwner()) {
//					if(0==0/*obbiettivo A == B died*/) {
//						//caso true fine gioco
//						//A hai vinto!
//						break;
//					}
//					//Caso false avanti
//				}
//			}
		}
	}

	/**
	 * 
	 * @param numarmies is the number of armies can attack.
	 * @return max 3 armies in case numarmies is 3 or more.
	 */
	private static int normalize(int numarmies) {

		if (numarmies > 3) {
			numarmies = 3; // normalizzo a 3 nel caso ce ne siano di piu
		}

		return numarmies;
	}

}
