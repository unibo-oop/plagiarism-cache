package it.unibo.pacman.model.utilities;

import java.util.Arrays;
import java.util.List;
/**
 * An enum which represents the {@link Status} of the various entity in this game.
 */
public enum Status {
    /**
     * Represent a movable entity that can not be eaten.
     */
    CHASING,
    /**
     * Represent a movable entity that can be eaten.
     */
    CHASED,
    /**
     * Represent the last moments of chased entity to chasing entity.
     */
    CHASED_END;
    /**
     * Used to know all the possible status of entity.
     * 
     * @return the list of this {@link Direction}
     */
    public static List<Status> getStatusList() {
        return Arrays.asList(Status.values());
    }
}
