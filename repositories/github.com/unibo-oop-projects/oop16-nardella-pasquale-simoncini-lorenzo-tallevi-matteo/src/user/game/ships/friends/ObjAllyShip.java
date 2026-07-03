package user.game.ships.friends;

import user.enums.GameSprites;
import user.enums.Objects;
import user.game.bullets.ObjBullet;
import user.game.ships.ObjIAShip;
import user.game.ships.ObjShip;
import zengine.constants.ZengineMouseConstant;
import zengine.core.GameObject;
import zengine.geometry.HitboxCircle;

/**
 * This method represents an ally ship.
 */
public class ObjAllyShip extends ObjIAShip {

    // this field represents the value of the rotation speed of the ship
    private static final double ROTATION_SPEED = 7;
    // this field represents the value of the rotation speed of the ship
    private static final double ACCELERATION_FORCE = 0.5;
    // this field represents the value of the depth of the ship
    private static final int DEPTH = -6;
    // this field represents the stats' values of the ally ship
    private static final double HEALTH = 3;
    private static final double POWER = 1;
    private static final double ATTACK_COOLDOWN = 20;
    private static final double MAX_SPEED = 8;

    @Override
    public void create() {
        setSpriteIndex(GameSprites.ALLY_SHIP.getValue());
        setImageXscale(4);
        setImageYscale(4);

        addCollisionInteraction(Objects.ENEMY_BULLET.getValue());
        addCollisionInteraction(Objects.ENEMY_SHIP.getValue());

        this.setDepth(DEPTH);

        this.setRotationSpeed(ROTATION_SPEED);
        this.setAccelerationForce(ACCELERATION_FORCE);
        this.setHealth(HEALTH);
        this.setPower(POWER);
        this.setAttackCooldown(ATTACK_COOLDOWN);
        this.setAttackCooldownCounter(ATTACK_COOLDOWN);
        this.setMaxSpeed(MAX_SPEED);

        this.setHitbox(new HitboxCircle(this.getX(), this.getY(), this.getHitboxValue()));

        this.setTrailColorIndex(0);

        this.addCollisionInteraction(Objects.ENEMY_BULLET.getValue());
        this.addCollisionInteraction(Objects.ENEMY_SHIP.getValue());
    }

    @Override
    public void collide(final GameObject other) {
        if (z().instanceClassName(other).equals(Objects.ENEMY_BULLET.getValue())) {
            this.setHealth(this.getHealth() - ((ObjBullet) other).getDamage());
            z().instanceDestroy(other);
            if (this.isShipDead()) {
                z().instanceDestroy(this);
            }
        } else if (z().instanceClassName(other).equals(Objects.ENEMY_SHIP.getValue())) {
            this.setHealth(this.getHealth() - ((ObjShip) other).getPower());
            z().instanceDestroy(other);
            if (this.isShipDead()) {
                z().instanceDestroy(this);
            }
        }
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
    protected void chooseTarget() {
        this.setTarget(z().instanceNearest(this.getX(), this.getY(), Objects.ENEMY_SHIP.getValue(),
                Objects.BOSS_SHIP.getValue()));
    }

    @Override
    protected void shoot() {
        if (z().instanceExists(this.getTarget())
                && z().pointDistance(this.getX(), this.getY(), this.getTarget().getX(),
                        this.getTarget().getY()) < this.getMaxDistanceFromTarget() * 4
                && this.getAttackCooldownCounter() <= 0) {
            z().soundPlay("shoot2");
            this.generateBullet(Objects.FRIEND_BULLET.getValue(), this.getX(), this.getY(), this.getImageAngle());
        }
    }

}
