package model.entities.components.enemies;

import com.almasb.fxgl.app.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.entity.components.PositionComponent;

import model.entities.GameEntityTypes;
import model.entities.components.PlayerController;
import model.entities.components.HealthComponent;
import model.entities.components.PlayerBehaviours;

/**
 * Detects entities.
 */
public class SensorComponent extends Component {

    private final Entity player = FXGL.getApp().getGameWorld().getEntitiesByType(GameEntityTypes.PLAYER).get(0);
    private final EnemyController controller;
    private final int radius;
    private final PlayerController playerController = player.getComponent(PlayerController.class);
    /**
     * Returns a sensor component.
     * @param controller
     *                          this entity controller.
     * @param radius
     *                          the sensor radius.
     */
    public SensorComponent(final EnemyController controller, final int radius) {
        super();
        this.controller = controller;
        this.radius = radius;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onUpdate(final double tpf) {

        if (distanceXToPlayer(player) < 0 && controller.getBehaviour() != EnemyBehaviour.DYING) {
            entity.setScaleX(1);
        } else {
            entity.setScaleX(-1);
        }

        if (distanceToPlayer(player) < radius && controller.getBehaviour() != EnemyBehaviour.DYING) {
           controller.setBehaviour(EnemyBehaviour.AGGRESSIVE);
           player.getComponent(HealthComponent.class).incrementHealth(-1);
        } else if (distanceToPlayer(player) > radius) {
            controller.setBehaviour(EnemyBehaviour.LOOKING);
        }

        if ((distanceToPlayer(player) < playerController.getAttackRadius())
                && (playerController.getBehaviour().equals(PlayerBehaviours.ATTACKING))) {
            entity.getComponent(HealthComponent.class).incrementHealth(-playerController.getDamage());
         }
    }

    /**
     * 
     * @param player
     *                  the player entity.
     * @return          the distance between the enemy and the player.
     */
    private double distanceToPlayer(final Entity player) {
        return player.getComponent(PositionComponent.class).distance(getEntity().getComponent(PositionComponent.class));
    }

    /**
     * 
     * @param player
     *                  the player entity.
     * @return          the distance between the enemy and the player in the X axis.
     */
    private double distanceXToPlayer(final Entity player) {
        return entity.getX() - player.getX();
    }
}
