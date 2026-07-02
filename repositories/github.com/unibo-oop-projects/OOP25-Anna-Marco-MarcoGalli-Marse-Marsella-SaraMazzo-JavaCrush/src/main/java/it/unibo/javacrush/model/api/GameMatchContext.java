package it.unibo.javacrush.model.api;

/**
 * Represents the context of a game match, providing access to all the necessary components.
 */
public interface GameMatchContext {

    /**
     * Returns the board associated with the game match.
     *
     * @return the board
     */
    Board getBoard();

    /**
     * Returns the physics handler associated with the game match.
     *
     * @return the physics handler
     */
    PhysicsHandler getPhysicsHandler();

    /**
     * Returns the level configuration associated with the game match.
     *
     * @return the level configuration
     */
    LevelConfig getLevelConfig();

    /**
     * Returns the move engine associated with the game match.
     *
     * @return the move engine
     */
    MoveEngine getMoveEngine();

    /**
     * Returns the match manager associated with the game match.
     *
     * @return the match manager
     */
    MatchManager getMatchManager();

    /**
     * Returns the stall engine associated with the game match.
     *
     * @return the stall engine
     */
    StallEngine getStallEngine();

    /**
     * Returns the session associated with the game match.
     *
     * @return the session
     */
    Session getSession();
}
