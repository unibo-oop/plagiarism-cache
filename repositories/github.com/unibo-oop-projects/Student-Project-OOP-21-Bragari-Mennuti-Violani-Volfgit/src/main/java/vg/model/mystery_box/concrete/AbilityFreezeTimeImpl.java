package vg.model.mystery_box.concrete;

import vg.controller.entity.boss.BossController;
import vg.model.Stage;
import vg.model.entity.dynamicEntity.DynamicEntity;
import vg.model.mystery_box.AbilityDurable;
import vg.model.mystery_box.AbstractAbilityDurable;
import vg.model.mystery_box.EAbility;
import vg.utils.V2D;

/**
 * This class represents the ability freeze time.
 */
public class AbilityFreezeTimeImpl extends AbstractAbilityDurable {
    private static final long serialVersionUID = 1L;
    public AbilityFreezeTimeImpl(final double duration) {
        super(EAbility.FREEZE_TIME, duration);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void activate(final Stage<V2D> stage) {
        this.activated();
        stage.getDynamicEntitySet().forEach(enemy -> {
            enemy.saveMySpeed();
            enemy.setSpeed(new V2D(0, 0));
        });
        final BossController boss = stage.getEntityManager().getBoss();
        boss.saveMySpeed();
        boss.setSpeed(new V2D(0, 0));
        stage.getBoss().saveMySpeed();
        stage.getBoss().setSpeed(new V2D(0, 0));
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void deActivate(final Stage<V2D> stage) {
        stage.getDynamicEntitySet().forEach(DynamicEntity::restoreMySpeed);
        stage.getEntityManager().getBoss().restoreMySpeed();
        stage.getBoss().restoreMySpeed();
    }
}
