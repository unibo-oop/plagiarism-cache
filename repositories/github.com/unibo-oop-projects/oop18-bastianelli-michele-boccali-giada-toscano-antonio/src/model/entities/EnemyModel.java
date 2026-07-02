package model.entities;

import enumerators.EnemyCharacter;
import enumerators.EntityType;
import model.CollisionHandler.CollisionSide;
import model.components.AttackImpl;
import model.components.CollisionImpl;
import model.components.JumpImpl;
import model.components.LifeImpl;
import model.physics.PhysicEntity;

public class EnemyModel extends AbstractEntityModel {

    private static final EntityType TYPE = EntityType.ENEMY;
    private static final float DEFAULT_BOUNCE = 100.0f;
    private static final int DEFAULT_LIFE = 1;
    private static final int DEFAULT_ATTACK = 1;

    public EnemyModel(final EnemyCharacter character, final PhysicEntity physicEntity) {
        super(TYPE, character, physicEntity);
    }

    /**
     * Add the default life value for an enemy.
     */
    protected void addDefaultEnemyLife() {
        this.add(new LifeImpl(this, DEFAULT_LIFE, DEFAULT_LIFE, DEFAULT_LIFE));
    }

    /**
     * Add the default collision effect that will do the other entity bounce away.
     */
    protected void addDefaultEnemyCollisionEffect() {
        this.add(new CollisionImpl((e, s) -> {
            if (e.getEntityType().equals(EntityType.PLAYER)) {
                // if the player collided from his bottom side he gets a jump
                if (s.equals(CollisionSide.BOTTOM) && e.contain(JumpImpl.class)) {
                    e.getComponent(JumpImpl.class).jumpFromExternalForce(DEFAULT_BOUNCE);
                } else {
                    // if the player collided from other sides he gets a damage
                    if (this.contain(AttackImpl.class)) {
                        this.getComponent(AttackImpl.class).applyDamage(e);
                    }
                }
            }
        }));
    }

    /**
     * Add the default attack value for an enemy.
     */
    protected void addDefaultEnemyAttack() {
        this.add(new AttackImpl(DEFAULT_ATTACK));
    }

}
