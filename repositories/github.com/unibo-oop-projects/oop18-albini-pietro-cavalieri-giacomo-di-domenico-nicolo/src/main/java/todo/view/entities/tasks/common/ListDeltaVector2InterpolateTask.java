package todo.view.entities.tasks.common;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.Vector2;

import todo.view.entities.tasks.LoopableTask;

public class ListDeltaVector2InterpolateTask implements LoopableTask {
    private final List<Vector2InterpolateTask> delegates;
    private final Consumer<List<Vector2>> setter;
    private final Map<Vector2, Vector2> interpolated;

    /**
     * Create a new delta {@link Vector2} interpolation task that operates on a list
     * of vectors.
     *
     * @param from is the list of starting vectors
     * @param delta is the delta to be applied to each starting vector
     * @param setter is a {@link Consumer} that uses the interpolated vector list,
     *            with the same order of the input list
     * @param duration is the duration of the interpolation
     * @param interpolation is an instance of the {@link Interpolation} class that
     *            specifies how to interpolate the values
     */
    public ListDeltaVector2InterpolateTask(final List<Vector2> from, final Vector2 delta,
            final Consumer<List<Vector2>> setter, final float duration, final Interpolation interpolation) {
        this.interpolated = new LinkedHashMap<>();
        from.forEach(v -> this.interpolated.put(v.cpy(), v.cpy()));
        this.setter = Objects.requireNonNull(setter);
        this.delegates = from.stream()
                             .map(v -> v.cpy())
                             .map(v -> new Vector2InterpolateTask(v, v.cpy().add(delta),
                                     i -> this.interpolated.get(v).set(i), duration, interpolation))
                             .collect(Collectors.toList());
    }

    @Override
    public void onInit() {
        this.delegates.forEach(Vector2InterpolateTask::onInit);
    }

    @Override
    public void onTick(final float deltaTime) {
        this.delegates.forEach(d -> d.onTick(deltaTime));
        this.setter.accept(new ArrayList<>(this.interpolated.values()));
    }

    @Override
    public void onDestroy() {
        this.delegates.forEach(Vector2InterpolateTask::onDestroy);
    }

    @Override
    public boolean isDone() {
        return this.delegates.stream().allMatch(Vector2InterpolateTask::isDone);
    }
}
