package todo.view.drawables.level;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import todo.view.drawables.BaseDrawable;
import todo.view.drawables.screens.ResolutionManagerImpl;
import todo.view.drawables.screens.Texture;
import todo.view.drawables.screens.Textures;
import todo.view.entities.level.Floor;

/**
 * This class represents a drawable floor.
 */
public final class DrawableFloor extends BaseDrawable<Floor> {
    private final Texture floor;
    private final Texture walls;

    /**
     * Create a drawable floor.
     *
     * @param entity is the floor entity associated with this drawable
     */
    public DrawableFloor(final Floor entity) {
        super(entity);
        final Textures textures = ResolutionManagerImpl.getInstance().getCurrentAspectRatio().getTextures();
        this.floor = textures.getTexture(Textures.FLOOR).scaled();
        this.walls = textures.getTexture(Textures.WALLS).scaled();
    }

    @Override
    protected float getUnscaledWidth() {
        return this.floor.getWidth();
    }

    @Override
    protected float getUnscaledHeight() {
        return this.floor.getHeight();
    }

    @Override
    public int getZIndex() {
        return 0;
    }

    @Override
    public void onDraw(final SpriteBatch batch, final float deltaTime) {
        final Vector2 pos = getEntity().getPosition();
        // First draw the floor, then the surrounding walls
        batch.draw(this.floor.getRegion(), pos.x, pos.y, getWidth(), getHeight());
        batch.draw(this.walls.getRegion(), 0f, 0f, this.walls.getWidth(), this.walls.getHeight());
    }
}
