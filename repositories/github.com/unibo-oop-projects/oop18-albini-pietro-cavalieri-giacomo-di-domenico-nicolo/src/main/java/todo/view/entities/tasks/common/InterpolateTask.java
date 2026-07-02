package todo.view.entities.tasks.common;

import java.util.function.Consumer;

import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.Vector2;

import todo.view.entities.tasks.LoopableTask;

/**
 * This class represents a generic task that interpolates between two generic
 * values. For interpolating {@link Vector2}, use the more optimized
 * {@link Vector2InterpolateTask}. For interpolating floats, you can use the
 * simplified {@link FloatInterpolateTask}.
 *
 * @param <T> is the type to be interpolated
 */
public class InterpolateTask<T> implements LoopableTask {

    private final Interpolation interpolation;
    private final float duration;
    private final Interpolator<T> interpolator;
    private final T from;
    private final T to;
    private final Consumer<T> setter;
    private float currentTime;
    private float currentAlpha;

    /**
     * Create a new interpolation task.
     *
     * @param interpolator is the {@link Interpolator} instance that knows how to
     *            interpolate between values of the specified type
     * @param from is the starting value to be interpolated
     * @param to is the end value to be interpolated
     * @param setter is a {@link Consumer} that specifies how to apply the
     *            interpolated result
     * @param duration is the duration of the interpolation
     * @param interpolation is an instance of the {@link Interpolation} class that
     *            specifies how to interpolate the values
     */
    public InterpolateTask(final Interpolator<T> interpolator, final T from, final T to, final Consumer<T> setter,
            final float duration, final Interpolation interpolation) {
        this.from = from;
        this.interpolator = interpolator;
        this.to = to;
        this.setter = setter;
        this.duration = duration;
        this.interpolation = interpolation;
    }

    @Override
    public void onInit() {
        this.currentTime = 0f;
        this.currentAlpha = 0f;
    }

    @Override
    public void onTick(final float deltaTime) {
        // Calculate the current alpha by fixed amount: this value is going to be fed to
        // the interpolator
        this.currentTime += deltaTime;
        this.currentAlpha = Math.min(1f, this.currentTime / this.duration);
        this.setter.accept(this.interpolator.interpolate(this.from, this.to, this.currentAlpha, this.interpolation));
    }

    @Override
    public void onDestroy() {
        // Do nothing
    }

    @Override
    public boolean isDone() {
        return this.currentAlpha >= 1f;
    }
}
