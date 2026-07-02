package javawulf;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javawulf.model.Coordinate;
import javawulf.model.CoordinateImpl;
import javawulf.model.item.AmuletPiece;
import javawulf.model.item.Cure;
import javawulf.model.item.CureMax;
import javawulf.model.item.ExtraHeart;
import javawulf.model.item.GreatSword;
import javawulf.model.item.ItemFactory;
import javawulf.model.item.ItemFactoryImpl;
import javawulf.model.item.Shield;
import javawulf.model.player.Player;
import javawulf.model.player.PlayerImpl;
import javawulf.model.player.PlayerHealth.ShieldStatus;
import javawulf.model.player.Sword.SwordType;

/**
 * Test class for Items' effect.
 */
final class ItemTest {

    private static final int STARTING_X = 12;
    private static final int STARTING_Y = 12;
    private static final int STARTING_HEALTH = 3;
    private static final int DAMAGED_PLAYER = -2;

    private final ItemFactory factory = new ItemFactoryImpl();
    private Coordinate test;
    private Player player;

    @BeforeEach
    void setUp() {
        this.player = new PlayerImpl(STARTING_X, STARTING_Y, STARTING_HEALTH, 0);
        this.test = new CoordinateImpl(STARTING_X, STARTING_Y);
    }

    @Test
    void testCollection() {
        // Creating an item (any item will do for this test)
        final Shield shield = factory.createShield(test);
        // Checking that the player meets the requirements to collect the item
        assertTrue(player.getBounds().isCollidingWith(shield.getBounds().getCollisionArea()));
        // Collecting the item
        shield.collect(player);
        // Checking that the item has been collected
        assertNotEquals(0, player.getScore().getPoints());
    }

    @Test
    void testAmuletPiece() {
        final AmuletPiece piece = factory.createAmuletPiece(test);
        // Player collect the item
        piece.collect(player);
        // Checking that the effect has been applied
        assertEquals(1, player.getNumberOfPieces());
    }

    @Test
    void testCure() {
        final Cure cure = factory.createCure(test);
        player.getPlayerHealth().setHealth(DAMAGED_PLAYER);
        // Player collect the item
        cure.collect(player);
        // Checking that the effect has been applied
        assertEquals(2, player.getPlayerHealth().getHealth());
    }

    @Test
    void testCureMax() {
        final CureMax cure = factory.createCureMax(test);
        player.getPlayerHealth().setHealth(DAMAGED_PLAYER);
        // Player collect the item
        cure.collect(player);
        // Checking that the effect has been applied
        assertEquals(player.getPlayerHealth().getHealth(), player.getPlayerHealth().getMaxHealth());
    }

    @Test
    void testExtraHeart() {
        final ExtraHeart heart = factory.createExtraHeart(test);
        // Player collect the item
        heart.collect(player);
        // Checking that the effect has been applied correctly
        assertEquals(STARTING_HEALTH + 1, player.getPlayerHealth().getMaxHealth());
        assertNotEquals(player.getPlayerHealth().getMaxHealth(), player.getPlayerHealth().getHealth());
    }

    @Test
    void testGreatSword() {
        final GreatSword greatSword = factory.createGreatSword(test);
        // Player collect the item
        greatSword.collect(player);
        // Checking that the effect has been applied correctly
        assertEquals(SwordType.GREATSWORD, player.getSword().getSwordType());
        assertEquals(2, player.getSword().getSwordStrength());
    }

    @Test
    void testShield() {
        final Shield shield = factory.createShield(test);
        // Player collect the item
        shield.collect(player);
        // Checking that the effect has been applied correctly
        assertEquals(ShieldStatus.FULL, player.getPlayerHealth().getShieldStatus());
    }
}
