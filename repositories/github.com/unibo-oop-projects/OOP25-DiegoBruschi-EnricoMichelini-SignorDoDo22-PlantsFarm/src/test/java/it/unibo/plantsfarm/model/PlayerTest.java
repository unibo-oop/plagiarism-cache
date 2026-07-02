package it.unibo.plantsfarm.model;

import org.junit.jupiter.api.Test;

import it.unibo.plantsfarm.model.player.ExpertFarmer;
import it.unibo.plantsfarm.model.player.FarmerPlayer;
import it.unibo.plantsfarm.model.player.ImplFactoryPlayer;
import it.unibo.plantsfarm.model.player.PlayersTypes;
import it.unibo.plantsfarm.model.player.api.Player;
import it.unibo.plantsfarm.model.player.api.FactoryPlayer;
import it.unibo.plantsfarm.controller.player.ImplActionHandler;
import it.unibo.plantsfarm.controller.player.api.ActionHandler;
import static it.unibo.plantsfarm.controller.gamepanel.api.ControllerGamePanel.UserInput.DOWN;
import static it.unibo.plantsfarm.controller.gamepanel.api.ControllerGamePanel.UserInput.LEFT;
import static it.unibo.plantsfarm.controller.gamepanel.api.ControllerGamePanel.UserInput.RIGHT;
import static it.unibo.plantsfarm.controller.gamepanel.api.ControllerGamePanel.UserInput.UP;
import static org.junit.jupiter.api.Assertions.assertTrue;

final class PlayerTest {

    private static final int SIMULATED_DELTA = 2000;

    private final FactoryPlayer factory = new ImplFactoryPlayer();

    @Test
    void testCreationPlayer() {
        final Player player = factory.createPlayer(PlayersTypes.FARMER);
        assertTrue(player instanceof FarmerPlayer);
        final Player playerExpert = factory.createPlayer(PlayersTypes.EXPERTFARMER);
        assertTrue(playerExpert instanceof ExpertFarmer);
    }

    @Test
    void testMovementRight() {
        final Player player = factory.createPlayer(PlayersTypes.EXPERTFARMER);
        final ActionHandler action = new ImplActionHandler(player);
        final double startX = player.getPosx();
        action.updateDirection(RIGHT);

        player.updatePlayer(SIMULATED_DELTA);
        player.applyMovement();

        final double endX = player.getPosx();
        assertTrue(endX > startX);
    }

    @Test
    void testMovementLeft() {
        final Player player = factory.createPlayer(PlayersTypes.EXPERTFARMER);
        final ActionHandler action = new ImplActionHandler(player);
        action.updateDirection(LEFT);
        final double startX = player.getPosx();

        final long t0 = 0;
        final long t1 = 1000;

        player.updatePlayer(t0);
        player.updatePlayer(t1);
        player.applyMovement();

        final double endX = player.getPosx();

        assertTrue(endX < startX);
    }

    @Test
    void testMovementUp() {
        final Player player = factory.createPlayer(PlayersTypes.EXPERTFARMER);
        final ActionHandler action = new ImplActionHandler(player);
        action.updateDirection(UP);
        final double startY = player.getPosy();

        final long t0 = 0;
        final long t1 = 1000;

        player.updatePlayer(t0);
        player.updatePlayer(t1);
        player.applyMovement();

        final double endY = player.getPosy();

        assertTrue(endY < startY);
    }

    @Test
    void testMovementDown() {
        final Player player = factory.createPlayer(PlayersTypes.EXPERTFARMER);
        final ActionHandler action = new ImplActionHandler(player);
        action.updateDirection(DOWN);
        final double startY = player.getPosy();
        final long t0 = 0;
        final long t1 = 1000;
        player.updatePlayer(t0);
        player.updatePlayer(t1);
        player.applyMovement();

        final double endY = player.getPosy();

        assertTrue(startY < endY);
    }

}
