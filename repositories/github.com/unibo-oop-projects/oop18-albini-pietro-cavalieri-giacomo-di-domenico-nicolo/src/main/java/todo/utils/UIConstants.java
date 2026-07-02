package todo.utils;

import java.util.Objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;

import todo.view.menu.MenuScreen;

public final class UIConstants {
    private static final Texture GREEN_BUTTON_TEXTURE = new Texture(Gdx.files.internal("assets/buttons/green.png"));
    private static final Texture RED_BUTTON_TEXTURE = new Texture(Gdx.files.internal("assets/buttons/red.png"));
    private static final Texture BROWN_BUTTON_TEXTURE = new Texture(Gdx.files.internal("assets/buttons/brown.png"));
    private static final Texture BLUE_BUTTON_TEXTURE = new Texture(Gdx.files.internal("assets/buttons/blue.png"));
    private static final Texture WHITE_BUTTON_TEXTURE = new Texture(Gdx.files.internal("assets/buttons/white.png"));
    private static final Texture TRANSPARENT_BUTTON_TEXTURE = new Texture(
            Gdx.files.internal("assets/buttons/transparent.png"));
    private static final Texture SELECTED_BUTTON_TEXTURE = new Texture(
            Gdx.files.internal("assets/buttons/selected.png"));
    private static final Texture SCROLLBAR_TEXTURE = new Texture(Gdx.files.internal("assets/buttons/scroll.png"));
    private static final Texture SCROLLBAR_KNOB_TEXTURE = new Texture(Gdx.files.internal("assets/buttons/knob.png"));

    private static final Texture ARROW_TEXTURE = new Texture(Gdx.files.internal("assets/menu/up-arrow.png"));
    private static final Texture BACKGROUND_TEXTURE = new Texture(
            Gdx.files.internal("assets/rooms/memory-area-fill.png"));
    private static final Texture LEVEL_BUTTON_TEXTURE = new Texture(Gdx.files.internal("assets/menu/level-button.png"));
    private static final Texture SETTINGS_TEXTURE = new Texture(Gdx.files.internal("assets/menu/settings-logo.png"));
    private static final Texture EXIT_TEXTURE = new Texture(Gdx.files.internal("assets/menu/left-arrow.png"));
    private static final Texture SELECT_BOX_BACKGROUND_TEXTURE = new Texture(
            Gdx.files.internal("assets/menu/selectbox-background.png"));
    private static final Texture CHECKMARK_TEXTURE = new Texture(Gdx.files.internal("assets/menu/checkmark.png"));

    private static final int BUTTON_PATCH_SIZE = 16;
    private static final int SCROLL_PATCH_SIZE = 3;

    public static final NinePatch GREEN_BUTTON_PATCH = new NinePatch(GREEN_BUTTON_TEXTURE, BUTTON_PATCH_SIZE,
            BUTTON_PATCH_SIZE, BUTTON_PATCH_SIZE, BUTTON_PATCH_SIZE);
    public static final NinePatch RED_BUTTON_PATCH = new NinePatch(RED_BUTTON_TEXTURE, BUTTON_PATCH_SIZE,
            BUTTON_PATCH_SIZE, BUTTON_PATCH_SIZE, BUTTON_PATCH_SIZE);
    public static final NinePatch BLUE_BUTTON_PATCH = new NinePatch(BLUE_BUTTON_TEXTURE, BUTTON_PATCH_SIZE,
            BUTTON_PATCH_SIZE, BUTTON_PATCH_SIZE, BUTTON_PATCH_SIZE);
    public static final NinePatch BROWN_BUTTON_PATCH = new NinePatch(BROWN_BUTTON_TEXTURE, BUTTON_PATCH_SIZE,
            BUTTON_PATCH_SIZE, BUTTON_PATCH_SIZE, BUTTON_PATCH_SIZE);
    public static final NinePatch WHITE_BUTTON_PATCH = new NinePatch(WHITE_BUTTON_TEXTURE, BUTTON_PATCH_SIZE,
            BUTTON_PATCH_SIZE, BUTTON_PATCH_SIZE, BUTTON_PATCH_SIZE);
    public static final NinePatch TRANSPARENT_PATCH = new NinePatch(TRANSPARENT_BUTTON_TEXTURE, BUTTON_PATCH_SIZE,
            BUTTON_PATCH_SIZE, BUTTON_PATCH_SIZE, BUTTON_PATCH_SIZE);
    public static final NinePatch SELECTED_PATCH = new NinePatch(SELECTED_BUTTON_TEXTURE, BUTTON_PATCH_SIZE,
            BUTTON_PATCH_SIZE, BUTTON_PATCH_SIZE, BUTTON_PATCH_SIZE);
    public static final NinePatch SCROLLBAR_PATCH = new NinePatch(SCROLLBAR_TEXTURE, SCROLL_PATCH_SIZE,
            SCROLL_PATCH_SIZE, SCROLL_PATCH_SIZE, SCROLL_PATCH_SIZE);
    public static final NinePatch SCROLLBAR_KNOB_PATCH = new NinePatch(SCROLLBAR_KNOB_TEXTURE, SCROLL_PATCH_SIZE,
            SCROLL_PATCH_SIZE, SCROLL_PATCH_SIZE, SCROLL_PATCH_SIZE);

    public static final NinePatch MENU_BACKGROUND_PATCH = new NinePatch(BACKGROUND_TEXTURE, BUTTON_PATCH_SIZE,
            BUTTON_PATCH_SIZE, BUTTON_PATCH_SIZE, BUTTON_PATCH_SIZE);
    public static final NinePatch ARROW_PATCH = new NinePatch(ARROW_TEXTURE, BUTTON_PATCH_SIZE, BUTTON_PATCH_SIZE,
            BUTTON_PATCH_SIZE, BUTTON_PATCH_SIZE);
    public static final NinePatch SELECTBOX_PATCH = new NinePatch(SELECT_BOX_BACKGROUND_TEXTURE, BUTTON_PATCH_SIZE,
            BUTTON_PATCH_SIZE, BUTTON_PATCH_SIZE, BUTTON_PATCH_SIZE);
    public static final NinePatch CHECKMARK_PATCH = new NinePatch(CHECKMARK_TEXTURE, BUTTON_PATCH_SIZE,
            BUTTON_PATCH_SIZE, BUTTON_PATCH_SIZE, BUTTON_PATCH_SIZE);
    public static final NinePatch LEVEL_BUTTON_PATCH = new NinePatch(LEVEL_BUTTON_TEXTURE, BUTTON_PATCH_SIZE,
            BUTTON_PATCH_SIZE, BUTTON_PATCH_SIZE, BUTTON_PATCH_SIZE);
    public static final NinePatch SETTINGS_BUTTON_PATCH = new NinePatch(SETTINGS_TEXTURE, BUTTON_PATCH_SIZE,
            BUTTON_PATCH_SIZE, BUTTON_PATCH_SIZE, BUTTON_PATCH_SIZE);
    public static final NinePatch EXIT_BUTTON_PATCH = new NinePatch(EXIT_TEXTURE, BUTTON_PATCH_SIZE, BUTTON_PATCH_SIZE,
            BUTTON_PATCH_SIZE, BUTTON_PATCH_SIZE);

    /**
     * Default length of the animations, in seconds.
     */
    public static final float ANIMATIONS_BASE_SECONDS = 0.80f;
    /**
     * Length of the duration increments applied when changing animations speed.
     */
    public static final float ANIMATIONS_INCREMENTS = 0.35f;
    /**
     * Maximum number of increments when increasing the animation speed.
     */
    public static final int ANIMATIONS_MAX_INCREMENTS = 2;
    /**
     * Minimum number of increments when decreasing the animation speed.
     */
    public static final int ANIMATIONS_MIN_INCREMENTS = -1;

    public static final FileHandle ARIAL_FILE = Gdx.files.internal("assets/fonts/arial.ttf");
    public static final FileHandle JOYSTIX_FONT_FILE = Gdx.files.internal("assets/fonts/joystix.ttf");
    public static final FileHandle ARIAL_BOLD_FONT_FILE = Gdx.files.internal("assets/fonts/arialbd.ttf");

    private UIConstants() {
    }

    /**
     * Generate a {@link LabelStyle} from a {@link FileHandle} of a font.
     *
     * @param font the font for the {@link LabelStyle}
     * @param color the {@link Color} for the {@link LabelStyle}
     * @param size the size for the {@link LabelStyle}
     * @return a new {@link LabelStyle} with the specified parameters
     */
    public static LabelStyle labelStyleFromFont(final FileHandle font, final Color color, final int size) {
        final LabelStyle style = new LabelStyle();
        style.font = generateFont(font, color, size);
        return style;
    }

    /**
     * Generate a {@link BitmapFont} from a {@link FileHandle} of a font.
     *
     * @param fontName the {@link FileHandle} for the font
     * @param color the {@link Color} for the {@link BitmapFont}
     * @param size the size for the {@link BitmapFont}
     * @return a new {@link BitmapFont} with the specified parameters
     */
    public static BitmapFont generateFont(final FileHandle fontName, final Color color, final int size) {
        return FontCache.get(fontName, size, color);
    }

    /**
     * Generate a {@link TextButton} for a {@link Level} in the {@link MenuScreen}.
     *
     * @param index the index for this button
     * @return a new {@link TextButton} for the {@link Level}
     */
    public static TextButton fromLevelToButton(final int index) {
        final NinePatch levelPatch = new NinePatch(LEVEL_BUTTON_TEXTURE, BUTTON_PATCH_SIZE, BUTTON_PATCH_SIZE,
                BUTTON_PATCH_SIZE, BUTTON_PATCH_SIZE);
        final NinePatchDrawable levelDrawable = new NinePatchDrawable(levelPatch);
        final TextButtonStyle numbersStyle = new TextButtonStyle(levelDrawable, levelDrawable, levelDrawable,
                generateFont(ARIAL_BOLD_FONT_FILE, Color.BLACK, 16));
        return new TextButton(Integer.toString(index), numbersStyle);
    }

    /**
     * Generate a {@link ImageButton} with an interaction specified by
     * {@link onClick}.
     *
     * @param patch the {@link NinePatch} of the {@link ImageButton}
     * @param onClick specifies the interaction for this button
     * @return a new {@link ImageButton}
     */
    public static ImageButton generateImageButton(final NinePatch patch, final Runnable onClick) {
        final NinePatchDrawable drawable = new NinePatchDrawable(Objects.requireNonNull(patch));
        final ImageButton btn = new ImageButton(drawable);
        btn.addListener(new ChangeListener() {
            @Override
            public void changed(final ChangeEvent event, final Actor actor) {
                onClick.run();
            }
        });
        return btn;
    }
}
