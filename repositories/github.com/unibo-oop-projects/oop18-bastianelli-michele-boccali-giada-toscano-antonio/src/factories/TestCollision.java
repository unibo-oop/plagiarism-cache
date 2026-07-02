package factories;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.jbox2d.common.Vec2;
import org.junit.Before;
import org.junit.Test;

import enumerators.EnemyCharacter;
import enumerators.Level;
import enumerators.PlatformType;
import enumerators.PlayerCharacter;
import model.CollisionHandler.CollisionSide;
import model.GameModel;
import model.components.CollisionImpl;
import model.entities.EnemyModel;
import model.entities.PlatformModel;
import model.entities.PlayerModel;

public class TestCollision {

    private static final long FPS = 60;
    private GameModel gameModel = new GameModel(FPS);
    private final FactoryPlayer fp = new FactoryPlayer();
    private final FactoryPlatform fpp = new FactoryPlatform();
    private final FactoryEnemy fEnemy = new FactoryEnemy();
    private PlayerModel tux;

    @Before
    public void setUp() {
        gameModel.init(Level.LEVEL_1);
        tux = (PlayerModel) fp.createModel(PlayerCharacter.TUX, new Vec2(100, 100));
    }

    @Test
    public void test1() {
        assertNotNull(tux);
        PlatformModel plat = (PlatformModel) fpp.createModel(PlatformType.ONEJUMP, new Vec2(150, 250));
        assertNotNull(plat);

        tux.getComponent(CollisionImpl.class).applyCollisionEffect(plat, CollisionSide.BOTTOM);
        plat.getComponent(CollisionImpl.class).applyCollisionEffect(tux, CollisionSide.BOTTOM);
        // platform should die after one jump
        assertFalse(plat.isAlive());
        assertTrue(tux.isAlive());
        assertTrue(tux.getPhysicPosition().y <= 150);
    }

    @Test
    public void test2() {
        PlatformModel plat = (PlatformModel) fpp.createModel(PlatformType.ONEJUMP, new Vec2(150, 250));
        assertNotNull(plat);

        tux.getComponent(CollisionImpl.class).applyCollisionEffect(plat, CollisionSide.OTHERS);
        plat.getComponent(CollisionImpl.class).applyCollisionEffect(tux, CollisionSide.OTHERS);
        // platform should not die
        assertTrue(plat.isAlive());
        assertTrue(tux.isAlive());
    }

    @Test
    public void test3() {
        EnemyModel enemy = (EnemyModel) fEnemy.createModel(EnemyCharacter.FROSTY, new Vec2(100, 100));
        assertNotNull(enemy);
        tux.getComponent(CollisionImpl.class).applyCollisionEffect(enemy, CollisionSide.BOTTOM);
        enemy.getComponent(CollisionImpl.class).applyCollisionEffect(tux, CollisionSide.BOTTOM);
        assertFalse(enemy.isAlive());
        assertTrue(tux.isAlive());
    }

    @Test
    public void test4() {
        EnemyModel enemy = (EnemyModel) fEnemy.createModel(EnemyCharacter.FROSTY, new Vec2(100, 100));
        assertNotNull(enemy);
        tux.getComponent(CollisionImpl.class).applyCollisionEffect(enemy, CollisionSide.OTHERS);
        enemy.getComponent(CollisionImpl.class).applyCollisionEffect(tux, CollisionSide.OTHERS);
        assertTrue(enemy.isAlive());
        assertFalse(tux.isAlive());
    }
}
