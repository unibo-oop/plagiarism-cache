package game.enemy;

import java.awt.Rectangle;
import java.util.concurrent.ThreadLocalRandom;
import game.Entity;
import game.GameImpl;
import game.ID;
import utilities.Pair;

/**
 * Abstract class implements {@link InterfaceEnemy} to create entity for all the enemies. {@link BasicEnemy}, {@link SmartEnemy}, {@link BigEnemy},
 * {@link FastEnemy}, {@link BossEnemy}.
 *
 */
public abstract class AbstractEnemy extends Entity implements InterfaceEnemy {

    private static final int WIDTH_X = GameImpl.GAMEAREA_WIDTH;
    private static final int HEIGHT_Y = GameImpl.GAMEAREA_HEIGHT;
    private final InterfaceModelEnemy model;
    private final int vel;
    private final int hit;
    private boolean done;

    /**
     * Classic constructor for {@link AbstractEnemy}.
     * @param position : Position of the enemy.
     * @param velocityX : Velocity's coordinate x.
     * @param velocityY : Velocity's coordinate y.
     * @param id : ID of the enemy.
     * @param hit : size of the Hitbox.
     */
    public AbstractEnemy(final Pair<Integer, Integer> position, final int velocityX, final int velocityY, final ID id, final int hit) {
        super(position, velocityX, velocityY, id);
        this.vel = velocityX;
        this.hit = hit;
        done = false;
        model = new ModelEnemy(hit);
    }
    /**
     * Method to create the enemi's coordinate X and Y.
     * @return {@link AbstractEnemy}.
     */
    protected AbstractEnemy createEnemy() {
        double number;
        do {
            number = ThreadLocalRandom.current().nextDouble(0, WIDTH_X);
        } while ((number > WIDTH_X / 3 && number < (WIDTH_X * 2) / 3) || model.busyX((int) number));
        model.addNumber(true, (int) number);
        this.getPosition().setX((int) number);
        do {
            number = ThreadLocalRandom.current().nextDouble(0, HEIGHT_Y);
        } while ((number > HEIGHT_Y / 4 && number < (HEIGHT_Y * 3) / 4) || model.busyY((int) number));
        this.getPosition().setY((int) number);
        done = false;
        return this;
    }
    /**
     * Method to delete the list in {@link ModelEnemy}.
     */
    protected void deleteList() {
        if (!done) {
            model.deleteList();
            done = true;
        }
    }
    /**
     * Method for set the hitbox.
     */
    protected void setHitbox() {
        setHitbox(new Rectangle(this.getPosition().getX() - (hit / 2), this.getPosition().getY() - (hit / 2), hit, hit));
    }
    /**
     * Method to create the enemi's shots with {@link Shot}.
     * @param dir {@link DirEnemy}.
     * @param game {@link game.GameImpl}.
     * @param id {@link game.GameImpl}.
     */
    protected void createShot(final DirEnemy dir, final GameImpl game, final ID id) {
        if (id == ID.BOSS_ENEMY) {
            switch (dir) {
            case UP: game.getShots().add(new Shot(this.getPosition().getX(), this.getPosition().getY() + vel, dir));
            game.getShots().add(new Shot(this.getPosition().getX() + vel, this.getPosition().getY() + vel, DirEnemy.U_R));
            game.getShots().add(new Shot(this.getPosition().getX() - vel, this.getPosition().getY() + vel, DirEnemy.U_L));
            break;
            case DOWN: game.getShots().add(new Shot(this.getPosition().getX(), this.getPosition().getY() - vel, dir));
            game.getShots().add(new Shot(this.getPosition().getX() + vel, this.getPosition().getY() - vel, DirEnemy.D_R));
            game.getShots().add(new Shot(this.getPosition().getX() - vel, this.getPosition().getY() - vel, DirEnemy.D_L));
            break;
            case LEFT: game.getShots().add(new Shot(this.getPosition().getX() - vel, this.getPosition().getY(), dir));
            game.getShots().add(new Shot(this.getPosition().getX() - vel, this.getPosition().getY() + vel, DirEnemy.U_L));
            game.getShots().add(new Shot(this.getPosition().getX() - vel, this.getPosition().getY() - vel, DirEnemy.D_L));
            break;
            case RIGHT: game.getShots().add(new Shot(this.getPosition().getX() + vel, this.getPosition().getY(), dir));
            game.getShots().add(new Shot(this.getPosition().getX() + vel, this.getPosition().getY() + vel, DirEnemy.U_R));
            game.getShots().add(new Shot(this.getPosition().getX() + vel, this.getPosition().getY() - vel, DirEnemy.D_R));
            break;
            case U_R: game.getShots().add(new Shot(this.getPosition().getX() + vel, this.getPosition().getY() + vel, dir));
            game.getShots().add(new Shot(this.getPosition().getX(), this.getPosition().getY() + vel, DirEnemy.UP));
            game.getShots().add(new Shot(this.getPosition().getX() + vel, this.getPosition().getY(), DirEnemy.RIGHT));
            break;
            case U_L: game.getShots().add(new Shot(this.getPosition().getX() - vel, this.getPosition().getY() + vel, dir));
            game.getShots().add(new Shot(this.getPosition().getX(), this.getPosition().getY() + vel, DirEnemy.UP));
            game.getShots().add(new Shot(this.getPosition().getX() - vel, this.getPosition().getY(), DirEnemy.LEFT));
            break;
            case D_R: game.getShots().add(new Shot(this.getPosition().getX() + vel, this.getPosition().getY() - vel, dir));
            game.getShots().add(new Shot(this.getPosition().getX(), this.getPosition().getY() - vel, DirEnemy.DOWN));
            game.getShots().add(new Shot(this.getPosition().getX() + vel, this.getPosition().getY(), DirEnemy.RIGHT));
            break;
            case D_L: game.getShots().add(new Shot(this.getPosition().getX() - vel, this.getPosition().getY() - vel, dir));
            game.getShots().add(new Shot(this.getPosition().getX(), this.getPosition().getY() - vel, DirEnemy.DOWN));
            game.getShots().add(new Shot(this.getPosition().getX() - vel, this.getPosition().getY(), DirEnemy.LEFT));
            break;
            default: 
                break;
            }
        } else {
            switch (dir) {
            case UP: game.getShots().add(new Shot(this.getPosition().getX(), this.getPosition().getY() + vel, dir));
            break;
            case DOWN: game.getShots().add(new Shot(this.getPosition().getX(), this.getPosition().getY() - vel, dir));
            break;
            case LEFT: game.getShots().add(new Shot(this.getPosition().getX() - vel, this.getPosition().getY(), dir));
            break;
            case RIGHT: game.getShots().add(new Shot(this.getPosition().getX() + vel, this.getPosition().getY(), dir));
            break;
            case U_R: game.getShots().add(new Shot(this.getPosition().getX() + vel, this.getPosition().getY() + vel, dir));
            break;
            case U_L: game.getShots().add(new Shot(this.getPosition().getX() - vel, this.getPosition().getY() + vel, dir));
            break;
            case D_R: game.getShots().add(new Shot(this.getPosition().getX() + vel, this.getPosition().getY() - vel, dir));
            break;
            case D_L: game.getShots().add(new Shot(this.getPosition().getX() - vel, this.getPosition().getY() - vel, dir));
            break;
            default: 
                break;
            }
        }
    }
    /**
     * Method to check the variable ShotGun.
     * @param shotgun variable to shot.
     * @param timeShot time to shot.
     * @return boolean: true if the shot {@link Shot} can be create, false otherwise.
     */
    protected boolean checkShotgun(final int shotgun, final int timeShot) {
        return shotgun == timeShot ? true : false;
    }
    /**
     * Method to check the direction of the enemy and to move it.
     * @param dir {@link DirEnemy}.
     */
    protected void movement(final DirEnemy dir) {
        switch (dir) {
        case UP: this.getPosition().setY(this.getPosition().getY() + this.getVelocity().getY());
        break;
        case DOWN: this.getPosition().setY(this.getPosition().getY() - this.getVelocity().getY());
        break;
        case LEFT: this.getPosition().setX(this.getPosition().getX() - this.getVelocity().getX());
        break;
        case RIGHT: this.getPosition().setX(this.getPosition().getX() + this.getVelocity().getX());
        break;
        case U_R: this.getPosition().setY(this.getPosition().getY() + this.getVelocity().getY());
        this.getPosition().setX(this.getPosition().getX() + this.getVelocity().getX());
        break;
        case U_L: this.getPosition().setY(this.getPosition().getY() + this.getVelocity().getY());
        this.getPosition().setX(this.getPosition().getX() - this.getVelocity().getX());
        break;
        case D_R: this.getPosition().setY(this.getPosition().getY() - this.getVelocity().getY());
        this.getPosition().setX(this.getPosition().getX() + this.getVelocity().getX());
        break;
        case D_L: this.getPosition().setY(this.getPosition().getY() - this.getVelocity().getY());
        this.getPosition().setX(this.getPosition().getX() - this.getVelocity().getX());
        break;
        default: 
            break;
        }
        setHitbox();
    }
    /**
     * Method for check the position of enemy.
     * @param dir {@link DirEnemy}.
     * @return DirEnemy {@link DirEnemy}.
     */
    protected DirEnemy checkPosition(final DirEnemy dir) {
        if (this.getPosition().getX() - hit <= 0) {
            return DirEnemy.RIGHT;
        } else if (this.getPosition().getX() + hit >= GameImpl.GAMEAREA_WIDTH) {
            return DirEnemy.LEFT;
        } else if (this.getPosition().getY() - hit <= 0) {
            return DirEnemy.UP;
        } else if (this.getPosition().getY() + hit >= GameImpl.GAMEAREA_HEIGHT) {
            return DirEnemy.DOWN;
        }
        setHitbox();
        return dir;
    }
}
