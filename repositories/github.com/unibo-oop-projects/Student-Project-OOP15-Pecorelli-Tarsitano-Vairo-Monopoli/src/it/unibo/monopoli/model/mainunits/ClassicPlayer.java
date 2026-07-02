package it.unibo.monopoli.model.mainunits;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import it.unibo.monopoli.model.cards.Card;
import it.unibo.monopoli.model.table.Ownership;

/**
 * This class represents the {@link Player} used in the classic version of
 * Monopoly.
 *
 */
public class ClassicPlayer implements Player {

    private final String name;
    private final Pawn pawn;
    private final List<Ownership> ownerships;
    private final List<Card> cards;
    private boolean alreadyRolled;
    private boolean isAPrisoner;
    private Card cardDrew;
    private final List<Integer> dicesNumbers;
    private int money;
    private boolean debtsPaid;
    private final boolean human;
    private int turnsInPrison;
    private boolean isTheFirst = true;

    /**
     * Constructs an instance of {@link ClassicPlayer}. It needs a name and a
     * {@link Pawn}. He can be a Human or a computer.
     * 
     * @param name
     *            - {@link Player}'s name
     * @param pawn
     *            - {@link Player}'s {@link Pawn}
     * @param isHuman
     *            - true if the {@link Player} is human
     */
    public ClassicPlayer(final String name, final Pawn pawn, final boolean isHuman) {
        this.name = name;
        this.pawn = pawn;
        this.ownerships = new LinkedList<>();
        this.cards = new LinkedList<>();
        this.alreadyRolled = false;
        this.isAPrisoner = false;
        this.dicesNumbers = new LinkedList<>();
        this.human = isHuman;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public Pawn getPawn() {
        return this.pawn;
    }

    @Override
    public List<Ownership> getOwnerships() {
        return this.ownerships;
    }

    @Override
    public void addCard(final Card card) {
        this.cards.add(card);
        card.setPlayer(this);
    }

    @Override
    public void removeCard(final Card card) {
        this.cards.remove(card);
        card.setPlayer(null);
    }

    @Override
    public List<Card> getCards() {
        return this.cards;
    }

    @Override
    public boolean dicesAlreadyRolled() {
        return this.alreadyRolled;
    }

    @Override
    public void setDicesRoll(final boolean alreadyRolled) {
        this.alreadyRolled = alreadyRolled;
    }

    @Override
    public boolean isInPrison() {
        return this.isAPrisoner;
    }

    @Override
    public void setPrison(final boolean isGoingToPrison) {
        this.isAPrisoner = isGoingToPrison;
        if (!isGoingToPrison) {
            this.turnsInPrison = 0;
        }
    }

    @Override
    public int howManyTurnsHasBeenInPrison() {
        return this.turnsInPrison;
    }

    @Override
    public void incrementsTurnsInPrison() {
        this.turnsInPrison++;
    }

    @Override
    public Card lastCardDrew() {
        return this.cardDrew;
    }

    @Override
    public void setLastCardDrew(final Card lastCard) {
        this.cardDrew = lastCard;
    }

    @Override
    public List<Integer> lastDicesNumber() {
        return Collections.unmodifiableList(this.dicesNumbers);
    }

    @Override
    public void setLastDicesNumber(final List<Integer> numbers) {
        if (!this.dicesNumbers.isEmpty()) {
            this.dicesNumbers.clear();
        }
        this.dicesNumbers.addAll(numbers);
    }

    @Override
    public void addOwnership(final Ownership ownership) {
        this.ownerships.add(ownership);
    }

    @Override
    public void removeOwnership(final Ownership ownership) {
        this.ownerships.remove(ownership);
    }

    @Override
    public int getMoney() {
        return this.money;
    }

    @Override
    public void setMoney(final int amount) {
        this.money = amount;
    }

    @Override
    public boolean areDebtsPaid() {
        return this.debtsPaid;
    }

    @Override
    public void setDebts(final boolean arePaid) {
        this.debtsPaid = arePaid;
    }

    @Override
    public boolean isHuman() {
        return this.human;
    }

    @Override
    public void setIfIsTheFirstLaunch(final boolean isTheFirst) {
        this.isTheFirst = isTheFirst;
    }

    @Override
    public boolean isTheFirtsLaunch() {
        return this.isTheFirst;
    }

}
