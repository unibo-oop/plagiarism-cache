package it.unibo.model.launchedgame.impl;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.model.camera.impl.AltitudeManager;
import it.unibo.model.launchedgame.api.AbstractLaunchedState;
import it.unibo.model.launchedgame.api.LaunchedGame;
import it.unibo.model.physics.collision.api.CollisionManager;
import it.unibo.model.score.api.ScoreManager;
import it.unibo.model.world.impl.World;

/**
 * Represents the state where the game is actively being played.
 * Handles the core gameplay logic.
 */
public class RunningState extends AbstractLaunchedState {

    /**
     * The world instance representing the game environment.
     */
    private final World world;

    /**
     * The collision manager responsible for handling collisions in the game.
     */
    private final CollisionManager collisionManager;

    /**
     * The altitude manager responsible for tracking the player's altitude.
     */
    private final AltitudeManager altitudeManager;

    /**
     * The score manager responsible for tracking and updating the player's score.
     */
    private final ScoreManager scoreManager;

    /**
     * Constructs a new RunningState.
     * 
     * @param launchedGame     the game context.
     * @param world            the world instance for the game.
     * @param collisionManager the collision manager for handling collisions.
     * @param altitudeManager  the altitude manager for tracking altitude.
     * @param scoreManager     the score manager for tracking and updating the
     *                         score.
     */
    @SuppressFBWarnings(value = "EI_EXPOSE_REP2", justification = "Score and Altitude managers are necessary for the game "
            + "loop beacause they are responsible for updating the score and altitude during the game execution.")
    public RunningState(final LaunchedGame launchedGame, final World world, final CollisionManager collisionManager,
            final AltitudeManager altitudeManager, final ScoreManager scoreManager) {
        super(launchedGame);
        this.world = world;
        this.collisionManager = collisionManager;
        this.altitudeManager = altitudeManager;
        this.scoreManager = scoreManager;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onEnter() {
        this.launchedGame.setRunning(true);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute(final double dt) {
        world.getRealWorld().getAlien().updatePosition(dt, world.getRealWorld().getBoundWorld(), this.launchedGame);
        world.getRealWorld().getMovingPlatforms()
                .forEach(p -> p.updatePosition(dt, world.getRealWorld().getBoundWorld().getBoundX()));
        collisionManager.detectCollisions(world.getRealWorld(), this.launchedGame);
        scoreManager.updateScore(world.getRealWorld().getAlien().getPosY());
        altitudeManager.verifiedAltitude();
    }

}
