package todo.view.entities;

import todo.view.entities.level.Floor;
import todo.view.entities.level.InputBelt;
import todo.view.entities.level.MemoryArea;
import todo.view.entities.level.OutputBelt;
import todo.view.entities.level.Player;
import todo.view.entities.level.ValueBox;

/**
 * This interface represents an entity visitor that returns something.
 *
 * @param <T> is the return type of the visitor
 */
public interface EntityVisitor<T> {
    /**
     * Visit an {@link InputBelt}.
     *
     * @param belt is the belt
     * @return the desired return value
     */
    T visit(InputBelt belt);

    /**
     * Visit an {@link OutputBelt}.
     *
     * @param belt is the belt
     * @return the desired return value
     */
    T visit(OutputBelt belt);

    /**
     * Visit a {@link Player}.
     *
     * @param player is the player
     * @return the desired return value
     */
    T visit(Player player);

    /**
     * Visit a {@link MemoryArea}.
     *
     * @param area is the memory area
     * @return the desired return value
     */
    T visit(MemoryArea area);

    /**
     * Visit a {@link ValueBox}.
     *
     * @param box is the value box
     * @return the desired return value
     */
    T visit(ValueBox box);

    /**
     * Visit a {@link Floor}.
     *
     * @param floor is the floor
     * @return the desired return value
     */
    T visit(Floor floor);
}
