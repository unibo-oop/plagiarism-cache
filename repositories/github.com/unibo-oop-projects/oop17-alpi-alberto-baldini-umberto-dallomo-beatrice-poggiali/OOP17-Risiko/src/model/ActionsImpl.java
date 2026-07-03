package model;

import java.util.LinkedList;
import controller.Controller;
import players.Player;
import lands.Lands.MyJButton;

/**
 * 
 * this class implements actions and 
 * is used to understand what a pressed botton does it means and what the game must to do .
 *
 */
public final class ActionsImpl implements Actions {

	private static final int FIRSTTURN = 1;
	private static final int PLACEMENTSTARTTURN = 2;
	private static final int FIRSTBUTTONATK = 3;
	private static final int SECONDBUTTONATK = 4;
	private static final int FIRSTBUTTONMOVE = 5;
	private static final int SECONDBUTTONMOVE = 6;
	private static final int PLACEMENTAFTERATK = 7;
	private static final String PLACEMENTMESSAGE = "Premi lo stato dove vuoi aggiungere una armata";
	private static LinkedList<MyJButton> territori;
	private static MyJButton att, temp;
	private static int NumStatS;
	private static int NumStatE;
	private static int ArmiesToAdd;
	private static int LeftArmies;
	private static int intent;
	private static Actions singleton;
	private static TargetsManager obbiettivi;
	private static int step;

	/**
	 * 
	 * @param terr is the list of country.
	 */
	private ActionsImpl(final LinkedList<MyJButton> terr) {	
		territori = terr;
		obbiettivi = new TargetsManager(Controller.getPlayers().size());
		step = Controller.getPlayers().size();
		randomicAssignmentCountry(territori);
        newTurn(0);
	}

	/**
	 * 
	 * @param terr is the list of country.
	 * @return if actions has already been created.
	 */
    public static Actions getActions(final LinkedList<MyJButton> terr) {
		if (singleton == null) {
			singleton = new ActionsImpl(terr);
		}
		return singleton;
	}

	/**
	 * 
	 * @param terr is the list of country.
	 */
	public void randomicAssignmentCountry(final LinkedList<MyJButton> terr) {
		RandomicAssignmentCountry.RAC(terr, Controller.getPlayers().size());
		intent = FIRSTTURN;
	}

	@Override
    public void newTurn(final int combo) {

		Check.activateOnlyOwn();
		Controller.getMoveArmies().setEnabled(false);
		Controller.getFineTurno().setEnabled(false);
		controller.Controller.updateObbiettivi(CheckTargets.getString(Controller.getActualPlayer().getColore()));
		if (intent == FIRSTTURN) {
		    controller.Controller.updateComandi(PLACEMENTMESSAGE);
			setLeftArmies(Controller.getActualPlayer());
		} else  if (step > 0) {
			step--;
			intent = FIRSTBUTTONATK;
			Controller.getMoveArmies().setEnabled(true);
			Controller.getFineTurno().setEnabled(true);
			Check.intent3();
		} else if (step == 0) {
		    controller.Controller.updateComandi(PLACEMENTMESSAGE);
			intent = PLACEMENTSTARTTURN;
			setArmiesToAdd(combo);
		}
	}
	

	/**
	 * 
	 * @param p is the actual player in turn.
	 */
	public static void setLeftArmies(final Player p) {
		LeftArmies = GetLeftArmies.GLA(getTerritori(), Controller.getPlayers().size(), p);
		controller.Controller.updateLeftarmies("" + LeftArmies);
	}

	/**
	 * 
	 * @return the number of armies left to add.
	 */
	public static boolean checkLeftArmies() {
		LeftArmies--;
		controller.Controller.updateLeftarmies("" + LeftArmies);
		return LeftArmies == 0;
	}
	
	/**
	 * 
	 * @param combo the number of armies to add by cards .
	 */
	public static void setArmiesToAdd(final int combo) {
		ArmiesToAdd = (CounterStartTurn.CST(territori, Controller.getActualPlayer()));

		if (combo > 0) {
			controller.Controller.updateLeftarmies(ArmiesToAdd + " + " + combo);
			ArmiesToAdd += combo;
		} else {
			controller.Controller.updateLeftarmies("" + ArmiesToAdd);
		}
	}
	
	/**
	 * 
	 * @return the number of armies left to add.
	 */
	public static boolean checkArmiesToAdd() {
		ArmiesToAdd--;
		controller.Controller.updateLeftarmies("" + ArmiesToAdd);
		return ArmiesToAdd == 0;
	}
	
	@Override
    public void setIntentMove() {
		intent = FIRSTBUTTONMOVE;
		Check.intent5();
		Controller.getMoveArmies().setEnabled(false);
	}

	@Override
    public void switchCase(final MyJButton pressedbotton) {

		switch (intent) {

		case FIRSTTURN:

			Check.place(pressedbotton, 1);
			if (checkLeftArmies()) {
				if (Controller.getActualPlayer() == Controller.getPlayers().getLast()) {
					intent = FIRSTBUTTONATK;
					Controller.nextPlayer();
				} else {
					Controller.nextPlayer();
				}
			}
			break;

		case PLACEMENTSTARTTURN:

			Check.place(pressedbotton, 1);

			if (checkArmiesToAdd()) {
				Controller.getMoveArmies().setEnabled(true);
				Controller.getFineTurno().setEnabled(true);	
				if (Check.hasOneMinCountryCanAttak()) {
					intent = FIRSTBUTTONATK;
					Check.intent3();
				} else if (Check.hasOneMinCountryCanMove()) {
					intent = FIRSTBUTTONMOVE;
					Check.intent5();
				} else {
					Check.deactivateAll();
					Controller.getMoveArmies().setEnabled(false);
				}
			}

			break;

		case FIRSTBUTTONATK:

			NumStatS = (int) ActionsImpl.getTerritori().stream()
					.filter(a -> a.getOwner() == Controller.getActualPlayer().getColore()).count();
			intent = SECONDBUTTONATK;
			Check.intent4(pressedbotton);
			att = pressedbotton;
			break;

		case SECONDBUTTONATK:

			Attack.attack(att, pressedbotton, Controller.getPlayers());
			NumStatE = (int) territori.stream()
					.filter(a -> a.getOwner() == Controller.getActualPlayer().getColore()).count();

			if (NumStatE > NumStatS) {
				Controller.draw();
				CheckTargets.win(ActionsImpl.getTerritori(), Controller.getActualPlayer());
				if (att.getArmies() > 1) {
					intent = PLACEMENTAFTERATK;
					Check.intent7(att, pressedbotton);
					temp = pressedbotton;
				} else {

					if (Check.hasOneMinCountryCanAttak()) {
						intent = FIRSTBUTTONATK;
						Check.intent3();
					} else if (Check.hasOneMinCountryCanMove()) {
						intent = FIRSTBUTTONMOVE;
						Check.intent5();
					} else {
						Check.deactivateAll();
						Controller.getMoveArmies().setEnabled(false);
					}
				}
			} else {
				if (Check.hasOneMinCountryCanAttak()) {
					intent = FIRSTBUTTONATK;
					Check.intent3();
				} else if (Check.hasOneMinCountryCanMove()) {
					intent = FIRSTBUTTONMOVE;
					Check.intent5();
				} else {
					Check.deactivateAll();
					Controller.getMoveArmies().setEnabled(false);
				}
			}

			break;

		case FIRSTBUTTONMOVE:
			temp = pressedbotton;
			intent = SECONDBUTTONMOVE;
			Controller.getMoveArmies().setEnabled(false);
			Check.intent6(pressedbotton);
			break;

		case SECONDBUTTONMOVE:

			Check.deactivateAll();
			Check.move(temp, pressedbotton, 1);
			pressedbotton.setEnabled(true);

			if (temp.getArmies() < 2) {
				intent = PLACEMENTSTARTTURN;
				Check.deactivateAll();
			}

			break;

		case PLACEMENTAFTERATK:

			if (pressedbotton != temp) {
				if (Check.hasOneMinCountryCanAttak()) {
					intent = FIRSTBUTTONATK;
					Check.intent3();
				} else if (Check.hasOneMinCountryCanMove()) {
					intent = FIRSTBUTTONMOVE;
					Check.intent5();
				} else {
					Check.deactivateAll();
					Controller.getMoveArmies().setEnabled(false);
				}

			} else {
				Check.move(att, pressedbotton, 1);

				if (att.getArmies() < 2) {
					if (Check.hasOneMinCountryCanAttak()) {
						intent = FIRSTBUTTONATK;
						Check.intent3();
					} else if (Check.hasOneMinCountryCanMove()) {
						intent = FIRSTBUTTONMOVE;
						Check.intent5();
					} else {
						Check.deactivateAll();
						Controller.getMoveArmies().setEnabled(false);
					}
				}
			}

			break;

		default:
			//c'è qualcosa che non va
			break;
		}
	}

	/**
	 * 
	 * @return the list of country.
	 */
	public static LinkedList<MyJButton> getTerritori() {
		return territori;
	}
	
	/**
	 * 
	 * @return the array of targets.
	 */
	public static TargetsManager getObbiettivi() {
		return obbiettivi;
	}
}
