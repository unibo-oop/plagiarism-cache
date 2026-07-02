package todo.view.drawables.level.ui;

import java.util.Objects;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Container;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;

import todo.launcher.LauncherOptions;
import todo.utils.UIConstants;
import todo.vm.instructions.Instruction;

/**
 * This abstract class is a base implementation of a GDX button with padding,
 * text and background. It provides some basic functionality and has an
 * overridable method that can extend the button creation process.
 */
abstract class BaseInstructionActor implements InstructionActor {
    protected static final float WIDTH_HEIGHT_RATIO = 0.25f;
    protected static final float FONT_HEIGHT_RATIO = 0.4f;
    protected static final float PAD_LEFTRIGHT = 8f;
    protected static final float PAD_UPDOWN = 4f;

    private final Class<? extends Instruction> instructionClass;
    private final NinePatch image;
    private final FileHandle fontFile;
    private final String text;
    private final float width;
    private final Container<TextButton> button;

    BaseInstructionActor(final Class<? extends Instruction> instructionClass, final NinePatch image,
            final FileHandle fontFile, final String text, final float width) {
        this.instructionClass = Objects.requireNonNull(instructionClass);
        this.image = Objects.requireNonNull(image);
        this.fontFile = Objects.requireNonNull(fontFile);
        this.text = Objects.requireNonNull(text);
        this.width = width;

        // Load the font file and size it accordingly
        // Use the cache with the tuple (font file, size) and try to get a match;
        // if the bitmap font is not present, generate and cache it
        final int fontHeight = (int) (width * WIDTH_HEIGHT_RATIO * FONT_HEIGHT_RATIO);
        final BitmapFont font = UIConstants.generateFont(fontFile, Color.WHITE, fontHeight);

        final NinePatchDrawable drawable = new NinePatchDrawable(image);
        final TextButtonStyle style = new TextButtonStyle(drawable, drawable, drawable, font);
        // Make the button and size it
        final TextButton button = additionalBuild(new TextButton(text, style), width);
        // Change the button according to the strategy
        this.button = new Container<>(button);
        this.button.width(width - PAD_LEFTRIGHT * 2)
                   .height(width * WIDTH_HEIGHT_RATIO)
                   .pad(PAD_UPDOWN, PAD_LEFTRIGHT, PAD_UPDOWN, PAD_LEFTRIGHT);
        this.button.setTouchable(Touchable.enabled);
        if (LauncherOptions.getInstance().isDebugModeEnabled()) {
            this.button.setDebug(true, true);
        }
    }

    @Override
    public Class<? extends Instruction> getInstructionClass() {
        return this.instructionClass;
    }

    @Override
    public Actor getActor() {
        return this.button;
    }

    @Override
    public void dispose() {
        // Do nothing
    }

    /**
     * Override this method to extend the button build process.
     *
     * @param btn is the original button
     * @param width is the width of the button
     * @return is the modified button
     */
    protected TextButton additionalBuild(final TextButton btn, final float width) {
        // Do nothing
        return btn;
    }

    protected NinePatch getImage() {
        return this.image;
    }

    protected FileHandle getFontFile() {
        return this.fontFile;
    }

    protected String getText() {
        return this.text;
    }

    protected float getWidth() {
        return this.width;
    }
}
