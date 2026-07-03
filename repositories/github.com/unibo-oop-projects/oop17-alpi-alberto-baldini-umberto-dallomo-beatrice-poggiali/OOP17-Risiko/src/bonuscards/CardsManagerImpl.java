package bonuscards;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import controller.Controller;
import data.BonusCards;

/**
 * 
 * the interface plus the controls.
 */
public final class CardsManagerImpl extends CardsCheck implements CardsManager {

    private static CardsManager istanza;
    private static List<String> carte;
    private static Map<Integer, List<String>> playersCards;
    private Random num = new Random();
    private int actualPlayer;
    private boolean drawed;

    private CardsManagerImpl(final int nPlayers) {
        this.drawed = false;
        playersCards = new HashMap<Integer, List<String>>();
        actualPlayer = Controller.getPlayers().indexOf(Controller.getActualPlayer());
        carte = new LinkedList<String>();

        for (int i = 0; i < BonusCards.TYPEOFCARDS; i++) {
            for (int j = 0; j < BonusCards.values()[i].getNcards(); j++) {
                carte.add(BonusCards.values()[i].getName());
            }
        }

        Collections.shuffle(carte);     //doppia casualità.

        for (int j = 0; j < nPlayers; j++) {
            playersCards.put(j, new LinkedList<String>());
        }
    }

       /**
        * 
        * @param nPlayers will be passed to the constructor.
        * @return the instance of this class.
        */
    public static CardsManager getCardsManager(final int nPlayers) {
        if (istanza == null) {
            istanza = new CardsManagerImpl(nPlayers);
        }
        return istanza;
    }

    /* (non-Javadoc)
     * @see bonuscards.CardsManager#newTurn(int)
     */
    @Override
    public int newTurn(final int player) {
        setDrawed(false);
        this.actualPlayer = player;

        int numArmies = controlForExchange(playersCards.get(actualPlayer));

        view.ViewCards.updatePanel(playersCards.get(actualPlayer));

        return numArmies;
    }

    /* (non-Javadoc)
     * @see bonuscards.CardsManager#getDrawed()
     */
    @Override
    public boolean getDrawed() {
        // TODO Auto-generated method stub
        return this.drawed;
    }

    /* (non-Javadoc)
     * @see bonuscards.CardsManager#setDrawed(boolean)
     */
    @Override
    public void setDrawed(final boolean condition) {
        // TODO Auto-generated method stub
        this.drawed = condition;
    }

    /* (non-Javadoc)
     * @see bonuscards.CardsManager#draw()
     */
    @Override
    public void draw() {
        // TODO Auto-generated method stub
        int n;
        if (!getDrawed() && carte.size() != 0) {
            n = num.nextInt(carte.size());
            playersCards.get(actualPlayer).add(carte.get(n));
            carte.remove(n);
            view.ViewCards.updatePanel(playersCards.get(actualPlayer));
            this.setDrawed(true);
        }
    }

    /* (non-Javadoc)
     * @see bonuscards.CardsManager#getPlayersCards()
     */
    @Override
    public Map<Integer, List<String>> getPlayersCards() {
        return playersCards;
    }
}
