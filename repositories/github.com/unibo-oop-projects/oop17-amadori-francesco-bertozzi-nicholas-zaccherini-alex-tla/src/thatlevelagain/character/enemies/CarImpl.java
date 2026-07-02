package thatlevelagain.character.enemies;

import javax.sound.sampled.Clip;
import thatlevelagain.character.enemies.collision.Collision;
import thatlevelagain.character.enemies.collision.CollisionCar;
import thatlevelagain.sound.SoundManager;
import thatlevelagain.sound.SoundPath;
import thatlevelagain.view.load_image.ImageManager;
import thatlevelagain.view.load_image.ImagePath;
import thatlevelagain.view.map.MapImpl;

/**
 * Class that extends Enemy. It is the representation of the enemy "Accalappiagatti"
 * 
 * @author
 *
 */
public class CarImpl extends Enemy {

    /**
     * Interval of space between Car and Player in which the Car does not have to move.
     */
    public static final int FREE_SPACE_LEFT = 20;

    /**
     * Above this value the car does not have to jump.
     */
    public static final int MINIMUM_Y = 335;

    /**
     * Speed up of the car. Its X change by 1
     */
    public static final int DELTA_MOVEMENT = 1;

    private static final int SPACE_Y_ATTACK = 20;
    private static final int SPACE_X_ATTACK = 150;

    private final CollisionCar collisionManager;
    private final Clip retro = SoundManager.getListLoader().get(SoundPath.RETRO.getPosition()).getClip();


    /**
     * 
     * CarImpl constructor.
     * 
     * @param x 
     *          the initial X of the car
     * @param y 
     *          the initial y of the car
     * @param width 
     *          the width of the car
     * @param height 
     *          the height of the car
     * @param map 
     *          object of class MapImpl that contains the Sprite Car
     */
    public CarImpl(final int x, final int y, final int width, final int height, final MapImpl map) {
        super(x, y, width, height, ImageManager.getListLoader().get(ImagePath.ACCALAPPIAGATTI_SINISTRA.getPosition()), map);
        collisionManager = new CollisionCar();
    }

    /**
     * 
     * @return
     *          true if the Y of the enemy allows to jump
     */
    private boolean checkYJump() {
        return getY() > getMap().getCat().getY() || getY() >= MINIMUM_Y;
    }

    private boolean checkYAttack() {
        return getY() > getMap().getCat().getY() - SPACE_Y_ATTACK && getY() < getMap().getCat().getY() + SPACE_Y_ATTACK;
    }

    private boolean checkXAttack() {
        return getX() > getMap().getCat().getX() - SPACE_X_ATTACK && getX() < getMap().getCat().getX() + SPACE_X_ATTACK;
    }

    private boolean checkAttack() {
        return checkYAttack() && checkXAttack();
    }

    @Override
    protected final void setInitAvailableMovement() {
        setAvailableMovement(true);
    }

    @Override
    protected final void updateCollision() {
        collisionManager.intersection(this, this.getMap().getMattoni());
        setGoDown(collisionManager.isFalling() && getJumpState() == STOP);
    }

    @Override
    protected final void gravityAction() {
        incrementY(DELTA_MOVEMENT);
    }

    @Override
    protected final boolean checkRightDirection() {
        return getMap().getCat().getX() > this.getX() + FREE_SPACE_LEFT;
    }

    @Override
    protected final boolean canJump() {
        final boolean checkEdge = CheckerEdge.getChecker().check(this);
        return checkYJump() && checkEdge;
    }

    @Override
    protected final void goUp() {
        incrementY(-DELTA_SALTO);
        if (isAvailableMovement()) {
            speedUp(DELTA_MOVEMENT);
        }
    }

    @Override
    public final Collision getCollisionManager() {
        return collisionManager;
    }

    @Override
    public final void update() {
        incrementCountJump(ONE);
        updateMyDir();          //update the direction of the car
        jump();                 //jump if it is necessary
        updateCollision();      //check and update collision
        move();                 //move the car if it is possible
        attack();               //attack if there are condition to do that
        spineCollision();       //check the collision with spine, restart
        gravityEffect();        //gravity effect cause the increment of y
        collisionManager.resetColliding();      //every loop game reset collision with Mattoni
    }

    @Override
    public final void move() {
        if (isAvailableMovement()) {
            updateMyDir();
            speedUp(DELTA_MOVEMENT);
        }
        if (getDirection() == RIGHT && !retro.isRunning()) {
            SoundManager.getListLoader().get(SoundPath.RETRO.getPosition()).play();
        }
    }

    /**
     * if the Car can attack, increment speed of the Car direct to the Player.
     */
    public void attack() {
        if (checkAttack()) {
            speedUp(DELTA_MOVEMENT);
        }
    }

}
