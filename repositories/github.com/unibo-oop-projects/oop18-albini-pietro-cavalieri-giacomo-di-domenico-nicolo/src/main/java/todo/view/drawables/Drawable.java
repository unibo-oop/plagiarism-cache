package todo.view.drawables;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import todo.view.entities.Entity;

/**
 * This interface represents an object that can be drawn.
 *
 * @param <E> is the entity associated with the drawable
 */
public interface Drawable<E extends Entity> {
    /**
     * @return the entity associated with this drawable
     */
    E getEntity();

    /**
     * @return the scaled width of the sprite
     */
    float getWidth();

    /**
     * @return the scaled height of the sprite
     */
    float getHeight();

    /**
     * @return the z-index of the sprite, where bigger numbers are rendered on top
     *         of smaller numbers
     */
    int getZIndex();

    /**
     * This method is called every frame update, after all the entities have been
     * updated.
     *
     * @param batch is the sprite batch for the screen
     * @param deltaTime represents the time in seconds since the last frame
     */
    void onDraw(SpriteBatch batch, float deltaTime);

    /**
     * This method is called when the drawable is destroyed because the level has
     * changed or the game is closed. Used to free up of resources that have to be
     * manually disposed.
     */
    void onDestroy();
}
