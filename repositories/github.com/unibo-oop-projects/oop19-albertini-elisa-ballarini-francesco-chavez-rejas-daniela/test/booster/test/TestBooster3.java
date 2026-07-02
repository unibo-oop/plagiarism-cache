package booster.test;

import static org.junit.Assert.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.awt.Color;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.junit.Test;

import booster.Booster;
import booster.Booster3Impl;
import gamelogic.GameLogic;
import manager.ControllerManager;
import manager.ControllerManagerImpl;
import pair.Pair;
import piece.Piece;
import piece.PieceImpl;
import piece.Type;
import playcontroller.PlayController;
import playcontroller.PlayControllerImpl;

/**
 *
 */
public class TestBooster3 {
    private final ControllerManager controllerManager = new ControllerManagerImpl();
    private final PlayController playController = new PlayControllerImpl(controllerManager);
    private final GameLogic game = this.playController.getGame();
    private final Booster booster3 = new Booster3Impl(true);

    private static final Set<Pair<Integer, Integer>> TEST_SET = new HashSet<>();
    private static final Pair<Integer, Integer> TEST_CENTER = new Pair<>(1, 1);
    static {
        TEST_SET.add(TEST_CENTER);
    }
    private final Piece testBlock = new PieceImpl(Type.CUSTOM, Optional.of(TEST_SET), Optional.of(Color.BLACK),
            Optional.of(TEST_CENTER));

    /**
     * Test the booster3 : switch the current piece in the board with a black 1x1
     * piece.
     */
    @Test
    public void testPiece() {

        this.booster3.useBooster(this.playController);

        assertEquals(testBlock.getCoordinates().size(), this.game.getCurrent().getCoordinates().size());
        assertEquals(testBlock.getColor(), this.game.getCurrent().getColor());
    }

    /**
     * Test of the correct activation of the booster.
     */
    @Test
    public void testUse() {

        this.booster3.useBooster(playController);
        this.game.setCurrent(new PieceImpl(Type.I, Optional.empty(), Optional.empty(), Optional.empty()));
        this.booster3.useBooster(playController);

        assertNotSame(this.testBlock, this.game.getCurrent());

        this.game.getScore().addPoints(2);

        this.booster3.useBooster(playController);

        assertNotEquals(testBlock.getCoordinates().size(), this.game.getCurrent().getCoordinates().size());

        this.game.getScore().addPoints(20);

        this.booster3.useBooster(playController);

        assertEquals(testBlock.getCoordinates().size(), this.game.getCurrent().getCoordinates().size());
        assertEquals(testBlock.getColor(), this.game.getCurrent().getColor());
    }
}
