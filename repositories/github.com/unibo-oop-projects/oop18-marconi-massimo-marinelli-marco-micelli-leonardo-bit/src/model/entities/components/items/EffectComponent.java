package model.entities.components.items;

import com.almasb.fxgl.entity.component.Component;

/**
 * Implements a component which has an effect that can be triggered.
 */
public abstract class EffectComponent extends Component implements Effect {

    /**
     * {@inheritDoc}
     */
    @Override
    public abstract void whenTriggered();

}
