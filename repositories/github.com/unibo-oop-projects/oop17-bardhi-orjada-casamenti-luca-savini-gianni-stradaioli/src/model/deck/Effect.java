package model.deck;

import java.util.List;

import model.player.Player;

/**
 *
 */
public interface Effect {
    /**
     * 
     */
    enum Target {
        ALL, ME, OTHER
    }

    int MIN = 1;
    int SMALL = 2;
    int MEDIUM = 3;
    int HIGH = 4;
    int MAX = 5;

    /**
     * @param player
     *            is player who's playing turn.
     * @param other
     *            is the list of players who are waiting his turn.
     */
    void active(Player player, List<Player> other);
}
