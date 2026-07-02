package vg.model.mystery_box.concrete;

import vg.model.Stage;
import vg.model.entity.dynamicEntity.player.Player;
import vg.model.mystery_box.AbilityDurable;
import vg.model.mystery_box.AbstractAbilityDurable;
import vg.model.mystery_box.EAbility;
import vg.utils.V2D;
/**
 * This class represents the ability speed.
 */
public class AbilitySpeedImpl extends AbstractAbilityDurable {
    private static final long serialVersionUID = 1L;
    private final double speedIncrease;
    public AbilitySpeedImpl(final double duration, final double speedIncrease) {
        super(EAbility.SPEED_UP, duration);
        this.speedIncrease = speedIncrease;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void activate(final Stage<V2D> stage) {
        this.activated();
        final Player player = stage.getPlayer();
        final V2D baseSpeed = player.getSpeed();
        final V2D speedIncrease = new V2D(baseSpeed.getX() + this.speedIncrease, baseSpeed.getY() + this.speedIncrease);
        player.enableSpeedUp(speedIncrease);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void deActivate(final Stage<V2D> stage) {
        final Player player = stage.getPlayer();
        player.disableSpeedUp();
    }
}
