package thatlevelagain.character.enemies;


import java.awt.image.BufferedImage;

import thatlevelagain.character.enemies.collision.CollisionGomitolo;
import thatlevelagain.character.enemies.collision.CollisionInterface;
import thatlevelagain.view.load_image.ImageManager;
import thatlevelagain.view.load_image.ImagePath;
import thatlevelagain.view.map.MapImpl;

/**
 * @author 
 * Class that extends Enemy and implements EnemyInterface e Runnable. 
 * It represents the enemy Gomitolo
 */
public class GomitoloImpl extends Enemy implements Runnable {

    /**
     * Minimum Y to allow ball jump.
     */
    public static final int MINIMUM_Y = 290;

    /**
     * Maximum Y to allow ball jump.
     */
    public static final int MAXIMUM_Y = 340;

    private static final int ZERO = 0;
    private static final int UNO = 1;
    private static final int QUINDICI = 15;
    private static final long WAIT = 150;

    private final CollisionGomitolo collisionManager;
    private boolean walk;
    private boolean active;
    private int contImm;
    private int lastX;
    private boolean temp;
    private final int initialHeight;
    private boolean fatto;
    private final BufferedImage image1, image2, image3;

    /**
     * GomitoloImpl constructor.
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
     *          Object of MapImpl class
     *
     */
    public GomitoloImpl(final int x, final int y, final int width, final int height, final MapImpl map) {
        super(x, y, width, height, ImageManager.getListLoader().get(ImagePath.GOMITOLO_TERRA.getPosition()), map);
        collisionManager = new CollisionGomitolo();
        walk = false;
        active = true;
        temp = true;
        fatto = false;
        image1 = ImageManager.getListLoader().get(ImagePath.GOMITOLO_TERRA.getPosition());
        image2 = ImageManager.getListLoader().get(ImagePath.GOMITOLO_PIEDI_UNITI.getPosition());
        image3 = ImageManager.getListLoader().get(ImagePath.GOMITOLO_CAMMINA.getPosition());
        this.contImm = 0;
        this.lastX = x;
        this.initialHeight = height;
        final Thread th =  new Thread(this);
        th.start();
    }

    /**
     * 
     * @return
     *          true if the Y of the enemy allows to jump
     */
    private boolean checkYJump() {
        return getY() > getMap().getCat().getY() || (getY() >= MINIMUM_Y && getY() <= MAXIMUM_Y);
    }

    @Override
    protected final void setInitAvailableMovement() {
        setAvailableMovement(true);
    }

    @Override
    protected final void updateCollision() {
            this.collisionManager.intersection(this, this.getMap().getMattoni());
            setGoDown(collisionManager.isFalling() && getJumpState() == STOP);
    }

    @Override
    protected final boolean checkRightDirection() {
        return getMap().getCat().getX() > getX() - FREE_SPACE;
    }

    @Override
    protected final void gravityAction() {
        incrementY(DELTA_MOVEMENT);
    }

    @Override
    protected final boolean canJump() {
        final boolean checkEdge = CheckerEdge.getChecker().check(this);
        return checkYJump() && isAvailableMovement() && checkEdge && !isJumping();
    }

    @Override
    protected final void goUp() {
        incrementY(-DELTA_SALTO);
    }

    @Override
    public final CollisionInterface getCollisionManager() {
        return this.collisionManager;
    }

    /**
     * 
     * @return
     *         true if Gomitolo is walking
     */
    public boolean isWalk() {
        return this.walk;
    }

    /**
     * 
     * @return
     *         true if Gomitolo is active
     */
    public boolean isActive() {
        return active;
    }

    /**
     * 
     * @param walk
     *           set walk;
     */
    public void setWalk(final boolean walk) {
        this.walk = walk;
    }

    /**
     * 
     * @param active
     *          set active value
     */
    public void setActive(final boolean active) {
         this.active = active;
    }

    @Override
    public final void update() {
        incrementCountJump(ONE);
        updateMyDir();
        updateAvailableMovement();     //per gomitolo
        jump();
        updateCollision();
        move();            //muovo il gomitolo nella direzione stabilita
        spineCollision();
        gravityEffect();
        collisionManager.resetColliding();
    }

    /**
     * Method that update the capability of the ball to move.
     */
    public void updateAvailableMovement() {
        if (this.getDirection() == this.getMap().getCat().getTowardPlayer()) {
            this.setAvailableMovement(true);
        } else {
            this.setAvailableMovement(false);
            finishJump();
        }
    }

    @Override
    public final void move() {
        if (isAvailableMovement()) {
            updateMyDir();
            speedUp(DELTA_MOVEMENT);
        }
    }

    /**
     * set gomitolo rotation.
     */
    @Override
    public final void run() {
        while (this.active) {
            if (!this.isAvailableMovement()) { 
                if (!this.temp) {
                    this.temp = true;
                    this.fatto = false;
                    this.setImage(image1);
                    this.setHeight(this.initialHeight);
                    this.setY(this.getY() + this.initialHeight);
                }
            } else {
                this.temp = false;
                if (this.getX() - this.lastX > QUINDICI || this.lastX - this.getX() > QUINDICI) {
                    this.lastX = this.getX();
                    if (this.contImm == ZERO) {
                        this.contImm = UNO;
                    } else {
                        this.contImm = ZERO;
                    }
                    if (this.contImm == ZERO) {
                        this.setImage(image2);
                        this.setHeight(2 * this.initialHeight);
                        if (!this.fatto) {
                            this.fatto = true;
                            this.setY(this.getY() - this.initialHeight);
                        }
                    } else {
                        this.setImage(image3);
                        this.setHeight(2 * this.initialHeight);
                        if (!this.fatto) {
                            this.fatto = true;
                            this.setY(this.getY() - this.initialHeight);
                        }
                    }
                }
            }
            try {
                Thread.sleep(WAIT);
            } catch (InterruptedException e) {
                 e.printStackTrace();
            }
        }
    }

}
