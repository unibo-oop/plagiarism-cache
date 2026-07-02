package model.effects.regolations;

import java.awt.Color;
import java.awt.image.BufferedImage;

import model.effects.EffectType;

/**
 * Implementation of Regolation.
 */
public abstract class RegolationImpl implements Regolation {
    private static final long serialVersionUID = 1L;
    private int intensity;

    /**
     * Constructor of RegolationImpl.
     */
    public RegolationImpl() {
        intensity = 0;
    }

    /**
     * @see model.effects.Effect#apply(java.awt.image.BufferedImage)
     */
    @Override
    public BufferedImage apply(final BufferedImage source) {
        final BufferedImage destination = new BufferedImage(source.getWidth(), source.getHeight(), source.getType());

        for (int y = 0; y < source.getHeight(); y++) {
            for (int x = 0; x < source.getWidth(); x++) {
                final Color color = changePixel(new Color(source.getRGB(x, y), true));
                destination.setRGB(x, y, color.getRGB());
            }
        }
        return destination;
    }

    /**
     * @see model.effects.Effect#getEffectType()
     */
    @Override
    public EffectType getEffectType() {
        // return type
        return EffectType.Regolation;
    }

    /**
     * @see model.effects.regolations.Regolation#getIndex()
     */
    @Override
    public int getIndex() {
        return this.intensity;
    }

    /**
     * @see model.effects.regolations.Regolation#setIndex(int)
     */
    @Override
    public Regolation setIndex(final int inputIndex) {
        this.intensity = inputIndex;
        return this;
    }
}
