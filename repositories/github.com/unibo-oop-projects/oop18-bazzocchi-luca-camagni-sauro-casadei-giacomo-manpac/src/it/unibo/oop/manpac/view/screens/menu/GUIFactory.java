package it.unibo.oop.manpac.view.screens.menu;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;

/**
 * Interface for creating GUI objects.
 */
public interface GUIFactory {

    /**
     * Create a Texture.
     * 
     * @param texturePath the path where the texture is present
     * @return The texture
     */
    Texture createTexture(String texturePath);

    /**
     * Create a Bitmap font.
     * 
     * @param path the path where the font is present
     * @return The bitmap font
     */
    BitmapFont createBitmapFont(String path);

    /**
     * Create a Label.
     * 
     * @param name       The text in the label
     * @param bitmapFont the text font
     * @param color      the text color
     * @return The label
     */
    Label createLabel(String name, BitmapFont bitmapFont, Color color);

    /**
     * Create a Skin.
     * 
     * @param packPath the path where the pack of textures is present
     * @return The skin
     */
    Skin createSkin(String packPath);

    /**
     * Create a TextButtonStyle.
     * 
     * @param styleUp    the name of the skin to display when the button is inactive
     * @param styleDown  the name of the skin to display when the button is active
     * @param skin       the skin
     * @param bitmapFont the text font
     * @return The style
     */
    TextButtonStyle createTextButtonStyle(String styleUp, String styleDown, Skin skin, BitmapFont bitmapFont);

    /**
     * Create a TextButton.
     * 
     * @param text  the button text
     * @param style the button style
     * @return The TextButton
     */
    TextButton createTextButton(String text, TextButtonStyle style);
}
