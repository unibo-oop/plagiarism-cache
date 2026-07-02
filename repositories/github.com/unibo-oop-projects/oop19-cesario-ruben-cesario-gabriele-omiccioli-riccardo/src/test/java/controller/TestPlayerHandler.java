package controller;

import org.junit.jupiter.api.Test;

import controller.stagehandler.playerhandler.PlayerHandler;
import controller.stagehandler.playerhandler.PlayerHandler.PlayerAction;
import controller.stagehandler.playerhandler.PlayerHandlerImpl;
import model.entity.EntityID;
import model.ship.PlayerShip;
import model.ship.basic.BasicSpaceShipFactory;
import utilities.math.Point2DImpl;
import utilities.math.Vector2DImpl;

/**
 * Tests the correct management of the PlayerHandler of all actions operable
 * by a player spaceship.
 */
public class TestPlayerHandler {

    /**
     * This test checks if the player handler properly manages the update
     * of the player PlayerScore.
     */
    @Test
    public void testIncrementScoreAndLevel() {
        final BasicSpaceShipFactory factory = new BasicSpaceShipFactory();
        final PlayerShip player = factory.buildPlayerShip(EntityID.SPACESHIP_BASIC, new Point2DImpl(), "player1");
        final PlayerHandler playerHandler = new PlayerHandlerImpl(player);
        /* PlayerHandler doesn't change immediately any data of the the player. */
        assert player.getPlayerScore().getLevelBeaten() == 0;
        assert player.getPlayerScore().getTotalScore() == 0;
        assert playerHandler.getFiredProjectiles().size() == 0;
        /* Test the score updating of the player. */
        playerHandler.addScore(500);
        assert player.getPlayerScore().getTotalScore() == 500;
        playerHandler.addScore(2_000);
        assert player.getPlayerScore().getTotalScore() == 2_500;
        /* Test the increment of the level beat by the player. */
        playerHandler.beatLevel();
        playerHandler.beatLevel();
        playerHandler.beatLevel();
        assert player.getPlayerScore().getLevelBeaten() == 3;
    }

    /**
     * This test checks if the player handler properly manages the PlayerAction actions.
     */
    @Test
    public void testAction() {
        final BasicSpaceShipFactory factory = new BasicSpaceShipFactory();
        final PlayerShip player = factory.buildPlayerShip(EntityID.FIGHTER, new Point2DImpl(), "player1");
        final PlayerHandler playerHandler = new PlayerHandlerImpl(player);
        /* Only computeAction() call the methods that change data of the player. */
        playerHandler.activate(PlayerAction.SHOOT);
        assert playerHandler.getFiredProjectiles().size() == 0;
        playerHandler.deactivate(PlayerAction.SHOOT);
        playerHandler.activate(PlayerAction.ROTATE_CLOCKWISE);
        assert player.getRotation() == 0.0;
        playerHandler.activate(PlayerAction.ROTATE_ANTICLOCKWISE);
        /* computeAction() call all the methods of active keys. */
        playerHandler.computeAction();
        playerHandler.computeAction();
        playerHandler.computeAction();
        assert player.getRotation() == 0.0;
        playerHandler.deactivate(PlayerAction.ROTATE_CLOCKWISE);
        playerHandler.computeAction();
        assert player.getRotation() != 0.0;
        playerHandler.deactivate(PlayerAction.ROTATE_ANTICLOCKWISE);
        playerHandler.activate(PlayerAction.ACCELERATE);
        playerHandler.computeAction();
        assert !player.getSpeed().equals(new Vector2DImpl());
    }

}
