package model.entities.components.items;

import com.almasb.fxgl.app.FXGL;
import com.almasb.fxgl.entity.Entity;

import model.entities.GameEntityTypes;
import model.entities.components.HealthComponent;

/**
 * Implements the effect of an healing potion.
 */
public class RedPotionEffect extends EffectComponent {
    private static final int HEALING = 100;

    /**
     * {@inheritDoc}
     */
    @Override
    public void whenTriggered() {
        final Entity player = FXGL.getApp().getGameWorld().getEntitiesByType(GameEntityTypes.PLAYER).get(0);

        player.getComponent(HealthComponent.class).incrementHealth(HEALING);

        entity.removeFromWorld();
    }

}
