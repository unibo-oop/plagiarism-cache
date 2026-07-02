package javaclimber.command;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import it.unibo.model.command.api.RunningCommand;
import it.unibo.model.command.impl.MoveAlienLeft;
import it.unibo.model.command.impl.MoveAlienRight;
import it.unibo.model.command.impl.StopAlienMovement;
import it.unibo.model.gameobj.impl.AlienImpl;
import it.unibo.model.launchedgame.impl.LaunchedGameImpl;
import it.unibo.model.physics.impl.Vector2dImpl;
import it.unibo.model.shop.impl.ActiveUpgradesImpl;
import it.unibo.model.shop.impl.InventoryImpl;
import it.unibo.model.shop.impl.ShopItemFactoryImpl;

/** Test class for the {@link RunningCommand} implementations. */
class MovementCommandTest {

    private static final double X = 10;
    private static final double Y = 20;

    private static final double SPEED_X = 0;
    private static final double SPEED_Y = 0;

    private static final double WIDTH = 50;
    private static final double HEIGHT = 50;

    /** Test the {@link MoveAlienLeft} command and verify movement state. */
    @Test
    void moveAlienLeftTest() {
        final RunningCommand moveLeft = new MoveAlienLeft();
        final ActiveUpgradesImpl upgrades = new ActiveUpgradesImpl(new InventoryImpl(new ShopItemFactoryImpl()));
        final AlienImpl alien = new AlienImpl(
                new Vector2dImpl(X, Y),
                new Vector2dImpl(SPEED_X, SPEED_Y),
                WIDTH,
                HEIGHT,
                upgrades
        );
        moveLeft.execute(alien, new LaunchedGameImpl(null));
        assertTrue(alien.isMovingLeft());
    }

    /** Test the {@link MoveAlienRight} command and verify movement state. */
    @Test
    void moveAlienRightTest() {
        final RunningCommand moveRight = new MoveAlienRight();
        final ActiveUpgradesImpl upgrades = new ActiveUpgradesImpl(new InventoryImpl(new ShopItemFactoryImpl()));
        final AlienImpl alien = new AlienImpl(
                new Vector2dImpl(X, Y),
                new Vector2dImpl(SPEED_X, SPEED_Y),
                WIDTH,
                HEIGHT,
                upgrades
        );
        moveRight.execute(alien, new LaunchedGameImpl(null));
        assertTrue(alien.isMovingRight());
    }

    /** Test the {@link StopAlienMovement} command and verify no movement. */
    @Test
    void stopAlien() {
        final RunningCommand stop = new StopAlienMovement();
        final ActiveUpgradesImpl upgrades = new ActiveUpgradesImpl(new InventoryImpl(new ShopItemFactoryImpl()));
        final AlienImpl alien = new AlienImpl(
                new Vector2dImpl(X, Y),
                new Vector2dImpl(SPEED_X, SPEED_Y),
                WIDTH,
                HEIGHT,
                upgrades
        );
        stop.execute(alien, new LaunchedGameImpl(null));
        assertFalse(alien.isMovingLeft() || alien.isMovingRight());
    }
}
