package game.enemy;
import java.awt.Rectangle;
import game.Entity;
import game.GameImpl;
import game.GameObject;
import game.ID;
import utilities.Pair;

/**
 * Class to create the enemies's shots, implements the interface {@link InterfaceShot}.
 *
 */
public class Shot extends Entity implements InterfaceShot {

    private static final ID MYID = ID.SHOOT;
    private static final int STDVEL = 3;
    private static final int HIT = GameImpl.GAMEAREA_HEIGHT / 25;
    private final Pair<Integer, Integer> vel; 
    private DirEnemy dir;
    /**
     * Classic constructor for Shoot Class. {@link Shot}
     * @param enemyX Integer: Coordinate X of the enemy that called this class.
     * @param enemyY Integer: Coordinate Y of the enemy that called this class.
     * @param dir DirEnemy: Direction of the enemy that called this class.
     */
    public Shot(final Integer enemyX, final Integer enemyY, final DirEnemy dir) {
       super(new Pair<Integer, Integer>(enemyX, enemyY), 0, 0, MYID);
       this.dir = dir;
       this.vel = new Pair<Integer, Integer>(0, 0);
       whichVelocity();
       setVelocity(vel.getX(), vel.getY());
       setHitbox(new Rectangle(this.getPosition().getX() - (HIT / 2), this.getPosition().getY() - (HIT / 2), HIT, HIT));
    }
    /**
     * Method to check the direction of the shot and to set the velocity.
     */
    private void whichVelocity() {
        switch (dir) {
        case UP: vel.setX(0);
        vel.setY(STDVEL);
        break;
        case DOWN: vel.setX(0);
        vel.setY(-STDVEL);
        break;
        case LEFT: vel.setX(-STDVEL);
        vel.setY(0);
        break;
        case RIGHT: vel.setX(STDVEL);
        vel.setY(0);
        break;
        case U_R: vel.setX(STDVEL);
        vel.setY(STDVEL);
        break;
        case U_L: vel.setX(-STDVEL);
        vel.setY(STDVEL);
        break;
        case D_R: vel.setX(STDVEL);
        vel.setY(-STDVEL);
        break;
        case D_L: vel.setX(-STDVEL);
        vel.setY(-STDVEL);
        break;
        default: 
            break;
        }
    }
    /**
     *Method of {@link game.Entity}.
     */
    @Override
    public void update() {
        whichVelocity();
        setVelocity(vel.getX(), vel.getY());
        this.setPosition(new Pair<Integer, Integer>(this.getPosition().getX() + vel.getX(), this.getPosition().getY() + vel.getY()));
        setHitbox(new Rectangle(this.getPosition().getX() - (HIT / 2), this.getPosition().getY() - (HIT / 2), HIT, HIT));
        if (this.getPosition().getY() >= GameImpl.GAMEAREA_HEIGHT || this.getPosition().getY() < 0 || this.getPosition().getX() >= GameImpl.GAMEAREA_WIDTH || this.getPosition().getX() < 0) {
            this.setDead();
        }
    }
    /**
     * Method of {@link game.Entity}.
     */
    @Override
    public void collide(final GameObject entity) {
        this.setDead();
    }
    /**
     * Method of {@link InterfaceShot}.
     * @param dir {@link DirEnemy}.
     */
    @Override
    public void setDir(final DirEnemy dir) {
        this.dir = dir;
    }
}

