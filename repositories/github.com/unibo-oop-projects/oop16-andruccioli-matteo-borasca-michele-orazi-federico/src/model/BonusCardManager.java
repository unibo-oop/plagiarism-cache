package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.Set;

import javafx.util.Pair;
import model.bonus.Bonus;
import model.bonus.BonusCard;
import model.bonus.BonusFactory;
import model.bonus.BonusFactoryImpl;
import model.bonus.StateBonusCard;
import model.player.Player;
import model.state.State;
import utils.HistoryLog;

/**
 * 
 * This class manages the assignment of bonusCards to players and trading of
 * bonusCard combo for Tanks.
 *
 */
public final class BonusCardManager implements BonusCardManagerInterface {

    private static final int SECOND_TYPE_CARD_NUM = 2;
    private static final int TANKS_PER_STATE_OWNED = 2;
    private static final int COMBO_SIZE = 3;
    private static final int JOLLY_COMBO_TANK = 12;
    private static final int ALL_TYPES_COMBO_TANK = 10;
    private static final int CAVALRY_COMBO_TANK = 8;
    private static final int INFANTRY_COMBO_TANK = 6;
    private static final int ARTILLERY_COMBO_TANK = 4;
    private static final int NUM_JOLLY = 2;
    private static final int NUM_STATEBONUSCARD = 3;
    private List<State> states;
    private List<BonusCard> bonusCardDeck;
    private final Map<List<Bonus>, Integer> basicBonusAssociation;
    private boolean initCalled;
    private final BonusFactory bf;
    private final Random random;
    private static final BonusCardManager SINGLETON = new BonusCardManager();

    /**
     * 
     * @return List of BonusCard with elements of bonusCardDeck inside
     * 
     */
    public List<BonusCard> getDeck() {
        return new LinkedList<>(this.bonusCardDeck);
    }

    private BonusCardManager() {
        this.basicBonusAssociation = new HashMap<>();
        this.initCalled = false;
        this.bonusCardDeck = new LinkedList<>();
        this.bf = new BonusFactoryImpl();
        this.random = new Random();
    }

    /**
     * 
     * @return The instance of the BonusCardManager.
     * 
     */
    public static BonusCardManager getInstance() {
        return SINGLETON;
    }

    private void fillDeck() {
        int num = 0;
        final int fraction = this.states.size() / NUM_STATEBONUSCARD;
        int end = fraction;

        for (; num < end; num++) {
            this.bonusCardDeck.add(this.bf.getArtillery(getRandomState()));
        }
        end += fraction;
        for (; num < end; num++) {
            this.bonusCardDeck.add(this.bf.getInfantry(getRandomState()));
        }
        while (!this.states.isEmpty()) {
            this.bonusCardDeck.add(this.bf.getCavalry(getRandomState()));
        }

        for (int i = 0; i < NUM_JOLLY; i++) {
            this.bonusCardDeck.add(this.bf.getJolly());
        }

    }

    private void initBasicBonusAssociation() {
        this.basicBonusAssociation.put(new ArrayList<>(Arrays.asList(Bonus.JOLLY, Bonus.ARTILLERY, Bonus.ARTILLERY)),
                JOLLY_COMBO_TANK);
        this.basicBonusAssociation.put(new ArrayList<>(Arrays.asList(Bonus.JOLLY, Bonus.INFANTRY, Bonus.INFANTRY)),
                JOLLY_COMBO_TANK);
        this.basicBonusAssociation.put(new ArrayList<>(Arrays.asList(Bonus.JOLLY, Bonus.CAVALRY, Bonus.CAVALRY)),
                JOLLY_COMBO_TANK);
        this.basicBonusAssociation.put(new ArrayList<>(Arrays.asList(Bonus.CAVALRY, Bonus.INFANTRY, Bonus.ARTILLERY)),
                ALL_TYPES_COMBO_TANK);
        this.basicBonusAssociation.put(new ArrayList<>(Arrays.asList(Bonus.CAVALRY, Bonus.CAVALRY, Bonus.CAVALRY)),
                CAVALRY_COMBO_TANK);
        this.basicBonusAssociation.put(new ArrayList<>(Arrays.asList(Bonus.INFANTRY, Bonus.INFANTRY, Bonus.INFANTRY)),
                INFANTRY_COMBO_TANK);
        this.basicBonusAssociation.put(
                new ArrayList<>(Arrays.asList(Bonus.ARTILLERY, Bonus.ARTILLERY, Bonus.ARTILLERY)),
                ARTILLERY_COMBO_TANK);
    }

    /**
     * 
     * This method initializes the BonusCardManager. This method can be called
     * just once.
     * 
     * @param states
     *            list of all states;
     * 
     */
    public void init(final List<State> states) {
        if (states == null) {
            throw new IllegalArgumentException("states must be non null");
        }
        if (states.isEmpty()) {
            throw new IllegalArgumentException("states must have elements");
        }
        if (!this.initCalled) {
            this.initCalled = true;
            this.states = new ArrayList<>(states);
            fillDeck();
            initBasicBonusAssociation();
        }

    }

    private void initCheck() {
        if (!this.initCalled) {
            throw new IllegalStateException("init method must be called before");
        }
    }

    private State getRandomState() {
        final int pos = random.nextInt(this.states.size());
        return this.states.remove(pos);
    }

    private BonusCard getRandomBonusCard() {
        final int pos = random.nextInt(this.bonusCardDeck.size());
        return this.bonusCardDeck.remove(pos);
    }

    private boolean isCombo(final List<Bonus> combo) {
        return this.basicBonusAssociation.keySet().contains(new ArrayList<>(combo));
    }

    /**
     * This method check if some of those states contained in the BonusCards
     * (which compose combo) are controlled by the player. In that case the
     * player gains 2 more tanks for each state which satisfies this condition.
     * 
     * @param player
     *            player trading the combo
     * @param combo
     *            combo of BonusCards traded by the player
     * @return number of additional tanks the player will gain
     * 
     */
    private int calculateStateTanks(final Player player, final List<BonusCard> combo) {
        int num = 0;
        for (final BonusCard bc : combo) {
            if (bc instanceof StateBonusCard && player.getStates().contains(((StateBonusCard) bc).getState())) {
                num++;
            }
        }
        return (num * TANKS_PER_STATE_OWNED);
    }

    /**
     * 
     * This method randomly assigns a BonusCard to the specified player if the
     * BonusCard Deck isn't empty.
     * 
     * @param player
     *            player who obtained a BonusCard;
     * 
     */
    public void addRandomBonusCardTo(final Player player) {
        if (player == null) {
            throw new IllegalArgumentException("player must be a non null object");
        }
        initCheck();
        if (!this.bonusCardDeck.isEmpty()) {
            player.addBonusCard(getRandomBonusCard());
            HistoryLog.getLog().send("A new BonusCard assigned to " + player.getName());
        }
    }

    @Override
    public void tradeCombo(final Player player, final List<BonusCard> combo) {
        if (player == null) {
            throw new IllegalArgumentException("player must be a non null object");
        }
        if (combo == null) {
            throw new IllegalArgumentException("combo must be a non null object");
        }
        if (combo.size() != COMBO_SIZE) {
            throw new IllegalArgumentException("combo must contain " + COMBO_SIZE + " objects");
        }
        initCheck();

        final List<Bonus> tmp = new ArrayList<>();
        for (final BonusCard bc : combo) {
            tmp.add(bc.getBonusType());
        }
        Collections.sort(tmp, new Comparator<Bonus>() {
            public int compare(final Bonus o1, final Bonus o2) {
                return o2.getPriority().compareTo(o1.getPriority());
            }
        });
        if (isCombo(tmp) && player.getBonusCardsList().containsAll(combo)) {
            player.addTanksToDeploy((this.basicBonusAssociation.get(tmp) + calculateStateTanks(player, combo)));
            player.removeCombo(combo);
            this.bonusCardDeck.addAll(combo);

              String s = player.getName() + "traded the combo "; for (final
              BonusCard bc : combo) {
                  s = s.concat(bc.toString() + " "); 
              }

              HistoryLog.getLog().send(s + "for "
              + (this.basicBonusAssociation.get(tmp)
              + calculateStateTanks(player, combo)) + " tanks");

        }

    }

    /**
     * Search among the list of Bonuscard a card which has a reference to an
     * object state contained in the list of states. if the method finds a card
     * satisfying this condition an optional of this card is returned, otherwise
     * an optional empty will be returned
     * 
     * @param cards
     *            player's list of Bonuscard of a specified Type
     * @param states
     *            player's list of states conquered
     * 
     * @return optional of the searched element
     */
    private Optional<BonusCard> drawWhithState(final List<BonusCard> cards, final Set<State> states) {
        for (final BonusCard c : cards) {
            if (c instanceof StateBonusCard && states.contains(((StateBonusCard) c).getState())) {
                cards.remove(c);
                return Optional.of(c);
            }
        }
        return Optional.empty();
    }

    private BonusCard drawFirst(final List<BonusCard> cards) {
        return cards.remove(0);
    }

    /**
     * 
     * This method fill those entries (of the map passed) related to combo
     * containing a BonusCard of type Jolly
     * 
     */
    private void fillJollyEntries(final Player player, final Map<List<Bonus>, Pair<Integer, List<BonusCard>>> tmp,
            final Bonus secondType) {

        int numWithState = 0;

        if (player.getBonusCards().get(secondType).size() < SECOND_TYPE_CARD_NUM) {
            tmp.put(new ArrayList<>(Arrays.asList(Bonus.JOLLY, secondType, secondType)),
                    new Pair<>(0, new LinkedList<>()));
        } else {
            final List<BonusCard> toAdd = new LinkedList<>();
            toAdd.add(player.getBonusCards().get(Bonus.JOLLY).get(0));
            final List<BonusCard> cards = new LinkedList<>(player.getBonusCards().get(secondType));

            numWithState += getNSameTypeBonusCard(toAdd, cards, player, SECOND_TYPE_CARD_NUM);

            while (toAdd.size() < COMBO_SIZE) {
                toAdd.add(drawFirst(cards));
            }
            tmp.put(new ArrayList<>(Arrays.asList(Bonus.JOLLY, secondType, secondType)),
                    new Pair<>(((numWithState * TANKS_PER_STATE_OWNED) + this.basicBonusAssociation
                            .get(new ArrayList<>(Arrays.asList(Bonus.JOLLY, secondType, secondType)))), toAdd));

        }
    }

    /**
     * 
     * This method fill that entry (of the map passed) related to combo
     * containing a BonusCard for each different bonus type except Jolly
     * 
     */
    private void fillAllDifferentEntry(final Player player,
            final Map<List<Bonus>, Pair<Integer, List<BonusCard>>> tmp) {
        int numWithState = 0;
        final List<BonusCard> toAdd = new LinkedList<>();

        numWithState += specifiedTypeCardChoser(toAdd, new LinkedList<>(player.getBonusCards().get(Bonus.CAVALRY)),
                player);
        numWithState += specifiedTypeCardChoser(toAdd, new LinkedList<>(player.getBonusCards().get(Bonus.INFANTRY)),
                player);
        numWithState += specifiedTypeCardChoser(toAdd, new LinkedList<>(player.getBonusCards().get(Bonus.ARTILLERY)),
                player);

        tmp.put(new ArrayList<>(Arrays.asList(Bonus.CAVALRY, Bonus.INFANTRY, Bonus.ARTILLERY)),
                new Pair<>(
                        ((numWithState * TANKS_PER_STATE_OWNED) + this.basicBonusAssociation
                                .get(new ArrayList<>(Arrays.asList(Bonus.CAVALRY, Bonus.INFANTRY, Bonus.ARTILLERY)))),
                        toAdd));
    }

    /**
     * 
     * This method chooses a StateBonusCard from "cards" and add it to list
     * "toAdd", if the BonusCard is related to a state owned by player
     * numWithState will be incremented
     * 
     */
    private int specifiedTypeCardChoser(final List<BonusCard> toAdd, final List<BonusCard> cards, final Player player) {
        int numWithState = 0;
        Optional<BonusCard> opTmp;
        opTmp = drawWhithState(cards, player.getStates());
        if (opTmp.isPresent()) {
            toAdd.add(opTmp.get());
            numWithState++;
        } else {
            toAdd.add(drawFirst(cards));
        }
        return numWithState;
    }

    /**
     * 
     * This method fill those entries (of the map passed) related to combo
     * containing 3 BonusCards for the same bonus type
     * 
     */
    private void fillEqualEntries(final Player player, final Map<List<Bonus>, Pair<Integer, List<BonusCard>>> tmp,
            final Bonus type) {

        int numWithState = 0;

        if (player.getBonusCards().get(type).size() < COMBO_SIZE) {
            tmp.put(new ArrayList<>(Arrays.asList(type, type, type)), new Pair<>(0, new LinkedList<>()));
        } else {
            final List<BonusCard> toAdd = new LinkedList<>();
            final List<BonusCard> cards = new LinkedList<>(player.getBonusCards().get(type));

            numWithState += getNSameTypeBonusCard(toAdd, cards, player, COMBO_SIZE);

            while (toAdd.size() < COMBO_SIZE) {
                toAdd.add(drawFirst(cards));
            }
            tmp.put(new ArrayList<>(Arrays.asList(type, type, type)),
                    new Pair<>(
                            ((numWithState * TANKS_PER_STATE_OWNED)
                                    + this.basicBonusAssociation.get(new ArrayList<>(Arrays.asList(type, type, type)))),
                            toAdd));
        }
    }

    /**
     * 
     * This method chooses num StateBonusCards from "cards" and add it to list
     * "toAdd", for each BonusCard related to a state owned by player,
     * numWithState will be incremented by an unit
     * 
     */
    private int getNSameTypeBonusCard(final List<BonusCard> toAdd, final List<BonusCard> cards, final Player player,
            final int num) {
        int numWithState = 0;
        Optional<BonusCard> opTmp;
        for (int i = 0; i < num; i++) {
            opTmp = Optional.empty();
            opTmp = drawWhithState(cards, player.getStates());
            if (opTmp.isPresent()) {
                numWithState++;
                toAdd.add(opTmp.get());
            }
        }
        return numWithState;
    }

    @Override
    public List<BonusCard> getBestCombo(final Player player) {
        if (player == null) {
            throw new IllegalArgumentException("player must be a non null object");
        }
        initCheck();
        if (player.getBonusCardsList().size() < COMBO_SIZE) {
            return new ArrayList<>();
        }

        List<Bonus> bestComboKey = new ArrayList<>();
        int bestComboTank = 0;

        final Map<List<Bonus>, Pair<Integer, List<BonusCard>>> tmp = new HashMap<>();
        if (player.getBonusCards().get(Bonus.JOLLY).isEmpty()) {
            tmp.put(new ArrayList<>(Arrays.asList(Bonus.JOLLY, Bonus.ARTILLERY, Bonus.ARTILLERY)),
                    new Pair<>(0, new LinkedList<>()));
            tmp.put(new ArrayList<>(Arrays.asList(Bonus.JOLLY, Bonus.INFANTRY, Bonus.INFANTRY)),
                    new Pair<>(0, new LinkedList<>()));
            tmp.put(new ArrayList<>(Arrays.asList(Bonus.JOLLY, Bonus.CAVALRY, Bonus.CAVALRY)),
                    new Pair<>(0, new LinkedList<>()));
        } else {
            fillJollyEntries(player, tmp, Bonus.ARTILLERY);
            fillJollyEntries(player, tmp, Bonus.INFANTRY);
            fillJollyEntries(player, tmp, Bonus.CAVALRY);
        }
        if (player.getBonusCards().get(Bonus.ARTILLERY).isEmpty()
                || player.getBonusCards().get(Bonus.INFANTRY).isEmpty()
                || player.getBonusCards().get(Bonus.CAVALRY).isEmpty()) {
            tmp.put(new ArrayList<>(Arrays.asList(Bonus.CAVALRY, Bonus.INFANTRY, Bonus.ARTILLERY)),
                    new Pair<>(0, new LinkedList<>()));
        } else {
            fillAllDifferentEntry(player, tmp);
        }

        fillEqualEntries(player, tmp, Bonus.CAVALRY);
        fillEqualEntries(player, tmp, Bonus.INFANTRY);
        fillEqualEntries(player, tmp, Bonus.ARTILLERY);

        for (final List<Bonus> ck : tmp.keySet()) {
            if (tmp.get(ck).getKey() >= bestComboTank) {
                bestComboTank = tmp.get(ck).getKey();
                bestComboKey = ck;
            }
        }

        return tmp.get(bestComboKey).getValue();

    }

    /**
     * 
     * This method assigns to the Player (conqueror) who defeated another player
     * (defeated) the deck of BonusCard owned by the last one.
     * 
     * @param conqueror
     *            player who defeated the other player;
     * 
     * @param defeated
     *            player defeated.
     * 
     */
    public void acquireBonusCard(final Player conqueror, final Player defeated) {
        if (conqueror == null) {
            throw new IllegalArgumentException("conqueror must be a non null object");
        }
        if (defeated == null) {
            throw new IllegalArgumentException("defeated must be a non null object");
        }
        initCheck();
        if (!defeated.getBonusCards().isEmpty()) {
            final int lenght = defeated.getBonusCardsList().size();
            conqueror.addBonusCardCollection(defeated.getBonusCardsList());
            defeated.removeAllBonusCard();
            HistoryLog.getLog().send(conqueror.getName() + " received " + lenght + " BonusCards from " + defeated.getName());
        }
    }

    /**
     * bring BonusCardMAnager back to default settings.
     */
    public void resetInitCalled() {
        this.initCalled = false;
        this.bonusCardDeck = new LinkedList<>();
    }

    /**
     * 
     * This method is supposed to be called just once and only when a saved game
     * is loaded.
     * 
     * @param bonusCardDeck
     *            Collection representing the bonusCardDeck when the game was
     *            saved;
     * 
     */
    public void recovery(final Collection<BonusCard> bonusCardDeck) {
        if (bonusCardDeck == null) {
            throw new IllegalArgumentException("bonusCardDeck must be non null");
        }
        if (!this.initCalled) {
            this.initCalled = true;
            this.bonusCardDeck.addAll(bonusCardDeck);
            initBasicBonusAssociation();
        }
    }
}
