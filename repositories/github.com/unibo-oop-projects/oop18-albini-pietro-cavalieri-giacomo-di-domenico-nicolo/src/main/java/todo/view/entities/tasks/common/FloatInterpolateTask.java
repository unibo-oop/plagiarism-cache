package todo.view.entities.tasks.common;

import java.util.function.Consumer;

import com.badlogic.gdx.math.Interpolation;

/**
 * This class represents a task that interpolates between two floats. This is a
 * slightly simplified version of its base class, as the interpolator is well
 * known.
 */
public class FloatInterpolateTask extends InterpolateTask<Float> {

    /**
     * Create a new float interpolation task.
     *
     * @param from is the starting value to be interpolated
     * @param to is the end value to be interpolated
     * @param setter is a {@link Consumer} that specifies how to apply the
     *            interpolated result
     * @param duration is the duration of the interpolation
     * @param interpolation is an instance of the {@link Interpolation} class that
     *            specifies how to interpolate the floats
     */
    public FloatInterpolateTask(final float from, final float to, final Consumer<Float> setter, final float duration,
            final Interpolation interpolation) {
        super((f, t, a, i) -> i.apply(f, t, a), from, to, setter, duration, interpolation);
    }
}
