package it.unibo.roguekong.model.entity.impl;

import it.unibo.roguekong.model.entity.Player;
import it.unibo.roguekong.model.entity.PowerUp;
import it.unibo.roguekong.model.entity.impl.powerup.ChangePlayerGravity;
import it.unibo.roguekong.model.entity.impl.powerup.ChangePlayerSpeed;
import it.unibo.roguekong.model.entity.impl.powerup.DoubleJump;
import it.unibo.roguekong.model.game.impl.HitboxImpl;
import it.unibo.roguekong.model.game.impl.TileManager;
import it.unibo.roguekong.model.game.impl.TileType;
import it.unibo.roguekong.model.value.impl.Gravity;
import it.unibo.roguekong.model.value.impl.LivesImpl;
import it.unibo.roguekong.model.value.impl.PositionImpl;
import it.unibo.roguekong.model.value.impl.VelocityImpl;

import java.util.ArrayList;
import java.util.List;

public class PlayerImpl implements Player {
    private static final double GRAVITY = 0.05;
    private static final double MAX_FALL_SPEED = 2;
    private static final int LIVES_AT_START = 3;

    private PositionImpl position = new PositionImpl();
    private HitboxImpl hitbox;
    private VelocityImpl velocity = new VelocityImpl();
    private int remainingJumps;
    private int maxJumps;
    private int jumpForce;
    private List<PowerUp> activePowerUps = new ArrayList<PowerUp>();
    private String sprite = "";
    private LivesImpl lives = new LivesImpl();
    private TileManager tileManager;
    private boolean isInvulnerable;
    private Gravity gravity;

    /**
     * Void constructor that sets the standard values for the player initialization
     */
    public PlayerImpl() {
        hitbox = new HitboxImpl(getPosition(), 32, 32);
        this.maxJumps = 1;
        this.remainingJumps = this.maxJumps;
        this.jumpForce = 3;
        this.gravity = new Gravity(GRAVITY, MAX_FALL_SPEED);
        setLives(new LivesImpl(LIVES_AT_START));
        setSprite("/assets/sprites/standing-mario-right.png");
        isInvulnerable = false;
    }

    /**
     * @return LivesImpl value, that shows the remaining lives of the player
     */
    @Override
    public LivesImpl getLives() {
        return lives;
    }

    /**
     * set values of player's lives
     * @param lives lives of player
     */
    @Override
    public void setLives(LivesImpl lives) {
        this.lives = lives;
    }

    /**
     * gives the player's sprite
     * @return String value, gives the path of player image
     */
    @Override
    public String getSprite() {
        return this.sprite;
    }

    /**
     * gives the player's position
     * @return PositionImpl value, gives the cords of player's top-left pixel
     */
    @Override
    public PositionImpl getPosition() {
        return this.position;
    }

    /**
     * gives the player's velocity
     * @return VelocityImpl value, gives the value of player's velocity in both axes
     */
    @Override
    public VelocityImpl getVelocity() {
        return this.velocity;
    }

    /**
     * gives the player's active power-ups
     * @return List<PowerUp>, gives the player's list of active power-ups
     */
    @Override
    public List<PowerUp> getActivePowerUps() {
        return activePowerUps;
    }

    /**
     * let to add a powerUp class at player's powerUp list
     * @param powerUp player power-up
     */
    @Override
    public void addPowerUp(PowerUp powerUp) {
        this.activePowerUps.add(powerUp);
    }

    /**
     * can set the player's image sprite
     * @param sprite image path string
     */
    public void setSprite(String sprite) {
        this.sprite = sprite;
    }

    /**
     * can set the player's x position
     * @param x player's x position
     */
    @Override
    public void moveX(double x) {
        setPosition(x, getPosition().getY());
    }

    /**
     * can set the player's y position
     * @param y player's y position
     */
    @Override
    public void moveY(double y) {
        setPosition(getPosition().getX(), y);
    }

    /**
     * can set to the player the actual tileSet of a single level
     * @param tileManager gives at player the acknowledgement of tileSet
     */
    public void setTileManager(TileManager tileManager) {
        this.tileManager = tileManager;
    }

    /**
     * can set player's position in both axes
     * @param x position x
     * @param y positions y
     */
    @Override
    public void setPosition(double x, double y) {
        if(!collidesAt(x, y)){
            getHitbox().moveHitBox(x, y);
            setXandY(new PositionImpl(getHitbox().getBounds().getMinX(), getHitbox().getBounds().getMinY()));
        }
    }

    /**
     * can increment by 1 the number of player's jumps
     */
    public void incrementMaxJumps(){
        this.maxJumps++;
        this.remainingJumps = this.maxJumps;
    }

    /**
     * gives the number of player's max jumps
     * @return int value
     */
    public int getMaxJumps(){
        return this.maxJumps;
    }

    /**
     * set a specific number of maxJumps
     * @param maxJumps value of new jumps the player can do
     */
    public void setMaxJumps(int maxJumps){
        this.maxJumps = maxJumps;
    }

    /**
     * check if it is colliding with some tile
     * @param x value of x position
     * @param y value of y position
     * @return boolean value, true if is colliding with a collidable tile, false if is not
     */
    private boolean collidesAt(double x, double y) {
        double left = x;
        double right = x + 31;
        double top = y;
        double bottom = y + 31;

        return tileManager.getTileAtPosition(new PositionImpl(left, top)).isCollidable()
                || tileManager.getTileAtPosition(new PositionImpl(right, top)).isCollidable()
                || tileManager.getTileAtPosition(new PositionImpl(left, bottom)).isCollidable()
                || tileManager.getTileAtPosition(new PositionImpl(right, bottom)).isCollidable();
    }

    /**
     * Check if player is on a ladder tile type
     * @param x player world position
     * @param y player world position
     * @return boolean value, that evaluates if player is or is not on a ladder
     */
    public boolean collidesWithLadder(double x, double y) {
        double left = x;
        double right = x + 31;
        double top = y;
        double bottom = y + 31;

        return tileManager.getTileAtPosition(new PositionImpl(left, top)).getTileType() == TileType.LADDER
                || tileManager.getTileAtPosition(new PositionImpl(right, top)).getTileType() == TileType.LADDER
                || tileManager.getTileAtPosition(new PositionImpl(left, bottom)).getTileType() == TileType.LADDER
                || tileManager.getTileAtPosition(new PositionImpl(right, bottom)).getTileType() == TileType.LADDER;
    }

    /**
     * Check if player collides with a tile that deals damage
     * @param x player position
     * @param y player position
     * @return boolean value, that evaluates if player got hit or not
     */
    public boolean isPlayerHit(double x, double y) {
        double left = x;
        double right = x + 31;
        double top = y;
        double bottom = y + 31;

        return tileManager.getTileAtPosition(new PositionImpl(left, top)).isCanDealDamage()
                || tileManager.getTileAtPosition(new PositionImpl(right, top)).isCanDealDamage()
                || tileManager.getTileAtPosition(new PositionImpl(left, bottom)).isCanDealDamage()
                || tileManager.getTileAtPosition(new PositionImpl(right, bottom)).isCanDealDamage();

    }

    /**
     * change position of player by the value of a PositionImpl class
     * @param position class PositionImpl, set the actual position
     */
    private void setXandY(PositionImpl position) {
        this.position = position;
    }

    /**
     * gives the values of player's hitbox
     * @return HitboxImpl, the player's hitbox
     */
    public HitboxImpl getHitbox() {
        return hitbox;
    }

    /**
     * set the player's velocity
     * @param velocity values of velocity
     */
    @Override
    public void setVelocity(VelocityImpl velocity) {
        this.velocity = velocity;
    }

    /**
     * invoke the method for decrementing the lives
     */
    public void hit(){
        this.lives.decrementLives();
    }

    /**
     * reset the player status when is necessary
     */
    public void resetPlayerStatus(){
        setLives(new LivesImpl(LIVES_AT_START));
        ChangePlayerGravity.removeEffect(this);
        ChangePlayerSpeed.removeEffect(this);
        DoubleJump.removeEffect(this);
    }

    /**
     * Method that contains the actual logic jump
     * @return true if player can actually jump, else it returns false
     */
    public boolean jump(){
        if(this.remainingJumps > 0) {
            this.velocity.setVelocityY(-jumpForce);
            this.remainingJumps--;
            return true;
        } else {
            return false;
        }
    }

    /**
     * Must be used to update players physic
     */
    public void setGravityOnPlayer() {
        this.velocity.setVelocityY(Math.min(this.velocity.getVelocityY() + this.gravity.gravity(), this.gravity.max_fall_speed()));

        if (!collidesAt(this.position.getX(), this.position.getY() + this.velocity.getVelocityY())) {
            moveY(this.position.getY() + this.velocity.getVelocityY());
        } else {
            if (this.velocity.getVelocityY() > 0) {
                this.remainingJumps = this.maxJumps;
            }
            this.velocity.setVelocityY(0);
        }
    }

    /**
     * Must be called when it's necessary to change world gravity
     * @param gravity world
     * @param max_fall_speed max velocity when player is falling
     */
    public void setGravity(double gravity, double max_fall_speed){
        this.gravity = new Gravity(gravity, max_fall_speed);
    }

    public Gravity getGravity(){
        return this.gravity;
    }

    public void setInvulnerability(boolean isInvulnerable){
        this.isInvulnerable = isInvulnerable;
    }

    public boolean hasInvulnerability(){
        return this.isInvulnerable;
    }
}

