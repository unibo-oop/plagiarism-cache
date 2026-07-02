package view.loader.stagefactory;

import java.io.IOException;

/**
 * This is an interface for Stages. These objects can be shown as new Stages and
 * interact with the user.
 */
@FunctionalInterface
public interface StageLoader {

    /**
     * Sets and load the stage.
     * @throws IOException if fail to load the stage
     */
    void load() throws IOException;
}
