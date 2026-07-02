package it.unibo.javacrush.model.impl;

import it.unibo.javacrush.model.api.Board;
import it.unibo.javacrush.model.api.GameMatchContext;
import it.unibo.javacrush.model.api.LevelConfig;
import it.unibo.javacrush.model.api.MatchManager;
import it.unibo.javacrush.model.api.MoveEngine;
import it.unibo.javacrush.model.api.PhysicsHandler;
import it.unibo.javacrush.model.api.Session;
import it.unibo.javacrush.model.api.StallEngine;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

/**
 * Implementation of the {@link GameMatchContext} interface.
 */
@SuppressFBWarnings(
    value = "EI_EXPOSE_REP", 
    justification = "Shared state is intentional for MVC"
)
public class GameMatchContextImpl implements GameMatchContext {

    private final Board board;
    private final PhysicsHandler physicsHandler;
    private final LevelConfig levelConfig;
    private final MoveEngine moveEngine;
    private final MatchManager matchManager;
    private final StallEngine stallEngine;
    private final Session session;

    /**
     * Constructor for the GameMatchContextImpl class.
     * 
     * @param board the game board
     * @param physicsHandler the physics handler for the game
     * @param levelConfig the configuration of the level
     * @param moveEngine the engine responsible for handling moves
     * @param matchManager the manager responsible for handling matches
     * @param stallEngine the engine responsible for handling stalls
     * @param session the current game session
     */
    public GameMatchContextImpl(final Board board, final PhysicsHandler physicsHandler, final LevelConfig levelConfig,
            final MoveEngine moveEngine, final MatchManager matchManager, final StallEngine stallEngine,
            final Session session) {
        this.board = board;
        this.physicsHandler = physicsHandler;
        this.levelConfig = levelConfig;
        this.moveEngine = moveEngine;
        this.matchManager = matchManager;
        this.stallEngine = stallEngine;
        this.session = session;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Board getBoard() {
        return board;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public StallEngine getStallEngine() {
        return stallEngine;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Session getSession() {
        return session;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PhysicsHandler getPhysicsHandler() {
        return physicsHandler;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LevelConfig getLevelConfig() {
        return levelConfig;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public MoveEngine getMoveEngine() {
        return moveEngine;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public MatchManager getMatchManager() {
        return matchManager;
    }

}
