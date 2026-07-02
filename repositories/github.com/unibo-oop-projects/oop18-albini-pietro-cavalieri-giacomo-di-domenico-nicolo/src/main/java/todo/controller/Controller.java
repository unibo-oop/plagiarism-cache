package todo.controller;

import java.util.List;

public interface Controller {
    /**
     * @param levelName the name of the level to be loaded
     * @return a {@link LevelController} of the level identified by the parameter
     *         passed (if any)
     * @throws java.util.NoSuchElementException if there is no level with the
     *             specified name
     */
    LevelController loadLevel(String levelName);

    /**
     * @return a {@link List} of all available levels' titles, sorted by their
     *         progressive number
     */
    List<String> getAvailableLevels();
}
