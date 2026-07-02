package ballblast.view.rendering.gameobject;

import ballblast.model.gameobjects.GameObject;
import ballblast.view.rendering.Renderer;
import ballblast.view.rendering.Sprite;

/**
 * A simple implementation of Renderer for {@link GameObject}s.
 * 
 * @param <G> the {@link GameObject}.
 */
public abstract class GameObjectRenderer<G extends GameObject> implements Renderer {

    private final Sprite sprite;
    private final G gameObject;

    /**
     * 
     * @param sprite     the sprite used to render the {@link GameObject}.
     * @param gameObject the {@link GameObject} to render.
     */
    public GameObjectRenderer(final Sprite sprite, final G gameObject) {
        this.sprite = sprite;
        this.gameObject = gameObject;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void render() {
        this.sprite.setPosition(this.gameObject.getPosition());
        this.sprite.render();
    }

    /**
     * Returns the {@link GameObject} that this object will render.
     * 
     * @return the {@link GameObject}.
     */
    protected G getGameObject() {
        return this.gameObject;
    }

    /**
     * Return the {@link Sprite} used to render the {@link GameObject}.
     * 
     * @return the {@link Sprite} object.
     */
    protected Sprite getSprite() {
        return this.sprite;
    }
}
