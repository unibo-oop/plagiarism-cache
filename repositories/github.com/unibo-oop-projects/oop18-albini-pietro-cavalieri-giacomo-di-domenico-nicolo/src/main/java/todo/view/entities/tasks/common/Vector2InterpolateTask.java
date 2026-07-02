package todo.view.entities.tasks.common;

import java.util.function.Consumer;

import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.Vector2;

import todo.view.entities.tasks.LoopableTask;

/**
 * This class represents a task that interpolates between two {@link Vector2}.
 */
public class Vector2InterpolateTask implements LoopableTask {
    private final Vector2 current;
    private final InterpolateTask<Vector2> delegate;

    /**
     * Create a new {@link Vector2} interpolation task.
     *
     * @param from is the starting vector
     * @param to is the end vector
     * @param setter is a {@link Consumer} that uses the interpolated vector and
     *            sets it to the right field
     * @param duration is the duration of the interpolation
     * @param interpolation is an instance of the {@link Interpolation} class that
     *            specifies how to interpolate the values
     */
    public Vector2InterpolateTask(final Vector2 from, final Vector2 to, final Consumer<Vector2> setter,
            final float duration, final Interpolation interpolation) {
        this.current = from.cpy();
        this.delegate = new InterpolateTask<Vector2>(
                (f, t, a, i) -> this.current.set(i.apply(f.x, t.x, a), i.apply(f.y, t.y, a)).cpy(), from, to, setter,
                duration, interpolation);
    }

    @Override
    public void onInit() {
        this.delegate.onInit();
    }

    @Override
    public void onTick(final float deltaTime) {
        this.delegate.onTick(deltaTime);
    }

    @Override
    public void onDestroy() {
        this.delegate.onDestroy();
    }

    @Override
    public boolean isDone() {
        return this.delegate.isDone();
    }
}
