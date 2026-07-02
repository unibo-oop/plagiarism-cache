package vg.model.mystery_box;


import vg.model.timedObject.TimedObject;
import vg.model.timedObject.TimedObjectImpl;
/**
 * This class is the superclass of all abilities that are durable.
 */
public abstract class AbstractAbilityDurable extends AbstractAbility implements AbilityDurable {
    private static final long serialVersionUID = 1L;
    private final TimedObject timedObject;
    public AbstractAbilityDurable(final EAbility idAbility, final double duration) {
        super(idAbility, ETypeAbility.DURABLE);
        this.timedObject = new TimedObjectImpl(duration);
    }
    /**
     * This method return if the time of ability is expired.
     * @return the duration of the ability.
     */
    public Boolean isTimeOver() {
        return this.timedObject.isTimeOver();
    }
    /**
     * This method is used to update the time of abilities.
    * @param elapsedTime defines the time.
    */
    public void updateTimer(final double elapsedTime) {
          this.timedObject.updateTimer(elapsedTime);
    }
    /**
     * This method is used to get the remaining time.
     * @return the duration of the ability.
     */
    public double getRemainingTime() {
        return this.timedObject.getRemainingTime();
    }
}
