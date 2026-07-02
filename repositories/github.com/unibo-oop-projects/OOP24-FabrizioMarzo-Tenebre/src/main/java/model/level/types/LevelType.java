package model.level.types;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * Enumeration representing the available level types in the domain model.
 * <p>
 * Each {@code LevelType} is associated with a display name string
 * that can be used to instantiate or identify specific level configurations.
 * </p>
 */
public enum LevelType {

    /**
     * The initial tutorial level.
     */
    LEVEL_TUTORIAL("LevelTutorial");

    // Future levels (to be enabled/added when implemented):
    // LEVEL_ONE("Level1"),
    // BOSS("LevelBoss");

    private final String displayName;

    /**
     * Constructs a new {@code LevelType} with the given display name.
     *
     * @param displayName the identifier string associated with the level type
     */
    LevelType(String displayName) {
        this.displayName = displayName;
    }

    /**
     * Returns the display name associated with this level type.
     *
     * @return the display name string
     */
    public String getDisplayName() {
        return displayName;
    }

    /**
     * Returns the next level in the sequence, if available.
     *
     * @return an {@link Optional} containing the next {@code LevelType},
     *         or an empty optional if this is the last one
     */
    public Optional<LevelType> next() {
        List<LevelType> levels = Arrays.asList(LevelType.values());
        int nextIndex = levels.indexOf(this) + 1;
        if (nextIndex < levels.size()) {
            return Optional.of(levels.get(nextIndex));
        } else {
            return Optional.empty();
        }
    }
}
