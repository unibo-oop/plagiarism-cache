package net.pokemonbt.model.move;

import java.util.Locale;
import java.util.Objects;
import java.util.Optional;

/**
 * Each type of {@link Move} has a Category, 
 * it indactes the {@link PokeStat} used to attack and for defence.
 */
public enum MoveCategory {

    PHYSICAL("Physical"),
    SPECIAL("Special"),
    STATUS("Status");

    private final String category;

    /**
     * @param category The String associated to the enum value.
     */
    MoveCategory(final String category) {
        this.category = category;
    }

    /**
     * @param str The String that should rappresent a {@link Category}
     * @return The value that equals the display name of the Category.
     */
    public static Optional<MoveCategory> stringToType(final String str) {
        Objects.requireNonNull(str);
        if (str.isBlank()) {
            throw new IllegalArgumentException();
        }
        for (final var i : values()) {
            if (i.categoryType().toUpperCase(Locale.ROOT).equals(str)) {
                return Optional.of(i);
            }
        }
        return Optional.empty();
    }

    /**
     * Get the current type.
     * 
     * @return The String associated to the current value.
     */
    public String categoryType() {
        return this.category;
    }
}

