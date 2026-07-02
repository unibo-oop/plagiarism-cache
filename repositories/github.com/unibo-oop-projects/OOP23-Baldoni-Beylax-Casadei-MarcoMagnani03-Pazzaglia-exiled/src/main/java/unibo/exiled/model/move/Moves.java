package unibo.exiled.model.move;

import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;
import java.util.Random;
import java.util.Set;
import java.util.Arrays;
import java.util.Optional;
import java.util.List;

import unibo.exiled.utilities.ElementalType;

/**
 * A container of every move usable in the game.
 */
public final class Moves {

    private static final Random RANDOM = new Random();

    private Moves() {
    }

    /**
     * Gets first move found with the selected name.
     *
     * @param name The name of the move to search.
     * @return An optional containing the move if a move with the selected name exists, Optional.empty() otherwise.
     */
    public static MagicMove getMoveByName(final String name) {
        final AtomicReference<MagicMove> out = new AtomicReference<>();
        Arrays.stream(MagicMove.values())
                .filter(move -> move.name().equals(name)).findFirst().ifPresent(out::set);
        return out.get();
    }

    /**
     * Gets every move in the game.
     *
     * @return An unmodifiable set of every move in the game.
     */
    public static Set<MagicMove> getAllMagicMoves() {
        return Arrays.stream(MagicMove.values()).collect(Collectors.toSet());
    }

    /**
     * Gets every move of the selected type.
     *
     * @param type  The type of the moves to get.
     * @param level The necessary level to learn the move.
     * @return An unmodifiable set of moves of the selected type.
     */
    public static Set<MagicMove> getAllMagicMovesOfType(final ElementalType type, final int level) {
        return Arrays.stream(MagicMove.values())
                .filter(magicMove -> magicMove.getType().equals(type) && magicMove.getMinimumLevelToLearn() <= level)
                .collect(Collectors.toUnmodifiableSet());
    }

    /**
     * Gets a random move of the selected type.
     *
     * @param type  The type of the move to get.
     * @param level The necessary level to learn the move.
     * @return An optional containing the random move of the selected
     * type if at least one is found, optional.Empty()
     * otherwise.
     */
    public static Optional<MagicMove> getRandomMagicMoveByType(final ElementalType type, final int level) {
        final List<MagicMove> movesOfType = Arrays.stream(MagicMove.values())
                .filter(magicMove -> magicMove.getType().equals(type) && magicMove.getMinimumLevelToLearn() <= level)
                .toList();

        if (movesOfType.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(movesOfType.get(RANDOM.nextInt(movesOfType.size())));
    }

    /**
     * Gets a random move.
     *
     * @param level The necessary level to learn the move.
     * @return A random move of any type.
     */
    public static MagicMove getTotallyRandomMove(final int level) {
        final int bound = (int) Arrays.stream(MagicMove.values())
                .filter(magicMove -> magicMove.getMinimumLevelToLearn() <= level).count();
        return Arrays.stream(MagicMove.values())
                .filter(magicMove -> magicMove.getMinimumLevelToLearn() <= level)
                .toList().get(RANDOM.nextInt(bound));
    }
}
