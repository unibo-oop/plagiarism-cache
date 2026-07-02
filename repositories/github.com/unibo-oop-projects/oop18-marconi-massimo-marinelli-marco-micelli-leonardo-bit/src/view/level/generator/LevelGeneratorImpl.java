package view.level.generator;

import java.io.IOException;
import java.util.Random;

import view.level.settings.LevelSettings;

/**
 * This class transforms a map trace into a new level and writes it in a .txt file.
 */

public final class LevelGeneratorImpl {

    private static char[][] matrix;

    LevelGeneratorImpl() {
    }

    /**
     * This public method is called whenever it is necessary to build a new level.
     * 
     * @param nLivello
     *                every level has a number working as identifier
     *
     * @param nExitDoors
     *                number of the exit doors into the generated level
     * @param entrances
     *
     * @param entryStairs 
     * 
     * @param exitStairs 
     */

    public void levelGenerator(final char var, final int nLivello, final int nExitDoors, final boolean entrances, final boolean entryStairs, final boolean exitStairs) {
        loadMapTrace();
        entryDoorSet(var, entryStairs, entrances);
        exitDoorSet(nExitDoors, exitStairs);
        enemiesSpawnPoint();
        itemsSpawnPoint();
        placeFloor();
        WritingLevelsOnTxtImpl.writeOnTxt(nLivello, matrix);
        }


     /**
      * This method choose randomly a map trace and loads it into the level matrix.
      */

    private static void loadMapTrace() {
        final Random rand = new Random();
        final LevelSettings variable = new LevelSettings();
        final int n = rand.nextInt(variable.getnMapTraces()) + 1;
        final String path = "src/assets/MapTraces/MapTrace" + n + ".txt";
        try {
            MapTracesLoaderImpl reader = new MapTracesLoaderImpl();
            matrix = reader.readFromTxt(path);
        } catch (IOException e) {
            e.printStackTrace();
            }
        }
    
    /**
     * This method handle the spawning of entry doors and entry stairs.
     */
    private static void entryDoorSet(final char var, final boolean entryStairs, final boolean entrances) {
        final LevelSettings variable = new LevelSettings();
        variable.setPlayerSpawnChar(var);
        final int yMax = variable.getnBlockWidth() / 3;
        final int xMax = variable.getnBlockHeight();
        final Random rand = new Random();
        while (true) {
            final int x = rand.nextInt(xMax);
            final int y = rand.nextInt(yMax);
            if (canBeADoor(x, y)) {
                if (entryStairs) {
                    matrix[x][y + 1] = variable.getCharEntryStairs();
                    matrix[x][y + 2] = variable.getCharSpawnPlayer();
                    }
                else if (!entrances) {
                	matrix[x][y + 1] = variable.getCharSpawnPlayer();
                }
                else {
                    matrix[x][y] = variable.getCharEntryDoor();
                    matrix[x][y + 1] = variable.getCharSpawnPlayer();
                    }
                break;
                }
            }
        }

    /**
     * This method handle the spawning of exit doors and exit stairs.
     * 
     *  @param nExitDoors 
     *
     *  @param exitStairs
     */

    private static void exitDoorSet(final int nExitDoors, final boolean exitStairs) {
        final LevelSettings variable = new LevelSettings();
        final int yMin = variable.getnBlockWidth() / 2;
        final int xMax = variable.getnBlockHeight();
        final Random rand = new Random();
        int count = nExitDoors;
        while (count != 0) {
            final int x = rand.nextInt(xMax);
            final int y = rand.nextInt(yMin) + yMin;
            if (canBeADoor(x, y)) {
                if (exitStairs) {
                    matrix[x][y - 1] = variable.getCharExitStairs();
                    count = 0;
                    break;
                    }
                matrix[x][y] = variable.getCharExitDoor();
                count--;
                }
            }
        }

    /**
     * This method checks if one point of the matrix is ok to spawn a door.
     */

    private static boolean canBeADoor(final int x, final int y) {
        final LevelSettings variable = new LevelSettings();
        if (topOrDownBoardBlock(x)) {
            return false;
            }
        if ((matrix[x][y] == variable.getCharWall()) 
                && (matrix[x - 1][y] == variable.getCharWall()) 
                && (matrix[x + 1][y] == variable.getCharWall())) {
            return true;
        }
        return false;
        }

    /**
     * This method checks if one point is out of the matrix (axis of the ordinates).
     */

    private static boolean topOrDownBoardBlock(final int x) {
        final LevelSettings variable = new LevelSettings();
        if (((x - 1) < 0) || ((x + 1) > variable.getnBlockHeight())) {
            return true;
            }
        return false;
        }

    /**
     * This method set randomly n blocks into a spawn point for enemies.
     */

    private static void enemiesSpawnPoint() {
        final LevelSettings variable = new LevelSettings();
        final Random rand = new Random();
        int nEnemiesToSpawn = rand.nextInt(variable.getnMaxSpawnedEnemies() - variable.getnMinSpawnedEnemies()) + variable.getnMinSpawnedEnemies();
        while (nEnemiesToSpawn > 0) {
            final int x = rand.nextInt(variable.getnBlockHeight());
            final int y = rand.nextInt(variable.getnBlockWidth());
            if (canSpawnEnemies(x, y)) {
            	int gOS = rand.nextInt(2);
            	if(gOS == 0) {
            		matrix[x][y] = variable.getCharSpawnEnemiesS();
            	}
            	else {
            		matrix[x][y] = variable.getCharSpawnEnemiesG();
            	}
                nEnemiesToSpawn--;
                }
            }
        }

    /**
     * check if i can spawn an enemy in x, y.
     *
     * @param x
     *         x coordinate
     * @param y
     *        y coordinate
     */

    private static boolean canSpawnEnemies(final int x, final int y) {
        final LevelSettings variable = new LevelSettings();
        if ((matrix[x][y] == variable.getCharEmptySpace()) && checkBlockAround(x, y)) {
            return true;
            }
        return false;
        }

    /**
     * This method checks if any of the 8 blocks around the one selected is a spawn point or an entry door.
     * @param x
     *        x coordinate
     * @param y
     *        y coordinate
     */

    private static boolean checkBlockAround(final int x, final int y) {
        final LevelSettings variable = new LevelSettings();
        for (int i = (x - 1); i <= (x + 1); i++) {
            for (int o = (y - 1); o <= (y + 1); o++) {
                if (matrix[i][o] == variable.getCharSpawnPlayer() || matrix[i][o] == variable.getCharEntryDoor()
                        || matrix[i][o] == variable.getCharSpawnEnemiesG() || matrix[i][o] == variable.getCharEntryStairs()
                        || matrix[i][o] == variable.getCharSpawnEnemiesS() || matrix[i][o] == variable.getCharExitDoor()) {
                    return false;
                    }
                }
            }
        return true;
        }

    /**
     * This method set randomly n blocks into a spawn point for items.
     */

    private static void itemsSpawnPoint() {
    	final LevelSettings variable = new LevelSettings();
        final Random rand = new Random();
        int nItemsToSpawn = rand.nextInt(variable.getnMaxSpawnedItems() - variable.getnMinSpawnedItems()) + variable.getnMinSpawnedItems();
        while (nItemsToSpawn > 0) {
            final int x = rand.nextInt(variable.getnBlockHeight());
            final int y = rand.nextInt(variable.getnBlockWidth());
            if (matrix[x][y] == variable.getCharEmptySpace()) {
            	int rOB = rand.nextInt(2);
            	if(rOB == 0) {
            		matrix[x][y] = variable.getCharSpawnItemB();
            	}
            	else {
            		matrix[x][y] = variable.getCharSpawnItemR();
            	}
                nItemsToSpawn--;
                }
            }
    }
    
    /**
     * This method sets the remaining free blocks into floor.
     */
    private static void placeFloor() {
        final LevelSettings variable = new LevelSettings();
        for (int u = 0; u < variable.getnBlockWidth(); u++) {
            for (int i = 0; i < variable.getnBlockHeight(); i++) {
                if (matrix[i][u] == variable.getCharEmptySpace()) {
                    matrix[i][u] = variable.getCharFloor();
                    }
                }
            }
        }
    }
