package user.game.ships.enemies;

import user.enums.GameSprites;
import user.enums.Objects;
import user.game.bullets.ObjBullet;
import user.watcher.LevelWatcher;
import zengine.constants.ZengineMouseConstant;
import zengine.core.GameObject;
import zengine.geometry.HitboxCircle;

/**
 * This class represents a basic Enemy Ship.
 */
public class ObjEnemyShip extends ObjAbstractEnemyShip {

    // this field represents the value of the rotation speed of the ship
    private static final double ROTATION_SPEED = 3;
    // this field represents the value of the rotation speed of the ship
    private static final double ACCELERATION_FORCE = 0.5;
    // this field represents the value of the depth of the ship
    private static final int DEPTH = -6;

    @Override
    public void collide(final GameObject other) {
        if (z().instanceClassName(other).equals(Objects.FRIEND_BULLET.getValue())) {
            this.setHealth(this.getHealth() - ((ObjBullet) other).getDamage());
            z().instanceDestroy(other);
            if (this.isShipDead()) {
                z().instanceDestroy(this);
            }
        }
    }

    @Override
    public void create() {
        setSpriteIndex(GameSprites.ENEMY_SHIP.getValue());
        setImageXscale(4);
        setImageYscale(4);

        this.setDepth(DEPTH);

        this.setRotationSpeed(ROTATION_SPEED);
        this.setAccelerationForce(ACCELERATION_FORCE);

        addCollisionInteraction(Objects.FRIEND_BULLET.getValue());

        this.setTrailColorIndex(1);

        this.setHitbox(new HitboxCircle(this.getX(), this.getY(), this.getHitboxValue()));

    }

    @Override
    public void destroy() {
        super.destroy();
        LevelWatcher.getWatcher().enemyIsDead(this.getRank());
    }

    @Override
    public void mouseClicked(final ZengineMouseConstant button) {
    }

    @Override
    public void update() {
        super.update();
        this.generateTrail();
    }

    @Override
    public void setType(final int type) {
        if (!this.isInitialized()) {
            this.setRank(type);
            switch (type) {
            case 1:
                this.setImageIndex(1);
                this.setHealth(EnemyRank1Stats.HEALTH.getValue());
                this.setPower(EnemyRank1Stats.POWER.getValue());
                this.setAttackCooldown(EnemyRank1Stats.ATTACK_COOLDOWN.getValue());
                this.setAttackCooldownCounter(EnemyRank1Stats.ATTACK_COOLDOWN.getValue());
                this.setMaxSpeed(EnemyRank1Stats.MAX_SPEED.getValue());
                break;
            case 2:
                this.setImageIndex(2);
                this.setHealth(EnemyRank2Stats.HEALTH.getValue());
                this.setPower(EnemyRank2Stats.POWER.getValue());
                this.setAttackCooldown(EnemyRank2Stats.ATTACK_COOLDOWN.getValue());
                this.setAttackCooldownCounter(EnemyRank2Stats.ATTACK_COOLDOWN.getValue());
                this.setMaxSpeed(EnemyRank2Stats.MAX_SPEED.getValue());
                break;
            case 3:
                this.setImageIndex(0);
                this.setHealth(EnemyRank3Stats.HEALTH.getValue());
                this.setPower(EnemyRank3Stats.POWER.getValue());
                this.setAttackCooldown(EnemyRank3Stats.ATTACK_COOLDOWN.getValue());
                this.setAttackCooldownCounter(EnemyRank3Stats.ATTACK_COOLDOWN.getValue());
                this.setMaxSpeed(EnemyRank3Stats.MAX_SPEED.getValue());
                break;
            default:
                break;
            }
        }
    }

    @Override
    protected void shoot() {
        if (z().instanceExists(this.getTarget())
                && z().pointDistance(this.getX(), this.getY(), this.getTarget().getX(),
                        this.getTarget().getY()) < this.getMaxDistanceFromTarget() * 4
                && this.getAttackCooldownCounter() <= 0) {
            z().soundPlay("shoot1");
            this.generateBullet(Objects.ENEMY_BULLET.getValue(), this.getX(), this.getY(), this.getImageAngle());
        }
    }

}
