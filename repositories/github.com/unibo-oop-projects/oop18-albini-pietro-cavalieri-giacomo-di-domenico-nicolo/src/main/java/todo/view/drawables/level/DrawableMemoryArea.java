package todo.view.drawables.level;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import todo.utils.UIConstants;
import todo.view.drawables.BaseDrawable;
import todo.view.drawables.screens.ResolutionManagerImpl;
import todo.view.drawables.screens.ResolutionManifest;
import todo.view.entities.level.MemoryArea;

/**
 * This class represents a drawable memory area.
 */
public final class DrawableMemoryArea extends BaseDrawable<MemoryArea> {
    private static final int PATCH_SIZE = 128;
    private static final float TEXT_X_DISTANCE_RATIO = 0.125f;
    private static final float TEXT_Y_DISTANCE_RATIO = 0.125f;
    private static final float TEXT_TO_TILE_RATIO = 0.25f;

    private final float tileSize;
    private final Texture memoryAreaFillTexture;
    private final Texture memoryAreaBorderTexture;
    private final NinePatch memoryAreaBorderPatch;
    private final ResolutionManifest resolutionManifest;
    private final BitmapFont memoryCellFont;

    /**
     * Create a drawable memory area.
     *
     * @param entity is the memory area entity associated with this drawable
     */
    public DrawableMemoryArea(final MemoryArea entity) {
        super(entity);
        final float scaleFactor = ResolutionManagerImpl.getInstance().getScaleFactor();
        this.resolutionManifest = ResolutionManagerImpl.getInstance().getCurrentAspectRatio().getManifest();
        this.tileSize = ResolutionManagerImpl.getInstance()
                                             .getCurrentAspectRatio()
                                             .getManifest()
                                             .getScaledFloorTileSize();
        // Load the textures
        this.memoryAreaFillTexture = new Texture("assets/rooms/memory-area-fill.png");
        this.memoryAreaBorderTexture = new Texture("assets/rooms/memory-area-border.png");
        this.memoryAreaBorderPatch = new NinePatch(this.memoryAreaBorderTexture, PATCH_SIZE, PATCH_SIZE, PATCH_SIZE,
                PATCH_SIZE);
        this.memoryAreaBorderPatch.scale(scaleFactor, scaleFactor);
        // Generate and load the font
        this.memoryCellFont = UIConstants.generateFont(UIConstants.ARIAL_FILE, Color.WHITE,
                Math.round(this.tileSize * TEXT_TO_TILE_RATIO));
    }

    @Override
    protected float getUnscaledWidth() {
        return this.resolutionManifest.getScaledFloorTileSize() * getEntity().getHorizontalSlotsCount();
    }

    @Override
    protected float getUnscaledHeight() {
        return this.resolutionManifest.getScaledFloorTileSize() * getEntity().getVerticalSlotsCount();
    }

    @Override
    public int getZIndex() {
        return 2;
    }

    @Override
    public void onDraw(final SpriteBatch batch, final float deltaTime) {
        final Vector2 pos = getEntity().getPosition();
        final float rot = getEntity().getRotation();
        final Vector2 scl = getEntity().getScale();
        // First draw the semi-transparent fill...
        super.draw(batch, this.memoryAreaFillTexture);
        // ...then its border...
        this.memoryAreaBorderPatch.draw(batch, pos.x, pos.y, 0f, 0f, getUnscaledWidth(), getUnscaledHeight(), scl.x,
                scl.y, rot);
        // ...and finally the indices
        int indexToDraw = 1;
        final float tileSize = this.resolutionManifest.getScaledFloorTileSize();
        for (int y = getEntity().getVerticalSlotsCount() - 1; y >= 0; y--) {
            for (int x = 0; x < getEntity().getHorizontalSlotsCount(); x++) {
                final Vector2 textPos = pos.cpy()
                                           .add(x * tileSize + TEXT_X_DISTANCE_RATIO * tileSize,
                                                   y * tileSize + TEXT_Y_DISTANCE_RATIO * tileSize
                                                           + this.memoryCellFont.getCapHeight());
                this.memoryCellFont.draw(batch, Integer.toString(indexToDraw), textPos.x, textPos.y);
                indexToDraw++;
            }
        }
    }

    @Override
    public void onDestroy() {
        this.memoryAreaBorderTexture.dispose();
        this.memoryCellFont.dispose();
    }
}
