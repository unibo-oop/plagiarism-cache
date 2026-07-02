package todo.view.drawables.level.ui.dialogs;

import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Window.WindowStyle;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;

import todo.utils.UIConstants;

/**
 * This class represents a dialog with only an OK button.
 */
public class GameDialogImpl implements GameDialog {
    private static final int TITLE_FONT_SIZE = 20;
    private final Dialog dialog;
    private final Stage stage;
    private Optional<DialogResponse> lastResponse;

    /**
     * Create a dialog with an OK button.
     *
     * @param stage is the stage that will be used by the dialog to draw itself
     * @param text is the color-formattable text of the dialog
     * @param onButtonPress is a consumer of {@link DialogResponse} that contains
     *            the user's response to the dialog
     * @param buttonColor the color of the button
     * @param allowedResponses a comma-separated list of allowed responses
     */
    public GameDialogImpl(final Stage stage, final String text, final Consumer<DialogResponse> onButtonPress,
            final ButtonColor buttonColor, final DialogResponse... allowedResponses) {
        this.stage = Objects.requireNonNull(stage);
        this.lastResponse = Optional.empty();
        // Generate the font and allow for formatting
        final BitmapFont font = UIConstants.generateFont(UIConstants.ARIAL_FILE, Color.WHITE, TITLE_FONT_SIZE);
        font.getData().markupEnabled = true;
        // Generate the styling
        final Drawable dialogBackground = new NinePatchDrawable(UIConstants.BROWN_BUTTON_PATCH);
        final NinePatchDrawable buttonDrawable = new NinePatchDrawable(buttonColor.color);
        final WindowStyle windowStyle = new WindowStyle(font, Color.WHITE, dialogBackground);
        final LabelStyle textStyle = new LabelStyle(font, Color.WHITE);
        final TextButtonStyle okButtonStyle = new TextButtonStyle(buttonDrawable, buttonDrawable.tint(Color.LIGHT_GRAY),
                buttonDrawable, font);
        // Create the dialog box
        this.dialog = new Dialog("", windowStyle) {
            @Override
            public void result(final Object obj) {
                final DialogResponse response = (DialogResponse) obj;
                GameDialogImpl.this.lastResponse = Optional.of(response);
                onButtonPress.accept(response);
            }
        };
        this.dialog.text(text, textStyle);
        for (final DialogResponse response : allowedResponses) {
            this.dialog.button(response.toString(), response, okButtonStyle);
        }
    }

    @Override
    public Optional<DialogResponse> getResponse() {
        return this.lastResponse;
    }

    @Override
    public void show() {
        this.dialog.show(this.stage);
    }

    public enum ButtonColor {
        GREEN(UIConstants.GREEN_BUTTON_PATCH), RED(UIConstants.RED_BUTTON_PATCH);

        private final NinePatch color;

        ButtonColor(final NinePatch color) {
            this.color = color;
        }
    }
}
