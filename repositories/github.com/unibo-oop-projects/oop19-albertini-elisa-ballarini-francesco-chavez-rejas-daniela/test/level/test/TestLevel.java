package level.test;

import static org.junit.Assert.assertEquals;
import level.Levels;


/**
 * This class tests methods of class Levels.
 */
public class TestLevel {

    private static final int LEVEL1 = 1000;
    private static final int LEVEL2 = 900;
    private static final int LEVEL3 = 800;
    private static final int LEVEL4 = 700;
    private static final int LEVEL5 = 600;
    private static final int LEVEL6 = 550;
    private static final int LEVEL7 = 500;
    private static final int LEVEL8 = 450;
    private static final int LEVEL9 = 400;
    private static final int LEVEL10 = 350;

    /**
     * Tests the speed associated to each level.
     */
    @org.junit.Test
    public void testLevelSpeed() {
        assertEquals(Levels.LEVEL_1.getSpeed(), LEVEL1);
        assertEquals(Levels.LEVEL_2.getSpeed(), LEVEL2);
        assertEquals(Levels.LEVEL_3.getSpeed(), LEVEL3);
        assertEquals(Levels.LEVEL_4.getSpeed(), LEVEL4);
        assertEquals(Levels.LEVEL_5.getSpeed(), LEVEL5);
        assertEquals(Levels.LEVEL_6.getSpeed(), LEVEL6);
        assertEquals(Levels.LEVEL_7.getSpeed(), LEVEL7);
        assertEquals(Levels.LEVEL_8.getSpeed(), LEVEL8);
        assertEquals(Levels.LEVEL_9.getSpeed(), LEVEL9);
        assertEquals(Levels.LEVEL_10.getSpeed(), LEVEL10);
    }

    /**
     * Tests the method getNextLevel(). In particular the next level of level 10 must be level 10.
     */
    @org.junit.Test
    public void testNextLevel() {
        assertEquals(Levels.LEVEL_1.getNextLevel(), Levels.LEVEL_2);
        assertEquals(Levels.LEVEL_2.getNextLevel(), Levels.LEVEL_3);
        assertEquals(Levels.LEVEL_3.getNextLevel(), Levels.LEVEL_4);
        assertEquals(Levels.LEVEL_4.getNextLevel(), Levels.LEVEL_5);
        assertEquals(Levels.LEVEL_5.getNextLevel(), Levels.LEVEL_6);
        assertEquals(Levels.LEVEL_6.getNextLevel(), Levels.LEVEL_7);
        assertEquals(Levels.LEVEL_7.getNextLevel(), Levels.LEVEL_8);
        assertEquals(Levels.LEVEL_8.getNextLevel(), Levels.LEVEL_9);
        assertEquals(Levels.LEVEL_9.getNextLevel(), Levels.LEVEL_10);
        assertEquals(Levels.LEVEL_10.getNextLevel(), Levels.LEVEL_10);
        assertEquals(Levels.LEVEL_10.getNextLevel(), Levels.LEVEL_10);
    }
}
