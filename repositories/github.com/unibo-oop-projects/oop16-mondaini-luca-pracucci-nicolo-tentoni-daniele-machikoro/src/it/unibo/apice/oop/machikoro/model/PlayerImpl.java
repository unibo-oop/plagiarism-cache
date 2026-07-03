package it.unibo.apice.oop.machikoro.model;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 * Implementazione dell'interfaccia Player.
 */
public class PlayerImpl implements Player {
    private int money;
    private List<Card> cards;
    private String playerName;
    private boolean iAControlled;
    private static final int TRAIN_STATION_COST = 4;
    private static final int SHOPPING_MALL_COST = 10;
    private static final int AMUSEMENT_PARK_COST = 16;
    private static final int RADIO_TOWER_COST = 22;

    /**
     * Costruttore da richiamare nel momento del caricamento della partita, per
     * settare da subito le monete e le carte.
     * 
     * @param name
     *            Nome del giocatore.
     * @param money
     *            Monete del giocatore.
     * @param cards
     *            Carte possedute dal giocatore.
     * @param iAControlled
     *            Specifica se il player è gestito dal computer oppure no
     */
    public PlayerImpl(final String name, final int money, final List<Card> cards, final boolean iAControlled) {
        this.playerName = name;
        this.cards = cards;
        this.money = money;
        this.iAControlled = iAControlled;
    }

    /**
     * Costruttore da richiamare nel momento della creazione di una nuova
     * partita.
     * 
     * @param name
     *            Nome del giocatore
     * @param iAControlled
     *            Valore booleano per sapere se il giocatore è controllato dal
     *            computer oppure no.
     */
    public PlayerImpl(final String name, final boolean iAControlled) {
        this(name, 3, new LinkedList<Card>(), iAControlled);
        setInitialAimCards();
    }

    private void setInitialAimCards() {
        this.cards.add(new AimCard("TrainStation", TRAIN_STATION_COST, AimEffect.TRAIN_STATION));
        this.cards.add(new AimCard("ShoppingMall", SHOPPING_MALL_COST, AimEffect.SHOPPING_MALL));
        this.cards.add(new AimCard("AmusementPark", AMUSEMENT_PARK_COST, AimEffect.AMUSEMENT_PARK));
        this.cards.add(new AimCard("RadioTower", RADIO_TOWER_COST, AimEffect.RADIO_TOWER));
    }

    @Override
    public String getName() {
        return this.playerName;
    }

    @Override
    public int getMoney() {
        return this.money;
    }

    @Override
    public void receiveMoney(final int amount) {
        Objects.requireNonNull(amount);
        this.money += amount;
    }

    @Override
    public void giveMoney(final Player player, final int amount) {
        Objects.requireNonNull(player);
        Objects.requireNonNull(amount);
        if (amount >= this.money) {
            player.receiveMoney(this.money);
            this.money = 0;
        } else {
            this.money -= amount;
            player.receiveMoney(amount);
        }
    }

    @Override
    public void buyCard(final Card card) throws NotEnoughMoneyException, AlreadyBoughtCardException {
        Objects.requireNonNull(card);
        if (card.getCost() > this.money) {
            throw new NotEnoughMoneyException(card.getClone());
        } else {
            if (card.getClass().equals(AimCard.class)) {
                if (cards.stream().filter(e -> e.getClass().equals(AimCard.class) && ((AimCard) e).isEnabled()
                        && ((AimCard) e).getEffect().equals(((AimCard) card).getEffect())).count() > 0) {
                    throw new AlreadyBoughtCardException(card.getClone());
                }
                cards.stream().filter(e -> e.getClass().equals(AimCard.class)
                        && ((AimCard) e).getEffect().equals(((AimCard) card).getEffect())).forEach(e -> {
                            ((AimCard) e).setEnabled(true);
                        });
                /*
                 * for (int i = 0; i < this.getAimCards().size(); i++) { if
                 * (((AimCard)
                 * this.getAimCards().get(i)).getEffect().equals(((AimCard)
                 * card).getEffect())) { System.out.println(
                 * "Adesso compro la carta: " + card + " con la carta: " +
                 * this.getAimCards().get(i)); System.out.println(cards.get(i) +
                 * "\n"); ((AimCard) cards.get(i)).setEnabled(true);
                 * System.out.println(this.getAimCards().get(i)); } }
                 */
            } else {
                cards.add(card);
            }
            this.money -= card.getCost();
        }
    }

    @Override
    public List<Effect> getEffectsByNumber(final int number) {
        Objects.requireNonNull(number);
        final List<Effect> tmp = new LinkedList<Effect>();
        cards.stream().filter(e -> e.getClass().equals(BoardCard.class)).forEach(e -> {
            if (((BoardCard) e).getTriggers().contains(number)) {
                tmp.add(((BoardCard) e).getEffect());
            }
        });
        return tmp;
    }

    @Override
    public List<Card> getBoardCards(final CardType type) {
        Objects.requireNonNull(type);
        if (type.equals(CardType.NOTHING)) {
            return this.getBoardCards();
        }
        final List<Card> tmp = new LinkedList<Card>();
        cards.stream().filter(e -> e.getClass().equals(BoardCard.class) && ((BoardCard) e).getType().equals(type))
                .forEach(e -> tmp.add(e));
        return tmp;
    }

    @Override
    public List<Card> getBoardCards() {
        final List<Card> tmp = new LinkedList<Card>();
        cards.stream().filter(e -> e.getClass().equals(BoardCard.class)).forEach(e -> tmp.add(e));
        return tmp;
    }

    @Override
    public List<Card> getAimCards() {
        final List<Card> tmp = new LinkedList<Card>();
        cards.stream().filter(e -> e.getClass().equals(AimCard.class)).forEach(e -> tmp.add(e));
        return tmp;
    }

    @Override
    public boolean isIAPlayer() {
        return this.iAControlled;
    }

    @Override
    public boolean checkWin() {
        final long count = cards.stream().filter(e -> e.getClass().equals(AimCard.class) && !((AimCard) e).isEnabled())
                .count();
        return count == 0;
    }

    @Override
    public String toString() {
        String cardsString = "#";
        for (int i = 0; i < cards.size(); i++) {
            cardsString += cards.get(i).toString();
        }
        return playerName + ";" + money + ";" + this.iAControlled + cardsString;
    }
}