package todo.view.entities.tasks.common;

import com.badlogic.gdx.math.Interpolation;

/**
 * This functional interface represents a generic interpolator that knows how to
 * interpolate the specified type.
 *
 * @param <T> is the type of the interpolated object
 */
@FunctionalInterface
public interface Interpolator<T> {
    /**
     * Interpolate the object of the specified type at a certain alpha.
     *
     * @param from is the starting value
     * @param to is the end value
     * @param alpha is a value between 0 and 1 inclusive, where 0 represents the
     *            "from" parameter, and 1 the "to" parameter
     * @param interpolation is an instance of the {@link Interpolation} class
     * @return the interpolated value with the specified alpha
     */
    T interpolate(T from, T to, float alpha, Interpolation interpolation);
}
