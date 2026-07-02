package projection.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.awt.Color;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import gamelogic.GameLogic;
import gamelogic.GameLogicImpl;
import level.Levels;
import pair.Pair;
import piece.PieceImpl;
import piece.Type;
import projection.Projection;
import projection.ProjectionImpl;


/**
 * This class tests methods of class Projection. 
 */
public class TestProjection {
    private static final int TOP = 18;
    private static final int LEFT = 3;
    private static final Set<Pair<Integer, Integer>> COORDINATES = new HashSet<>();
    static {
        COORDINATES.add(new Pair<>(1, 0));
        COORDINATES.add(new Pair<>(1, 1));
        COORDINATES.add(new Pair<>(1, 2));
        COORDINATES.add(new Pair<>(0, 2));
    }
    private static final Set<Pair<Integer, Integer>> PROJECTION_COORDINATES = new HashSet<>();
    static {
        PROJECTION_COORDINATES.add(new Pair<>(1 + TOP, 0 + LEFT));
        PROJECTION_COORDINATES.add(new Pair<>(1 + TOP, 1 + LEFT));
        PROJECTION_COORDINATES.add(new Pair<>(1 + TOP, 2 + LEFT));
        PROJECTION_COORDINATES.add(new Pair<>(0 + TOP, 2 + LEFT));
    }
    private static final Set<Pair<Integer, Integer>> DISABLED_COORDINATES = new HashSet<>();
    private final GameLogic game = new GameLogicImpl(Levels.LEVEL_1, Optional.empty());
    private final Projection proj = new ProjectionImpl(this.game);

    /**
     * Set the current piece to a specific one.
     */
    @org.junit.Before
    public void initProjection() {
        // select piece L
        this.game.setCurrent(new PieceImpl(Type.L, Optional.empty(), Optional.empty(), Optional.empty()));
    }

    /**
     * Test the color returned by getColor.
     */
    @org.junit.Test
    public void testColor() {
        final Color newColor = this.proj.getColor();
        assertEquals(newColor.getAlpha(), Projection.TRANSPARENCY); // same alpha
        assertEquals(newColor.getRed(), this.game.getCurrent().getColor().getRed()); // same red
        assertEquals(newColor.getGreen(), this.game.getCurrent().getColor().getGreen()); // same green
        assertEquals(newColor.getBlue(), this.game.getCurrent().getColor().getBlue()); // same blue
    }

    /**
     * Tests the projection in case it is enabled or disabled.
     */
    @org.junit.Test
    public void testNewProjection() {
        assertTrue(this.proj.isEnabled());
        assertEquals(this.proj.newProjection(), PROJECTION_COORDINATES);
        this.proj.setEnable(false);
        assertFalse(this.proj.isEnabled());
        assertEquals(this.proj.newProjection(), DISABLED_COORDINATES);
    }
}
