package model.level.types;

import java.util.Optional;

/**
 * Interface for a factory that creates {@link Level} instances based on
 * {@link LevelType}.
 * <p>
 * Implementations of this interface are responsible for instantiating the
 * correct
 * level configuration according to the provided level type.
 * </p>
 */
public interface ILevelFactory {

    /**
     * Creates a level instance corresponding to the specified {@link LevelType}.
     *
     * @param lvlType the type of level to create
     * @return an {@link Optional} containing the created {@link Level} if
     *         supported,
     *         or an empty optional if the type is not handled
     */
    Optional<Level> createLevel(final LevelType lvlType);
}
