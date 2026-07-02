package model.entities.components;


import com.almasb.fxgl.core.math.FXGLMath;
import com.almasb.fxgl.physics.PhysicsComponent;

import model.entities.GameEntityClasses;

/**
 * Models a generic controller for an entity, with all it's movements.
 *
 */
public class PlayerController extends AbstractAnimationController {
    private static final int VELOCITY = 200;
    private int attackSpeed;
    private final int attackRadius;
    private int damage;
    private final PhysicsComponent physics;
    private PlayerBehaviours behaviour = PlayerBehaviours.PACIFIC;

    /**
     * A controller for an entity.
     * 
     * @param className
     *                          the entity class.
     * @param physics
     *                          a physic component.
     */
    public PlayerController(final GameEntityClasses className, final PhysicsComponent physics, final int attackRadius, final int damage) {
        super(className);
        this.physics = physics;
        this.attackRadius = attackRadius;
        this.damage = damage;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onUpdate(final double tpf) {

        /////////////////////////////////////////////////////////////////////////////////
        if (attackSpeed != 0) {

            if (super.getCurrentAnimation().equals(super.getAnimation("idle"))) {
                   super.loopAnimation("attack_l/r");
               }

           attackSpeed = (int) (attackSpeed * 0.9);

              if (FXGLMath.abs(attackSpeed) < 1) {
                  attackSpeed = 0;
                  super.loopAnimation("idle");
              } 
          }

        ////////////////////////////////////////////////////////////////////////////////
        if (physics.getVelocityX() != 0) {

            if (super.getCurrentAnimation().equals(super.getAnimation("idle"))) {
                super.loopAnimation("walk_l/r");
            }

            physics.setVelocityX((int) physics.getVelocityX() * 0.5);

            if (FXGLMath.abs(physics.getVelocityX()) < 1) {
                super.loopAnimation("idle");
            }
        }
        ///////////////////////////////////////////////////////////////////////////////////

        if (physics.getVelocityY() < 0) {
            if (super.getCurrentAnimation().equals(super.getAnimation("idle"))) {
                super.loopAnimation("walk_up");
            }

            physics.setVelocityY((int) physics.getVelocityY() * 0.5);

            if (FXGLMath.abs(physics.getVelocityY()) < 1) {
                super.loopAnimation("idle");
            }
        }

        //////////////////////////////////////////////////////////////////////////////////

        if (physics.getVelocityY() > 0) {
            if (super.getCurrentAnimation().equals(super.getAnimation("idle"))) {
                super.loopAnimation("walk_down");
            }

            physics.setVelocityY((int) physics.getVelocityY() * 0.5);

            if (FXGLMath.abs(physics.getVelocityY()) < 1) {
                super.loopAnimation("idle");
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void stayIdle() {
       super.loopAnimation("idle");
    }

    /**
     * Moves the entity in the right direction.
     */
    public void moveRight() {
        physics.setVelocityX(VELOCITY);
        getEntity().setScaleX(1);
    }

    /**
     * Moves the entity in the left direction.
     */
    public void moveLeft() {
        physics.setVelocityX(-VELOCITY);
        getEntity().setScaleX(-1);
    }

    /**
     * Moves up the entity.
     */
    public void moveUp() {
        physics.setVelocityY(-VELOCITY);
    }

    /**
     * Moves down the entity.
     */
    public void moveDown() {
        physics.setVelocityY(VELOCITY);
    }

    /**
     * Let's the player entity.
     */
    public void attack() {
        this.attackSpeed = 150;
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public void onAdded() {
            entity.setView(super.getTexture());
    }

    /**
     * @return the behaviour
     */
    public PlayerBehaviours getBehaviour() {
        return behaviour;
    }

    /**
     * @param behaviour the behaviour to set
     */
    public void setBehaviour(final PlayerBehaviours behaviour) {
        this.behaviour = behaviour;
    }

    /**
     * @return the attackRadius
     */
    public int getAttackRadius() {
        return attackRadius;
    }

    /**
     * @return the damage
     */
    public int getDamage() {
        return damage;
    }

    /**
     * @param damage the damage to set
     */
    public void incrementDamage(final int quantity) {
        damage += quantity;
    }
}
