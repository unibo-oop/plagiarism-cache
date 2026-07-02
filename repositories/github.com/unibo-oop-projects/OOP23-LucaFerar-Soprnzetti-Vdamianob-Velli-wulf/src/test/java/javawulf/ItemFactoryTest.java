package javawulf;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
//import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javawulf.model.BoundingBoxImpl;
//import javawulf.model.Collectable;
import javawulf.model.Coordinate;
import javawulf.model.CoordinateImpl;
//import javawulf.model.GameElement;
import javawulf.model.GameObject;
import javawulf.model.BoundingBox.CollisionType;
import javawulf.model.item.AmuletPiece;
import javawulf.model.item.Cure;
import javawulf.model.item.CureMax;
import javawulf.model.item.ExtraHeart;
import javawulf.model.item.GreatSword;
import javawulf.model.item.ItemFactory;
import javawulf.model.item.ItemFactoryImpl;
import javawulf.model.item.Shield;

/**
 * Test class for ItemFactory.
 */
final class ItemFactoryTest {

        private static final int STARTING_X = 12;
        private static final int STARTING_Y = 12;

        private final Coordinate position = new CoordinateImpl(STARTING_X, STARTING_Y);
        private ItemFactory factory;

        @BeforeEach
        void setUpFactory() {
                factory = new ItemFactoryImpl();
        }

        @Test
        void testCreateAmuletPiece() {
                final AmuletPiece piece = factory.createAmuletPiece(position);
                // Check if the piece is correctly created
                assertNotNull(piece);
                // Check if the piece is instantiated as an AmuletPiece
                //assertTrue(piece instanceof Collectable);
                //assertTrue(piece instanceof GameElement);
                // Check the coordinates of the piece
                assertEquals(position.getPosition(), piece.getPosition().getPosition());
                assertEquals(STARTING_X, piece.getPosition().getX());
                assertEquals(STARTING_Y, piece.getPosition().getY());
                // Check the BoundingBox of the piece
                assertEquals(piece.getBounds().getCollisionType(),
                                new BoundingBoxImpl(STARTING_X, STARTING_Y, GameObject.OBJECT_SIZE,
                                                GameObject.OBJECT_SIZE, CollisionType.COLLECTABLE).getCollisionType());
                assertEquals(piece.getBounds().getCollisionArea(),
                                new BoundingBoxImpl(STARTING_X, STARTING_Y, GameObject.OBJECT_SIZE,
                                                GameObject.OBJECT_SIZE, CollisionType.COLLECTABLE).getCollisionArea());
        }

        @Test
        void testCreateCure() {
                final Cure cure = factory.createCure(position);
                // Check if the cure is correctly created
                assertNotNull(cure);
                // Check if the cure is instantiated as a Cure
                //assertTrue(cure instanceof Collectable);
                // Check the coordinates of the cure
                assertEquals(position.getPosition(), cure.getPosition().getPosition());
                assertEquals(STARTING_X, cure.getPosition().getX());
                assertEquals(STARTING_Y, cure.getPosition().getY());
                // Check the BoundingBox of the cure
                assertEquals(cure.getBounds().getCollisionType(),
                                new BoundingBoxImpl(STARTING_X, STARTING_Y, GameObject.OBJECT_SIZE,
                                                GameObject.OBJECT_SIZE, CollisionType.COLLECTABLE).getCollisionType());
                assertEquals(cure.getBounds().getCollisionArea(),
                                new BoundingBoxImpl(STARTING_X, STARTING_Y, GameObject.OBJECT_SIZE,
                                                GameObject.OBJECT_SIZE, CollisionType.COLLECTABLE).getCollisionArea());
        }

        @Test
        void testCreateCureMax() {
                final CureMax cureMax = factory.createCureMax(position);
                // Check if the cureMax is correctly created
                assertNotNull(cureMax);
                // Check if the cureMax is instantiated as a CureMax
                //assertTrue(cureMax instanceof Collectable);
                // Check the coordinates of the cureMax
                assertEquals(position.getPosition(), cureMax.getPosition().getPosition());
                assertEquals(STARTING_X, cureMax.getPosition().getX());
                assertEquals(STARTING_Y, cureMax.getPosition().getY());
                // Check the BoundingBox of the cureMax
                assertEquals(cureMax.getBounds().getCollisionType(),
                                new BoundingBoxImpl(STARTING_X, STARTING_Y, GameObject.OBJECT_SIZE,
                                                GameObject.OBJECT_SIZE, CollisionType.COLLECTABLE).getCollisionType());
                assertEquals(cureMax.getBounds().getCollisionArea(),
                                new BoundingBoxImpl(STARTING_X, STARTING_Y, GameObject.OBJECT_SIZE,
                                                GameObject.OBJECT_SIZE, CollisionType.COLLECTABLE).getCollisionArea());
        }

        @Test
        void testCreateExtraHeart() {
                final ExtraHeart heart = factory.createExtraHeart(position);
                // Check if the heart is correctly created
                assertNotNull(heart);
                // Check if the heart is instantiated as an ExtraHeart
                //assertTrue(heart instanceof Collectable);
                // Check the coordinates of the heart
                assertEquals(position.getPosition(), heart.getPosition().getPosition());
                assertEquals(STARTING_X, heart.getPosition().getX());
                assertEquals(STARTING_Y, heart.getPosition().getY());
                // Check the BoundingBox of the heart
                assertEquals(heart.getBounds().getCollisionType(),
                                new BoundingBoxImpl(STARTING_X, STARTING_Y, GameObject.OBJECT_SIZE,
                                                GameObject.OBJECT_SIZE, CollisionType.COLLECTABLE).getCollisionType());
                assertEquals(heart.getBounds().getCollisionArea(),
                                new BoundingBoxImpl(STARTING_X, STARTING_Y, GameObject.OBJECT_SIZE,
                                                GameObject.OBJECT_SIZE, CollisionType.COLLECTABLE).getCollisionArea());
        }

        @Test
        void testCreateGreatSword() {
                final GreatSword greatSword = factory.createGreatSword(position);
                // Check if the greatSword is correctly created
                assertNotNull(greatSword);
                // Check if the greatSword is instantiated as a GreatSword
                //assertTrue(greatSword instanceof Collectable);
                // Check the coordinates of the greatSword
                assertEquals(position.getPosition(), greatSword.getPosition().getPosition());
                assertEquals(STARTING_X, greatSword.getPosition().getX());
                assertEquals(STARTING_Y, greatSword.getPosition().getY());
                // Check the BoundingBox of the greatSword
                assertEquals(greatSword.getBounds().getCollisionType(),
                                new BoundingBoxImpl(STARTING_X, STARTING_Y, GameObject.OBJECT_SIZE,
                                                GameObject.OBJECT_SIZE, CollisionType.COLLECTABLE).getCollisionType());
                assertEquals(greatSword.getBounds().getCollisionArea(),
                                new BoundingBoxImpl(STARTING_X, STARTING_Y, GameObject.OBJECT_SIZE,
                                                GameObject.OBJECT_SIZE, CollisionType.COLLECTABLE).getCollisionArea());
        }

        @Test
        void testCreateShield() {
                final Shield shield = factory.createShield(position);
                // Check if the shield is correctly created
                assertNotNull(shield);
                // Check if the shield is instantiated as a Shield
                //assertTrue(shield instanceof Collectable);
                // Check the coordinates of the shield
                assertEquals(position.getPosition(), shield.getPosition().getPosition());
                assertEquals(STARTING_X, shield.getPosition().getX());
                assertEquals(STARTING_Y, shield.getPosition().getY());
                // Check the BoundingBox of the shield
                assertEquals(shield.getBounds().getCollisionType(),
                                new BoundingBoxImpl(STARTING_X, STARTING_Y, GameObject.OBJECT_SIZE,
                                                GameObject.OBJECT_SIZE, CollisionType.COLLECTABLE).getCollisionType());
                assertEquals(shield.getBounds().getCollisionArea(),
                                new BoundingBoxImpl(STARTING_X, STARTING_Y, GameObject.OBJECT_SIZE,
                                                GameObject.OBJECT_SIZE, CollisionType.COLLECTABLE).getCollisionArea());
        }

}
