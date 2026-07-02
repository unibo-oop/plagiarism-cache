package it.unibo.michelito.model.enemy.impl.ai;

import it.unibo.michelito.model.enemy.api.ai.MoodAI;
import it.unibo.michelito.model.enemy.api.ai.MovementFactory;
import it.unibo.michelito.model.enemy.api.ai.MovementType;
import it.unibo.michelito.model.enemy.api.ai.Movement;
import it.unibo.michelito.model.maze.api.Maze;

/**
 * Implementation of {@link MoodAI}.
 */
public final class MoodAIImpl implements MoodAI {
    private static final long SLEEP_TIME = 5_000;
    private static final double DEATH_FOR_AVENGES = 0.70;
    private final MovementFactory movementFactory = new MovementFactoryImpl();
    private long createdTime;
    private final int initialEnemy;
    private MovementType movementType;

    /**
     * Constructor for {@link MoodAIImpl}.
     * @param maze the maze so it can accede information about the current state of the maze.
     */
    public MoodAIImpl(final Maze maze) {
        this.createdTime = 0;
        initialEnemy = maze.getEnemies().size();
        movementType = MovementType.SLEEPING;
    }

    private Movement getCurrentMovement() {
        return switch (this.movementType) {
            case SLEEPING -> movementFactory.sleeping();
            case CHILLING -> movementFactory.chilling();
            case SEARCHING -> movementFactory.searching();
        };
    }

    @Override
    public Movement getMovement() {
        return getCurrentMovement();
    }

    @Override
    public void update(final long deltaTime, final Maze maze) {
        this.createdTime = createdTime + deltaTime;
        if (createdTime >= SLEEP_TIME) {
            this.movementType = MovementType.CHILLING;
        }
        if (maze.getEnemies().size() < initialEnemy * DEATH_FOR_AVENGES) {
            this.movementType = MovementType.SEARCHING;
        }
    }
}
