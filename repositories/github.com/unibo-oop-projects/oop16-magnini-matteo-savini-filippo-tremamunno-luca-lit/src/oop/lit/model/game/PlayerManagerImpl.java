package oop.lit.model.game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import oop.lit.model.PlayerModel;
import oop.lit.model.groups.SelectableElementGroup;
import oop.lit.util.ObservableImpl;

/**
 * A bsic implementation of the playerManager which supports all operations.
 *
 * @param <P> the type of Player stored
 * @param <H> the type of Group representing a player hand
 */
public class PlayerManagerImpl<P extends PlayerModel, H extends SelectableElementGroup<?>> extends ObservableImpl implements PlayerManager<P, H> {
    /**
     * 
     */
    private static final long serialVersionUID = -3471210123100449457L;

    /**
     * A message informing the provided player was not added to the manager.
     */
    protected static final String NO_PLAYER_ERR = "Provided player was not added to this player manager.";

    private final List<P> players = new ArrayList<>();
    private List<P> activePlayers = new ArrayList<>();
    private P playingPlayer; //= null
    private final Map<P, H> playerHands = new HashMap<>();

    @Override
    public boolean addPlayer(final P player) {
        if (this.players.contains(player)) {
            return false;
        }
        this.players.add(player);
        this.notifyObservers();
        return true;
    }
    @Override
    public boolean removePlayer(final PlayerModel player) {
        Objects.requireNonNull(player);
        final boolean res = this.players.remove(player);
        if (!res) {
            return false;
        }
        //se era il playing player lo cavo
        if (player.equals(playingPlayer)) {
            this.playingPlayer = null;
        }
        //se era tra i player di turno lo cavo.
        this.activePlayers.remove(player);
        //se aveva una mano associata la cavo
        final H hand = this.playerHands.remove(player);
        if (hand != null) {
            hand.removed();
        }
        this.notifyObservers();
        return true;
    }
    @Override
    public List<P> getPlayers() {
        return new ArrayList<>(this.players);
    }

//turns
    @Override
    public boolean isPlayerTurn(final PlayerModel player) {
        return this.activePlayers.contains(player);
    }
    @Override
    public List<P> getActivePlayers() {
        return new ArrayList<>(this.activePlayers);
    }
    @Override
    public void setActivePlayer(final PlayerModel... players) {
        final List<P> newActive = new ArrayList<>();
        for (final PlayerModel player : Arrays.asList(players)) {
            final Optional<P> actualPlayer = this.getActualPlayer(player);
            if (!actualPlayer.isPresent()) {
                throw new IllegalArgumentException(NO_PLAYER_ERR);
            }
            newActive.add(actualPlayer.get());
        }
        this.activePlayers = newActive;
        this.notifyObservers();
    }

//playing (più giocatori su stesso computer)
    @Override
    public boolean setPlayingPlayer(final PlayerModel player) {
        Objects.requireNonNull(player);
        if (player.equals(playingPlayer)) {
            return false;
        }

        final Optional<P> actualPlayer = getActualPlayer(player);
        if (!actualPlayer.isPresent()) {
            throw new IllegalArgumentException(NO_PLAYER_ERR);
        }
        this.playingPlayer = actualPlayer.get();
        this.notifyObservers();
        return true;
    }
    @Override
    public Optional<P> getPlayingPlayer() {
        return Optional.ofNullable(this.playingPlayer);
    }

//PlayerHand
    @Override
    public void addPlayerHand(final PlayerModel player, final H playerHand) {
        if (playerHands.containsKey(player)) {
            throw new IllegalArgumentException("provided player already has an hand associated.");
        }
        final Optional<P> actualPlayer = this.getActualPlayer(player); 
        if (!actualPlayer.isPresent()) {
            throw new IllegalArgumentException(NO_PLAYER_ERR);
        }
        playerHands.put(actualPlayer.get(), playerHand);
        this.notifyObservers();
    }
    @Override
    public Optional<H> getPlayerHand(final PlayerModel player) {
        return Optional.ofNullable(playerHands.get(player));
    }
    @Override
    public Map<P, H> getAllPlayersHand() {
        return new HashMap<>(this.playerHands);
    }

    /**
     * Get the specific type of player from the generic one.
     *
     * @param player
     *      a player.
     * @return
     *      an optional containing the actual player, if present.
     */
    protected Optional<P> getActualPlayer(final PlayerModel player) {
        final int index = this.players.indexOf(player);
        if (index == -1) {
            return Optional.empty();
        }
        return Optional.of(this.players.get(index));
    }
}
