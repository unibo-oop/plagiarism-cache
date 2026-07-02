package todo.view.rooms;

import java.util.Optional;

import todo.view.entities.level.Floor;
import todo.view.entities.level.InputBelt;
import todo.view.entities.level.MemoryArea;
import todo.view.entities.level.OutputBelt;
import todo.view.entities.level.Player;

/**
 * This interface represents a room, which is merely a container for the two
 * belts, the floor, the memory area, and the player.
 */
public interface Room {
    /**
     * @return the floor
     */
    Floor getFloor();

    /**
     * @return the input belt
     */
    InputBelt getInputBelt();

    /**
     * @return the output belt
     */
    OutputBelt getOutputBelt();

    /**
     * @return the memory area
     */
    Optional<MemoryArea> getMemoryArea();

    /**
     * @return the player
     */
    Player getPlayer();
}
