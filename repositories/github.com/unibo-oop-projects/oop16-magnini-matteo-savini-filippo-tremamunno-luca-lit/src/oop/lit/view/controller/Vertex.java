package oop.lit.view.controller;

import javafx.scene.input.MouseEvent;
import oop.lit.util.Vector2D;
import oop.lit.util.Vector2DImpl;
import oop.lit.view.BoardElementView;

/**
 * Given a GameElementView calculates its vertex.
 */
public class Vertex {

    private BoardElementView bev;

    /*
     * =================== GEOMETRIC PRIVATE METHODS =======================
     */

    private double getHeight() {
        return bev.getHeight();
    }

    private double getWidth() {
        return bev.getWidth();
    }

    private double getSemiDiag() {
        return Math.sqrt(Math.pow(getWidth(), 2) + Math.pow(getHeight(), 2)) / 2;
    }

    private double getAlfa() {
        return Math.toRadians(bev.getBoardElement().getRotation());
    }

    private double getTheta() {
        return Math.asin(getHeight() / (2 * getSemiDiag()));
    }

    private double getHeightContributionA() {
        return Math.sin(getTheta() + getAlfa()) * getSemiDiag();
    }

    private double getWidthContributionA() {
        return Math.cos(getTheta() + getAlfa()) * getSemiDiag();
    }

    private double getHeightContributionB() {
        return Math.sin(getTheta() - getAlfa()) * getSemiDiag();
    }

    private double getWidthContributionB() {
        return Math.cos(getTheta() - getAlfa()) * getSemiDiag();
    }

    /*
     * ==================== GEOMETRIC PUBLIC METHODS =======================
     */

    /**
     * @param bev
     *            BoardElementView.
     * @return the north-west vertex.
     */
    public Vector2D getPointA(final BoardElementView bev) {
        this.bev = bev;
        return new Vector2DImpl(bev.getScenePosition().getX() - getWidthContributionA(), bev.getScenePosition().getY()
                - getHeightContributionA());
    }

    /**
     * @param bev
     *            BoardElementView.
     * @return the north-east vertex.
     */
    public Vector2D getPointB(final BoardElementView bev) {
        this.bev = bev;
        return new Vector2DImpl(bev.getScenePosition().getX() + getWidthContributionB(), bev.getScenePosition().getY()
                - getHeightContributionB());
    }

    /**
     * @param bev
     *            BoardElementView.
     * @return the south-east vertex.
     */
    public Vector2D getPointC(final BoardElementView bev) {
        return getPointA(bev).invertWithDifferentOrigin(bev.getScenePosition());
    }

    /**
     * @param bev
     *            BoardElementView.
     * @return the south-west vertex.
     */
    public Vector2D getPointD(final BoardElementView bev) {
        return getPointB(bev).invertWithDifferentOrigin(bev.getScenePosition());
    }

    /**
     * @param e
     *            MouseEvent to calculate cursor position.
     * @param bev
     *            BoardElementView.
     * @return true if cursor is near point A (north-west).
     */
    public boolean isNWResizeZone(final MouseEvent e, final BoardElementView bev) {
        return getPointA(bev).isNearby((new Vector2DImpl(e.getSceneX(), e.getSceneY())));
    }

    /**
     * @param e
     *            MouseEvent to calculate cursor position.
     * @param bev
     *            BoardElementView.
     * @return true if cursor is near point B (north-east).
     */
    public boolean isNEResizeZone(final MouseEvent e, final BoardElementView bev) {
        return getPointB(bev).isNearby((new Vector2DImpl(e.getSceneX(), e.getSceneY())));
    }

    /**
     * @param e
     *            MouseEvent to calculate cursor position.
     * @param bev
     *            BoardElementView.
     * @return true if cursor is near point C (south-east).
     */
    public boolean isSEResizeZone(final MouseEvent e, final BoardElementView bev) {
        return getPointC(bev).isNearby((new Vector2DImpl(e.getSceneX(), e.getSceneY())));
    }

    /**
     * @param e
     *            MouseEvent to calculate cursor position.
     * @param bev
     *            BoardElementView.
     * @return true if cursor is near point D (south-west).
     */
    public boolean isSWResizeZone(final MouseEvent e, final BoardElementView bev) {
        return getPointD(bev).isNearby((new Vector2DImpl(e.getSceneX(), e.getSceneY())));
    }

    /**
     * 
     * @param e
     *            MouseEvent.
     * @param bev
     *            BoardElementView.
     * @return true if cursor is near any vertex.
     */
    public boolean isInResizeZone(final MouseEvent e, final BoardElementView bev) {
        return isNEResizeZone(e, bev) || isNWResizeZone(e, bev) || isSWResizeZone(e, bev) || isSEResizeZone(e, bev);
    }
}
