package it.unibo.monoopoly.model.player.impl;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import it.unibo.monoopoly.model.gameboard.api.Buyable;
import it.unibo.monoopoly.model.player.api.Player;

/**
 * Represents the player of the game.
 */
public class PlayerImpl implements Player {

    private static final int MAX_CELL = 39;
    private final String name;
    private int moneyAmount;
    private int actualPosition;
    private boolean prisoned;
    private boolean bankrupt;
    private int freeJailCards;
    private final Set<Buyable> properties;

    /**
     * Constructor for the player.
     * 
     * @param name           the name of the player.
     * @param moneyAmount    the amount of money the player has.
     * @param actualPosition the current position of the player.
     * @param prisoned       true if the player is in prison, false otherwise.
     */
    public PlayerImpl(final String name, final int moneyAmount, final int actualPosition, final boolean prisoned) {
        this.name = name;
        this.moneyAmount = validatePositive(moneyAmount, "Money amount cannot be negative");
        this.actualPosition = validatePositive(actualPosition, "Position cannot be negative");
        this.prisoned = prisoned;
        this.bankrupt = false;
        this.freeJailCards = 0;
        this.properties = new HashSet<>();
    }

    /**
     * Validates that the given value is positive.
     * 
     * @param value        the value to be validated.
     * @param errorMessage the error message to be thrown if the value is not
     *                     positive.
     * @return the value if it is positive.
     * @throws IllegalArgumentException if the value is not positive.
     */
    private int validatePositive(final int value, final String errorMessage) {
        return Optional.of(value).filter(i -> i >= 0).orElseThrow(() -> new IllegalArgumentException(errorMessage));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getName() {
        return this.name;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getMoneyAmount() {
        return this.moneyAmount;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getActualPosition() {
        return this.actualPosition;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isPrisoned() {
        return this.prisoned;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isPayable(final int amount) {
        return this.moneyAmount >= amount;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void pay(final int amount) {
        this.moneyAmount -= validatePositive(amount, "Amount cannot be negative");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void receive(final int amount) {
        this.moneyAmount += validatePositive(amount, "Amount cannot be negative");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean addProperty(final Buyable property) {
        return this.properties.add(property);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean removeProperty(final Buyable property) {
        return this.properties.remove(property);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<Buyable> getProperties() {
        return Set.copyOf(this.properties);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void inBankrupt() {
        this.bankrupt = true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isBankrupt() {
        return this.bankrupt;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addGetOutOfJailCard() {
        this.freeJailCards++;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getFreeJailCards() {
        return this.freeJailCards;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean useGetOutOfJailCard() {
        return this.freeJailCards-- > 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setPrisoned() {
        this.prisoned = true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void releaseFromPrison() {
        this.prisoned = false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void changePosition(final int position) {
        this.actualPosition = Optional.of(position)
                .filter(i -> i >= 0 && i <= MAX_CELL)
                .orElseThrow(() -> new IllegalArgumentException("Position must be between 0 and 39"));
    }
}
