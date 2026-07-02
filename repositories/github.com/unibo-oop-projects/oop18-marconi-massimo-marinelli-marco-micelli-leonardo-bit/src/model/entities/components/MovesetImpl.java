package model.entities.components;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.almasb.fxgl.texture.AnimationChannel;
/**
 * Implements a generic Moveset for moving entities.
 *
 */
public class MovesetImpl implements Moveset {

    private final Map<String, AnimationChannel> moveset = new HashMap<>();

    /**
     * {@inheritDoc}
     */
    @Override
    public void addAnimation(final String name, final AnimationChannel animation) {
        moveset.put(name, animation);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Map<String, AnimationChannel> getMoveset() {
        return moveset;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public AnimationChannel getAnimation(final String name) {
        return moveset.get(name);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addMoveset(final Moveset newMoveset) {
        for (final Entry<String, AnimationChannel> entry : newMoveset.getMoveset().entrySet()) {
            addAnimation(entry.getKey(), entry.getValue());
        }
    }
}
