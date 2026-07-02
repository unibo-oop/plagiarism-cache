package it.unibo.monopoli.model.cards;

import java.util.List;
import java.util.Optional;

import it.unibo.monopoli.model.actions.Action;
import it.unibo.monopoli.model.mainunits.Player;

/**
 * This is a classic implementation of {@link Card} used in Monopoly.
 *
 */
public class ClassicCard implements Card {

    private final String description;
    private final int cardId;
    private final Optional<List<Action>> actions;
    private Optional<Player> player;

    /**
     * Constructs an instance of the {@link ClassicCard}. It needs a description
     * of the possible {@link Action} to take if you draw the card, an ID and
     * all the {@link Action} to take (also zero).
     * 
     * @param description - {@link Card}'s description
     * @param id - {@link Card}'s ID
     * @param actions - {@link Card}'s possible {@link Action}s
     */
    public ClassicCard(final String description, final int id, final Optional<List<Action>> actions) {
        this.description = description;
        this.cardId = id;
        this.actions = actions;
        this.player = Optional.empty();
    }

    @Override
    public int getID() {
        return this.cardId;
    }

    @Override
    public String getDescription() {
        return this.description;
    }

    @Override
    public Optional<List<Action>> getActions() {
        return this.actions;
    }

    @Override
    public Optional<Player> getPlayer() {
        return this.player;
    }

    @Override
    public void setPlayer(final Player player) {
        this.player = Optional.ofNullable(player);
    }
}