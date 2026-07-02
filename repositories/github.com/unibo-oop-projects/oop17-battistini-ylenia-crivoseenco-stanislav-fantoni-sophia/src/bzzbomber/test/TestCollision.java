package bzzbomber.test;

import org.junit.Assert;
import org.junit.Test;
import java.awt.Point;
import bzzbomber.model.Model;
import bzzbomber.model.entities.Block;
import bzzbomber.model.entities.Bomb;
import bzzbomber.model.entities.BzzBomber;
import bzzbomber.model.entities.BzzBomberImpl;
import bzzbomber.model.entities.Door;
import bzzbomber.model.entities.Explosion;
import bzzbomber.model.entities.Insects;
import bzzbomber.model.entities.InsectsImpl;
import bzzbomber.model.utilities.Direction;
import bzzbomber.model.utilities.FileLoader;

/**
 * This class is a test's collision class.
 *
 */
public class TestCollision {

    private final Model model = new Model();
    private Insects insect;
    private BzzBomber hero;
    private final Point bombpoint = new Point(1, 4);
    private final Point insectsPointBomb = new Point(2, 4);
    private final Point blockPoint = new Point(1, 6);
    private final Point insectsPointBlock = new Point(1, 5);
    private final Point insectsPointHero = new Point(1, 1);
    private final Point insectsPointDoor = new Point(3, 4);
    private final Point heroPointWall = new Point(1, 1);
    private final Point heroPointInsect = new Point(1, 2);
    private final Point doorPoint = new Point(2, 4);
    private final Point explosionPoint = new Point(4, 3);
    private final Point insectsExplosion = new Point(3, 2);
    private final Point heroExplosion = new Point(4, 2);

    /**
     * This method test a @BombCollision. I created one @BzzBomber and one insects
     * and one bomb and improve collision.
     */
    @Test
    public void testBombCollision() {
        final Bomb bomb = new Bomb(this.bombpoint);
        this.hero = new BzzBomberImpl(this.bombpoint, this.model);
        Assert.assertTrue(this.hero.getCollisionBox().intersects(bomb.getCollisionBox()));
        this.insect = new InsectsImpl(this.insectsPointBomb, this.model);
        this.insect.move(Direction.UP);
        Assert.assertTrue((this.insect.getCollisionBox().intersects(bomb.getCollisionBox())));

    }

    /**
     * This method test a @BlockCollision. I created one @BzzBomber and one insects
     * and one block and improve collision.
     */
    @Test
    public void testBlockCollision() {
        final Block block;
        block = new Block(this.blockPoint);
        this.hero = new BzzBomberImpl(this.blockPoint, this.model);
        Assert.assertTrue(this.hero.getCollisionBox().intersects(block.getCollisionBox()));
        this.insect = new InsectsImpl(this.insectsPointBlock, this.model);
        this.insect.move(Direction.DOWN);
        Assert.assertTrue((this.insect.getCollisionBox().intersects(block.getCollisionBox())));
    }

    /**
     * This method test a (unbroken block) @WallCollision. I created one @BzzBomber
     * and one @Insects. To set a wall position i read an existed file.
     */
    @Test
    public void testWallCollision() {
        FileLoader.loadMap("/Map/level1.map");
        this.hero = new BzzBomberImpl(this.heroPointWall, this.model);
        this.hero.move(Direction.LEFT);
        Assert.assertTrue(this.model.getCurrentLevel().intersectsWithWall(this.hero.getCollisionBox()));
        this.hero.move(Direction.UP);
        Assert.assertTrue(this.model.getCurrentLevel().intersectsWithWall(this.hero.getCollisionBox()));
        this.insect = new InsectsImpl(this.insectsPointBlock, this.model);
        this.insect.move(Direction.UP);
        Assert.assertTrue(this.model.getCurrentLevel().intersectsWithWall(this.insect.getCollisionBox()));
    }

    /**
     * This method test a @InsectsCollision. I created one hero and one @Insects and
     * improve collision.
     */
    @Test
    public void testInsectsCollision() {

        this.hero = new BzzBomberImpl(this.heroPointInsect, this.model);
        this.insect = new InsectsImpl(this.insectsPointHero, this.model);
        this.hero.move(Direction.LEFT);
        Assert.assertTrue(this.hero.getCollisionBox().intersects(this.insect.getCollisionBox()));
    }

    /**
     * This method test a @doorCollision. I created one @BzzBomber and one @Insects
     * and one door and improve collision.
     */
    @Test
    public void testDoorCollision() {
        final Door door = new Door(this.doorPoint);
        this.hero = new BzzBomberImpl(this.bombpoint, this.model);
        this.hero.move(Direction.DOWN);
        Assert.assertTrue(this.hero.getCollisionBox().intersects(door.getCollisionBox()));
        this.insect = new InsectsImpl(this.insectsPointDoor, this.model);
        this.insect.move(Direction.UP);
        Assert.assertTrue(this.hero.getCollisionBox().intersects(door.getCollisionBox()));
    }

    /**
     * This method test an @ExplosionCollision. I created one @BzzBomber and
     * one @Insects and one explosion and improve collision.
     */
    @Test
    public void testExplosionCollision() {

        final Explosion explosionTest = new Explosion(this.explosionPoint, this.model);
        this.hero = new BzzBomberImpl(this.heroExplosion, this.model);
        this.hero.move(Direction.DOWN);
        Assert.assertTrue(this.hero.getCollisionBox().intersects(explosionTest.getCollisionBox()));
        this.insect = new InsectsImpl(this.insectsExplosion, this.model);
        this.insect.move(Direction.RIGHT);
        this.insect.move(Direction.DOWN);
        Assert.assertTrue(this.insect.getCollisionBox().intersects(explosionTest.getCollisionBox()));
    }

}
