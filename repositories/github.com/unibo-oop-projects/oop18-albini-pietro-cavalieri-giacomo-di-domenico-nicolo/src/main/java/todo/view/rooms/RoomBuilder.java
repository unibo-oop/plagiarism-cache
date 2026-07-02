package todo.view.rooms;

import java.util.List;
import java.util.Optional;

import todo.view.entities.level.ValueBox;

/**
 * This interface represents a builder to create {@link Room} objects.
 */
public interface RoomBuilder {
    /**
     * Set the initial input for the room.
     *
     * @param boxes is a comma-separated list of value boxes
     * @return the builder
     */
    RoomBuilder initialInput(ValueBox... boxes);

    /**
     * Set the initial input for the room.
     *
     * @param boxes is a list of value boxes
     * @return the builder
     */
    RoomBuilder initialInput(List<ValueBox> boxes);

    /**
     * Set the width and height of the memory area.
     *
     * @param width is the width of the memory area
     * @param height is the height of the memory area
     * @return the builder
     */
    RoomBuilder memoryArea(int width, int height);

    /**
     * Set the width and height of the memory area with the first width * height
     * initial values.
     *
     * @param width is the width of the memory area
     * @param height is the height of the memory area
     * @param values is the list of values in the memory area
     * @return the builder
     */
    RoomBuilder memoryArea(int width, int height, List<Optional<ValueBox>> values);

    /**
     * Build the room.
     *
     * @return the built room
     */
    Room build();
}
