package it.unibo.modularcheckers.model;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.google.common.collect.Iterables;

/**
 * A sequential turn iterator, which iterate the color list infinitely and skips dead colors.
 */
public class TurnIteratorSequential implements TurnIterator {

    private final Iterator<Color> colorTurn;
    private final Set<Color> colorToSkip;
    private Color actualTurn; 
    private final int iteratorSize;
    private static final int MIN_COLORS = 2;

    /**
     * @param colorList the ordered colorList;
     */
    public TurnIteratorSequential(final List<Color> colorList) {
        if (colorList.size() < MIN_COLORS) {
            throw new IllegalArgumentException("Minimum colors required: " + MIN_COLORS);
        }
        this.colorTurn = Iterables.cycle(colorList).iterator();
        this.colorToSkip =  new HashSet<>();
        this.iteratorSize = colorList.size();
    }

    /**
     * {@inheritDoc}
     * If all the colors are in colorToSkip, there's no next.
     */
    @Override
    public boolean hasNext() {
        return this.iteratorSize != this.colorToSkip.size();
    }

    /**
     * {@inheritDoc}
     * Skips every color present in colorToSkip.
     */
    @Override
    public Color next() {
        this.actualTurn =  colorTurn.next();
        while (this.colorToSkip.contains(getActualTurn())) {
            this.actualTurn = colorTurn.next();
        }
        return this.getActualTurn();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void skipColor(final Color color) {
        this.checkColorIsSkipped(false, color);
        this.colorToSkip.add(color);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void reviveColor(final Color color) {
       this.checkColorIsSkipped(true, color);
       this.colorToSkip.remove(color);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Color getActualTurn() {
        return this.actualTurn;
    }

    /**
     * Check Exception for skipColor and reviveColor.
     * @param isSkipped if the color needs to be in skipList.
     * @param color the color to check.
     */
    public void checkColorIsSkipped(final boolean isSkipped, final Color color) {
        if (this.colorToSkip.contains(color) != isSkipped) {
            throw new IllegalArgumentException("Color is in skip list:" + isSkipped);
       }
    }

}
