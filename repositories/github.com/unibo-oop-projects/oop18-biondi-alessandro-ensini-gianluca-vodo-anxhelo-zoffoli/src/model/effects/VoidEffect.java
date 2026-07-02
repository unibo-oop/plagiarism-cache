package model.effects;

import java.awt.image.BufferedImage;

/**
 * This class represents a void effect that don't modify the image and store its
 * format. This is the first effect of an history.
 */
public final class VoidEffect implements Effect {

    private static final long serialVersionUID = 1L;
    private String name = "VoidEffect";
    private String imageFormat;
    private Integer savedIndex;

    /**
     * Create a new voidEffect.
     */
    public VoidEffect() {
        this("");
    }

    /**
     * Create a new voidEffect setting its image format.
     * 
     * @param format of the image
     */
    public VoidEffect(final String format) {
        this.imageFormat = format;
    }

    @Override
    public BufferedImage apply(final BufferedImage source) {
        return source;

    }

    @Override
    public String getEffectName() {
        return name;

    }

    @Override
    public void setEffectName(final String name) {
        this.name = name;
    }

    @Override
    public EffectType getEffectType() {
        return EffectType.Void;
    }

    /**
     * @return .
     */
    public String getImageFormat() {
        return imageFormat;
    }

    /**
     * @param imageFormat .
     */
    public void setImageFormat(final String imageFormat) {
        this.imageFormat = imageFormat;
    }

    /**
     * @return current index before history closing
     */
    public Integer getSavedIndex() {
        return savedIndex;
    }

    /**
     * @param currentIndex of visualized
     */
    public void setSavedIndex(final Integer currentIndex) {
        this.savedIndex = currentIndex;
    }
}
