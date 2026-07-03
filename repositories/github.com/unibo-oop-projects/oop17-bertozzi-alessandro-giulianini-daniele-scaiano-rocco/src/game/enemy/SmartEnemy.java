package game.enemy;

import game.GameImpl;
import game.GameObject;
import game.ID;
import utilities.Pair;

/**
 * Class to create smart enemy {@link SmartEnemy} extends the abstract class {@link AbstractEnemy}.
 *
 */
public class SmartEnemy extends AbstractEnemy {

    private static final int VEL = 1;
    private static final ID MYID = ID.SMART_ENEMY;
    private static final int HIT = GameImpl.GAMEAREA_HEIGHT / 12;
    private static final int TIMESHOT = 75;
    private final GameImpl game;
    private boolean done;
    private int shotgun;
    private int life = 3;
    private DirEnemy dir;

    /**
     * Classic Constructor of {@link SmartEnemy}.
     * @param game {@link GameImpl}.
     */
    public SmartEnemy(final GameImpl game) {
        super(new Pair<Integer, Integer>(0, 0), VEL, VEL, MYID, HIT);
        this.game = game;
        this.done = false;
    }
    /**
     * Method of {@link InterfaceEnemy}.
     * @return {@link AbstractEnemy}.
     */
    @Override
    public AbstractEnemy createThisEnemy() {
        createEnemy();
        setHitbox();
        return this;
    }
    /**
     * Method of {@link InterfaceEnemy}.
     */
    @Override
    public void followPlayer() {
        dir = null;
        final int kingX = game.getPlayer().get(0).getPosition().getX().intValue();
        final int kingY = game.getPlayer().get(0).getPosition().getY().intValue();
        if (this.getPosition().getX() == kingX) {
            if (kingY >= this.getPosition().getY()) {
                this.getPosition().setY(this.getPosition().getY() + VEL);
                dir = DirEnemy.UP;
            } else {
                this.getPosition().setY(this.getPosition().getY() - VEL);
                dir = DirEnemy.DOWN;
            }
        } else if (this.getPosition().getY() == kingY) {
            if (kingX > this.getPosition().getX()) {
                this.getPosition().setX(this.getPosition().getX() + VEL);
                dir = DirEnemy.RIGHT;
            } else {
                this.getPosition().setX(this.getPosition().getX() - VEL);
                dir = DirEnemy.LEFT;
            }
        } else {
            if (kingY > this.getPosition().getY()) {
                this.getPosition().setY(this.getPosition().getY() + VEL);
                if (kingX > this.getPosition().getX()) {
                    this.getPosition().setX(this.getPosition().getX() + VEL);
                    dir = DirEnemy.U_R;
                } else {
                    this.getPosition().setX(this.getPosition().getX() - VEL);
                    dir = DirEnemy.U_L;
                }
            } else {
                this.getPosition().setY(this.getPosition().getY() - VEL);
                if (kingX > this.getPosition().getX()) {
                    this.getPosition().setX(this.getPosition().getX() + VEL);
                    dir = DirEnemy.D_R;
                } else {
                    this.getPosition().setX(this.getPosition().getX() - VEL);
                    dir = DirEnemy.D_L;
                }
            }
        }
        setHitbox();
    }
    /**
     * Method of {@link game.Entity}.
     */
    @Override
    public void update() {
        shotgun++;
        if (!done) {
            deleteList();
            done = true;
        }
        followPlayer();
        if (checkShotgun(shotgun, TIMESHOT)) {
            createShot(dir, game, MYID);
            shotgun = 0;
        }
    }
    /**
     * Method of {@link game.Entity}.
     * @param entity {@link game.GameObject}.
     */
    @Override
    public void collide(final GameObject entity) {
        switch (entity.getID()) {
        case SMART_ENEMY: case BASIC_ENEMY: case BIG_ENEMY: case FAST_ENEMY: case SHOOT:
            break;
        default: 
            life--;
        }
        if (life == 0) {
            this.setDead();
        }
    }
    /**
     * Method of {@link InterfaceEnemy}.
     */
    @Override
    public DirEnemy randomMovement() {
        return null;
    }
}
