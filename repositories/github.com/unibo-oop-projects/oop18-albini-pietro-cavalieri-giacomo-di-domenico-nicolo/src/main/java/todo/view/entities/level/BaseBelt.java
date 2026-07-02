package todo.view.entities.level;

import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import com.badlogic.gdx.math.Vector2;

import todo.view.entities.BaseTaskAcceptingEntity;
import todo.view.entities.tasks.LoopableTaskManager;
import todo.view.entities.tasks.ParallelLoopableTaskManager;

/**
 * This abstract class represents a generic belt implementation.
 *
 */
public abstract class BaseBelt extends BaseTaskAcceptingEntity<LoopableTaskManager> implements Belt {
    private final LoopableTaskManager manager;
    private final Deque<ValueBox> boxes;
    private final Vector2 endPos;
    private final float horizontalBoxMargin;
    private final float verticalBoxMargin;

    protected BaseBelt(final Vector2 endPosition, final float horizontalBoxMargin, final float verticalBoxMargin) {
        super();
        this.manager = new ParallelLoopableTaskManager();
        this.boxes = new LinkedList<>();
        this.endPos = endPosition;
        this.horizontalBoxMargin = horizontalBoxMargin;
        this.verticalBoxMargin = verticalBoxMargin;
    }

    @Override
    public final LoopableTaskManager getLoopableTaskManager() {
        return this.manager;
    }

    @Override
    public final List<ValueBox> getValueBoxes() {
        return this.boxes.stream()
                         .collect(Collectors.collectingAndThen(Collectors.toList(), Collections::unmodifiableList));
    }

    @Override
    public final Vector2 getEndPosition() {
        return this.endPos;
    }

    @Override
    public final float getHorizontalBoxMargin() {
        return this.horizontalBoxMargin;
    }

    @Override
    public final float getVerticalBoxMargin() {
        return this.verticalBoxMargin;
    }

    /**
     * Get a reference to the modifiable collection for internal use.
     *
     * @return the {@link Deque} that holds the {@link ValueBox}.
     */
    protected Deque<ValueBox> getModifiableCollection() {
        return this.boxes;
    }
}
