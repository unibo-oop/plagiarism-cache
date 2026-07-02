package model.entities.components.items;

import com.almasb.fxgl.app.FXGL;
import com.almasb.fxgl.entity.Entity;

import model.entities.GameEntityTypes;
import model.entities.components.PlayerController;

/**
 * Implements the effect of a strengthening potion.
 */
public class BluePotionEffect extends EffectComponent {

    private static final int STRENGTHENING = 2;

    /**
     * {@inheritDoc}
     */
    @Override
    public void whenTriggered() {
        final Entity player = FXGL.getApp().getGameWorld().getEntitiesByType(GameEntityTypes.PLAYER).get(0);
        final PlayerController controller = player.getComponent(PlayerController.class);

        controller.incrementDamage(STRENGTHENING);

        entity.removeFromWorld();
    }

}
