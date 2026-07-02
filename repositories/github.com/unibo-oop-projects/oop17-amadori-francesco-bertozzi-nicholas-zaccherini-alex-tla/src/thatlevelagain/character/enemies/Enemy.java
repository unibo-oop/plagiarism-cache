package thatlevelagain.character.enemies;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import thatlevelagain.view.map.MapImpl;
import thatlevelagain.view.sprite.SpineAlte;
import thatlevelagain.view.sprite.SpriteImpl;

/**
 * Abstract class that contains method common to the enemies of the game: Car and Gomitolo.
 * 
 * @author
 *
 */
public abstract class Enemy extends SpriteImpl implements EnemyInterface {

    /**
     * Increment of the count jump value.
     */
    public static final int ONE = 1;

    private final MapImpl map;
    private Rectangle rect;
    private final int initialX, initialY;
    private int aumentoX, aumentoY;
    private int direction;
    private boolean availableMovement;
    private boolean goDown;
    private boolean jumping;
    private int jumpState;
    private int countJump;
    private int currentCountJump;

    /**
     * 
     * Enemy constructor.
     * 
     * @param x 
     *          the initial X of the car
     * @param y 
     *          the initial y of the car
     * @param width 
     *          the width of the car
     * @param height 
     *          the height of the car
     * @param image 
     *          the image that represent the Sprite 
     * @param map 
     *          object of class MapImpl that contains the Sprite Car
     */
    public Enemy(final int x, final int y, final int width, final int height, final BufferedImage image, final MapImpl map) {
        super(x, y, width, height, image);
        this.map = map;
        rect = null;
        initialX = x;
        initialY = y;
        aumentoX = 0;
        aumentoY = 0;
        direction = LEFT;
        setInitAvailableMovement();
        goDown = true;
        jumping = false;
        jumpState = STOP;
        countJump = 0;
        currentCountJump = 0;
    }

    /**
     * Method that set the initial value of availableMovement.
     */
    protected abstract void setInitAvailableMovement();

    /**
     * Simulation of gravity effect. Behavior of the enemy if enemy is going down
     */
    protected abstract void gravityAction();

    /**
     * Method that update the collision of the car with the objects of class Mattoni. Set 
     * the goDown field if, after the check of the collision, the car is falling and is not
     * jumping
     */
    protected abstract void updateCollision();

    /**
     * 
     * @param deltaMovement 
     *          increment of the speed of the enemy
     */
    protected void speedUp(final int deltaMovement) {
        if (direction == LEFT) {
            incrementX(-deltaMovement);
        } else if (direction == RIGHT) {
            incrementX(deltaMovement);
        } else {
            incrementX(NONE);
        }
    }

    /**
     * 
     * @return 
     *          true if enemy can jump
     */
    protected abstract boolean canJump();

    /**
     * Behavior of enemy while it is jumping.
     */
    protected abstract void goUp();

    /**
     * Method that end the jump.
     */
    protected void finishJump() {
        goDown = true;
        jumping = false;
        jumpState = STOP;
    }

    /**
     * 
     * @return
     *     true if direction is right 
     */
    protected abstract boolean checkRightDirection();

    /**
     * Method that verify if enemy is colliding with object of class Spine.
     * If it is true, restart level setting initial values of enemy's fields
     */
    protected void spineCollision() {
        for (final SpineAlte s : this.map.getSpineAlte()) {
            if (this.getRectBottom().intersects(s.getBounds())) {
                restartLevel();
            }
        }
    }

    @Override
    public abstract void update(); 

    /**
     * 
     * @param aumentoX
     *           set aumentoX;
     */
    public void setAumentoX(final int aumentoX) {
        this.aumentoX = aumentoX;
    }

    /**
     * 
     * @param aumentoY
     *           set aumentoX;
     */
    public void setAumentoY(final int aumentoY) {
        this.aumentoY = aumentoY;
    }

    /**
     * 
     * @param dir 
     *          set direction of the Enemy
     */
    public void setDirection(final int dir) {
        direction = dir;
    }

    @Override
    public final void setAvailableMovement(final boolean flag) {
        availableMovement = flag;
    }

    @Override
    public final void setGoDown(final boolean flag) {
        goDown = flag;
    }

    @Override
    public final void setJumping(final boolean flag) {
        jumping = flag;
    }

    @Override
    public final MapImpl getMap() {
        return map;
    }

    @Override
    public final Rectangle getRect() {
        return rect;
    }

    /**
     * 
     * @return 
     *          value of InitialX field
     */
    public int getInitialX() {
        return initialX;
    }

    /**
     * 
     * @return 
     *          value of InitialY field
     */
    public int getInitialY() {
        return initialX;
    }

    /**
     * 
     * @return
     *         value of aumentoX field. 0 if there is not orizzontal movement
     */
    public int getAumentoX() {
        return this.aumentoX;
    }

    /**
     * 
     * @return
     *         value of aumentoY field. 0 if there is not vertical movement
     */
    public int getAumentoY() {
        return this.aumentoY;
    }

    @Override
    public final int getDirection() {
        return direction;
    }

    /**
     * 
     * @return 
     *          the state of jumping. 0 if enemy is not jumping, 1 if it is jumping 
     */
    public int getJumpState() {
        return jumpState;
    }

    @Override
    public final boolean isAvailableMovement() {
        return this.availableMovement;
    }

    @Override
    public final boolean isGoDown() {
        return goDown;
    }

    @Override
    public final boolean isJumping() {
        return jumping;
    }

    /**
     * 
     * @param amount 
     *          increment the counter used for jumping by amount
     */
    public void incrementCountJump(final int amount) {
        countJump += amount;
    }

    @Override
    public final void gravityEffect() {
        if (this.goDown) {
            gravityAction();
        } else {
            incrementY(NONE);
        }
    }

    @Override
    public final void updateMyDir() {
        if (map.getCat().getX() < this.getX() - FREE_SPACE) {
            direction = LEFT;
        } else if (checkRightDirection()) {
            direction = RIGHT;
        } else {
            direction = NONE;
        }
    }

    @Override
    public final void incrementX(final int movement) {
        this.setAumentoX(movement);
        this.setX(this.getX() + movement);
    }

    @Override
    public final void incrementY(final int movement) {
        this.setAumentoY(movement);
        this.setY(this.getY() + movement);
    }

    @Override
    public abstract void move();

    @Override
    public final void jump() {
        rect = RectCreator.getRectCreator().create(this);
        if (jumpState == STOP) {
            if (canJump()) {
                jumping = true;
                goDown = false;
                currentCountJump = countJump + N;
                incrementY(-DELTA_SALTO);
                jumpState = STARTED;
            }
        } else {
            if (countJump < currentCountJump && jumping) {
                goUp();
            } else {
                finishJump();
            }
        }
    }

    /**
     * Method that reset initial value of the enemy.
     */
    public final void restartLevel() {
        this.setX(initialX);
        this.setY(initialY);
        setGoDown(true);
        setDirection(LEFT);
        jumpState = STOP;
    }
}
