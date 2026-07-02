package net.pokemonbt.model.pokemon;

import java.util.Locale;
import java.util.Objects;
import java.util.Optional;

/**
 * Enum representing each of a {@link Pokemon}'s statistic.
 */
public enum PokeStatType {
    HP_MAX("Max HP"),
    ATK("Attack"),
    DEF("Defence"),
    SPA("Special Attack"),
    SPD("Special Defence"),
    SPE("Speed"),
    EVA("Evasion"),
    ACC("Accuracy"),
    WEIGHT("Weight");

    private final String displayAs;

    /**
     * Use the enum's values instead of the constructor.
     *
     * @param displayAs The string the value will be
     *                  shown to the player as.
     */
    PokeStatType(final String displayAs) {
        this.displayAs = displayAs;
    }

    /**
     * Gets the statistic's string that will be displayed
     * in-game for that statistic.
     *
     * @return The display name string of that statistic.
     */
    public String displayAs() {
        return this.displayAs;
    }

    /**
     * @param str The string to convert to a {@link PokeStatType}.
     * @return  An {@link Optional} {@link PokeStatType} with
     *          the same name as the given string, or an empty
     *          optional if it could not be matched to anything.
     */
    public static Optional<PokeStatType> stringToStatType(final String str) {
        Objects.requireNonNull(str);
        if (str.isBlank()) {
            return Optional.empty();
        }
        for (final var i : values()) {
            if (i.name().equals(
                    str.toUpperCase(Locale.ROOT)
                            .replace(' ', '_'))
            ) {
                return Optional.of(i);
            }
        }
        return Optional.empty();
    }
}
