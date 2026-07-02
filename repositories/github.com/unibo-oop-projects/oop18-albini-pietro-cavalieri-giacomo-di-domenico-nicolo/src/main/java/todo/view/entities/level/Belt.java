package todo.view.entities.level;

import java.util.List;

import com.badlogic.gdx.math.Vector2;

import todo.view.entities.TaskAcceptingEntity;
import todo.view.entities.tasks.LoopableTaskManager;

/**
 * This interface represents a generic belt, without specifying the direction
 * (input/output).
 */
public interface Belt extends TaskAcceptingEntity<LoopableTaskManager> {
    /**
     * Get all the {@link ValueBox} associated with this belt.
     *
     * @return an immutable list of {@link ValueBox} associated with the belt
     */
    List<ValueBox> getValueBoxes();

    /**
     * @return the end position of the belt, where the {@link ValueBox} will stop
     */
    Vector2 getEndPosition();

    /**
     * @return the margin between the left border of the {@link ValueBox} and the
     *         left border of the belt
     */
    float getHorizontalBoxMargin();

    /**
     * @return the margin between the lower border of two consecutive
     *         {@link ValueBox}
     */
    float getVerticalBoxMargin();
}
