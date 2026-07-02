package vg.model.mystery_box;

import vg.model.Stage;
import vg.model.timedObject.TimedObject;
import vg.utils.V2D;

/**
 * This interface represents the ability of a {@link AbstractAbilityDurable} to be durable.
 */
public interface AbilityDurable extends AbilityInTheBox, TimedObject {
    /**
     * This method disable the ability.
     * @param stage defines the stage.
     */
    void deActivate(Stage<V2D> stage);
}
