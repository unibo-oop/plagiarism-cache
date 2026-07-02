package it.unibo.briscoola.model.impl.player;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import it.unibo.briscoola.model.api.card.Card;
import it.unibo.briscoola.model.api.player.Player;
import it.unibo.briscoola.model.impl.game.RoundStateImpl;

/**
 * Implementazion of {@link  Player} interface.
 * 
 * @author Riem Boukhama
 */
public class PlayerImpl implements Player {

    private final int id;
    private final String name;
    private final List<Card> hand;
    private final List<Card> pile;
    private int points;

    /**
     * Constructor that creates a new Player.
     *
     * @param id Id assigned to the player
     * @param name name of the player
     */
    public PlayerImpl(final int id, final String name) {
        this.id = id;
        this.name = name;
        this.points = 0;
        this.hand = new ArrayList<>();
        this.pile = new ArrayList<>();
    }

    /**
     * Constructor that creates a new {@link Player} based
     * on the parameter cpu player.
     *
     * @param player {@link Player} to copy
     */
    public PlayerImpl(final Player player) {
        this.id = player.getId();
        this.name = player.getName();
        this.points = player.getPoints();
        this.hand = new ArrayList<>(player.getHand());
        this.pile = new ArrayList<>(player.getPile());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Card playCard(final RoundStateImpl state) {
        return this.hand.removeFirst();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void receiveCard(final Card card) {
        this.hand.add(card);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void removeCard(final Card card) {
        this.hand.remove(card);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Card> getHand() {
        return List.copyOf(this.hand);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addtoPile(final Card card) {
        this.pile.add(card);
        this.points = this.points + card.getCardPoints();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Card> getPile() {
        return List.copyOf(this.pile);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void clearPile() {
        this.pile.clear();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getId() {
        return this.id;
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
     public int getPoints() {
        return this.points;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PlayerImpl copy() {
        return new PlayerImpl(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.hand, this.points);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final Player that = (Player) o;
        return this.id == that.getId() 
        && Objects.equals(this.hand, that.getHand()) 
        && Objects.equals(this.points, that.getPoints());
    }
}
