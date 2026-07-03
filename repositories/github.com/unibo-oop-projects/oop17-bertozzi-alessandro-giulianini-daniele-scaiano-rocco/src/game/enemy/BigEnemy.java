package game.enemy;

import java.util.Random;
import game.GameImpl;
import game.GameObject;
import game.ID;
import utilities.Pair;

/**
 * Class to create big enemy {@link BigEnemy} extends the abstract class {@link AbstractEnemy}.
 *
 */
public class BigEnemy extends AbstractEnemy {

    private static final int HIT = GameImpl.GAMEAREA_HEIGHT / 7;
    private static final int CMOVIM = 250;
    private static final ID MYID = ID.BIG_ENEMY;
    private static final int VEL = 2;
    private static final int TIMESHOT = 100;
    private final Random ran;
    private final GameImpl game;
    private int shotgun;
    private int count;
    private int life = 8;
    private boolean done;
    private DirEnemy dir;

    /**
     * Classic constructor for {@link BigEnemy}.
     * @param game {@link GameImpl}.
     */
    public BigEnemy(final GameImpl game) {
        super(new Pair<Integer, Integer>(0, 0), VEL, VEL, MYID, HIT);
        this.game = game;
        this.done = false;
        ran = new Random();
    }
    /**
     * Method of {@link InterfaceEnemy}.
     * @return {@link AbstractEnemy}.
     */
    @Override
    public AbstractEnemy createThisEnemy() {
        createEnemy();
        setHitbox();
        dir = randomMovement();
        return this;
    }
    /**
     * Method of {@link InterfaceEnemy}.
     * @return {@link DirEnemy}.
     */
    @Override
    public DirEnemy randomMovement() {
        if (ran.nextBoolean()) {
            if (ran.nextBoolean()) {
                dir = DirEnemy.RIGHT;
            } else {
                dir  = DirEnemy.LEFT;
            }
        } else if (ran.nextBoolean()) {
            dir = DirEnemy.UP;
        } else {
            dir = DirEnemy.DOWN;
        }
        return dir;
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
        if (count == CMOVIM) {
            dir = randomMovement();
            movement(dir);
            count = 0;
        } else {
            count++;
            movement(dir);
            dir = checkPosition(dir);
        }
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
    public void followPlayer() {
    }
}
