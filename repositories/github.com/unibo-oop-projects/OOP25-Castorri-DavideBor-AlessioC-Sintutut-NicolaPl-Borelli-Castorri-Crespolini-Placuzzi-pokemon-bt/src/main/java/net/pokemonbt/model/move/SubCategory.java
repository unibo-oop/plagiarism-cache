package net.pokemonbt.model.move;

import java.util.Objects;
import java.util.Optional;

import net.pokemonbt.model.pokemon.Pokemon;

/**
 * Describes how the {@link Move} is used by the {@link Pokemon}.
 * The way a {@link Move} is used impacts some interactions during a Battle.
 */
public enum SubCategory {
    AURA_PULSE("Aura Pulse"),
    BALL_BOMB("Ball Bomb"),
    BITING("Biting"),
    DANCE("Dance"),
    EXPLOSIVE("Explosive"),
    MULTISTRIKE("Multistrike"),
    ONE_HIT_KNOCKOUT("One Hit Knockout"),
    POWDER_SPORE("Powder Spore"),
    PUNCHING("Punching"),
    SLICING("Slicing"),
    SOUND("Sound"),
    WIND("Wind");

    private final String displayAs;

    SubCategory(final String displayAs) {
        this.displayAs = displayAs;
    }

    /**
     * @param str The String that should rappresent a {@link SubCategory}
     * @return The value that equals the display name of the SubCategory.
     */
    public static Optional<SubCategory> stringToType(final String str) {
        Objects.requireNonNull(str);
        if (str.isBlank()) {
            throw new IllegalArgumentException();
        }
        for (final var i : values()) {
            if (i.display().equals(str)) {
                return Optional.of(i);
            }
        }
        return Optional.empty();
    }

    /**
     * @return The name.
     */
    public String display() {
        return this.displayAs;
    }
}
