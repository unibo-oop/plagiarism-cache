package clashclass.elements.buildings;

import java.util.Arrays;
import java.util.Optional;

/**
 * Represents the types of buildings in the village.
 */
public enum VillageElementData {
    TOWN_HALL("town-hall", 3, 3),
    WALL("wall", 1, 1),
    CANNON("cannon", 2, 2),
    ARCHER_TOWER("archer-tower", 2, 2),
    GOLD_STORAGE("gold-storage", 2, 2),
    ELIXIR_STORAGE("elixir-storage", 2, 2),
    GOLD_EXTRACTOR("gold-extractor", 2, 2),
    ELIXIR_EXTRACTOR("elixir-extractor", 2, 2),
    ARMY_CAMP("army-camp", 3, 3),
    BARRACKS("barracks", 3, 3);

    private final String name;
    private final int rowSpan;
    private final int colSpan;

    /**
     * Constructs the village element data.
     *
     * @param name the name of the element
     * @param rowSpan the number of horizontal tiles of the element
     * @param colSpan the number of vertical tiles of the element
     */
    VillageElementData(final String name, final int rowSpan, final int colSpan) {
        this.name = name;
        this.rowSpan = rowSpan;
        this.colSpan = colSpan;
    }

    /**
     * Gets the enum value from name.
     *
     * @param id the name
     *
     * @return the enum value
     */
    public static Optional<VillageElementData> getValueFromName(final String id) {
        return Arrays.stream(values())
                .filter(x -> x.name.equalsIgnoreCase(id))
                .findFirst();
    }

    /**
     * Gets the name.
     *
     * @return the name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Gets the row span.
     *
     * @return the row span
     */
    public int getRowSpan() {
        return this.rowSpan;
    }

    /**
     * Gets the col span.
     *
     * @return the col span
     */
    public int getColSpan() {
        return this.colSpan;
    }
}
