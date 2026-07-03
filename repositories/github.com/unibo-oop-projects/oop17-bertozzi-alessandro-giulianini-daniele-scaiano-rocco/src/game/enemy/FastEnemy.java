package game.enemy;

import java.util.Random;

import game.GameImpl;
import game.GameObject;
import game.ID;
import utilities.Pair;

/**
 * Class to create fast enemy {@link FastEnemy} extends the abstract class {@link AbstractEnemy}.
 *
 */
public class FastEnemy extends AbstractEnemy {

    private static final int HIT = GameImpl.GAMEAREA_HEIGHT / 15;
    private static final ID MYID = ID.FAST_ENEMY;
    private static final int VEL = 4;
    private static final int TIMESHOT = 75;
    private static final int CMOVIM = 100;
    private final Random ran;
    private final GameImpl game;
    private int shotgun;
    private int count;
    private boolean done;
    private DirEnemy dir;

    /**
     * Classic constructor for {@link FastEnemy}.
     * @param game {@link game.GameImpl}.
     */
    public FastEnemy(final GameImpl game) {
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
        if (this.isDead()) {
            this.setAlive();
        }
        createEnemy();
        deleteList();
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
     * @param entity {@link game.Entity}.
     */
    @Override
    public void collide(final GameObject entity) {
        switch (entity.getID()) {
        case SMART_ENEMY: case BASIC_ENEMY: case BIG_ENEMY: case FAST_ENEMY: case SHOOT:
            break;
        default: 
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
