package zombietsunami;

import org.junit.jupiter.api.Test;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

import zombietsunami.model.MapData;
import zombietsunami.model.mapmodel.api.GameMap;
import zombietsunami.model.mapmodel.impl.GameMapImpl;

/**
 * This class is the test to check if the values in the map's txt file are
 * correctly readden.
 */
class TestLoadMap {
    // CPD-OFF
    /*
     * CPD suppressed because tests are naturally repetitive and their purpose
     * should be clear enough.
     */
    private static final int FIVE = 5;
    private static final int SIX = 6;
    private static final int SEVEN = 7;
    private static final int EIGHT = 8;
    private static final int NINE = 9;
    private static final int TEN = 10;
    private static final int ELEVEN = 11;
    private static final int TWELVE = 12;
    private static final int THIRTHEEN = 13;
    private static final int FOURTHEEN = 14;

    private final GameMap gameMap = new GameMapImpl();
    private List<Integer> values;

    /**
     * This method is the check for the first values line of the map.
     */
    @Test
    void checkLineOne() {
        values = List.of(2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2,
                2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2,
                2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2,
                2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2);
        assertEquals(values.size(), MapData.getMaxWorldCol());
        for (int i = 0; i < MapData.getMaxWorldCol(); i++) {
            assertEquals(values.get(i), gameMap.getLoadedMapList().get(i));
        }
    }

    /**
     * This method is the check for the second values line of the map.
     */
    @Test
    void checkLineTwo() {
        values = List.of(2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2,
                2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2,
                2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2,
                2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2);
        assertEquals(values.size(), MapData.getMaxWorldCol());
        for (int i = 0; i < MapData.getMaxWorldCol(); i++) {
            assertEquals(gameMap.getLoadedMapList().get(i + MapData.getMaxWorldCol()), values.get(i));
        }
    }

    /**
     * This method is the check for the third values line of the map.
     */
    @Test
    void checkLineThree() {
        values = List.of(2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2,
                2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2,
                2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2,
                2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2);
        assertEquals(values.size(), MapData.getMaxWorldCol());
        for (int i = 0; i < MapData.getMaxWorldCol(); i++) {
            assertEquals(gameMap.getLoadedMapList().get(i + MapData.getMaxWorldCol() * 2), values.get(i));
        }
    }

    /**
     * This method is the check for the fourth values line of the map.
     */
    @Test
    void checkLineFour() {
        values = List.of(2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 4, FIVE, SIX, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2,
                4, FIVE,
                SIX, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 4, FIVE, SIX, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 4,
                FIVE, SIX, 2, 2,
                2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 4, FIVE, SIX, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 4,
                FIVE, SIX, 2,
                2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 4, FIVE, SIX, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 4, FIVE, SIX,
                2, 2, 2);
        assertEquals(values.size(), MapData.getMaxWorldCol());
        for (int i = 0; i < MapData.getMaxWorldCol(); i++) {
            assertEquals(gameMap.getLoadedMapList().get(i + MapData.getMaxWorldCol() * 3), values.get(i));
        }
    }

    /**
     * This method is the check for the fifth values line of the map.
     */
    @Test
    void checkLineFive() {
        values = List.of(2, 2, 2, 4, FIVE, SIX, 2, 2, 2, 2, 2, 2, 2, 2, 3, NINE, SEVEN, 2, 2, 4, FIVE, SIX, 2, 2, 2, 2,
                2, 2, 2, 2, 3, NINE,
                SEVEN, 2, 2, 4, FIVE, SIX, 2, 2, 2, 2, 2, 2, 2, 2, 3, NINE, SEVEN, 2, 2, 4, FIVE, SIX, 2, 2, 2, 2, 2, 2,
                2, 2, 3, NINE, SEVEN, 2, 2,
                2, 2, 2, 2, 4, FIVE, SIX, 2, 2, 2, 2, 2, 2, 2, 2, 3, NINE, SEVEN, 2, 2, 4, FIVE, SIX, 2, 2, 2, 2, 2, 2,
                2, 2, 3, NINE, SEVEN, 2,
                2, 4, FIVE, SIX, 2, 2, 2, 2, 2, 2, 2, 2, 3, NINE, SEVEN, 2, 2, 4, FIVE, SIX, 2, 2, 2, 2, 2, 2, 2, 2, 3,
                NINE, SEVEN, 2, 2, 2);
        assertEquals(values.size(), MapData.getMaxWorldCol());
        for (int i = 0; i < MapData.getMaxWorldCol(); i++) {
            assertEquals(gameMap.getLoadedMapList().get(i + MapData.getMaxWorldCol() * 4), values.get(i));
        }
    }

    /**
     * This method is the check for the sixth values line of the map.
     */
    @Test
    void checkLineSix() {
        values = List.of(2, 2, 2, 3, NINE, SEVEN, 2, 2, 2, 2, 2, 2, 2, 2, 3, NINE, SEVEN, 2, 2, 3, NINE, SEVEN, 2, 2, 2,
                2, 2, 2, 2, 2, 3, NINE,
                SEVEN, 2, 2, 3, NINE, SEVEN, 2, 2, 2, 2, 2, 2, 2, 2, 3, NINE, SEVEN, 2, 2, 3, NINE, SEVEN, 2, 2, 2, 2,
                2, 2, 2, 2, 3, NINE, SEVEN, 2, 2,
                2, 2, 2, 2, 3, NINE, SEVEN, 2, 2, 2, 2, 2, 2, 2, 2, 3, NINE, SEVEN, 2, 2, 3, NINE, SEVEN, 2, 2, 2, 2, 2,
                2, 2, 2, 3, NINE, SEVEN, 2,
                2, 3, NINE, SEVEN, 2, 2, 2, 2, 2, 2, 2, 2, 3, NINE, SEVEN, 2, 2, 3, NINE, SEVEN, 2, 2, 2, 2, 2, 2, 2, 2,
                3, NINE, SEVEN, 2, 2, 2);
        assertEquals(values.size(), MapData.getMaxWorldCol());
        for (int i = 0; i < MapData.getMaxWorldCol(); i++) {
            assertEquals(gameMap.getLoadedMapList().get(i + MapData.getMaxWorldCol() * FIVE), values.get(i));
        }
    }

    /**
     * This method is the check for the seventh values line of the map.
     */
    @Test
    void checLineSeven() {
        values = List.of(SIX, 2, 2, 3, NINE, SEVEN, 2, 4, FIVE, SIX, 2, 2, 2, 2, 3, NINE, SEVEN, 2, 2, 3, NINE, SEVEN,
                2, 4, FIVE, SIX, 2, 2, 2, 2, 3, NINE,
                SEVEN, 2, 2, 3, NINE, SEVEN, 2, 4, FIVE, SIX, 2, 2, 2, 2, 3, NINE, SEVEN, 2, 2, 3, NINE, SEVEN, 2, 4,
                FIVE, SIX, 2, 2, 2, 2, 3, NINE, SEVEN, 2, 4,
                FIVE, SIX, 2, 2, 3, NINE, SEVEN, 2, 4, FIVE, SIX, 2, 2, 2, 2, 3, NINE, SEVEN, 2, 2, 3, NINE, SEVEN, 2,
                4, FIVE, SIX, 2, 2, 2, 2, 3, NINE, SEVEN, 2,
                2, 3, NINE, SEVEN, 2, 4, FIVE, SIX, 2, 2, 2, 2, 3, NINE, SEVEN, 2, 2, 3, NINE, SEVEN, 2, 4, FIVE, SIX,
                2, 2, 2, 2, 3, NINE, SEVEN, 2, 4, FIVE);
        assertEquals(values.size(), MapData.getMaxWorldCol());
        for (int i = 0; i < MapData.getMaxWorldCol(); i++) {
            assertEquals(gameMap.getLoadedMapList().get(i + MapData.getMaxWorldCol() * SIX), values.get(i));
        }
    }

    /**
     * This method is the check for the eighth values line of the map.
     */
    @Test
    void checkLineEight() {
        values = List.of(SEVEN, 2, 2, 3, NINE, SEVEN, 2, 3, NINE, SEVEN, 4, FIVE, SIX, 2, 3, NINE, SEVEN, 2, 2, 3, NINE,
                SEVEN, 2, 3, NINE, SEVEN, 4, FIVE, SIX, 2, 3, NINE,
                SEVEN, 2, 2, 3, NINE, SEVEN, 2, 3, NINE, SEVEN, 4, FIVE, SIX, 2, 3, NINE, SEVEN, 2, 2, 3, NINE, SEVEN,
                2, 3, NINE, SEVEN, 4, FIVE, SIX, 2, 3, NINE, SEVEN, 2, 3,
                NINE, SEVEN, 2, 2, 3, NINE, SEVEN, 2, 3, NINE, SEVEN, 4, FIVE, SIX, 2, 3, NINE, SEVEN, 2, 2, 3, NINE,
                SEVEN, 2, 3, NINE, SEVEN, 4, FIVE, SIX, 2, 3, NINE, SEVEN, 2,
                2, 3, NINE, SEVEN, 2, 3, NINE, SEVEN, 4, FIVE, SIX, 2, 3, NINE, SEVEN, 2, 2, 3, NINE, SEVEN, 2, 3, NINE,
                SEVEN, 4, FIVE, SIX, 2, 3, NINE, SEVEN, 2, 3, NINE);
        assertEquals(values.size(), MapData.getMaxWorldCol());
        for (int i = 0; i < MapData.getMaxWorldCol(); i++) {
            assertEquals(gameMap.getLoadedMapList().get(i + MapData.getMaxWorldCol() * SEVEN), values.get(i));
        }
    }

    /**
     * This method is the check for the nineth values line of the map.
     */
    @Test
    void checkLineNine() {
        values = List.of(SEVEN, 2, 2, 3, NINE, SEVEN, 2, 3, NINE, SEVEN, 3, NINE, SEVEN, 2, 3, NINE, SEVEN, 2, 2, 3,
                NINE, SEVEN, 2, 3, NINE, SEVEN, 3, NINE, SEVEN, 2, 3, NINE,
                SEVEN, 2, 2, 3, NINE, SEVEN, 2, 3, NINE, SEVEN, 3, NINE, SEVEN, 2, 3, NINE, SEVEN, 2, 2, 3, NINE, SEVEN,
                2, 3, NINE, SEVEN, 3, NINE, SEVEN, 2, 3, NINE, SEVEN, 2, 3,
                NINE, SEVEN, 2, 2, 3, NINE, SEVEN, 2, 3, NINE, SEVEN, 3, NINE, SEVEN, 2, 3, NINE, SEVEN, 2, 2, 3, NINE,
                SEVEN, 2, 3, NINE, SEVEN, 3, NINE, SEVEN, 2, 3, NINE, SEVEN, 2,
                2, 3, NINE, SEVEN, 2, 3, NINE, SEVEN, 3, NINE, SEVEN, 2, 3, NINE, SEVEN, 2, 2, 3, NINE, SEVEN, 2, 3,
                NINE, SEVEN, 3, NINE, SEVEN, 2, 3, NINE, SEVEN, 2, 3, NINE);
        assertEquals(values.size(), MapData.getMaxWorldCol());
        for (int i = 0; i < MapData.getMaxWorldCol(); i++) {
            assertEquals(gameMap.getLoadedMapList().get(i + MapData.getMaxWorldCol() * EIGHT), values.get(i));
        }
    }

    /**
     * This method is the check for the tenth values line of the map.
     */
    @Test
    void checkLineTen() {
        values = List.of(SEVEN, 2, 2, 3, EIGHT, SEVEN, 2, 3, EIGHT, SEVEN, 3, EIGHT, SEVEN, 2, 3, EIGHT, SEVEN, 2, 2, 3,
                EIGHT, SEVEN, 2, 3, EIGHT, SEVEN, 3, EIGHT, SEVEN, 2, 3, EIGHT,
                SEVEN, 2, 2, 3, EIGHT, SEVEN, 2, 3, EIGHT, SEVEN, 3, EIGHT, SEVEN, 2, 3, EIGHT, SEVEN, 2, 2, 3, EIGHT,
                SEVEN, 2, 3, EIGHT, SEVEN, 3, EIGHT, SEVEN, 2, 3, EIGHT, SEVEN, 2, 3,
                EIGHT, SEVEN, 2, 2, 3, EIGHT, SEVEN, 2, 3, EIGHT, SEVEN, 3, EIGHT, SEVEN, 2, 3, EIGHT, SEVEN, 2, 2, 3,
                EIGHT, SEVEN, 2, 3, EIGHT, SEVEN, 3, EIGHT, SEVEN, 2, 3, EIGHT, SEVEN, 2,
                2, 3, EIGHT, SEVEN, 2, 3, EIGHT, SEVEN, 3, EIGHT, SEVEN, 2, 3, EIGHT, SEVEN, 2, 2, 3, EIGHT, SEVEN, 2,
                3, EIGHT, SEVEN, 3, EIGHT, SEVEN, TEN, 3, EIGHT, SEVEN, 2, 3, EIGHT);
        assertEquals(values.size(), MapData.getMaxWorldCol());
        for (int i = 0; i < MapData.getMaxWorldCol(); i++) {
            assertEquals(gameMap.getLoadedMapList().get(i + MapData.getMaxWorldCol() * NINE), values.get(i));
        }
    }

    /**
     * This method is the check for the eleventh values line of the map.
     */
    @Test
    void checkLineEleven() {
        values = List.of(1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
                1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
                1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
                1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1);
        assertEquals(values.size(), MapData.getMaxWorldCol());
        for (int i = 0; i < MapData.getMaxWorldCol(); i++) {
            assertEquals(gameMap.getLoadedMapList().get(i + MapData.getMaxWorldCol() * TEN), values.get(i));
        }
    }

    /**
     * This method is the check for the twelveth values line of the map.
     */
    @Test
    void checkLineTwelve() {
        values = List.of(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
        assertEquals(values.size(), MapData.getMaxWorldCol());
        for (int i = 0; i < MapData.getMaxWorldCol(); i++) {
            assertEquals(gameMap.getLoadedMapList().get(i + MapData.getMaxWorldCol() * ELEVEN), values.get(i));
        }
    }

    /**
     * This method is the check for the thirtheenth values line of the map.
     */
    @Test
    void checkLineThirtheen() {
        values = List.of(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
        assertEquals(values.size(), MapData.getMaxWorldCol());
        for (int i = 0; i < MapData.getMaxWorldCol(); i++) {
            assertEquals(gameMap.getLoadedMapList().get(i + MapData.getMaxWorldCol() * TWELVE), values.get(i));
        }
    }

    /**
     * This method is the check for the fourtheenth values line of the map.
     */
    @Test
    void checkLineFourtheen() {
        values = List.of(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
        assertEquals(values.size(), MapData.getMaxWorldCol());
        for (int i = 0; i < MapData.getMaxWorldCol(); i++) {
            assertEquals(gameMap.getLoadedMapList().get(i + MapData.getMaxWorldCol() * THIRTHEEN), values.get(i));
        }
    }

    /**
     * This method is the check for the fivetheenth values line of the map.
     */
    @Test
    void checkLineFivetheen() {
        values = List.of(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
        assertEquals(values.size(), MapData.getMaxWorldCol());
        for (int i = 0; i < MapData.getMaxWorldCol(); i++) {
            assertEquals(gameMap.getLoadedMapList().get(i + MapData.getMaxWorldCol() * FOURTHEEN), values.get(i));
        }
    }

}
