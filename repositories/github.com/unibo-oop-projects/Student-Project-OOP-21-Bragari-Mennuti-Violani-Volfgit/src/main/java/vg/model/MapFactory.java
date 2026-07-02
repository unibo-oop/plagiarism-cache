package vg.model;

import vg.model.levels.LEVEL;
import vg.model.levels.levelGenerator;
import vg.utils.V2D;

import java.io.IOException;

/**
 * Factory interface for creating {@link Map}.
 * @param <T> the type of position, usually {@link vg.utils.V2D}
 * @see Map
 * @see vg.utils.V2D
 * @see Stage
 */
public interface MapFactory<T> {
    /**
     * Using an enum as an entry point, generates a playable map.
     * Usually for a standard level this will be used.
     * @param lv the
     * @return the generated map
     */
    Map<T> fromEnum(LEVEL lv);

    /**
     * {@link levelGenerator} creates defaults level and
     * serialize them. This will use {@link levelGenerator}
     * to read the appropriate map and return it.
     * @param lv the level to generate.
     * @return the generated map
     */
    Map<T> fromSerialized(int lv);

    /**
     * Similar to {@link #fromSerialized(int)} but it will use
     * {@link levelGenerator} to take a map saved that is not
     * generated as default. Saves are unique and will overwrite
     * each other when a new one is created.
     * @return {@link Map}
     */
    Map<T> fromSave();
    /**
     * Similar to the {@link #fromSerialized(int)} but the data are directly
     * passed to this method and must only be parsed.
     * @return the generated map
     */
    Map<T> fromData();
}
