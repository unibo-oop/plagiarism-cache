package net.pokemonbt.model.pokemon;

import java.util.Objects;

/**
 * Class that represents a {@link Pokemon}'s type.
 */
public enum PokeType {
    NONE("None"),
    NORMAL("Normal"),
    FIGHTING("Fighting"),
    FLYING("Flying"),
    POISON("Poison"),
    GROUND("Ground"),
    ROCK("Rock"),
    BUG("Bug"),
    GHOST("Ghost"),
    STEEL("Steel"),
    FIRE("Fire"),
    WATER("Water"),
    GRASS("Grass"),
    ELECTRIC("Electric"),
    PSYCHIC("Psychic"),
    ICE("Ice"),
    DRAGON("Dragon"),
    DARK("Dark"),
    FAIRY("Fairy");

    private final String displayAs;

    /**
     * Use the enum's values instead of the constructor.
     *
     * @param displayAs The string the value will be
     *                  shown to the player as.
     */
    PokeType(final String displayAs) {
        this.displayAs = displayAs;
    }

    /**
     * Gets the given enum value's display string.
     *
     * @return The value's display string.
     */
    public String displayAs() {
        return this.displayAs;
    }

    /**
     * Given a string, returns the PokeType that corresponds to it,
     * comparing the displayName of each type. If it doesn't find one
     * that fits, it returns {@code PokeType.None}.
     *
     * @param str The string to check.
     * @return The PokeType found.
     */
    public static PokeType stringToType(final String str) {
        Objects.requireNonNull(str);
        if (str.isBlank()) {
            return NONE;
        }
        for (final var i : values()) {
            if (i.displayAs().equalsIgnoreCase(str)) {
                return i;
            }
        }
        return NONE;
    }
}
