package oop.lit.controller.geometry;

import oop.lit.model.GameModel;
import oop.lit.util.Vector2D;

/**
 * An implementation for Transforms.
 */
public class TransformsImpl implements Transforms {

    private static final double MIN_SCALE = 0.25;
    private static final double MAX_SCALE = 4;
    private final GameModel gm;

    /**
     * Constructor.
     * 
     * @param gm
     *            GameModel.
     */
    public TransformsImpl(final GameModel gm) {
        super();
        this.gm = gm;
    }

    @Override
    public void moveSelected(final Vector2D distance) {
        if (gm.getPlayingPlayer().isPresent()
                && gm.getBoard().canMoveSelected(gm.getPlayingPlayer().get(), gm.getActivePlayers())) {
            gm.getBoard().moveSelected(distance);
        }
    }

    @Override
    public void rotateSelected(final double angle) {
        if (gm.getPlayingPlayer().isPresent()
                && gm.getBoard().canRotateSelected(gm.getPlayingPlayer().get(), gm.getActivePlayers())) {
            gm.getBoard().rotateSelected(angle);
        }
    }

    @Override
    public void resizeSelected(final double scalar) {
        if (gm.getPlayingPlayer().isPresent()
                && gm.getBoard().canScaleSelected(gm.getPlayingPlayer().get(), gm.getActivePlayers()) && scalar > 0) {
            gm.getBoard().scaleSelected(scalar, MIN_SCALE, MAX_SCALE);
        }

    }
}
