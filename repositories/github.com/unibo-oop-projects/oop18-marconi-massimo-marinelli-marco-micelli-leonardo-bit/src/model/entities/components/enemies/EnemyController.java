package model.entities.components.enemies;

import com.almasb.fxgl.app.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.physics.PhysicsComponent;

import model.entities.GameEntityClasses;
import model.entities.GameEntityTypes;
import model.entities.components.AbstractAnimationController;
import model.entities.components.PlayerController;
import model.entities.components.HealthComponent;

/**
 * Models a controller for an enemy entity, with all it's movements.
 */
public class EnemyController extends AbstractAnimationController {
    private final Entity player = FXGL.getApp().getGameWorld().getEntitiesByType(GameEntityTypes.PLAYER).get(0);
    private EnemyBehaviour behaviour;
    private int damage;

    /**
     * A controller for an enemy entity.
     * 
     * @param className
     *                          the entity class.
     * @param behaviour
     *                          determines the entity behaviour.
     */
    public EnemyController(final GameEntityClasses className, final EnemyBehaviour behaviour, final int damage) {
        super(className);
        this.behaviour = behaviour;
        this.damage = damage;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onUpdate(final double tpf) {
 
        if (behaviour.equals(EnemyBehaviour.LOOKING)) {
            if (super.getCurrentAnimation().equals(super.getAnimation("idle"))
                    || super.getCurrentAnimation().equals(super.getAnimation("attack_l/r"))) {
                super.loopAnimation("look_around");
            }
        } else if (behaviour.equals(EnemyBehaviour.AGGRESSIVE)) {
            if (super.getCurrentAnimation().equals(super.getAnimation("idle")) 
                    || super.getCurrentAnimation().equals(super.getAnimation("look_around"))) {
                super.loopAnimation("attack_l/r");

                player.getComponent(HealthComponent.class).incrementHealth(-damage);
                FXGL.getApp().getGameState().setValue("PlayerHealth", player.getComponent(HealthComponent.class).getHealth());
            }
        } else if (behaviour.equals(EnemyBehaviour.DYING)) {
            if (super.getCurrentAnimation().equals(super.getAnimation("look_around"))
                    || super.getCurrentAnimation().equals(super.getAnimation("attack_l/r"))) {
                super.loopAnimation("die");
            }

            if (super.getCurrentAnimation().equals(super.getAnimation("die"))) {
                super.loopAnimation("died");
            }
        }

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onAdded() {
        entity.setView(super.getTexture());
        entity.setScaleX(-1);
    }

    /**
     * 
     * @param behaviour
     *                          a new behaviour.
     */
    public void setBehaviour(final EnemyBehaviour behaviour) {
        this.behaviour = behaviour;
    }

    /**
     * 
     * @return behaviour.
     */
    public EnemyBehaviour getBehaviour() {
        return behaviour;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void stayIdle() {
        super.loopAnimation("idle");
    }
}
