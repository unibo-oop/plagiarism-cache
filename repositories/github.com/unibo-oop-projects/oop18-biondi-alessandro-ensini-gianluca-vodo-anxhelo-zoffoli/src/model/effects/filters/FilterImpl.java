package model.effects.filters;

import java.awt.Color;
import java.awt.image.BufferedImage;
import model.effects.EffectType;

/**
 * Implementation of interface Filter.
 */
public abstract class FilterImpl implements Filter {

    private static final long serialVersionUID = 1L;

    /**
     * Apply a filter using {@link Filter#changePixel(Color)}.
     * 
     * @see model.effects.Effect#apply(java.awt.image.BufferedImage)
     * 
     */
    @Override
    public BufferedImage apply(final BufferedImage source) {
        final BufferedImage dest = new BufferedImage(source.getWidth(), source.getHeight(), source.getType());

        for (int y = 0; y < source.getHeight(); y++) {
            for (int x = 0; x < source.getWidth(); x++) {
                final Color col = changePixel(new Color(source.getRGB(x, y), true));
                dest.setRGB(x, y, col.getRGB());
            }
        }
        return dest;
    }

    /**
     * Base effect type for any filter.
     * 
     * @return effect type
     */
    public EffectType getEffectType() {
        return EffectType.Filter;
    }
}
