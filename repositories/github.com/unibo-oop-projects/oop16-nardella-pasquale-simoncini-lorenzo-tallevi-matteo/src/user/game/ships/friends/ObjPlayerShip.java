package user.game.ships.friends;

import java.util.HashMap;
import java.util.Map;

import user.enums.GameSprites;
import user.enums.Objects;
import user.enums.PowerupRanks;
import user.game.ObjViewController;
import user.game.bullets.ObjBullet;
import user.game.ships.ObjShip;
import user.watcher.LevelWatcher;
import zengine.constants.ZengineKeyboardConstant;
import zengine.constants.ZengineMouseConstant;
import zengine.core.GameObject;
import zengine.geometry.HitboxCircle;

/**
 * This class represents the ship controlled by the player.
 */
public final class ObjPlayerShip extends ObjShip {

    // this field represents the value of the rotation speed of the ship
    private static final double ROTATION_SPEED = 7;
    // this field represents the value of the rotation speed of the ship
    private static final double ACCELERATION_FORCE = 0.5;
    // this field represents the value of the depth of the ship
    private static final int DEPTH = -6;
    // the value of the distance from the target necessary to make the target
    // pointer appear
    private static final int POINTER_TRIGGER = 500;
    // the value of the distance at which the target pointer appears
    private static final int POINTER_DISTANCE = 85;
    // the value of the target pointer's transparency
    private static final double POINTER_ALPHA = 0.8;
    // the value of the target pointer's scale
    private static final int POINTER_SCALE = 5;
    // a reference to the ObjViewController
    private ObjViewController viewController;
    // true if the view has been set
    private boolean viewSet;

    private final Map<Integer, Double> attackCooldownMap = new HashMap<Integer, Double>();
    private final Map<Integer, Double> maxSpeedMap = new HashMap<Integer, Double>();

    @Override
    public void create() {
        setSpriteIndex(GameSprites.PLAYER_SHIP.getValue());
        setImageXscale(4);
        setImageYscale(4);
        this.setDepth(DEPTH);

        this.setHitbox(new HitboxCircle(this.getX(), this.getY(), this.getHitboxValue()));

        this.setRotationSpeed(ROTATION_SPEED);
        this.setAccelerationForce(ACCELERATION_FORCE);

        addCollisionInteraction(Objects.ENEMY_BULLET.getValue());
        addCollisionInteraction(Objects.ENEMY_SHIP.getValue());

        this.setTrailColorIndex(0);

        this.fillAttackCooldownMap();
        this.fillMaxSpeedMap();

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
    public void destroy() {
        super.destroy();
        LevelWatcher.getWatcher().playerIsDead();
    }

    @Override
    public void draw() {
        super.draw();
        this.generatePointer();
    }

    /**
     * This method initializes the statistics of the ship.
     * 
     * @param health
     *            the health of the ship
     * @param power
     *            the power of the ship's bullets
     * @param attackCooldown
     *            the speed at which the ship shoots bullets
     * @param maxSpeed
     *            the movement speed of the ship
     */
    public void initializeShipStats(final int health, final int power, final int attackCooldown, final int maxSpeed) {
        if (!this.isInitialized()) {
            this.setHealth(health + 1);
            this.setPower(power + 1);
            this.setAttackCooldownCounter(attackCooldownMap.get(attackCooldown));
            this.setAttackCooldown(attackCooldownMap.get(attackCooldown));
            this.setMaxSpeed(maxSpeedMap.get(maxSpeed));
            this.setInitialized(true);
        }
    }

    @Override
    public void mouseClicked(final ZengineMouseConstant button) {
    }

    @Override
    public void update() {
        super.update();
        this.generateTrail();
        if (z().instanceExists(Objects.VIEW_CONTROLLER.getValue()) && !this.viewSet) {
            this.viewController = (ObjViewController) z().instanceGet(Objects.VIEW_CONTROLLER.getValue());
            this.viewController.setTarget(this);
            this.viewSet = true;
        }
    }

    @Override
    protected boolean canAccelerate() {
        return z().keyboardCheck(ZengineKeyboardConstant.VK_UP);
    }

    @Override
    protected void chooseTarget() {
        this.setTarget(z().instanceNearest(this.getX(), this.getY(), Objects.ENEMY_SHIP.getValue(),
                Objects.BOSS_SHIP.getValue()));
    }

    @Override
    protected void move() {
        if (z().keyboardCheck(ZengineKeyboardConstant.VK_LEFT)) {
            setImageAngle(getImageAngle() + this.getRotationSpeed());
            this.setRotatingLeft(true);
        } else {
            this.setRotatingLeft(false);
        }
        if (z().keyboardCheck(ZengineKeyboardConstant.VK_RIGHT)) {
            setImageAngle(getImageAngle() - this.getRotationSpeed());
            this.setRotatingRight(true);
        } else {
            this.setRotatingRight(false);
        }

        this.calculateSpeed();

        moveX(z().lengthDirX(getCurrentSpeed(), getImageAngle()));
        moveY(z().lengthDirY(getCurrentSpeed(), getImageAngle()));
        this.confineToRoomBoundaries();
    }

    @Override
    protected void shoot() {
        if (z().keyboardCheck(ZengineKeyboardConstant.VK_Z) && this.getAttackCooldownCounter() <= 0) {
            z().soundPlay("shoot0");
            this.generateBullet(Objects.FRIEND_BULLET.getValue(), this.getX(), this.getY(), this.getImageAngle());
        }
    }

    private void fillAttackCooldownMap() {
        this.attackCooldownMap.put(PowerupRanks.RANK_0.getValue(), PlayerAttackCooldownValues.RANK_0.getValue());
        this.attackCooldownMap.put(PowerupRanks.RANK_1.getValue(), PlayerAttackCooldownValues.RANK_1.getValue());
        this.attackCooldownMap.put(PowerupRanks.RANK_2.getValue(), PlayerAttackCooldownValues.RANK_2.getValue());
        this.attackCooldownMap.put(PowerupRanks.RANK_3.getValue(), PlayerAttackCooldownValues.RANK_3.getValue());
        this.attackCooldownMap.put(PowerupRanks.RANK_4.getValue(), PlayerAttackCooldownValues.RANK_4.getValue());
        this.attackCooldownMap.put(PowerupRanks.RANK_5.getValue(), PlayerAttackCooldownValues.RANK_5.getValue());
    }

    private void fillMaxSpeedMap() {
        this.maxSpeedMap.put(PowerupRanks.RANK_0.getValue(), PlayerMaxSpeedValues.RANK_0.getValue());
        this.maxSpeedMap.put(PowerupRanks.RANK_1.getValue(), PlayerMaxSpeedValues.RANK_1.getValue());
        this.maxSpeedMap.put(PowerupRanks.RANK_2.getValue(), PlayerMaxSpeedValues.RANK_2.getValue());
        this.maxSpeedMap.put(PowerupRanks.RANK_3.getValue(), PlayerMaxSpeedValues.RANK_3.getValue());
        this.maxSpeedMap.put(PowerupRanks.RANK_4.getValue(), PlayerMaxSpeedValues.RANK_4.getValue());
        this.maxSpeedMap.put(PowerupRanks.RANK_5.getValue(), PlayerMaxSpeedValues.RANK_5.getValue());
    }

    private void generatePointer() {
        if (z().instanceExists(this.getTarget()) && z().pointDistance(this.getX(), this.getY(), this.getTarget().getX(),
                this.getTarget().getY()) > POINTER_TRIGGER) {
            final double targetAngle = z().pointDirection(this.getX(), this.getY(), this.getTarget().getX(),
                    this.getTarget().getY());
            z().drawSpriteExt(GameSprites.TARGET_POINTER.getValue(), 0,
                    this.getX() + z().lengthDirX(POINTER_DISTANCE, targetAngle),
                    this.getY() + z().lengthDirY(POINTER_DISTANCE, targetAngle), POINTER_SCALE, POINTER_SCALE,
                    targetAngle, POINTER_ALPHA);
        }
    }

}
