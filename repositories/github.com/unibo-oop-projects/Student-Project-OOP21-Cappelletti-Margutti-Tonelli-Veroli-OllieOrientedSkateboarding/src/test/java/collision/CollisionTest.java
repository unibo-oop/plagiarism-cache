package collision;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import controller.GameInfo;
import javafx.embed.swing.JFXPanel;
import javafx.geometry.Dimension2D;
import model.Model;
import model.ModelImpl;
import model.collision.CollisionManager;
import model.collision.CollisionManagerImpl;
import model.entity.DynamicEntity;
import model.entity.SpawnLevel;
import model.entity.factory.EntityFactory;
import model.entity.factory.EntityFactoryImpl;
import model.player.JumpState;
import model.player.Player;
import sound.SoundFactoryImpl;

class CollisionTest {

    private static final int DISTANCE = 1000;
    private CollisionManager manager;
    private EntityFactory factory;
    private List<DynamicEntity> objects;
    private Model model;
    private Player player;

    @BeforeEach
    void setUp() {
        /*Lines to initializes JavaFx environment, so tests work, otherwise we get
         * "java.lang.RuntimeException: Internal graphics not initialized yet" error */
        final JFrame frame = new JFrame("Java Swing And JavaFX");
        final JFXPanel jfxPanel = new JFXPanel();
        frame.add(jfxPanel);
        final GameInfo info = new GameInfo();
        this.manager = new CollisionManagerImpl();
        final Dimension2D dimension = new Dimension2D(info.getWidth(), info.getHeight());
        this.factory = new EntityFactoryImpl(dimension);
        this.objects = new ArrayList<>();
        this.model = new ModelImpl(info.getWidth(), info.getHeight(), new SoundFactoryImpl());
        this.player = model.getGameState().getPlayer();
    }

    @Test
    void testCollisionWithObstacle() {
        /*Check if, after a collision with an obstacle, the lives counter decrease*/
        final int lives = player.getLives();
        objects.clear();
        objects.add(factory.createObstacle(SpawnLevel.ZERO));
        objects.get(0).updatePosition(DISTANCE);
        manager.playerCollidesWidth(player, objects, model);
        assertTrue(player.getLives() < lives);
    }

    @Test
    void testCollisionWithPlatform() {
        /*Check if, after a collision with a platform, the player can jump on the platform*/
        objects.clear();
        objects.add(factory.createPlatform(SpawnLevel.ZERO));
        player.jump();
        assertEquals(JumpState.UP, player.getJumpState());
    }

    @Test
    void testCollisionWithCoin() {
        /*Check if, after a collision with a coin, the coin counter increase*/
        objects.clear();
        objects.add(factory.createCoin(SpawnLevel.ZERO));
        assertEquals(model.getStatistics().getGameCoins(), 0);
        objects.get(0).updatePosition(DISTANCE);
        manager.playerCollidesWidth(player, objects, model);
        assertEquals(model.getStatistics().getGameCoins(), 1);
    }
}
