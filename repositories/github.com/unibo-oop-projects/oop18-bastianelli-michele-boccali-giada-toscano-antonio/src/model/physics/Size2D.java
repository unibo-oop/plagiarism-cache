package model.physics;

import org.jbox2d.common.Vec2;


/**
 * This class shouldn't be serialized.
 */
@SuppressWarnings("serial")
public class Size2D extends Vec2 {

    /**
     * @param width the width dimension
     * @param height the height dimension
     */
    public Size2D(final int width, final int height) {
        super((float) width, (float) height);
    }

}
