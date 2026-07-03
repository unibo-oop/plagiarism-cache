package oop.lit.model.game;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import oop.lit.model.PlayerModel;
import oop.lit.model.groups.SelectableElementGroup;
import oop.lit.util.Observable;

/**
 * Used to manage players and their turn.
 * Observers will be notified when contained Players, PlayerHands, activePlayers or playingPlayers change.
 *
 * @param <P> the type of Player stored
 * @param <H> the type of Group representing a player hand
 */
public interface PlayerManager<P extends PlayerModel, H extends SelectableElementGroup<?>> extends Serializable, Observable {

    /**
     * Adds a player, only if there is no player with the same name already present.
     * Notifyes observers.
     *
     * @param player
     *      the player to be added.
     * @return
     *      if the player was added.
     */
    boolean addPlayer(P player);

    /**
     * Removes a player.
     * Notifyes observers.
     * If the player was the playingPlayer or was an activePlayer these will change.
     * If the player was associated with a PlayerHand this will also be removed.
     * @param player
     *      the player to be removed.
     * @return
     *      if the player was removed (it was contained in this).
     */
    boolean removePlayer(PlayerModel player);

    /**
     * @return
     *      all players contained.
     */
    List<P> getPlayers();

    //turns
    /**
     * @param player
     *      a player.
     * @return
     *      if it is the given player turn.
     */
    boolean isPlayerTurn(PlayerModel player);

    /**
     * @return
     *      the players playing the current turn.
     */
    List<P> getActivePlayers();

    /**
     * Sets the active player.
     * Notifyes observers.
     *
     * @param players
     *      the players that will be active.
     *
     * @throws IllegalArgumentException
     *      if one or more of the provided players weren't added before.
     */
    void setActivePlayer(PlayerModel... players);

    //playing (più giocatori su stesso computer)
    /**
     * Sets the playing player (used for multiplayer).
     * Notifyes observers.
     *
     * @param player
     *      the player that will be playing.
     * @return
     *      if the active player was changed (the given player wasn't the playing player).
     *
     * @throws IllegalArgumentException
     *      if the provided player was not added before.
     */
    boolean setPlayingPlayer(PlayerModel player);

    /**
     * @return the playing player or an empty optional if it was not set yet.
     */
    Optional<P> getPlayingPlayer();

//HANDS
    /**
     * Adds a PlayerHand relative to a player.
     * Notifyes observers.
     *
     * @param player
     *      the player whose hand is to be added
     * @param playerHand
     *      the provided player hand
     *
     * @throws IllegalArgumentException 
     *      if the provided player was not added or the and relative to that player was already added.
     */
    void addPlayerHand(PlayerModel player, H playerHand);

    /**
     * @param player
     *      a player
     * @return
     *      the hand of the provided player or an empty optional if there is no hand associated with the provided player.
     */
    Optional<H> getPlayerHand(PlayerModel player);

    /**
     * @return
     *      a map in which entry keys are players and values are their corresponding hand.
     *      If a player has no hand associated he will not be present as key.
     */
    Map<P, H> getAllPlayersHand();

}