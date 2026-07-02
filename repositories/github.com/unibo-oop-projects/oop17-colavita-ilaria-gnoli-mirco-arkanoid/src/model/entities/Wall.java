package model.entities;

import javafx.util.Pair;

/**
 * Classe che modella una parete. Implementa {@link IEntity}
 * 
 * @author Gnoli Mirco
 *
 */
public class Wall implements IEntity {

    private int xMin, xMax, yMin, yMax;

    /**
     * 
     * @param startX - punto iniziale in X.
     * @param startY - punto iniziale in Y.
     * @param endX - punto finale in X.
     * @param endY - punto finale in Y.
     */
    public Wall(final int startX, final int startY, final int endX, final int endY) {
        xMin = Math.min(startX, endX);
        xMax = Math.max(startX, endX);
        yMin = Math.min(startY, endY);
        yMax = Math.max(startY, endY);
    }

    /**
     * @return true se la parete è orizzontale, false altrimenti.
     */
    public final boolean isHorizontalWall() {
        return yMin == yMax;
    }

    /**
     * @return true se la parete è verticale, false altrimenti.
     */
    public final boolean isVerticalWall() {
        return xMin == xMax;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final int getMinX() {
        return xMin;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final int getMaxX() {
        return xMax;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final int getMinY() {
        return yMin;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final int getMaxY() {
        return yMax;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final Pair<Integer, Integer> getPosition() {
        return new Pair<>(xMin, yMin);
    }

    /**
     * {@inheritDoc}
     * I parametri in input settano l'origine del punto inferiore.
     */
    @Override
    public final void setPosition(final int newX, final int newY) {
        this.xMin = newX;
        this.yMin = newY;

    }
}
