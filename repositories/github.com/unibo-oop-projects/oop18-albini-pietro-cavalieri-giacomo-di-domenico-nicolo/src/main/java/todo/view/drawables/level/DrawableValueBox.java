package todo.view.drawables.level;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import todo.utils.UIConstants;
import todo.view.drawables.BaseDrawable;
import todo.view.drawables.screens.ResolutionManagerImpl;
import todo.view.entities.level.ValueBox;

/**
 * This clas represents a drawable value box.
 */
public final class DrawableValueBox extends BaseDrawable<ValueBox> {
    private static final float FONT_TO_SIZE_RATIO = 0.5f;
    private final Texture numberTexture;
    private final Texture letterTexture;
    private final BitmapFont boxFont;
    private final float boxSize;

    public DrawableValueBox(final ValueBox valueBox) {
        super(valueBox);
        this.boxSize = ResolutionManagerImpl.getInstance()
                                            .getCurrentAspectRatio()
                                            .getManifest()
                                            .getScaledValueBoxSize();
        this.numberTexture = new Texture(Gdx.files.internal("assets/number-box.png"));
        this.letterTexture = new Texture(Gdx.files.internal("assets/letter-box.png"));
        // Generate the font
        this.boxFont = UIConstants.generateFont(UIConstants.ARIAL_FILE, Color.WHITE,
                Math.round(this.boxSize * FONT_TO_SIZE_RATIO));
    }

    @Override
    protected float getUnscaledWidth() {
        return this.boxSize;
    }

    @Override
    protected float getUnscaledHeight() {
        return this.boxSize;
    }

    @Override
    public int getZIndex() {
        return 50;
    }

    @Override
    public void onDraw(final SpriteBatch batch, final float deltaTime) {
        final Vector2 pos = getEntity().getPosition();
        super.draw(batch, getCorrectTexture());
        final GlyphLayout layout = new GlyphLayout(this.boxFont, valueAsString());
        this.boxFont.draw(batch, layout, pos.x + getWidth() / 2 - layout.width / 2,
                pos.y + getHeight() / 2 + layout.height / 2);
    }

    @Override
    public void onDestroy() {
        this.numberTexture.dispose();
        this.letterTexture.dispose();
        this.boxFont.dispose();
    }

    private Texture getCorrectTexture() {
        return getEntity().getValue().isASCII() ? this.letterTexture : this.numberTexture;
    }

    private String valueAsString() {
        return getEntity().getValue().isASCII() ? Character.toString((char) getEntity().getValue().asNumber())
                : Integer.toString(getEntity().getValue().asNumber());
    }
}
