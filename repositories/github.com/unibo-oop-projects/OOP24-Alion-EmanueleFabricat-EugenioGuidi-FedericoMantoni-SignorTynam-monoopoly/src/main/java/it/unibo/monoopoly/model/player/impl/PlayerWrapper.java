package it.unibo.monoopoly.model.player.impl;

import java.util.Set;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.monoopoly.model.gameboard.api.Buyable;
import it.unibo.monoopoly.model.player.api.Player;

/**
 * Proxy of {@link PlayerImpl}.
 */
public class PlayerWrapper implements Player {

    private final Player player;
    private static final String START_MESSAGE_ERROR = "The method";
    private static final String FINISH_MESSAGE_ERROR = "is not implemented in the Proxy pattern.";

    /**
     * Constructor of the proxy.
     * 
     * @param player the real Player.
     */
    @SuppressFBWarnings(value = "EI_EXPOSE_REP", justification = "Suppressing according to pattern Proxy")
    public PlayerWrapper(final Player player) {
        this.player = player;
    }

    /**
     * Proxy version.
     * {@inheritDoc}
     */
    @Override
    public String getName() {
        return String.valueOf(this.player.getName());
    }

    /**
     * Proxy version.
     * {@inheritDoc}
     */
    @Override
    public int getMoneyAmount() {
        return this.player.getMoneyAmount();
    }

    /**
     * Proxy version.
     * {@inheritDoc}
     */
    @Override
    public int getActualPosition() {
        return this.player.getActualPosition();
    }

    /**
     * Proxy version.
     * {@inheritDoc}
     */
    @Override
    public boolean isPrisoned() {
        return this.player.isPrisoned();
    }

    /**
     * Proxy version.
     * {@inheritDoc}
     */
    @Override
    public void changePosition(final int position) {
        throw new UnsupportedOperationException(START_MESSAGE_ERROR + " 'changePosition' " + FINISH_MESSAGE_ERROR);
    }

    /**
     * Proxy version.
     * {@inheritDoc}
     */
    @Override
    public boolean isPayable(final int amount) {
        return this.player.isPayable(amount);
    }

    /**
     * Proxy version.
     * {@inheritDoc}
     */
    @Override
    public void pay(final int amount) {
        throw new UnsupportedOperationException(START_MESSAGE_ERROR + " 'pay' " + FINISH_MESSAGE_ERROR);
    }

    /**
     * Proxy version.
     * {@inheritDoc}
     */
    @Override
    public void receive(final int amount) {
        throw new UnsupportedOperationException(START_MESSAGE_ERROR + " 'receive' " + FINISH_MESSAGE_ERROR);
    }

    /**
     * Proxy version.
     * {@inheritDoc}
     */
    @Override
    public boolean addProperty(final Buyable property) {
        throw new UnsupportedOperationException(START_MESSAGE_ERROR + " 'addProperty' " + FINISH_MESSAGE_ERROR);
    }

    /**
     * Proxy version.
     * {@inheritDoc}
     */
    @Override
    public boolean removeProperty(final Buyable property) {
        throw new UnsupportedOperationException(START_MESSAGE_ERROR + " 'removeProperty' " + FINISH_MESSAGE_ERROR);
    }

    /**
     * Proxy version.
     * {@inheritDoc}
     */
    @Override
    public Set<Buyable> getProperties() {
        return Set.copyOf(this.player.getProperties());
    }

    /**
     * Proxy version.
     * {@inheritDoc}
     */
    @Override
    public void inBankrupt() {
        throw new UnsupportedOperationException(START_MESSAGE_ERROR + " 'inBankrupt' " + FINISH_MESSAGE_ERROR);
    }

    /**
     * Proxy version.
     * {@inheritDoc}
     */
    @Override
    public boolean isBankrupt() {
        return this.player.isBankrupt();
    }

    /**
     * Proxy version.
     * {@inheritDoc}
     */
    @Override
    public void addGetOutOfJailCard() {
        throw new UnsupportedOperationException(START_MESSAGE_ERROR + " 'addGetOutOfJailCard' " + FINISH_MESSAGE_ERROR);
    }

    /**
     * Proxy version.
     * {@inheritDoc}
     */
    @Override
    public int getFreeJailCards() {
        return this.player.getFreeJailCards();
    }

    /**
     * Proxy version.
     * {@inheritDoc}
     */
    @Override
    public boolean useGetOutOfJailCard() {
        throw new UnsupportedOperationException(START_MESSAGE_ERROR + " 'useGetOutOfJailCard' " + FINISH_MESSAGE_ERROR);
    }

    /**
     * Proxy version.
     * {@inheritDoc}
     */
    @Override
    public void setPrisoned() {
        throw new UnsupportedOperationException(START_MESSAGE_ERROR + " 'setPrisoned' " + FINISH_MESSAGE_ERROR);
    }

    /**
     * Proxy version.
     * {@inheritDoc}
     */
    @Override
    public void releaseFromPrison() {
        throw new UnsupportedOperationException(START_MESSAGE_ERROR + " 'releaseFromPrison' " + FINISH_MESSAGE_ERROR);
    }

}
