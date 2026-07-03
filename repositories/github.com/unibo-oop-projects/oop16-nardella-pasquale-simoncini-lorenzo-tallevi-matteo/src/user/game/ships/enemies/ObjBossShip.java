package user.game.ships.enemies;

import user.enums.GameSprites;
import user.enums.Objects;
import user.game.bullets.ObjBullet;
import user.game.effects.ObjEffectEmitter;
import user.watcher.LevelWatcher;
import zengine.constants.ZengineMouseConstant;
import zengine.core.GameObject;
import zengine.geometry.HitboxCircle;

/**
 * This class represents a boss ship.
 */
public class ObjBossShip extends ObjAbstractEnemyShip {

    // this field represents the value of the rotation speed of the ship
    private static final double SHIP_ROTATION_SPEED = 0.5;
    // this field represents the value of the rotation speed of the turrets
    private static final double TURRET_ROTATION_SPEED = 1.5;
    // this field represents the value of the acceleration force that applies to
    // the ship
    private static final double ACCELERATION_FORCE = 0.2;
    // this fields represents the values that describes the turret's position in
    // relation to boss' position
    private static final double TURRET_SHIFT_X = 20;
    private static final double TURRET_SHIFT_Y = 80;
    // this field represent the image scale
    private static final double SCALE = 5;

    private double targetAngle;

    @Override
    public void create() {
        setImageXscale(SCALE);
        setImageYscale(SCALE);

        this.setDepth(-1);

        this.setRotationSpeed(SHIP_ROTATION_SPEED);
        this.setAccelerationForce(ACCELERATION_FORCE);

        this.setHitbox(new HitboxCircle(this.getX(), this.getY(), this.getHitboxValue() * 3));

        this.addCollisionInteraction(Objects.ALLY_SHIP.getValue());
        this.addCollisionInteraction(Objects.PLAYER_SHIP.getValue());
        this.addCollisionInteraction(Objects.FRIEND_BULLET.getValue());
    }

    @Override
    public void draw() {
        super.draw();
        z().drawSpriteExt(GameSprites.TURRET_BIG.getValue(), 0, this.getX(), this.getY(), 4, 4, this.targetAngle, 1);
        z().drawSpriteExt(GameSprites.TURRET_SMALL.getValue(), 0,
                z().pointXrotate(this.getX() + TURRET_SHIFT_X, this.getY() + TURRET_SHIFT_Y, this.getX(), this.getY(),
                        this.getImageAngle()),
                z().pointYrotate(this.getX() + TURRET_SHIFT_X, this.getY() + TURRET_SHIFT_Y, this.getX(), this.getY(),
                        this.getImageAngle()),
                4, 4, this.targetAngle, 1);
        z().drawSpriteExt(GameSprites.TURRET_SMALL.getValue(), 0,
                z().pointXrotate(this.getX() + TURRET_SHIFT_X, this.getY() - TURRET_SHIFT_Y, this.getX(), this.getY(),
                        this.getImageAngle()),
                z().pointYrotate(this.getX() + TURRET_SHIFT_X, this.getY() - TURRET_SHIFT_Y, this.getX(), this.getY(),
                        this.getImageAngle()),
                4, 4, this.targetAngle, 1);
    }

    @Override
    public void setType(final int type) {
        if (!this.isInitialized()) {
            this.setRank(type);
            switch (type) {
            case 1:
                this.setSpriteIndex(GameSprites.BOSS_SHIP_1.getValue());
                this.setHealth(BossRank1Stats.HEALTH.getValue());
                this.setPower(BossRank1Stats.POWER.getValue());
                this.setAttackCooldown(BossRank1Stats.ATTACK_COOLDOWN.getValue());
                this.setAttackCooldownCounter(BossRank1Stats.ATTACK_COOLDOWN.getValue());
                this.setMaxSpeed(BossRank1Stats.MAX_SPEED.getValue());
                break;
            case 2:
                this.setSpriteIndex(GameSprites.BOSS_SHIP_2.getValue());
                this.setHealth(BossRank2Stats.HEALTH.getValue());
                this.setPower(BossRank2Stats.POWER.getValue());
                this.setAttackCooldown(BossRank2Stats.ATTACK_COOLDOWN.getValue());
                this.setAttackCooldownCounter(BossRank2Stats.ATTACK_COOLDOWN.getValue());
                this.setMaxSpeed(BossRank2Stats.MAX_SPEED.getValue());
                break;
            default:
                break;
            }
        }
    }

    @Override
    public void collide(final GameObject other) {
        if (z().instanceClassName(other).equals(Objects.FRIEND_BULLET.getValue())) {
            this.setHealth(this.getHealth() - ((ObjBullet) other).getDamage());
            z().instanceDestroy(other);
            if (this.isShipDead()) {
                z().instanceDestroy(this);
            }
        } else {
            z().instanceDestroy(other);
        }
    }

    @Override
    public void destroy() {
        final ObjEffectEmitter ef = (ObjEffectEmitter) z().instanceCreate(this.getX(), this.getY(),
                Objects.EFFECT_EMITTER.getValue());
        ef.createBigExplosion();
        LevelWatcher.getWatcher().enemyIsDead(this.getRank() * 10);
    }

    @Override
    public void mouseClicked(final ZengineMouseConstant button) {
    }

    @Override
    public void update() {
        super.update();
        this.updateTargetAngle();
    }

    @Override
    protected void shoot() {
        ObjBullet bullet;
        if (z().instanceExists(this.getTarget())
                && z().pointDistance(this.getX(), this.getY(), this.getTarget().getX(),
                        this.getTarget().getY()) < this.getMaxDistanceFromTarget() * 4
                && this.getAttackCooldownCounter() <= 0) {
            z().soundPlay("shootHeavy");
            bullet = this.generateBullet(Objects.ENEMY_BULLET.getValue(), this.getX(), this.getY(), this.targetAngle);
            bullet.setSpriteIndex(GameSprites.PLASMA_BULLET.getValue());
            bullet.setImageIndex(1);
            bullet = this.generateBullet(Objects.ENEMY_BULLET.getValue(),
                    z().pointXrotate(this.getX() + TURRET_SHIFT_X, this.getY() + TURRET_SHIFT_Y, this.getX(),
                            this.getY(), this.getImageAngle()),
                    z().pointYrotate(this.getX() + TURRET_SHIFT_X, this.getY() + TURRET_SHIFT_Y, this.getX(),
                            this.getY(), this.getImageAngle()),
                    this.targetAngle);
            bullet.setSpriteIndex(GameSprites.TURRET_BULLET.getValue());
            bullet.setImageIndex(1);
            bullet = this.generateBullet(Objects.ENEMY_BULLET.getValue(),
                    z().pointXrotate(this.getX() + TURRET_SHIFT_X, this.getY() - TURRET_SHIFT_Y, this.getX(),
                            this.getY(), this.getImageAngle()),
                    z().pointYrotate(this.getX() + TURRET_SHIFT_X, this.getY() - TURRET_SHIFT_Y, this.getX(),
                            this.getY(), this.getImageAngle()),
                    this.targetAngle);
            bullet.setSpriteIndex(GameSprites.TURRET_BULLET.getValue());
            bullet.setImageIndex(1);

        }
    }

    private void updateTargetAngle() {
        if (z().instanceExists(this.getTarget())) {
            this.targetAngle = z().angleRotate(this.targetAngle,
                    z().pointDirection(this.getX(), this.getY(), this.getTarget().getX(), this.getTarget().getY()),
                    TURRET_ROTATION_SPEED);
        }
    }

}
