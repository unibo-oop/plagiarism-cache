package todo.utils;

import java.util.HashMap;
import java.util.Map;

import org.jooq.lambda.tuple.Tuple3;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;

/**
 * This class is used to cache the generated bitmap fonts.
 */
final class FontCache {
    private static final Map<Tuple3<FileHandle, Integer, Color>, BitmapFont> FONT_CACHE;

    static {
        FONT_CACHE = new HashMap<>();
    }

    private FontCache() {
    }

    /**
     * Generate a white bitmap font from the specified font file and size.
     *
     * @param fontFile is the file handle of the font file
     * @param size is the size of the font
     * @return the bitmap font representation
     */
    public static BitmapFont get(final FileHandle fontFile, final int size) {
        final BitmapFont font;
        final Tuple3<FileHandle, Integer, Color> cacheKey = new Tuple3<>(fontFile, size, Color.WHITE);
        if (FONT_CACHE.containsKey(cacheKey)) {
            font = FONT_CACHE.get(cacheKey);
        } else {
            final FreeTypeFontGenerator fontGenerator = new FreeTypeFontGenerator(fontFile);
            final FreeTypeFontParameter fontParams = new FreeTypeFontParameter();
            fontParams.size = size;
            font = fontGenerator.generateFont(fontParams);
            FONT_CACHE.put(cacheKey, font);
            fontGenerator.dispose();
        }
        return font;
    }

    /**
     * Generate a bitmap font with the specified font file, size and color.
     *
     * @param fontFile is the file handle of the font file
     * @param size is the size of the font
     * @param color is the color of the font
     * @return is the bitmap font representation
     */
    public static BitmapFont get(final FileHandle fontFile, final int size, final Color color) {
        final BitmapFont font;
        final Tuple3<FileHandle, Integer, Color> cacheKey = new Tuple3<>(fontFile, size, color);
        if (FONT_CACHE.containsKey(cacheKey)) {
            font = FONT_CACHE.get(cacheKey);
        } else {
            final FreeTypeFontGenerator fontGenerator = new FreeTypeFontGenerator(fontFile);
            final FreeTypeFontParameter fontParams = new FreeTypeFontParameter();
            fontParams.size = size;
            fontParams.color = color;
            font = fontGenerator.generateFont(fontParams);
            FONT_CACHE.put(cacheKey, font);
            fontGenerator.dispose();
        }
        return font;
    }
}
