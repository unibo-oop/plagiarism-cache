package booster.test;

import static org.junit.Assert.assertEquals;
import java.util.HashSet;
import java.util.Set;
import booster.Booster;
import booster.Booster1Impl;
import booster.Booster2Impl;
import gamelogic.GameLogic;
import manager.ControllerManager;
import manager.ControllerManagerImpl;
import pair.Pair;
import playcontroller.PlayController;
import playcontroller.PlayControllerImpl;

/**
 * Test for the Booster1 and Booster2.
 *
 */
public class TestBooster {
    private final ControllerManager controllerManager = new ControllerManagerImpl();
    private final PlayController playController = new PlayControllerImpl(controllerManager);
    private final GameLogic game = this.playController.getGame();
    private final Booster booster1 = new Booster1Impl(true);
    private final Booster booster2 = new Booster2Impl(true);

    private static final Set<Pair<Integer, Integer>> BOOSTER1TRYBOARD1 = new HashSet<>();
    static {
        BOOSTER1TRYBOARD1.add(new Pair<>(19, 1));
        BOOSTER1TRYBOARD1.add(new Pair<>(19, 2));
        BOOSTER1TRYBOARD1.add(new Pair<>(19, 3));
        BOOSTER1TRYBOARD1.add(new Pair<>(19, 4));
        BOOSTER1TRYBOARD1.add(new Pair<>(19, 5));
        BOOSTER1TRYBOARD1.add(new Pair<>(19, 6));
        BOOSTER1TRYBOARD1.add(new Pair<>(19, 7));
        BOOSTER1TRYBOARD1.add(new Pair<>(18, 0));
        BOOSTER1TRYBOARD1.add(new Pair<>(18, 1));
        BOOSTER1TRYBOARD1.add(new Pair<>(18, 2));
        BOOSTER1TRYBOARD1.add(new Pair<>(18, 7));
        BOOSTER1TRYBOARD1.add(new Pair<>(18, 8));
        BOOSTER1TRYBOARD1.add(new Pair<>(18, 9));
    }

    private static final Set<Pair<Integer, Integer>> BOOSTER2TRYBOARD1 = new HashSet<>();
    static {
        BOOSTER2TRYBOARD1.add(new Pair<>(18, 3));
        BOOSTER2TRYBOARD1.add(new Pair<>(19, 3));
        BOOSTER2TRYBOARD1.add(new Pair<>(19, 4));
    }

    private static final Set<Pair<Integer, Integer>> BOOSTER1TRYPIECE1 = new HashSet<>();
    static {
        BOOSTER1TRYPIECE1.add(new Pair<>(0, 0));
        BOOSTER1TRYPIECE1.add(new Pair<>(0, 1));
        BOOSTER1TRYPIECE1.add(new Pair<>(0, 2));
        BOOSTER1TRYPIECE1.add(new Pair<>(0, 3));
        BOOSTER1TRYPIECE1.add(new Pair<>(0, 4));
        BOOSTER1TRYPIECE1.add(new Pair<>(0, 5));
        BOOSTER1TRYPIECE1.add(new Pair<>(0, 6));
        BOOSTER1TRYPIECE1.add(new Pair<>(0, 7));
        BOOSTER1TRYPIECE1.add(new Pair<>(0, 8));
    }

    private static final Set<Pair<Integer, Integer>> BOOSTER1TRYPIECE2 = new HashSet<>();
    static {
        BOOSTER1TRYPIECE2.add(new Pair<>(0, 0));
        BOOSTER1TRYPIECE2.add(new Pair<>(0, 1));
        BOOSTER1TRYPIECE2.add(new Pair<>(0, 2));
        BOOSTER1TRYPIECE2.add(new Pair<>(0, 3));
        BOOSTER1TRYPIECE2.add(new Pair<>(0, 4));
        BOOSTER1TRYPIECE2.add(new Pair<>(0, 5));
        BOOSTER1TRYPIECE2.add(new Pair<>(0, 6));
    }

    private static final Set<Pair<Integer, Integer>> BOOSTER1TRYPIECE3 = new HashSet<>();
    static {
        BOOSTER1TRYPIECE3.add(new Pair<>(0, 0));
        BOOSTER1TRYPIECE3.add(new Pair<>(0, 1));
        BOOSTER1TRYPIECE3.add(new Pair<>(0, 2));
        BOOSTER1TRYPIECE3.add(new Pair<>(0, 7));
        BOOSTER1TRYPIECE3.add(new Pair<>(0, 8));
        BOOSTER1TRYPIECE3.add(new Pair<>(0, 9));
    }

    private static final Set<Pair<Integer, Integer>> BOOSTER2TRYPIECE1 = new HashSet<>();
    static {
        BOOSTER2TRYPIECE1.add(new Pair<>(0, 0));
        BOOSTER2TRYPIECE1.add(new Pair<>(1, 0));
        BOOSTER2TRYPIECE1.add(new Pair<>(2, 0));
        BOOSTER2TRYPIECE1.add(new Pair<>(2, 1));
    }

    /**
     * Tests the booster1.
     */
    @org.junit.Test
    public void testBooster1() {
        this.game.getCurrent().setCoordinates(BOOSTER1TRYPIECE1);
        this.game.getCurrent().setTop(19);
        this.game.getCurrent().setLeft(0);
        this.game.getBoard().placePiece();
        this.game.getCurrent().setCoordinates(BOOSTER1TRYPIECE2);
        this.game.getCurrent().setTop(18);
        this.game.getCurrent().setLeft(1);
        this.game.getBoard().placePiece();
        this.game.getCurrent().setCoordinates(BOOSTER1TRYPIECE3);
        this.game.getCurrent().setTop(17);
        this.game.getCurrent().setLeft(0);
        this.game.getBoard().placePiece();
        // The Board here before the booster contains
        // [19,0],[19,1],[19,2],[19,3],[19,4],[19,5],[19,6],[19,7],[19,8]
        // [18,1],[18,2],[18,3],[18,4],[18,5],[18,6],[18,7]
        // [17,0],[17,1],[17,2],[17,7],[17,8],[17,9]
        this.booster1.useBooster(this.playController);
        // The Board here after the booster should contain
        // [19,1],[19,2],[19,3],[19,4],[19,5],[19,6],[19,7]
        // [18,0],[18,1],[18,2],[18,7],[18,8],[18,9]
        assertEquals(BOOSTER1TRYBOARD1, this.game.getBoard().getBoard().keySet());
    }

    /**
     * Tests the booster2.
     */
    @org.junit.Test
    public void testBooster2() {
        this.game.getCurrent().setCoordinates(BOOSTER2TRYPIECE1);
        this.game.getCurrent().setTop(17);
        this.game.getBoard().placePiece();
        // The Board here before the booster contains
        // [19,4],[19,3],[18,3],[17,3]
        this.booster2.useBooster(this.playController);
        // The Board here after the booster should contain
        // [19,4],[19,3],[18,3]
        assertEquals(BOOSTER2TRYBOARD1, this.game.getBoard().getBoard().keySet());
    }
}
