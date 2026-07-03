package model;

import java.util.LinkedList;

import controller.Controller;
import controller.Restart;
import lands.Lands.MyJButton;
import players.Player;

/**
 * 
 * this class checks if a target has been reached and print the right message .
 *
 */
public class CheckTargets {

	private static final int AmN_Af = 1;
	private static final int AmN_Oc = 2;
	private static final int As_Oc = 3;
	private static final int As_Af = 4;
	private static final int Eu_AmS_smt = 5;
	private static final int Eu_Oc_smt = 6;
	private static final String ANA = "Conquista la totalità del Nord America e dell'Africa";
	private static final String ANO = "Conquista la totalità del Nord America e dell'Oceania";
	private static final String AA = "Conquista la totalità dell'Asia e dell'Africa";
	private static final String AO = "Conquista la totalità dell'Asia e dell'Oceania";
	private static final String EASMT = "Conquista l'Europa, l'Africa ed un terzo continente a tua scelta";
	private static final String EOSMT = "Conquista l'Europa, l'Oceania ed un terzo continente a tua scelta";
	private static int check = 0;

	/**
	 * @param t is the list of country
	 * @param p is the player in turn
	 *  this method check if a target has been reached and in this case stop the game .
	 */
	public static void win(final LinkedList<MyJButton> t, final Player p) {

		check = ActionsImpl.getObbiettivi().getTargetsArray() [p.getColore() - 1];

		switch (check) {

		case AmN_Af:
			if (TargetsStaticControls.AmN_Af(t, p)) {
				Check.deactivateAll();
				Controller.getFineTurno().setEnabled(false);
				Controller.getMoveArmies().setEnabled(false);
				controller.Restart.restart();
			}
			break;

		case AmN_Oc:
			if (TargetsStaticControls.AmN_Oc(t, p)) {
				Check.deactivateAll();
				Controller.getFineTurno().setEnabled(false);
				Controller.getMoveArmies().setEnabled(false);
				controller.Restart.restart();
			}
			break;

		case As_Oc:
			if (TargetsStaticControls.As_Oc(t, p)) {
				Check.deactivateAll();
				Controller.getFineTurno().setEnabled(false);
				Controller.getMoveArmies().setEnabled(false);
				controller.Restart.restart();
			}
			break;

		case As_Af:
			if (TargetsStaticControls.As_Af(t, p)) {
				Check.deactivateAll();
				Controller.getFineTurno().setEnabled(false);
				Controller.getMoveArmies().setEnabled(false);
				controller.Restart.restart();
			}
			break;

		case Eu_AmS_smt:
			if (TargetsStaticControls.Eu_AmS_smt(t, p)) {
				Check.deactivateAll();
				Controller.getFineTurno().setEnabled(false);
				Controller.getMoveArmies().setEnabled(false);
				controller.Restart.restart();
			}
			break;

		case Eu_Oc_smt:
			if (TargetsStaticControls.Eu_Oc_smt(t, p)) {
				Check.deactivateAll();
				Controller.getFineTurno().setEnabled(false);
				Controller.getMoveArmies().setEnabled(false);
				controller.Restart.restart();
			}
			break;

		default: //c'è stato qualche errore
			break;

		}
	
	}
	
	/**
	 * @param in is the color of the player (code of the player) .
	 * @return the message to print in HUD .
	 */
	public static String getString(final int in) {

		check = in - 1;

		switch (ActionsImpl.getObbiettivi().getTargetsArray()[check]) {

		case AmN_Af:
			return ANA;
		case AmN_Oc:
			return ANO;
		case As_Oc:
			return AA;
		case As_Af:
			return AO;
		case Eu_AmS_smt:
			return EASMT;
		case Eu_Oc_smt:
			return EOSMT;
		default: //c'è stato qualche errore
			return "ERRORE";
		}
	}

}
