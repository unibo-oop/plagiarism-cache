package it.unibo.monopoli.model.cards;

import java.util.List;
import java.util.Optional;

import it.unibo.monopoli.model.actions.Action;
import it.unibo.monopoli.model.mainunits.Player;

/**
 * This interface represent all the {@link Card}s in that game. They all have a
 * name, a description and an ID. Some of these have also one or more
 * {@link Action}s to execute.
 *
 */
public interface Card {

    /**
     * Returns the ID of the {@link Card}.
     * 
     * @return {@link Card}'s ID
     */
    int getID();

    /**
     * Return the description of the specific {@link Card}.
     * 
     * @return {@link Card}'s description
     */
    String getDescription();

    /**
     * Return an {@link Optional} {@link List} of {@link Action}s of the
     * specific {@link Card}.
     * 
     * @return {@link Card}'s {@link Action}s
     */
    Optional<List<Action>> getActions();

    /**
     * Returns the {@link Optional} {@link Player} owner of this {@link Card}.
     * 
     * @return the {@link Optional} {@link Player} owner of this {@link Card}
     */
    Optional<Player> getPlayer();

    /**
     * Sets the {@link Player} owner of the {@link Card}.
     * 
     * @param player
     *            - the {@link Player} owner of the {@link Card}.
     */
    void setPlayer(Player player);

}
