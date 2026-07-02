package todo.view.drawables;

import java.util.Objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import todo.view.entities.Entity;

/**
 * This abstract class represents a generic drawable for a generic entity.
 *
 * @param <E> is the entity type associated with the drawable
 */
public abstract class BaseDrawable<E extends Entity> implements Drawable<E> {
    private final E entity;

    /**
     * Create a new base drawable.
     *
     * @param entity is the associated entity to the drawable
     */
    public BaseDrawable(final E entity) {
        this.entity = Objects.requireNonNull(entity);
    }

    @Override
    public final E getEntity() {
        return this.entity;
    }

    @Override
    public float getWidth() {
        return getUnscaledWidth() * Math.abs(getEntity().getScale().x);
    }

    @Override
    public float getHeight() {
        return getUnscaledHeight() * Math.abs(getEntity().getScale().y);
    }

    @Override
    public void onDestroy() {
        // Do nothing
    }

    /**
     * @return the unscaled width of the drawable
     */
    protected abstract float getUnscaledWidth();

    /**
     * @return the unscaled height of the drawable
     */
    protected abstract float getUnscaledHeight();

    /**
     * Draw a generic texture following the position, rotation, scale of the entity.
     *
     * @param batch is the sprite batch
     * @param texture is the texture to be drawn at the entity's position, rotation
     *            and scale
     */
    protected final void draw(final SpriteBatch batch, final Texture texture) {
        this.draw(batch, texture, Vector2.Zero, 0f, Vector2.Zero);
    }

    /**
     * Draw a generic texture following the position, rotation, scale of the entity.
     *
     * @param batch is the sprite batch
     * @param texture is the texture to be drawn at the entity's position, rotation
     *            and scale
     * @param posDelta is the delta for the position relative to the absolute
     *            position of the entity
     */
    protected final void draw(final SpriteBatch batch, final Texture texture, final Vector2 posDelta) {
        this.draw(batch, texture, posDelta, 0f, Vector2.Zero);
    }

    /**
     * Draw a generic texture following the position, rotation, scale of the entity.
     *
     * @param batch is the sprite batch
     * @param texture is the texture to be drawn at the entity's position, rotation
     *            and scale
     * @param posDelta is the delta for the position relative to the absolute
     *            position of the entity
     * @param rotDelta is the delta for the rotation relative to the absolute
     *            rotation of the entity
     * @param scaleDelta is the delta for the transform relative to the absolute
     *            scale of the entity
     */
    protected final void draw(final SpriteBatch batch, final Texture texture, final Vector2 posDelta,
            final float rotDelta, final Vector2 scaleDelta) {
        final Vector2 pos = getEntity().getPosition().add(posDelta);
        final float rot = getEntity().getRotation() + rotDelta;
        final Vector2 scl = getEntity().getScale().add(scaleDelta);
        batch.draw(texture, pos.x, pos.y, 0f, 0f, getUnscaledWidth(), getUnscaledHeight(), scl.x, scl.y, rot, 0, 0,
                texture.getWidth(), texture.getHeight(), scl.x < 0, scl.y < 0);
    }

    /**
     * Draw a generic texture region following the position, rotation, scale of the
     * entity.
     *
     * @param batch is the sprite batch
     * @param textureRegion is the texture region to be drawn at the entity's
     *            position, rotation and scale
     */
    protected final void draw(final SpriteBatch batch, final TextureRegion textureRegion) {
        this.draw(batch, textureRegion, Vector2.Zero, 0f, Vector2.Zero);
    }

    /**
     * Draw a generic texture region following the position, rotation, scale of the
     * entity.
     *
     * @param batch is the sprite batch
     * @param textureRegion is the texture region to be drawn at the entity's
     *            position, rotation and scale
     * @param posDelta is the delta for the position relative to the absolute
     *            position of the entity
     */
    protected final void draw(final SpriteBatch batch, final TextureRegion textureRegion, final Vector2 posDelta) {
        this.draw(batch, textureRegion, posDelta, 0f, Vector2.Zero);
    }

    /**
     * Draw a generic texture region following the position, rotation, scale of the
     * entity.
     *
     * @param batch is the sprite batch
     * @param textureRegion is the texture region to be drawn at the entity's
     *            position, rotation and scale
     * @param posDelta is the delta for the position relative to the absolute
     *            position of the entity
     * @param rotDelta is the delta for the rotation relative to the absolute
     *            rotation of the entity
     * @param scaleDelta is the delta for the scale relative to the absolute scale
     *            of the entity
     */
    protected final void draw(final SpriteBatch batch, final TextureRegion textureRegion, final Vector2 posDelta,
            final float rotDelta, final Vector2 scaleDelta) {
        final Vector2 pos = getEntity().getPosition().add(posDelta);
        final float rot = getEntity().getRotation() + rotDelta;
        final Vector2 scl = getEntity().getScale().add(scaleDelta);
        batch.draw(textureRegion, pos.x, pos.y, 0f, 0f, getUnscaledWidth(), getUnscaledHeight(), scl.x, scl.y, rot);
    }
}
