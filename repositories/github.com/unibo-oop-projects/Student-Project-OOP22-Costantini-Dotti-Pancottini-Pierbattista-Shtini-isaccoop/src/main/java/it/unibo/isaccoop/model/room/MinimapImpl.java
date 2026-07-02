package it.unibo.isaccoop.model.room;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Implementation of {@link Minimap}.
 */
public final class MinimapImpl implements Minimap {

    private final Optional<Level> lvl;

    /**
     * Constructor, used to set the level of which to show the layout (and some basic information). 
     * @param currentLevel the level of which to show the layout inside the {@link Minimap} itself.
     */
    public MinimapImpl(final Level currentLevel) {
        this.lvl = Optional.of(currentLevel);
    }

    @Override
    public Room getCurrentRoom() {
        if (this.lvl.isPresent()) {
            return this.lvl.get().getCurrentRoom();
        }
        throw new IllegalArgumentException("No level set");
    }

    @Override
    public List<Room> getCompletedRooms() {
        if (this.lvl.isPresent()) {
            return this.lvl.get().getRooms().stream()
                    .filter(r -> r.isComplete())
                    .collect(Collectors.toUnmodifiableList());
        }
        throw new IllegalArgumentException("No level set");
    }

    @Override
    public List<Room> getUncompletedRooms() {
        if (this.lvl.isPresent()) {
            return this.lvl.get().getRooms().stream()
                    .filter(r -> !r.isComplete())
                    .collect(Collectors.toUnmodifiableList());
        }
        throw new IllegalArgumentException("No level set");
    }
}
