package model.managers;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import model.map.ObservableGameMap;
import model.player.Player;

/**
 * Handles the turns of the current game.
 */
public class TurnManagerImpl implements TurnManager {

    private final List<Player> inGamePlayers;
    private Player actualTurnPlayer;
    private Iterator<Player> playerIterator;

    /**
     * 
     * @param players the list of players selected at the starting menu
     */
    public TurnManagerImpl(final List<Player> players) {
        this.inGamePlayers = players.stream().sorted((x, y) -> x.getId() - y.getId()).collect(Collectors.toList());
        this.playerIterator = this.inGamePlayers.iterator();
        this.actualTurnPlayer = this.playerIterator.next();
    }

    /**
     * At the end of each turn a player wins if he completed his objective, this
     * method watches the map and the player status to check if he won.
     * 
     * @param player the player to check victory
     * 
     * @param map    the map to be checked
     * 
     * @return whether the player won the game
     */
    private boolean isWinner(final Player player, final ObservableGameMap map) {
        return this.actualTurnPlayer.getObjective().isCompleted(map, player);
    }

    private void startTurn() {
        if (this.playerIterator.hasNext()) {
            this.actualTurnPlayer = this.playerIterator.next();
        } else {
            this.playerIterator = this.inGamePlayers.iterator();
            this.actualTurnPlayer = this.playerIterator.next();
        }
    }

    /** {@inheritDoc} **/
    @Override
    public void nextTurn() {
        this.startTurn();
    }

    /** {@inheritDoc} **/
    @Override
    public Optional<Player> getWinner(final ObservableGameMap map) {
        return this.isWinner(actualTurnPlayer, map) ? Optional.of(actualTurnPlayer) : Optional.empty();
    }

    /** {@inheritDoc} **/
    @Override
    public Player getTurnPlayer() {
        return this.actualTurnPlayer;
    }

    /** {@inheritDoc} **/
    @Override
    public void removePlayer(final Player player) {
        this.inGamePlayers.remove(player);
        this.playerIterator = this.inGamePlayers.iterator();
        while (this.playerIterator.hasNext()) {
            if (this.playerIterator.next().equals(this.actualTurnPlayer)) {
                break;
            }
        }
    }

    /** {@inheritDoc} **/
    @Override
    public String toString() {
        return "TurnManager, current player: " + this.actualTurnPlayer.toString();
    }

}
