package view.level.settings;


/**
 * This class contains some of the settings used to spawn levels.
 */

public final class LevelSettings {

    /**
     * number of blocks in height.
     */
    private static final int N_BLOCK_HEIGHT = 12;

    /**
     * number of blocks in width.
     */
    private static final int N_BLOCK_WIDTH = 20;

    /**
     * number of different map traces.
     */
    private static final int N_MAP_TRACES = 4;

    /**
     * minimum number of spawned enemies.
     */
    private static final int N_MIN_SPAWNED_ENEMIES = 10;
    /**
     * maximum number of spawned enemies.
     */
    private static final int N_MAX_SPAWNED_ENEMIES = 12;

    /**
     * minimum number of spawned items.
     */
    private static final int N_MIN_SPAWNED_ITEMS = 2;
    /**
     * maximum number of spawned items.
     */
    private static final int N_MAX_SPAWNED_ITEMS = 7;

    /**
     * minimum number of spawned level for each type.
     */
    private static final int N_MIN_LEVELS_FOR_TYPE = 2;

    /**
     * maximum number of spawned level for each type.
     */
    private static final int N_MAX_LEVELS_FOR_TYPE = 4;

    /**
     * letter representing the entry door in the level matrix.
     */
    private static final char CHAR_ENTRY_DOOR = 'd';

    /**
     * letter representing the exit door in the level matrix.
     */
    private static final char CHAR_EXIT_DOOR = 'D';
    /**
     * letter representing the wall in the level matrix.
     */
    private static final char CHAR_WALL = 'W';
    /**
     * letter representing the player's spawn point in the level matrix.
     */
    private static char charSpawnPlayer = 'A';
    /**
     * letter representing the skeleton spawn point in the level matrix.
     */
    private static final char CHAR_SPAWN_ENEMIES_S = 's';

    /**
     * letter representing the goblin spawn point in the level matrix.
     */
    private static final char CHAR_SPAWN_ENEMIES_G = 'g';

    /**
     * letter representing the blue potion spawn point in the level matrix.
     */
    private static final char CHAR_SPAWN_ITEM_B = 'b';

    /**
     * letter representing the red potion spawn point in the level matrix.
     */
    private static final char CHAR_SPAWN_ITEM_R = 'r';

    /**
     * letter representing an empty space in the level matrix.
     */
    private static final char CHAR_EMPTY_SPACE = 'E';
    /**
     * letter representing the floor in the level matrix.
     */
    private static final char CHAR_FLOOR = 'F';
    /**
     * letter representing an entry stair in the level matrix.
     */
    private static final char CHAR_ENTRY_STAIRS = 'L';
    /**
     * letter representing an exit stair in the level matrix.
     */
    private static final char CHAR_EXIT_STAIRS = 'U';

    /**
     * @return the nBlockHeight
     */
    public int getnBlockHeight() {
        return N_BLOCK_HEIGHT;
    }

    /**
     * @return the nBlockWidth
     */
    public int getnBlockWidth() {
        return N_BLOCK_WIDTH;
    }

    /**
     * @return the nMapTraces
     */
    public int getnMapTraces() {
        return N_MAP_TRACES;
    }

    /**
     * @return the nMinSpawnedEnemies
     */
    public int getnMinSpawnedEnemies() {
        return N_MIN_SPAWNED_ENEMIES;
    }

    /**
     * @return the nMaxSpawnedEnemies
     */
    public int getnMaxSpawnedEnemies() {
        return N_MAX_SPAWNED_ENEMIES;
    }

    /**
     * @return the charWall
     */
    public char getCharWall() {
        return CHAR_WALL;
    }

    /**
     * @return the charSpawnPlayer
     */
    public char getCharSpawnPlayer() {
        return charSpawnPlayer;
    }

    /**
     * @return the charSpawnEnemies
     */
    public char getCharSpawnEnemiesS() {
        return CHAR_SPAWN_ENEMIES_S;
    }

    /**
     * @return the charSpawnEnemies
     */
    public char getCharSpawnEnemiesG() {
        return CHAR_SPAWN_ENEMIES_G;
    }

    /**
     * @return the charEmptySpace
     */
    public char getCharEmptySpace() {
        return CHAR_EMPTY_SPACE;
    }

    /**
     * @return the charFloor
     */
    public char getCharFloor() {
        return CHAR_FLOOR;
    }

    /**
     * @return the charEntryStairs
     */
    public char getCharEntryStairs() {
        return CHAR_ENTRY_STAIRS;
    }

    /**
     * @return the charExitStairs
     */
    public char getCharExitStairs() {
        return CHAR_EXIT_STAIRS;
    }

    /**
     * @return the nMinLevelsForType
     */
    public int getnMinLevelsForType() {
        return N_MIN_LEVELS_FOR_TYPE;
    }

    /**
     * @return the nMaxLevelsForType
     */
    public int getnMaxLevelsForType() {
        return N_MAX_LEVELS_FOR_TYPE;
    }

	/**
	 * @return the nMinSpawnedItems
	 */
	public int getnMinSpawnedItems() {
		return N_MIN_SPAWNED_ITEMS;
	}

	/**
	 * @return the nMaxSpawnedItems
	 */
	public int getnMaxSpawnedItems() {
		return N_MAX_SPAWNED_ITEMS;
	}

	/**
	 * @return the charSpawnItemB
	 */
	public char getCharSpawnItemB() {
		return CHAR_SPAWN_ITEM_B;
	}

	/**
	 * @return the charSpawnItemR
	 */
	public char getCharSpawnItemR() {
		return CHAR_SPAWN_ITEM_R;
	}

	/**
	 * @return the charEntryDoor
	 */
	public char getCharEntryDoor() {
		return CHAR_ENTRY_DOOR;
	}

	/**
	 * @return the charExitDoor
	 */
	public char getCharExitDoor() {
		return CHAR_EXIT_DOOR;
	}

	/**
	 * @param newChar the new character to set.
	 */
	public static void setPlayerSpawnChar(final char newChar) {
		charSpawnPlayer = newChar;
	}
}

