package controller.session;

import java.util.List;

import controller.player.Player;

/**
 * Interface that represents a session.
 */
public interface Session {

    /**
     * Start the session.
     * @return the final list of ordered players
     */
    List<Player> startSession();

}