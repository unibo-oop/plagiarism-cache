package it.unibo.michelito.model.enemy.impl.ai;

import it.unibo.michelito.model.enemy.api.ai.MovementFactory;
import it.unibo.michelito.model.enemy.api.ai.Movement;
import it.unibo.michelito.model.enemy.api.ai.MovementType;

/**
 * Implementation of {@link MovementFactory}.
 */
public final class MovementFactoryImpl implements MovementFactory {
    private static final double SLEEPING_VELOCITY = 0.00;
    private static final double CHILLING_VELOCITY = 0.012;
    private static final double SEARCHING_VELOCITY = 0.017;

    @Override
    public Movement chilling() {
        return new AbstractMovement() {
            @Override
             double velocity() {
                return CHILLING_VELOCITY;
            }

            @Override
            MovementType movementType() {
                return MovementType.CHILLING;
            }
        };
    }

    @Override
    public Movement sleeping() {
        return new AbstractMovement() {
            @Override
            double velocity() {
                return SLEEPING_VELOCITY;
            }

            @Override
            MovementType movementType() {
                return MovementType.SLEEPING;
            }
        };
    }

    @Override
    public Movement searching() {
        return new AbstractMovement() {
            @Override
            double velocity() {
                return SEARCHING_VELOCITY;
            }

            @Override
            MovementType movementType() {
                return MovementType.SEARCHING;
            }
        };
    }
}
