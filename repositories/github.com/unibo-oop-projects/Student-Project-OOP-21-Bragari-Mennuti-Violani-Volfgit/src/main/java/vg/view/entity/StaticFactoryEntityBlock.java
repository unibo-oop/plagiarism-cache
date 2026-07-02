package vg.view.entity;

import javafx.geometry.Dimension2D;
import vg.utils.V2D;
import vg.utils.path.PathImageEnemy;
import vg.utils.path.PathImageMysteryBox;

import java.util.List;

/**
 * This class is a factory for the entity view.
 */
public final class StaticFactoryEntityBlock {
    private StaticFactoryEntityBlock() {
    }

    /**
     * Create an entity view for mystery box.
     * @param position the position of the entity.
     * @param dimension the dimension of the entity.
     * @return the entity view.
     */
    public static EntityBlock createMysteryBox(final V2D position, final Dimension2D dimension) {
        return new EntityBlockImpl(position, dimension, List.of(PathImageMysteryBox.MYSTERY_BOX));
    }

    /**
     * Create an entity view for mosquitoes.
     * @param position the position of the entity.
     * @param dimension the dimension of the entity.
     * @return the entity view.
     */
    public static EntityBlock createMosquitoes(final V2D position, final Dimension2D dimension) {
        return new EntityBlockImpl(position, dimension, PathImageEnemy.MOSQUITOES);
    }

    /**
     * Create an entity view for boss.
     * @param position the position of the entity.
     * @param dimension the dimension of the entity.
     * @return the entity view.
     */
    public static EntityBlock createBoss(final V2D position, final Dimension2D dimension) {
        return new EntityBlockImpl(position, dimension, PathImageEnemy.BOSS);
    }
}
