package view.elements;

import java.util.ArrayList;
import java.util.List;

import javafx.geometry.Dimension2D;
import javafx.scene.image.Image;

/**
 * 
 * Abstract class for generic view element.
 *
 */
public abstract class AbstractElementView implements ElementView {

    private final List<Image> sprites = new ArrayList<>();
    private final Dimension2D dimension;
    private int spriteCounter = 0;

    /**
     * 
     * @param dimension generic element dimension.
     */
    public AbstractElementView(final Dimension2D dimension) {
        this.dimension = dimension;
    }

    @Override
    public final Dimension2D getDimension() {
        return dimension;
    }

    @Override
    public final List<Image> getSprites() {
        return sprites;
    }

    @Override
    public final int getSpriteCounter() {
        return spriteCounter;
    }

    @Override
    public final void incSpriteCounter() {
        if (spriteCounter == sprites.size() - 1) {
            spriteCounter = 0;
        } else {
            spriteCounter++;
        }
    }
}
