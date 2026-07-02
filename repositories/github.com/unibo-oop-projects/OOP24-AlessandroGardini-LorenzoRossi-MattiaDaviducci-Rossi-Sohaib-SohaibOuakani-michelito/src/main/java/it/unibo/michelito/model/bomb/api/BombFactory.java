package it.unibo.michelito.model.bomb.api;

import it.unibo.michelito.model.bomb.impl.BombFactoryImpl;
import it.unibo.michelito.util.Position;

/**
 * A factory for creating bombs.
 */
public interface BombFactory {
    /**
     *
     * @param bombType The type of the bomb.
     * @param position The position of the bomb.
     * @return The {@link Bomb}.
     */
    static Bomb createFromBombType(final BombType bombType, final Position position) {
        final BombFactory factory = new BombFactoryImpl();
        return switch (bombType) {
            case STANDARD -> factory.createStandardBomb(position);
            case NUKE -> factory.createNukeBomb(position);
            case PASS_THROUGH -> factory.createPassThroughBomb(position);
            case LONG -> factory.createLongBomb(position);
        };
    }

    /**
     * Creates a standard bomb.
     *
     * @param position The position of the bomb.
     * @return The {@link Bomb}.
     */
    Bomb createStandardBomb(Position position);

    /**
     * Creates a nuke bomb.
     *
     * @param position The position of the bomb.
     * @return The {@link Bomb}.
     */
    Bomb createNukeBomb(Position position);

    /**
     * Creates a pass-through bomb.
     *
     * @param position The position of the bomb.
     * @return The {@link Bomb}.
     */
    Bomb createPassThroughBomb(Position position);

    /**
     * Creates a long bomb.
     *
     * @param position The position of the bomb.
     * @return The {@link Bomb}.
     */
    Bomb createLongBomb(Position position);
}
