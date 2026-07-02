package todo.view.drawables.level;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import todo.view.drawables.BaseDrawable;
import todo.view.drawables.screens.ResolutionManagerImpl;
import todo.view.entities.level.Player;

/**
 * This class represents a drawable player.
 */
public final class DrawablePlayer extends BaseDrawable<Player> {
    private final Texture playerTexture;
    private final Texture playerShadowTexture;

    /**
     * Create a drawable player.
     *
     * @param player is the player entity associated with the drawable
     */
    public DrawablePlayer(final Player player) {
        super(player);
        this.playerTexture = new Texture(Gdx.files.internal("assets/player.png"));
        this.playerShadowTexture = new Texture(Gdx.files.internal("assets/player-shadow.png"));
    }

    @Override
    protected float getUnscaledWidth() {
        return ResolutionManagerImpl.getInstance().getCurrentAspectRatio().getManifest().getScaledFloorTileSize() * 2;
    }

    @Override
    protected float getUnscaledHeight() {
        return ResolutionManagerImpl.getInstance().getCurrentAspectRatio().getManifest().getScaledFloorTileSize() * 2;
    }

    @Override
    public int getZIndex() {
        return 100;
    }

    @Override
    public void onDraw(final SpriteBatch batch, final float deltaTime) {
        super.draw(batch, this.playerShadowTexture);
        super.draw(batch, this.playerTexture, new Vector2(0f, getEntity().getHeight()));
    }

    @Override
    public void onDestroy() {
        this.playerTexture.dispose();
        this.playerShadowTexture.dispose();
    }
}
