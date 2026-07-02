package zombietsunami.model.zombiemodel.impl;

import zombietsunami.model.EntityImpl;
import zombietsunami.model.MapData;
import zombietsunami.model.zombiemodel.api.Zombie;

/**
 * Implementation of the Zombie interface, representing a zombie entity in the
 * Zombie Tsunami game.
 *
 * @see zombietsunami.model.EntityImpl
 * @see zombietsunami.model.zombiemodel.api.Zombie
 */
public class ZombieImpl implements Zombie {
    private final EntityImpl entity = new EntityImpl();
    private final JumpZombieImpl jumpZombie = new JumpZombieImpl(this.entity);
    private final int screenX;
    private final int screenY;
    private static final int INITIALZOMBIE_X = 380;
    private static final int INITIALZOMBIE_Y = 450;
    private static final int INITIALZOMBIE_SPEED = 1;
    private static final int STRENGTH = 1;

    /**
     * Updates the zombie's position based on its speed.
     */
    public ZombieImpl() {
        this.screenX = MapData.getScreenW() / 2 - MapData.getTitSize() / 2;
        this.screenY = MapData.getScreenH() - (MapData.getTitSize() * 3);
        setDefaultValues();
    }

    private void setDefaultValues() {
        entity.setX(INITIALZOMBIE_X);
        entity.setY(INITIALZOMBIE_Y);
        entity.setSpeed(INITIALZOMBIE_SPEED);
        entity.setStrength(STRENGTH);
    }

    /**
     * Updates the zombie's position based on its speed.
     */
    @Override
    public void update() {
        entity.setX(entity.getX() + entity.getSpeed());
    }

    /**
     * Gets the X-coordinate of the zombie on the map.
     *
     * @return the X-coordinate of the zombie.
     */
    @Override
    public int getX() {
        return entity.getX();
    }

    /**
     * Gets the Y-coordinate of the zombie on the map.
     *
     * @return the Y-coordinate of the zombie.
     */
    @Override
    public int getY() {
        return entity.getY();

    }

    /**
     * Gets the speed of the zombie.
     *
     * @return the speed of the zombie.
     */
    @Override
    public int getSpeed() {
        return entity.getSpeed();
    }

    /**
     * Gets the screen X-coordinate where the zombie is initially placed on the
     * screen.
     *
     * @return the screen X-coordinate.
     */
    @Override
    public int getScreenX() {
        return this.screenX;
    }

    /**
     * Gets the screen Y-coordinate where the zombie is initially placed on the
     * screen.
     *
     * @return the screen Y-coordinate.
     */
    @Override
    public int getScreenY() {
        return this.screenY;
    }

    /**
     * Gets the strength of the zombie.
     *
     * @return the strength of the zombie.
     */
    @Override
    public int getStrength() {
        return this.entity.getStrength();
    }

    /**
     * Increases the strength of the zombie by one.
     */
    @Override
    public void increaseStrength() {
        this.entity.increaseStrength();
    }

    /**
     * Initiates a jump action for the zombie.
     */
    @Override
    public void jumpPress() {
        this.jumpZombie.jumpPress();
    }

    /**
     * Updates the jump action for the zombie.
     */
    @Override
    public void updateJumpZombie() {
        this.jumpZombie.updateJumpZombie();
    }

    /**
     * Checks if the zombie is currently jumping.
     *
     * @return true if the zombie is jumping.
     */
    @Override
    public boolean isJumping() {
        return jumpZombie.isJumping();
    }

    /**
     * Decreases the strength of the zombie by one.
     */
    @Override
    public void decreaseStrength() {
        this.entity.decreaseStrength();
    }

}
