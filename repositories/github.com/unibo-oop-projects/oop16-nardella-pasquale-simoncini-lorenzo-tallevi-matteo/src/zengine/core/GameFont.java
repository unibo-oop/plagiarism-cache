package zengine.core;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.IOException;
import java.io.InputStream;

import zengine.interfaces.GameEngine;

/**
 * This class represent a font used to draw texts in the Zengine GUI. It is
 * basically a wrapper for a java.awt.Font.
 */
final class GameFont {
    private final GameEngine ze = Zengine.getEngine();
    private boolean valid;
    private Font font;
    private final String name;

    /**
     * builds a new GameFont by loading a font from fullPath, assigns it the
     * specified name and specified size
     * 
     * @param fullPath
     *            full path of the font to be loaded
     * @param name
     *            alias of the font to be loaded
     * @param size
     *            height, in pixels, of the new font to build
     */
    GameFont(final String fullPath, final String name, final float size) {
        this.valid = false;
        this.name = name;

        final InputStream stream = this.getClass().getResourceAsStream("/" + ZengineAssets.getAssets().normalizePath(fullPath));
        if (stream != null) {
            try {
                font = Font.createFont(Font.TRUETYPE_FONT, stream).deriveFont(size);
                valid = true;
            } catch (FontFormatException e) {
                ze.loggerWarning("FontFormatException when loading font " + fullPath);
                valid = false;
            } catch (IOException e) {
                ze.loggerWarning("IOException when loading font " + fullPath);
                valid = false;
            }
        } else {
            ze.loggerWarning("could not load font " + fullPath);
            valid = false;
        }
    }

    /**
     * returns true if this font is valid, which means that it has been
     * correctly loaded and can be used.
     * 
     * @return true if this font is valid
     */
    public boolean isValid() {
        return valid;
    }

    /**
     * returns the reference of the font wrapped in this object.
     * 
     * @return the reference of the font wrapped in this object
     */
    public Font getFont() {
        return font;
    }

    /**
     * returns the alias of the font described by this object.
     * 
     * @return the alias of the font described by this object
     */
    public String getName() {
        return name;
    }

    /**
     * returns the height, in pixels, of this font.
     * 
     * @return the height, in pixels, of this font
     */
    public double getHeight() {
        if (isValid()) {
            return font.getSize2D();
        } else {
            return 0;
        }
    }
}
