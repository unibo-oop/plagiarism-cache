package model.logic;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import controller.PlayerColor;
import model.board.GameBoard;
import model.board.GameBoardImpl;
import model.players.Player;
import model.players.PlayerCircularQueue;
import model.players.PlayerImpl;
import model.utils.Boat;
import model.utils.BoatImpl;
import model.utils.Direction;
import model.utils.PossibleActionAfterMove;

/**
 * The purpose of this class is to test some logic class expecially related to movement and playerOptions.
 */
public class TestLogics {

    private final GameBoard gB = new GameBoardImpl();
    private final Boat<Player> boat = new BoatImpl<Player>();
    private final MovementLogic mL = new MovementLogicImpl();
    private final PlayerOptions pOI = new PlayerOptionsImpl();
    private final ScoreLogic sL = new ScoreLogicImpl();
    private static final Integer NPLAYER = 3;
    private static final Integer TOENDLINE = 32;
    private final PlayerCircularQueue pCQ = new PlayerCircularQueue(NPLAYER);
    private static final Player PLAYERONE = new PlayerImpl("Matteo", PlayerColor.GREEN);
    private static final Player PLAYERTWO = new PlayerImpl("Luca", PlayerColor.ORANGE);
    private static final Player PLAYERTHREE = new PlayerImpl("Paolo", PlayerColor.RED);
    private static Integer sampleValue = 4;

    /**
     * This test has the purpose of simulate a part of game, testing movement logic.
     */
    @Test
    public void testOne() {
        this.pCQ.offer(PLAYERONE);
        this.pCQ.offer(PLAYERTWO);
        this.pCQ.offer(PLAYERTHREE);
        this.mL.movePlayer(gB, pCQ.poll(), sampleValue, pCQ, boat);
        this.mL.movePlayer(gB, pCQ.poll(), sampleValue, pCQ, boat);
        this.mL.movePlayer(gB, pCQ.poll(), sampleValue, pCQ, boat);
        assertEquals("Something gone bad in movement", gB.getPlayerIndex(PLAYERONE), sampleValue);
        sampleValue++;
        assertEquals("Something gone bad in movement", gB.getPlayerIndex(PLAYERTWO), sampleValue);
        sampleValue++;
        assertEquals("Something gone bad in movement", gB.getPlayerIndex(PLAYERTHREE), sampleValue);
        this.mL.moveAllPlayerToBoat(gB, boat);
        assertEquals("Something gone bad in moving from tilesLine to boat", (Integer) boat.size(), NPLAYER);
    }

    /**
     * This test has the purpose of simulate a part of game, the possible directions for player in turn.
     */
    @Test
    public void testTwo() {
        final List<String> expectedMovePossibility = new ArrayList<String>();
        expectedMovePossibility.add(Direction.DEEP.name());
        expectedMovePossibility.add(Direction.TO_BOAT.name());
        this.pCQ.offer(PLAYERONE);
        this.mL.movePlayer(gB, pCQ.poll(), sampleValue, pCQ, boat);
        assertEquals("List of move option not as expected", this.pOI.whereCanIMove(PLAYERONE, this.gB, this.mL),
                expectedMovePossibility);
        this.mL.moveAllPlayerToBoat(gB, boat);
        /*
         * Test the end of line possibility to move, violating for testing purpose the max dice value of 6.
         */
        this.pCQ.offer(PLAYERONE);
        expectedMovePossibility.remove(0);
        this.mL.movePlayer(gB, pCQ.poll(), TOENDLINE, pCQ, boat);
        assertEquals("List of move option not as expected", this.pOI.whereCanIMove(PLAYERONE, this.gB, this.mL),
                expectedMovePossibility);
    }

    /**
     * This test has the purpose of simulate a part of game, the possible actions for player in turn.
     */
    @Test
    public void testThree() {
        final List<String> expectedActionPossible = new ArrayList<String>();
        expectedActionPossible.add(PossibleActionAfterMove.PICK_UP.name());
        expectedActionPossible.add(PossibleActionAfterMove.PASS_TURN.name());
        this.pCQ.offer(PLAYERONE);
        this.pCQ.offer(PLAYERTWO);
        this.mL.movePlayer(gB, pCQ.poll(), 4, pCQ, boat);
        assertEquals("List of action option not as expected", this.pOI.whatICanDoAfterMovement(PLAYERONE, this.gB),
                expectedActionPossible);
        this.sL.playerPickUpTreasure(gB, PLAYERONE);
        this.mL.movePlayer(gB, pCQ.poll(), 3, pCQ, boat);
        this.mL.movePlayer(gB, pCQ.poll(), 1, pCQ, boat);
        this.mL.movePlayer(gB, pCQ.poll(), 1, pCQ, boat);
        /*
         * Now set expected action for player two at this point.
         */
        expectedActionPossible.clear();
        expectedActionPossible.add(PossibleActionAfterMove.PASS_TURN.name());
        assertEquals("List of action option not as expected", this.pOI.whatICanDoAfterMovement(PLAYERTWO, this.gB),
                expectedActionPossible);

    }
}
