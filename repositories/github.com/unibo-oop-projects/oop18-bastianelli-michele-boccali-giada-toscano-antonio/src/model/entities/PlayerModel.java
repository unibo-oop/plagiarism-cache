package model.entities;

import enumerators.EntityType;
import enumerators.PlayerCharacter;
import model.CollisionHandler.CollisionSide;
import model.components.AttackImpl;
import model.components.CollisionImpl;
import model.components.ComandableMovement;
import model.components.JumpImpl;
import model.components.LifeImpl;
import model.physics.PhysicEntity;

/**
 * The player model.
 */
public class PlayerModel extends AbstractEntityModel {

    private static final EntityType TYPE = EntityType.PLAYER;
    private static final float DEFAULT_SPEED = 2;
    private static final float DEFAULT_JUMP = 70;
    private static final int DEFAULT_ATTACK = 1;
    private static final int DEFAULT_LIFE = 1;

    /**
     * PLayer model constructor.
     * 
     * @param character    the specific player character
     * @param physicEntity the physic body of the entity
     */
    public PlayerModel(final PlayerCharacter character, final PhysicEntity physicEntity) {
        super(TYPE, character, physicEntity);
    }

    /**
     * Add the movement component with the default movement speed for a player.
     */
    protected void addDefaultPlayerMovement() {
        add(new ComandableMovement(this, DEFAULT_SPEED));
    }

    /**
     * Add the jump component with the default jump value for a player.
     */
    protected void addDefaultPlayerJump() {
        add(new JumpImpl(this, DEFAULT_JUMP));
    }

    /**
     * Add the attack component with the default attack value for a player.
     */
    protected void addDefaultPlayerAttack() {
        this.add(new AttackImpl(DEFAULT_ATTACK));
    }

    /**
     * Add the life component with the default life values for a player.
     */
    protected void addDefaultPlayerLife() {
        this.add(new LifeImpl(this, DEFAULT_LIFE, DEFAULT_LIFE, DEFAULT_LIFE));
    }

    /**
     * Add the collision component with the default collision effect for a player.
     */
    protected void addDefaultPlayerCollisionEffect() {
        this.add(new CollisionImpl((e, s) -> {
            if ((s.equals(CollisionSide.BOTTOM) || e.getEntityType().equals(EntityType.COIN)) && this.contain(AttackImpl.class)) {
                this.getComponent(AttackImpl.class).applyDamage(e);
            }
        }));
    }
}
