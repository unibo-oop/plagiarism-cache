package vg.model.entity.boss;

import javafx.geometry.Dimension2D;
import vg.model.entity.dynamicEntity.enemy.Boss;
import vg.utils.MassTier;
import vg.utils.Shape;
import vg.utils.V2D;

/**
 * This class represents the model of the boss in the game.
 */
public class BossImpl extends Boss implements BossModel {
    private static final long serialVersionUID = 1L;
    private static final Dimension2D DIMENSION_BOX = new Dimension2D(80, 80);
    private static final V2D INIT_POSITION = new V2D(100, 100);
    private final Dimension2D dimension;
    /**
     * Create a new boss.
     * @param speed the speed of the boss.
     */
    public BossImpl(final V2D speed) {
        super(INIT_POSITION, speed, (int) (DIMENSION_BOX.getWidth() / 2), Shape.SQUARE, MassTier.HIGH);
        this.dimension = DIMENSION_BOX;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public Dimension2D getDimension() {
        return this.dimension;
    }
}
