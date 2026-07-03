package game.enemy;

import java.util.Random;

import game.GameImpl;
import game.GameObject;
import game.ID;
import utilities.Pair;


/**
 * Class to create basic enemy {@link BasicEnemy} extends the abstract class {@link AbstractEnemy}.
 *
 */
public class BasicEnemy extends AbstractEnemy {

    private static final int HIT = GameImpl.GAMEAREA_HEIGHT / 12;
    private static final ID MYID = ID.BASIC_ENEMY;
    private static final int VEL = 1;
    private static final int TIMESHOT = 75;
    private static final int CMOVIM = 250;
    private final GameImpl game;
    private final Random ran;
    private int shotgun;
    private int life = 3;
    private int count;
    private boolean done;
    private DirEnemy dir;

    /**
     * Classic constructor for {@link BasicEnemy}.
     * @param game {@link game.GameImpl}.
     */
    public BasicEnemy(final GameImpl game) {
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
