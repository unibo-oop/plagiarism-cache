package todo.view.drawables.screens;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public interface Texture {
    /**
     * @return the region of this texture
     */
    TextureRegion getRegion();

    /**
     * @return the starting position of the texture
     */
    Vector2 getFrom();

    /**
     * @return the end position of the texture
     */
    Vector2 getTo();

    /**
     * @return the width of the texture
     */
    float getWidth();

    /**
     * @return the height of the texture, which includes the last bit of the
     *         texture, not included in the {@link #getTo()} method.
     */
    float getHeight();

    /**
     * @return a scaled version of this texture
     * @throws UnsupportedOperationException if the texture was already scaled
     */
    Texture scaled();
}
