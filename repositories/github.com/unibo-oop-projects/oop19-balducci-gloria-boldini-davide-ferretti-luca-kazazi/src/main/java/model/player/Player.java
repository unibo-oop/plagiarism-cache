package model.player;

import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.util.List;
import java.util.Objects;
import controllers.collision.collisionsPlayer.CollisionsPlayer;
import controllers.movement.Movement;
import controllers.movement.animation.Animation;
import controllers.timer.GameTime;
import controllers.timer.LimitedTimer;
import model.Direction;
import model.ID;
import model.gameObject.GameObject;
import model.handler.Handler;
import model.staticObject.ActiveStaticObject;
import other.Pair;
import view.WindowDeath;

/**
 * Implementation of Player.
 *
 *
 */
public class Player extends GameObject implements PlayerInterface {

    private static final int SCREEN_WIDTH = Toolkit.getDefaultToolkit().getScreenSize().width;
    private static final int SCREEN_HEIGHT = Toolkit.getDefaultToolkit().getScreenSize().height;
    private static final int MAX_LIFES = 3;
    private double speed = 2.0;
    private int health;         // Il giocatore ha di default 3 vite
    private boolean knife;      // Il giocatore di default non fa danno (knife false)
    private boolean recovering;
    private boolean gameOver;
    private final Handler handler;
    private final CollisionsPlayer objCollision;
    private final Animation animation;
    private final Movement move;
    private GameTime time;
    private final List<BufferedImage> listUp;
    private final List<BufferedImage> listDown;
    private final List<BufferedImage> listLeft;
    private final List<BufferedImage> listRight;
    private ActiveStaticObject activePowerUpDebuff;

    /**
     * Constructor for Player.
     * @param id
     * @param handler
     * @param posX
     * @param posY
     * @param velX
     * @param velY
     * @param images
     */
    public Player(final ID id, final Handler handler, final int posX, final int posY, final double velX,
            final double velY, final List<Pair<Direction, BufferedImage>> images) {
        super(id, posX, posY, velX, velY, images);
        this.health = MAX_LIFES;
        this.knife = false;
        this.recovering = false;
        this.gameOver = false;
        this.handler = Objects.requireNonNull(handler);
        this.objCollision = new CollisionsPlayer();
        this.move = new Movement(this, speed);
        this.animation = new Animation(4);
        this.listUp = getTextureByDirection(Direction.UP);
        this.listDown = getTextureByDirection(Direction.DOWN);
        this.listLeft = getTextureByDirection(Direction.LEFT);
        this.listRight = getTextureByDirection(Direction.RIGHT);
        this.animation.init(this.listDown);
    }

    @Override
    public final void setSpeed(final double speed) {
        this.speed = Objects.requireNonNull(speed);
    }

    @Override
    public final double getSpeed() {
        return this.speed;
    }

    @Override
    public final void removeLife() {
        if (!this.hasKnife() && this.isVisible() && !this.recovering) {
            this.health--;
            this.recoverFromCollision();
        }
        if (this.getHealth() <= 0 && !this.gameOver) {
            this.setGameOver(true);
            this.death();
        }
    }

    @Override
    public final int getHealth() {
        return this.health;
    }

    @Override
    public final boolean isGameOver() {
        return this.gameOver;
    }

    @Override
    public final void setGameOver(final boolean gameOver) {
        this.gameOver = Objects.requireNonNull(gameOver);
    }

    @Override
    public final void addLife() {
            this.health++;
    }

    @Override
    public final void setKnife(final boolean newKnife) {
        this.knife = Objects.requireNonNull(newKnife);
    }

    @Override
    public final void setRecovering(final boolean recovering) {
        this.recovering = Objects.requireNonNull(recovering);
    }

    @Override
    public final boolean isRecovering() {
        return this.recovering;
    }

    @Override
    public final Animation getAnimation() {
        return this.animation;
    }

    @Override
    public final Movement getMovement() {
        return this.move;
    }

    @Override
    public List<BufferedImage> getListUP() {
        return this.listUp;
    }

    @Override
    public List<BufferedImage> getListDOWN() {
        return this.listDown;
    }

    @Override
    public List<BufferedImage> getListRIGHT() {
        return this.listRight;
    }

    @Override
    public List<BufferedImage> getListLEFT() {
        return this.listLeft;
    }

    @Override
    public final ActiveStaticObject getActivePowerUpDebuff() {
        return this.activePowerUpDebuff;
    }

    @Override
    public final boolean hasKnife() {
        return this.knife;
    }

    @Override
    public void tick() {
        super.setCoord(new Pair<Integer, Integer>(this.getCoord().getX() + this.getVelocity().getX().intValue(),
                this.getCoord().getY() + this.getVelocity().getY().intValue()));
        handler.getGameObjectList().stream()
            .forEach(p -> this.objCollision.collisionsInGame(this, p));
        move.moveEntity();
    }

    /**
     *  this methods starts the 2 seconds timer after player loses a life. In this 2 seconds
     *  the player won't be hit from rays or enemies
     */
    private final void recoverFromCollision() {
        this.recovering = true;
        this.setVisible(false);
        final LimitedTimer limitedTimer = new LimitedTimer(this, ID.PLAYER);
        limitedTimer.start();
    }

    @Override
    public final void removePowerUpDebuff() {
        this.activePowerUpDebuff = null;
    }

    @Override
    public final void setPowerUpDebuff(final ActiveStaticObject staticObject) {
        this.activePowerUpDebuff = Objects.requireNonNull(staticObject);
    }

    @Override
    public final GameTime getTimer() {
        return this.time;
    }

    @Override
    public final void setTimer(final GameTime time) {
        this.time = Objects.requireNonNull(time);
    }

    private void death() {
        final WindowDeath death = new WindowDeath();
        death.createWindow();
        death.setDimensions(SCREEN_WIDTH / 3, SCREEN_HEIGHT / 3);
        death.show();
    }
}
