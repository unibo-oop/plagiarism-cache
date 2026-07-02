package todo.model.level;

import java.util.NoSuchElementException;
import java.util.Set;

/**
 * This interface manages all the game's levels. It has a set containing all of
 * the levels that is filled upon creation by parsing all of the XML files
 * contained into the assets/levels folder.
 */
public interface LevelsStorage {
    /**
     * @return the levels' set
     */
    Set<Level> getAllLevels();

    /**
     * @param title the title of the queried level
     * @return the queried level
     * @throws NoSuchElementException if there is no level with that title
     */
    Level getLevel(String title);
}
