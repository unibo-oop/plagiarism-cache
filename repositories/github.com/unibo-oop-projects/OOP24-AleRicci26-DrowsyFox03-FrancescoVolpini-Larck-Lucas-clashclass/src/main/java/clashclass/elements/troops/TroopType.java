package clashclass.elements.troops;

import java.util.Arrays;
import java.util.Optional;

/**
 * Represents a troop type.
 */
public enum TroopType {
    BARBARIAN("barbarian"),
    ARCHER("archer");

    private final String name;

    /**
     * Constructs the troop type.
     *
     * @param name the name of the troop
     */
    TroopType(final String name) {
        this.name = name;
    }

    /**
     * Gets the enum value from the name.
     *
     * @param id the name
     *
     * @return the enum value
     */
    public static Optional<TroopType> getValueFromName(final String id) {
        return Arrays.stream(values())
                .filter(x -> x.name.equalsIgnoreCase(id))
                .findFirst();
    }

    /**
     * Gets the name of the troop.
     *
     * @return the name of the troop
     */
    public String getName() {
        return this.name;
    }
}
