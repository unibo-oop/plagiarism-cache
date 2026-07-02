package todo.view.drawables.level;

import java.util.Objects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import todo.view.drawables.BaseDrawable;
import todo.view.drawables.screens.ResolutionManagerImpl;
import todo.view.entities.level.Belt;

/**
 * This class represents a generic drawable belt.
 *
 * @param <B> is the type of belt entity
 */
public abstract class BaseDrawableBelt<B extends Belt> extends BaseDrawable<B> {
    private final TextureRegion texture;

    /**
     * Create a base drawable belt.
     *
     * @param entity is the associated belt-type entity with this drawable
     * @param texture is the texture to be used for rendering
     */
    public BaseDrawableBelt(final B entity, final TextureRegion texture) {
        super(entity);
        this.texture = Objects.requireNonNull(texture);
    }

    @Override
    protected float getUnscaledWidth() {
        return this.texture.getRegionWidth() * ResolutionManagerImpl.getInstance().getScaleFactor();
    }

    @Override
    protected float getUnscaledHeight() {
        return this.texture.getRegionHeight() * ResolutionManagerImpl.getInstance().getScaleFactor();
    }

    @Override
    public int getZIndex() {
        return 1;
    }

    @Override
    public void onDraw(final SpriteBatch batch, final float deltaTime) {
        super.draw(batch, this.texture);
    }
}
