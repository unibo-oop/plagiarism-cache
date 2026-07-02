package powerup; 
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.awt.geom.Point2D;

import javax.swing.JFrame;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import controller.GameInfo;
import javafx.embed.swing.JFXPanel;
import javafx.geometry.Dimension2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import model.Model;
import model.ModelImpl;
import model.entity.DynamicEntity;
import model.entity.EntityType;
import model.entity.SpawnLevel;
import model.entity.factory.EntityFactory;
import model.entity.factory.EntityFactoryImpl;
import model.entity.powerup.ExtraLife;
import model.entity.powerup.Mushroom;
import model.entity.powerup.Shield;
import model.entity.powerup.Spraybomb;
import model.entity.powerup.Superjump;
import model.player.JumpState;
import model.player.Player;
import model.player.PlayerImpl;
import sound.SoundFactoryImpl;
import view.entity.EntityImages;

class PowerupTest {

    private static final int X = 427; 
    private static final int Y = 440; 

    private static final double EXTRALIFE_WIDTH = 61; 
    private static final double POWERUP_HEIGHT = 50; 
    private static final double SUPERJUMP_WIDTH = 90; 
    private static final double MUSHROOM_WIDTH = 53; 
    private static final double SHIELD_WIDTH = 47; 
    private static final double SPRAYBOMB_WIDTH = 20; 
    private static final int EXPECTED_COINS = 2; 
    private static final long POWERUP_TIME = 11_000L; 
    private static final double DOUBLE_JUMP = 1.5; 
    private static final double GRAVITY = 4.5; 

    private Model model; 
    private GameInfo info; 
    private EntityFactory factory; 

    @BeforeEach
    void prepare() { 
        final JFrame frame = new JFrame(); 
        final JFXPanel jfxpanel = new JFXPanel(); 
        frame.add(jfxpanel); 
        info = new GameInfo();
        model = new ModelImpl(info.getWidth(), info.getHeight(), new SoundFactoryImpl()); 
        factory = new EntityFactoryImpl(new Dimension2D(info.getWidth(), info.getHeight())); 
    }

    @Test
    void testExtraLife() {
        final Image image = EntityImages.EXTRALIFE.getImage(); 
        final Point2D.Double coordinates = new Point2D.Double(X, Y); 
        final double distance = info.getWidth() - image.getWidth() * EntityType.POWERUP.getDistanceFactor();
        final DynamicEntity extralife = new ExtraLife(coordinates, image, SpawnLevel.ONE, EntityType.POWERUP, distance); 
        assertEquals(extralife.getBounds(), new Rectangle2D(X, Y, EXTRALIFE_WIDTH, POWERUP_HEIGHT));
        final int lives = model.getGameState().getPlayer().getLives(); 
        extralife.onCollision(model);
        assertEquals(model.getGameState().getPlayer().getLives(), lives + 1);
    }

    @Test
    void testSuperjump() {
        final Image image = EntityImages.SUPERJUMP.getImage(); 
        final Point2D.Double coordinates = new Point2D.Double(X, Y); 
        final double distance = info.getWidth() - image.getWidth() * EntityType.POWERUP.getDistanceFactor();
        final DynamicEntity superjump = new Superjump(coordinates, image, SpawnLevel.ONE, EntityType.POWERUP, distance); 
        assertEquals(superjump.getBounds(), new Rectangle2D(X, Y, SUPERJUMP_WIDTH, POWERUP_HEIGHT));
        superjump.onCollision(model);
        final Player player = model.getGameState().getPlayer(); 
        final double plY = player.getBounds().getMinY(); 
        player.jump();
        while (!player.getJumpState().equals(JumpState.DOWN)) {
            player.updateJump();
        }
        assertEquals(player.getBounds().getMinY(), plY - (PlayerImpl.JUMP_HEIGHT * DOUBLE_JUMP), GRAVITY);
    }

    @Test
    void testMushroom() {
        final Image image = EntityImages.MUSHROOM.getImage(); 
        final Point2D.Double coordinates = new Point2D.Double(X, Y); 
        final double distance = info.getWidth() - image.getWidth() * EntityType.POWERUP.getDistanceFactor();
        final DynamicEntity mushroom = new Mushroom(coordinates, image, SpawnLevel.ONE, EntityType.POWERUP, distance); 
        assertEquals(mushroom.getBounds(), new Rectangle2D(X, Y, MUSHROOM_WIDTH, POWERUP_HEIGHT));
        mushroom.onCollision(model);
        final DynamicEntity coin = factory.createCoin(SpawnLevel.ONE); 
        final int numcoins = model.getStatistics().getGameCoins(); 
        coin.onCollision(model);
        assertEquals(model.getStatistics().getGameCoins(), numcoins + EXPECTED_COINS);
    }

    @Test
    void testShield() {
        final Image image = EntityImages.SHIELD.getImage(); 
        final Point2D.Double coordinates = new Point2D.Double(X, Y); 
        final double distance = info.getWidth() - image.getWidth() * EntityType.POWERUP.getDistanceFactor();
        final DynamicEntity shield = new Shield(coordinates, image, SpawnLevel.ONE, EntityType.POWERUP, distance);
        assertEquals(shield.getBounds(), new Rectangle2D(X, Y, SHIELD_WIDTH, POWERUP_HEIGHT));
        shield.onCollision(model);
        assertTrue(model.getGameState().getPlayer().isShieldActive());
        try {
            Thread.sleep(POWERUP_TIME);
        } catch (InterruptedException e) {
            e.printStackTrace();
            fail("Thread has been interrupted and the condition cannot be modified"); 
        }
        assertFalse(model.getGameState().getPlayer().isShieldActive());
    }

    @Test
    void testSpraybomb() {
        final Image image = EntityImages.SPRAYBOMB.getImage(); 
        final Point2D.Double coordinates = new Point2D.Double(X, Y); 
        final double distance = info.getWidth() - image.getWidth() * EntityType.POWERUP.getDistanceFactor();
        final DynamicEntity spraybomb = new Spraybomb(coordinates, image, SpawnLevel.ONE, EntityType.POWERUP, distance);
        assertEquals(spraybomb.getBounds(), new Rectangle2D(X, Y, SPRAYBOMB_WIDTH, POWERUP_HEIGHT));
        spraybomb.onCollision(model);
        assertEquals(model.getGameState().getEntities().size(), 0);
    }

}
