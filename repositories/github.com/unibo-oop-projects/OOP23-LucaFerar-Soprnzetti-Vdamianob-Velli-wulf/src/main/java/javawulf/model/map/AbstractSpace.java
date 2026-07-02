package javawulf.model.map;

import java.util.List;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

import java.util.ArrayList;

import java.util.Collection;

import javawulf.model.GameElement;

/**
 * A space in the biome. It has a 'width' and a 'height' (in tiles)
 * Width and height are relative to the biome where space will be included, not to the whole map.
 * In spaces is possible to add entities.
 */
public abstract class AbstractSpace implements Space {
    private final int width;
    private final int height;
    private final List<GameElement> elements = new ArrayList<>();

    /**
     * Constructor that initializes a new space with width and height specified.
     * @param width (in 'tile number')
     * @param height (in 'tile number')
     */
    public AbstractSpace(final int width, final int height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public final int getWidth() {
        return this.width;
    }

    @Override
    public final int getHeight() {
        return this.height;
    }

    @Override
    public final Space addGameElement(final GameElement element) {
        this.elements.add(element);
        return this;
    }

    @Override
    public final Space addGameElements(final Collection<GameElement> elements) {
        this.elements.addAll(elements);
        return this;
    }

    @SuppressFBWarnings(
        value = {
            "M", "V", "EI"
        },
        justification = "Elements can be modified."
    )
    @Override
    public final List<GameElement> getElements() {
        return this.elements;
    }

    @Override
    public final String toString() {
        return "AbstractSpace [width=" + width + ", height=" + height + ", elements=" + elements + "]";
    }

}
