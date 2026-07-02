package it.unibo.oop.manpac.view.screens.menu;

import java.util.Objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;

import it.unibo.oop.manpac.utils.UtilsForUI;

/**
 * Implementation of GUIFactory interface for the creation of GUI objects.
 */
public final class GUIFactoryImpl implements GUIFactory {

    @Override
    public Texture createTexture(final String texturePath) {
        return new Texture(Objects.requireNonNull(texturePath));
    }

    @Override
    public BitmapFont createBitmapFont(final String path) {
        final BitmapFont bitmapFont = new BitmapFont(Gdx.files.internal(Objects.requireNonNull(path)), false);
        bitmapFont.getRegion().getTexture().setFilter(TextureFilter.Linear, TextureFilter.Linear);
        return bitmapFont;
    }

    @Override
    public Label createLabel(final String name, final BitmapFont bitmapFont, final Color color) {
        return new Label(Objects.requireNonNull(name),
                new LabelStyle(Objects.requireNonNull(bitmapFont), Objects.requireNonNull(color)));
    }

    @Override
    public Skin createSkin(final String packPath) {
        return new Skin(new TextureAtlas(Objects.requireNonNull(packPath)));
    }

    @Override
    public TextButtonStyle createTextButtonStyle(final String styleUp, final String styleDown, final Skin skin,
            final BitmapFont bitmapFont) {
        final TextButtonStyle style = new TextButtonStyle();
        style.up = skin.getDrawable(Objects.requireNonNull(styleUp));
        style.down = skin.getDrawable(Objects.requireNonNull(styleDown));
        style.font = Objects.requireNonNull(bitmapFont);
        return style;
    }

    @Override
    public TextButton createTextButton(final String text, final TextButtonStyle style) {
        final TextButtonStyle buttonStyle = Objects.requireNonNull(style);
        buttonStyle.fontColor = Color.BLACK;
        final TextButton button = new TextButton(Objects.requireNonNull(text), buttonStyle);
        button.pad(UtilsForUI.DISTANCE_FROM_MARGINS);
        return button;
    }

}
